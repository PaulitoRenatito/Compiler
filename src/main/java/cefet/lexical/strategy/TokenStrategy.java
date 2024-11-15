package cefet.lexical.strategy;

import cefet.lexical.Lexer;
import cefet.lexical.token.Token;

public interface TokenStrategy {
    Token identifyToken(Lexer lexer);
}
