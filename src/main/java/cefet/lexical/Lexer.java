package cefet.lexical;

import cefet.lexical.strategy.*;
import cefet.lexical.token.*;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

@Getter
public class Lexer {

    public static final char EOF_UNICODE = '\uffff';

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
            new IdentifierStrategy(),
            new LiteralStrategy()
    );

    @SneakyThrows
    public Lexer(String filename) {
        file = new FileReader(filename);

        reserve(new ReservedWord(TokenType.START, "start"));
        reserve(new ReservedWord(TokenType.END, "end"));
        reserve(new ReservedWord(TokenType.INT, "int"));
        reserve(new ReservedWord(TokenType.FLOAT, "float"));
        reserve(new ReservedWord(TokenType.STRING, "string"));
        reserve(new ReservedWord(TokenType.IF, "if"));
        reserve(new ReservedWord(TokenType.THEN, "then"));
        reserve(new ReservedWord(TokenType.ELSE, "else"));
        reserve(new ReservedWord(TokenType.DO, "do"));
        reserve(new ReservedWord(TokenType.WHILE, "while"));
        reserve(new ReservedWord(TokenType.SCAN, "scan"));
        reserve(new ReservedWord(TokenType.PRINT, "print"));
    }

    private void reserve(Word w) {
        words.put(w.getLexeme(), w);
    }

    @SneakyThrows
    public void readch() {
        int ch = file.read();
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

        // Ignore comments
        if (currentChar == '/') {
            readch();
            if (currentChar == '/') {
                while (currentChar != '\n' && currentChar != EOF_UNICODE) readch();
                return scan();
            }
        }

        for (TokenStrategy strategy : strategies) {
            Token t = strategy.identifyToken(this);
            if (t != null) return t;
        }

        if (currentChar == EOF_UNICODE) return new Token(TokenType.END_OF_FILE);

        Token t = new ErrorToken(TokenType.ERROR, "Invalid character: '" + currentChar + "'", currentLine);
        currentChar = ' ';
        return t;
    }

    private static void incrementLine() {
        currentLine++;
    }
}
