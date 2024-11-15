package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.OperatorToken;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;

public class RelationalOperatorStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        switch (lexer.getCurrentChar()) {
            case '=':
                if (lexer.readch('=')) return OperatorToken.EQUAL;
                else return new Token(TokenType.ERROR);
            case '>':
                if (lexer.readch('=')) return OperatorToken.GREATER_EQUAL;
                else return OperatorToken.GREATER;
            case '<':
                if (lexer.readch('=')) return OperatorToken.LESS_EQUAL;
                else return OperatorToken.LESS;
            case '!':
                if (lexer.readch('=')) return OperatorToken.NOT_EQUAL;
                else return new Token(TokenType.ERROR);
            default:
                return null;
        }
    }
}
