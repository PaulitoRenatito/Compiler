package cefet.syntatic;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;
import cefet.lexical.token.Word;
import cefet.semantic.SemanticException;
import cefet.semantic.SymbolTable;

public class SyntaticAnalysis {
    
    private final Lexer lex;
    private Token current;

    private final SymbolTable symbolTable;

    public SyntaticAnalysis(Lexer lex) {
        this.lex = lex;
        this.current = lex.scan();
        this.symbolTable = new SymbolTable();
    }
    
    private void advance() {
        //System.out.println("Found (\"" + current.toString() + "\", " +
        //    current.getType().toString() + ")");
        current = lex.scan();
    }

    private void eat(TokenType type) {
        if (type == current.getType()) {
            advance();
        } else {
            String reason = "Expected (..., " + type.toString() + "), found (\"" +
                    current + "\", " + current.getType().toString() + ")";
            reportError(reason);
        }
    }

    private void reportError(String message) {
        throw new SyntaticException(Lexer.currentLine, String.format("Erro sintático: token não esperado %n %s", message));
    }

    private void reportError() {
        throw new SyntaticException(Lexer.currentLine, "Erro sintático: token invalido");
    }

    private boolean check(TokenType ...types) {
        for (TokenType type : types) {
            if (current.getType() == type)
                return true;
        }

        return false;
    }

    // program ::= START [decl-list] stmt-list EXIT
    public void procProgram() {
        eat(TokenType.START);

        if (check(TokenType.INT, TokenType.FLOAT, TokenType.STRING)) {
            procDeclList();
        } else {
            reportError("Expected (..., INT, FLOAT, STRING), found (\"" + 
                current.toString() + "\", " + current.getType().toString() + ")");
        }

        procStmtList();

        eat(TokenType.EXIT);
    }

    // decl-list ::= decl {decl}
    private void procDeclList() {
        procDecl();

        while (check(TokenType.INT, TokenType.FLOAT, TokenType.STRING)) {
            procDecl();
        }
    }

    // decl ::= type ident-list ';'
    private void procDecl() {
        TokenType type = procType();
        procIdentList(type);
        eat(TokenType.SEMICOLON);
    }

    // ident-list ::= identifier {',' identifier}
    private void procIdentList(TokenType type) {
        Word word = (Word) current;
        String id = word.getLexeme();
        eat(TokenType.IDENTIFIER);
        symbolTable.addSymbol(id, type);

        while (current.getType() == TokenType.COMMA) {
            advance();
            Word nextIdent = (Word) current;
            String nextId = nextIdent.getLexeme();
            eat(TokenType.IDENTIFIER);
            symbolTable.addSymbol(nextId, type);
        }
    }

    // type ::= INT | FLOAT | STRING
    private TokenType procType() {
        TokenType type = current.getType ();
        if (check(TokenType.INT, TokenType.FLOAT, TokenType.STRING)) {
            advance();
            return type;
        } else {
            reportError();
            return TokenType.ERROR;
        }
    }

    // stmt-list ::= stmt {stmt}
    private void procStmtList() {
        procStmt();

        while (check(TokenType.IF, TokenType.DO, TokenType.SCAN, TokenType.PRINT, TokenType.IDENTIFIER)) {
            procStmt();
        }
    }

    // stmt ::= assing-stmt ';' | if-stmt | while-stmt | read-stmt ';' | write-stmt ';'
    private void procStmt() {
        switch (current.getType()) {
            case IDENTIFIER: 
                procAssignStmt(); 
                eat(TokenType.SEMICOLON);
                break;

            case IF: 
                procIfStmt();
                break;

            case DO:
                procWhileStmt();
                break;

            case SCAN:
                procReadStmt(); 
                eat(TokenType.SEMICOLON);
                break;

            case PRINT:
                procWriteStmt();
                eat(TokenType.SEMICOLON);
                break;

            default: 
                reportError();
        }
    }

    // assign-stmt ::= identifier '=' simple_expr
    private void procAssignStmt() {
        Word word = (Word) current;
        String id = word.getLexeme();
        eat(TokenType.IDENTIFIER);
        TokenType varType = symbolTable.getType(id);

        eat(TokenType.ASSIGN);
        TokenType exprType = procSimpleExpr();

        if (!isCompatible(varType, exprType)) {
            throw new SemanticException(Lexer.currentLine, "Type mismatch: Cannot assign " + exprType + " to " + varType);
        }
    }

    // if-stmt ::= IF condition THEN stmt-list END
    //             | IF condition THEN stmt-list else stmt-list END
    private void procIfStmt() {
        eat(TokenType.IF);
        procCondition();
        eat(TokenType.THEN);
        procStmtList();

        if (check( TokenType.ELSE)) {
            advance();
            procStmtList();
        }

        eat(TokenType.END);
    }

    // condition ::= expression
    private void procCondition() {
        TokenType type = procExpression();
        if (type != TokenType.INT) {
            throw new SemanticException(Lexer.currentLine, "Condition must be boolean-compatible (int type)");
        }
    }

    // while-stmt ::= DO stmt-list stmt-sufix
    private void procWhileStmt() {
        eat(TokenType.DO);
        procStmtList();
        procStmtSufix();
    }

    // stmt-sufix ::= WHILE condition END
    private void procStmtSufix() {
        eat(TokenType.WHILE);
        procCondition();
        eat(TokenType.END);
    }

    // read-stmt ::= SCAN "(" identifier ")"
    private void procReadStmt() {
        eat(TokenType.SCAN);
        eat(TokenType.OPEN_BRACKET);
        eat(TokenType.IDENTIFIER);
        eat(TokenType.CLOSE_BRACKET);
    }

    // write-stmt ::= PRINT "(" writable ")"
    private void procWriteStmt() {
        eat(TokenType.PRINT);
        eat(TokenType.OPEN_BRACKET);
        procWritable();
        eat(TokenType.CLOSE_BRACKET);
    }

    // writable ::= simple-expr | literal
    private void procWritable() {
        if(check(TokenType.NOT, TokenType.MINUS, TokenType.IDENTIFIER, TokenType.INTEGER_CONSTANT, TokenType.FLOAT_CONSTANT, TokenType.OPEN_BRACKET)) {
            procSimpleExpr();
        } else {
            eat(TokenType.LITERAL);
        }
    }

    // expression ::= simple-expr | simple-expr relop simple-expr
    private TokenType procExpression() {
        TokenType leftType = procSimpleExpr();

        if (check(TokenType.EQUAL, TokenType.NOT_EQUAL,
                TokenType.GREATER, TokenType.GREATER_EQUAL,
                TokenType.LESS, TokenType.LESS_EQUAL)) {
            TokenType op = current.getType();
            advance();
            TokenType rightType = procSimpleExpr();

            // Relational operations always return INT (boolean)
            checkRelationalOp(op, leftType, rightType);
            return TokenType.INT;
        }

        return leftType;
    }

    private void checkRelationalOp(TokenType op, TokenType left, TokenType right) {
        // Allow comparisons between numeric types
        if ((left == TokenType.INT || left == TokenType.FLOAT) &&
                (right == TokenType.INT || right == TokenType.FLOAT)) {
            return;
        }

        // Allow string comparisons
        if (left == TokenType.STRING && right == TokenType.STRING) {
            return;
        }

        throw new SemanticException(Lexer.currentLine, "Cannot compare " + left + " and " + right + " with " + op);
    }

    // simple-expr ::= term simple-expr-prime
    private TokenType procSimpleExpr() {
        TokenType termType = procTerm();
        return procSimpleExprPrime(termType);
    }

    // simple-expr-prime ::= addop term simple-expr-prime | λ
    private void procSimpleExprPrime() {
        if (check(TokenType.PLUS, TokenType.MINUS, TokenType.OR)) {
            advance();
            procTerm();
            procSimpleExprPrime();
        }
    }

    // term ::= factor-a term-prime
    private TokenType procTerm() {
        TokenType factorType = procFactorA();
        return procTermPrime(factorType);
    }

    // term-prime ::= mulop factor-a term-prime | λ
    private TokenType procTermPrime(TokenType leftType) {
        if (check(TokenType.MULTIPLY, TokenType.DIVIDE, TokenType.MOD, TokenType.AND)) {
            TokenType op = current.getType();
            advance();
            TokenType rightType = procFactorA();
            TokenType resultType = checkBinaryOp(op, leftType, rightType);
            return procTermPrime(resultType);
        }

        return leftType;
    }

    // factor-a ::= factor | "!" factor | "-" factor
    private TokenType procFactorA() {
        if (check(TokenType.NOT, TokenType.MINUS)) {
            advance();
            // Handle unary operators
            TokenType type = procFactor();
            if (current.getType() == TokenType.NOT && type != TokenType.INT) {
                throw new SemanticException(Lexer.currentLine, "Cannot apply NOT operator to non-integer type");
            }
            if (current.getType() == TokenType.MINUS && !(type == TokenType.INT || type == TokenType.FLOAT)) {
                throw new SemanticException(Lexer.currentLine, "Cannot apply negation to non-numeric type");
            }
            return type;
        }
        return procFactor();
    }

    // factor ::= identifier | constant | "(" expression ")"
    private TokenType procFactor() {
        if (check(TokenType.IDENTIFIER)) {
            Word word = (Word) current;
            String id = word.getLexeme();
            advance();
            return symbolTable.getType(id);
        }
        else if (check(TokenType.INTEGER_CONSTANT)) {
            advance();
            return TokenType.INT;
        }
        else if (check(TokenType.FLOAT_CONSTANT)) {
            advance();
            return TokenType.FLOAT;
        }
        else if (check(TokenType.LITERAL)) {
            advance();
            return TokenType.STRING;
        }
        else if (check(TokenType.OPEN_BRACKET)) {
            advance();
            TokenType type = procExpression();
            eat(TokenType.CLOSE_BRACKET);
            return type;
        }
        else {
            reportError();
            return TokenType.ERROR;
        }
    }


    private TokenType procSimpleExprPrime(TokenType leftType) {
        if (check(TokenType.PLUS, TokenType.MINUS, TokenType.OR)) {
            TokenType op = current.getType();
            advance();
            TokenType rightType = procTerm();
            TokenType resultType = checkBinaryOp(op, leftType, rightType);
            return procSimpleExprPrime(resultType);
        }

        return leftType;
    }

    private TokenType checkBinaryOp(TokenType op, TokenType left, TokenType right) {
        switch (op) {
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVIDE:
                if ((left == TokenType.INT || left == TokenType.FLOAT) &&
                        (right == TokenType.INT || right == TokenType.FLOAT)) {
                    return (left == TokenType.FLOAT || right == TokenType.FLOAT) ?
                            TokenType.FLOAT : TokenType.INT;
                }
                break;
            case OR:
            case AND:
            case MOD:
                if (left == TokenType.INT && right == TokenType.INT) {
                    return TokenType.INT;
                }
                break;
        }
        throw new SemanticException(Lexer.currentLine, "Operation " + op + " incompatible with types " + left + " and " + right);
    }

    private boolean isCompatible(TokenType target, TokenType source) {
        if (target == source) return true;
        // Allow implicit int to float conversion
        return (target == TokenType.FLOAT && source == TokenType.INT);
    }

}
