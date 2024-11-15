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

    // relop
    EQUAL("=="),
    GREATER(">"),
    GREATER_EQUAL(">="),
    LESS("<"),
    LESS_EQUAL("<="),
    NOT_EQUAL("!="),

    // addop
    PLUS("+"),
    MINUS("-"),
    OR("||"),

    // mulop
    TIMES("*"),
    DIVIDE("/"),
    MOD("%"),
    AND("&&"),

    // brackets
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
