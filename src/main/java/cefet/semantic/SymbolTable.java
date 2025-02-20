package cefet.semantic;

import cefet.lexical.token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final Map<String, TokenType> symbols = new HashMap<> ();

    public void addSymbol(String name, TokenType type, int line) {
        if (symbols.containsKey(name)) {
            throw new SemanticException( "Variable '" + name + "' already declared - line " + line);
        }
        symbols.put(name, type);
    }

    public TokenType getType(String name, int line) {
        if (!symbols.containsKey(name)) {
            throw new SemanticException( "Variable '" + name + "' not declared - line" + line);
        }
        return symbols.get(name);
    }
}
