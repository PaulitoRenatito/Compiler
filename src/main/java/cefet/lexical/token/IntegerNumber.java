package cefet.lexical.token;

public class IntegerNumber extends Token {

    public final int value;

    public IntegerNumber(int value) {
        super(TokenType.INTEGER_CONSTANT);
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + value + ")";
    }
}
