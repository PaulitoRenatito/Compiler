package cefet;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;
import cefet.lexical.utils.TokenPrinter;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer(args[0]);
        Token token;

        do {
            token = lexer.scan();
            TokenPrinter.printToken(token);
        } while (token.getType() != TokenType.END_OF_FILE);
    }
}
