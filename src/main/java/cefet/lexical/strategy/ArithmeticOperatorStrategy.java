package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.OperatorToken;
import cefet.lexical.token.Token;

public class ArithmeticOperatorStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        switch (lexer.getCurrentChar()) {
            case '+':
                lexer.readch();
                return OperatorToken.PLUS;
            case '-':
                lexer.readch();
                return OperatorToken.MINUS;
            case '*':
                lexer.readch();
                return OperatorToken.MULTIPLY;
            case '/':
                lexer.readch();
                return OperatorToken.DIVIDE;
            case '%':
                lexer.readch();
                return OperatorToken.MOD;
            default:
                return null;
        }
    }
}
