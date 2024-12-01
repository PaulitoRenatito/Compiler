package cefet.lexical.token;


public enum TokenType {

    // reserved words
    START("start"),
    END("end"),
    EXIT("exit"),
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    IF("if"),
    THEN("then"),
    ELSE("else"),
    DO("do"),
    WHILE("while"),
    SCAN("scan"),
    PRINT("print"),

    // arithmetic operators
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    MOD("%"),

    // relational operators
    EQUAL("=="),
    GREATER(">"),
    GREATER_EQUAL(">="),
    LESS("<"),
    LESS_EQUAL("<="),
    NOT_EQUAL("!="),

    // logical operators
    NOT("!"),
    OR("||"),
    AND("&&"),

    // punctuation
    OPEN_BRACKET("("),
    CLOSE_BRACKET(")"),

    SEMICOLON(";"),
    COMMA(","),
    DOT("."),

    // others
    ASSIGN("="),
    END_OF_FILE("EOF"),
    UNEXPECTED_EOF("Unexpected EOF"),
    IDENTIFIER("identifier"),
    INTEGER_CONSTANT("integer"),
    FLOAT_CONSTANT("float"),
    LITERAL("literal"),
    ERROR("error");

    private final String string;

    TokenType(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
