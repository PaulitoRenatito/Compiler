package cefet.lexical;

import cefet.lexical.strategy.IdentifierStrategy;
import cefet.lexical.strategy.NumberStrategy;
import cefet.lexical.strategy.OperatorStrategy;
import cefet.lexical.strategy.TokenStrategy;
import cefet.lexical.token.Token;
import cefet.lexical.token.TokenType;
import cefet.lexical.token.Word;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

@Getter
public class Lexer {

    public static int currentLine = 1;
    private char currentChar = ' ';
    private final FileReader file;

    private HashMap<String, Word> words = new HashMap<>();

    private final List<TokenStrategy> strategies = List.of(
            new OperatorStrategy(),
            new NumberStrategy(),
            new IdentifierStrategy()
    );

    @SneakyThrows
    public Lexer(String filename) {
        file = new FileReader(filename);

        reserve(new Word(TokenType.START, "start"));
        reserve(new Word(TokenType.END, "end"));
        reserve(new Word(TokenType.INT, "int"));
        reserve(new Word(TokenType.FLOAT, "float"));
        reserve(new Word(TokenType.STRING, "string"));
        reserve(new Word(TokenType.IF, "if"));
        reserve(new Word(TokenType.THEN, "then"));
        reserve(new Word(TokenType.ELSE, "else"));
        reserve(new Word(TokenType.DO, "do"));
        reserve(new Word(TokenType.WHILE, "while"));
        reserve(new Word(TokenType.SCAN, "scan"));
        reserve(new Word(TokenType.PRINT, "print"));
    }

    private void reserve(Word w) {
        words.put(w.getLexeme(), w);
    }

    @SneakyThrows
    public void readch() {
        currentChar = (char) file.read();
    }

    public boolean readch(char c) {
        readch();
        if (currentChar != c) {
            return false;
        }
        currentChar = ' ';
        return true;
    }

    public Token scan() {
        // Ignore white spaces
        for (;; readch()) {
            if (currentChar == ' ' || currentChar == '\t') {
                continue;
            } else if (currentChar == '\n') {
                incrementLine();
            } else {
                break;
            }
        }

        for (TokenStrategy strategy : strategies) {
            Token t = strategy.identifyToken(this);
            if (t != null) return t;
        }

        Token t = new Token(TokenType.ERROR);
        currentChar = ' ';
        return t;
    }

    private static void incrementLine() {
        currentLine++;
    }

}
