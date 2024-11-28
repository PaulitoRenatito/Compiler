package cefet.lexical.token;

public class ErrorToken extends Token {

    private final String message;
    private final int line;

    public ErrorToken(TokenType type, String message, int line) {
        super(type);
        this.message = message;
        this.line = line;
    }

    @Override
    public String toString() {
        return super.toString() + " { message=" +
                message + ", line=" + line + " }";
    }
}
