package cefet.lexical;

import lombok.Getter;

@Getter
public class Word extends Token {

    private final String lexeme;

    public static final Word equal = new Word(TokenType.EQUAL, "==");
    public static final Word greater = new Word(TokenType.GREATER, ">");
    public static final Word greaterEqual = new Word(TokenType.GREATER_EQUAL, ">=");
    public static final Word less = new Word(TokenType.LESS, "<");
    public static final Word lessEqual = new Word(TokenType.LESS_EQUAL, "<=");
    public static final Word notEqual = new Word(TokenType.NOT_EQUAL, "!=");

    public Word(TokenType type, String lexeme) {
        super(type);
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + lexeme + ")";
    }
}
