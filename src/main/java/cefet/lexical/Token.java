package cefet.lexical;

public class Token {

    public final TokenType type;

    public Token(TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
