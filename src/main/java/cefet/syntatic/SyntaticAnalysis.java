package cefet.syntatic;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;

public class SyntaticAnalysis {
    
    private final Lexer lex;
    private Token current;


    public SyntaticAnalysis(Lexer lex) {
        this.lex = lex;
        this.current = lex.scan();
    }
    
    private void advance() {
        System.out.println("Found (\"" + current.toString() + "\", " +
            current.getType().toString() + ")");
        current = lex.scan();
    }

    private void eat(TokenType type) {
        if (type == current.getType()) {
            advance();
        } else {
            String reason = "Expected (..., " + type.toString() + "), found (\"" + 
                current.toString() + "\", " + current.getType().toString() + ")";
            reportError(reason);
        }
    }

    private void reportError(String message) {
        throw new SyntaticException(Lexer.currentLine, String.format("Erro sintático: token não esperado \n %s", message));
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



//    program ::= START [decl-list] stmt-list EXIT
    public void procProgram(){
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



//  decl-list ::= decl {decl}
    private void procDeclList(){
        procDecl();

        while (check(TokenType.INT, TokenType.FLOAT, TokenType.STRING)) {
            procDecl();
        }
    }

//    decl ::= type ident-list ';'
    private void procDecl(){
        procType();
        procIdentList();
        eat(TokenType.SEMICOLON);
    }

//    ident-list ::= identifier {',' identifier}
    private void procIdentList(){
        eat(TokenType.IDENTIFIER);

        while (current.getType() == TokenType.COMMA) {
            advance();
            eat(TokenType.IDENTIFIER);
        }
    }

//    type ::= INT | FLOAT | STRING
    private void procType(){
        if (check(TokenType.INT, TokenType.FLOAT, TokenType.STRING)) {
            advance();
        } else {
            reportError();
        }
    }

//    stmt-list ::= stmt {stmt}
    private void procStmtList(){
        procStmt();

        while (check(TokenType.IF, TokenType.DO, TokenType.SCAN, TokenType.PRINT, TokenType.IDENTIFIER)) {
            procStmt();
        }
    }

//    stmt ::= assing-stmt ';' | if-stmt | while-stmt | read-stmt ';' | write-stmt ';'
    private void procStmt(){
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

//    assign-stmt ::= identifier '=' simple_expr
    private void procAssignStmt(){
        eat(TokenType.IDENTIFIER);
        eat(TokenType.ASSIGN);
        procSimpleExpr();
    }

//    if-stmt ::= IF condition THEN stmt-list END
//                | IF condition THEN stmt-list else stmt-list END
    private void procIfStmt(){
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

//    condition ::= expression
    private void procCondition(){
        procExpression();
    }

//    while-stmt ::= DO stmt-list stmt-sufix
    private void procWhileStmt(){
        eat(TokenType.DO);
        procStmtList();
        procStmtSufix();
    }

//    stmt-sufix ::= WHILE condition END
    private void procStmtSufix(){
        eat(TokenType.WHILE);
        procCondition();
        eat(TokenType.END);
    }

//    read-stmt ::= SCAN "(" identifier ")"
    private void procReadStmt(){
        eat(TokenType.SCAN);
        eat(TokenType.OPEN_BRACKET);
        eat(TokenType.IDENTIFIER);
        eat(TokenType.CLOSE_BRACKET);
    }

//    write-stmt ::= PRINT "(" writable ")"
    private void procWriteStmt(){
        eat(TokenType.PRINT);
        eat(TokenType.OPEN_BRACKET);
        procWritable();
        eat(TokenType.CLOSE_BRACKET);
    }

//    writable ::= simple-expr | literal
    private void procWritable(){
        if(check(TokenType.NOT, TokenType.MINUS, TokenType.IDENTIFIER, TokenType.INTEGER_CONSTANT, TokenType.FLOAT_CONSTANT, TokenType.OPEN_BRACKET)) {
            procSimpleExpr();
        } else {
            eat(TokenType.LITERAL);
        }
    }

//    expression ::= simple-expr | simple-expr relop simple-expr
    private void procExpression(){
        procSimpleExpr();

        if (check(TokenType.EQUAL, TokenType.NOT_EQUAL, TokenType.GREATER, TokenType.GREATER_EQUAL, TokenType.LESS, TokenType.LESS_EQUAL)) {
            advance();
            procSimpleExpr();
        }
    }

//    simple-expr ::= term simple-expr-prime
    private void procSimpleExpr(){
        procTerm();
        procSimpleExprPrime();
    }

//    simple-expr-prime ::= addop term simple-expr-prime | λ
    private void procSimpleExprPrime(){
        if (check(TokenType.PLUS, TokenType.MINUS, TokenType.OR)) {
            advance();
            procTerm();
            procSimpleExprPrime();
        }
    }

//    term ::= factor-a term-prime
    private void procTerm(){
        procFactorA();
        procTermPrime();
    }

//    term-prime ::= mulop factor-a term-prime | λ
    private void procTermPrime(){
        if (check(TokenType.MULTIPLY, TokenType.DIVIDE, TokenType.MOD, TokenType.AND)) {
            advance();
            procFactorA();
            procTermPrime();
        }
    }

//    factor-a ::= factor | "!" factor | "-" factor
    private void procFactorA(){
        if (check(TokenType.NOT, TokenType.MINUS)) {
            advance();
        }

        procFactor();
    }

//   factor ::= identifier | constant | "(" expression ")"
    private void procFactor(){
        if (check(TokenType.IDENTIFIER, TokenType.INTEGER_CONSTANT, TokenType.FLOAT_CONSTANT, TokenType.LITERAL)) {
            advance();
        } else if (check(TokenType.OPEN_BRACKET)) {
            advance();
            procExpression();
            eat(TokenType.CLOSE_BRACKET);
        } else {
            reportError();
        }
    } 

}
