package cefet.lexical.utils;

import cefet.lexical.Lexer;
import cefet.lexical.token.ErrorToken;
import cefet.lexical.token.Token;

public class TokenPrinter {

    private TokenPrinter() {
        throw new IllegalStateException("Utility class");
    }

    public static void printToken(Token token) {

        if (token instanceof ErrorToken errorToken) {
            System.out.println(errorToken);
            return;
        }

        System.out.println("Token: " + token + " at line " + Lexer.currentLine);
    }

}
