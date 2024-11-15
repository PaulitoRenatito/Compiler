package cefet.lexical.token;


public enum TokenType {

    // reserved words
    START("start"),
    END("end"),
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
    OR("||"),
    AND("&&"),

    // punctuation
    OPEN_BRACKET("("),
    CLOSE_BRACKET(")"),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),

    SEMICOLON(";"),
    COMMA(","),
    DOT("."),

    // others
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
