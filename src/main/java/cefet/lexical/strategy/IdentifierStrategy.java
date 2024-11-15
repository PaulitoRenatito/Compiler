package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;
import cefet.lexical.token.Word;

public class IdentifierStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        if (Character.isLetter(lexer.getCurrentChar())) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(lexer.getCurrentChar());
                lexer.readch();
            } while (Character.isLetterOrDigit(lexer.getCurrentChar()));

            String s = sb.toString();
            Word w = lexer.getWords().get(s);
            if (w != null) return w;

            w = new Word(TokenType.IDENTIFIER, s);
            lexer.getWords().put(s, w);
            return w;
        }
        return null;
    }
}
