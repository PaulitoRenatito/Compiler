package cefet.lexical.token;

public class OperatorToken extends Token {

    // arithmetic operators
    public static final OperatorToken PLUS = new OperatorToken(TokenType.PLUS);
    public static final OperatorToken MINUS = new OperatorToken(TokenType.MINUS);
    public static final OperatorToken MULTIPLY = new OperatorToken(TokenType.MULTIPLY);
    public static final OperatorToken DIVIDE = new OperatorToken(TokenType.DIVIDE);
    public static final OperatorToken MOD = new OperatorToken(TokenType.MOD);

    // relational operators
    public static final OperatorToken EQUAL = new OperatorToken(TokenType.EQUAL);
    public static final OperatorToken GREATER = new OperatorToken(TokenType.GREATER);
    public static final OperatorToken GREATER_EQUAL = new OperatorToken(TokenType.GREATER_EQUAL);
    public static final OperatorToken LESS = new OperatorToken(TokenType.LESS);
    public static final OperatorToken LESS_EQUAL = new OperatorToken(TokenType.LESS_EQUAL);
    public static final OperatorToken NOT_EQUAL = new OperatorToken(TokenType.NOT_EQUAL);

    // logical operators
    public static final OperatorToken OR = new OperatorToken(TokenType.OR);
    public static final OperatorToken AND = new OperatorToken(TokenType.AND);

    public OperatorToken(TokenType type) {
        super(type);
    }
}
