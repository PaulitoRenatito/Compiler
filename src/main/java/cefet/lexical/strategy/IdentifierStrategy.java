package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;
import cefet.lexical.token.Word;

public class IdentifierStrategy implements TokenStrategy {
    @Override
    public Token identifyToken(Lexer lexer) {
        if (Character.isLetter(lexer.getCurrentChar()) || lexer.getCurrentChar() == '_') {
            StringBuilder sb = new StringBuilder();
            boolean firstChar = true;
            do {
                char currentChar = lexer.getCurrentChar();
                if (firstChar) {
                    if (!Character.isLetter(currentChar) && currentChar != '_') {
                        return null;
                    }
                    firstChar = false;
                } else {
                    if (!Character.isLetterOrDigit(currentChar))
                        return null;
                }
                sb.append(currentChar);
                lexer.readch();
            } while (Character.isLetterOrDigit(lexer.getCurrentChar()) || lexer.getCurrentChar() == '_');

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
