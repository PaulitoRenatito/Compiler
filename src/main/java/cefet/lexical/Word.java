package cefet.lexical;

public class Word extends Token {

    public final String lexeme;

    public Word(TokenType type, String lexeme) {
        super(type);
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + lexeme + ")";
    }
}
