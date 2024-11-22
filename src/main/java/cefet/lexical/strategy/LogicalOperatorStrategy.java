package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.ErrorToken;
import cefet.lexical.token.OperatorToken;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;

public class LogicalOperatorStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        switch (lexer.getCurrentChar()) {
            case '|':
                if (lexer.readch('|')) return OperatorToken.OR;
                return new ErrorToken(TokenType.ERROR, "Unexpected character: '|'", Lexer.currentLine);
            case '&':
                if (lexer.readch('&')) return OperatorToken.AND;
                return new ErrorToken(TokenType.ERROR, "Unexpected character: '&'", Lexer.currentLine);
            default:
                return null;
        }
    }
}
