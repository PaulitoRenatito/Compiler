package cefet.lexical.token;

public class ReservedWord extends Word {

    public ReservedWord(TokenType type, String lexeme) {
        super(type, lexeme);
    }

    @Override
    public String toString() {
        return getType().toString();
    }
}
