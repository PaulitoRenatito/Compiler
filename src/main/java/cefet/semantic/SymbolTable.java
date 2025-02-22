package cefet.semantic;

import cefet.lexical.Lexer;
import cefet.lexical.token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final Map<String, TokenType> symbols = new HashMap<>();

    public void addSymbol(String name, TokenType type) {
        if (symbols.containsKey(name)) {
            throw new SemanticException(Lexer.currentLine, "Variable '" + name + "' already declared");
        }
        symbols.put(name, type);
    }

    public TokenType getType(String name) {
        if (!symbols.containsKey(name)) {
            throw new SemanticException(Lexer.currentLine, "Variable '" + name + "' not declared");
        }
        return symbols.get(name);
    }
}
