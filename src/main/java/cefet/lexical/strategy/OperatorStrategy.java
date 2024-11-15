package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;
import cefet.lexical.token.Word;

public class OperatorStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        switch (lexer.getCurrentChar()) {
            case '=':
                if (lexer.readch('=')) return Word.equal;
                else return new Token(TokenType.ERROR);
            case '>':
                if (lexer.readch('=')) return Word.greaterEqual;
                else return Word.greater;
            case '<':
                if (lexer.readch('=')) return Word.lessEqual;
                else return Word.less;
            case '!':
                if (lexer.readch('=')) return Word.notEqual;
                else return new Token(TokenType.ERROR);
            default:
                return null;
        }
    }
}
