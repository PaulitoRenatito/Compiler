package cefet.lexical;

import cefet.lexical.strategy.*;
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
            new ArithmeticOperatorStrategy(),
            new RelationalOperatorStrategy(),
            new LogicalOperatorStrategy(),
            new PunctuationStrategy(),
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
        int ch = file.read();
        if (ch == -1) {
            currentChar = '$';
            return;
        }
        currentChar = (char) ch;
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
            if (currentChar == ' ' || currentChar == '\t' || currentChar == '\r' || currentChar == '\b') continue;
            else if (currentChar == '\n') incrementLine();
            else break;
        }

        for (TokenStrategy strategy : strategies) {
            Token t = strategy.identifyToken(this);
            if (t != null) return t;
        }

        if (currentChar == '$') return new Token(TokenType.END);

        Token t = new Token(TokenType.ERROR);
        currentChar = ' ';
        return t;
    }

    private static void incrementLine() {
        currentLine++;
    }
}
