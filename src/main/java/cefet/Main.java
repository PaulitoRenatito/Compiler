package cefet;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer(args[0]);
        Token token;

        do {
            token = lexer.scan();
            System.out.println(token + " at line " + Lexer.currentLine);
        } while (token.getType() != TokenType.END);
    }
}
