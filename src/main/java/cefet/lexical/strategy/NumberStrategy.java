package cefet.lexical.strategy;

import cefet.lexical.token.FloatNumberToken;
import cefet.lexical.token.IntegerNumberToken;
import cefet.lexical.Lexer;
import cefet.lexical.token.Token;

public class NumberStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        if (Character.isDigit(lexer.getCurrentChar())) {
            int value = 0;
            do {
                value = 10 * value + Character.digit(lexer.getCurrentChar(), 10);
                lexer.readch();
            } while (Character.isDigit(lexer.getCurrentChar()));

            if (lexer.getCurrentChar() != '.') {
                return new IntegerNumberToken(value);
            }

            float x = value;
            float d = 10;
            for (;;) {
                lexer.readch();
                if (!Character.isDigit(lexer.getCurrentChar())) break;
                x = x + Character.digit(lexer.getCurrentChar(), 10) / d;
                d = d * 10;
            }
            return new FloatNumberToken(x);
        }
        return null;
    }
}
