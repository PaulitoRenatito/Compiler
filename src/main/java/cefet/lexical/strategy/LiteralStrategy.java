package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.ErrorToken;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;
import cefet.lexical.token.Word;

import static cefet.lexical.Lexer.EOF_UNICODE;

public class LiteralStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        if (lexer.getCurrentChar() == '{') {
            StringBuilder sb = new StringBuilder();

            while (true) {
                lexer.readch();
                if (lexer.getCurrentChar() == '}') {
                    break;
                }
                sb.append(lexer.getCurrentChar());
                if (lexer.getCurrentChar() == EOF_UNICODE)
                    return new ErrorToken(TokenType.ERROR, "Unexpected EOF", Lexer.currentLine);
            }

            lexer.readch();

            return new Word(TokenType.LITERAL, sb.toString());
        }

        return null;
    }
}
