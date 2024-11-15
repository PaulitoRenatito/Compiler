package cefet.lexical.token;

import lombok.Getter;

@Getter
public class Token {

    private final TokenType type;

    public Token(TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
