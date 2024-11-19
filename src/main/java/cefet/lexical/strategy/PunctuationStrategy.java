package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;

public class PunctuationStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        switch (lexer.getCurrentChar()) {
            case '(':
                lexer.readch();
                return new Token(TokenType.OPEN_BRACKET);
            case ')':
                lexer.readch();
                return new Token(TokenType.CLOSE_BRACKET);
            case ';':
                lexer.readch();
                return new Token(TokenType.SEMICOLON);
            case ',':
                lexer.readch();
                return new Token(TokenType.COMMA);
            case '.':
                lexer.readch();
                return new Token(TokenType.DOT);
            default:
                return null;
        }
    }
}
