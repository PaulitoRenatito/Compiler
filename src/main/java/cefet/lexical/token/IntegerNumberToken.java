package cefet.lexical.token;

public class IntegerNumberToken extends Token {

    public final int value;

    public IntegerNumberToken(int value) {
        super(TokenType.INTEGER_CONSTANT);
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + value + ")";
    }
}
