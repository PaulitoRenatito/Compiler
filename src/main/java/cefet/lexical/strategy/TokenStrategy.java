package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.Token;

public interface TokenStrategy {
    Token identifyToken(Lexer lexer);
}
