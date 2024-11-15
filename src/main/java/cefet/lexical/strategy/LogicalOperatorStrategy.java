package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.OperatorToken;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;

public class LogicalOperatorStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        switch (lexer.getCurrentChar()) {
            case '|':
                if (lexer.readch('|')) return OperatorToken.OR;
                return new Token(TokenType.ERROR);
            case '&':
                if (lexer.readch('&')) return OperatorToken.AND;
                return new Token(TokenType.ERROR);
            default:
                return null;
        }
    }
}
