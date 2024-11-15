package cefet.lexical.token;

import lombok.Getter;

@Getter
public class Word extends Token {

    private final String lexeme;

    public Word(TokenType type, String lexeme) {
        super(type);
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + lexeme + ")";
    }
}
