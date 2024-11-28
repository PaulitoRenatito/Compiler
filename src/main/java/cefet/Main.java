package cefet;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;
import cefet.lexical.utils.TokenPrinter;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        String path = "src/main/resources/examples/test1.txt";

        File file = new File(path);
        if (!file.exists()) {
            System.err.println("File not found: " + path);
            return;
        }

        Lexer lexer = new Lexer(file.getAbsolutePath());
        Token token;

        do {
            token = lexer.scan();
            TokenPrinter.printToken(token);
        } while (token.getType() != TokenType.END_OF_FILE);
    }
}
