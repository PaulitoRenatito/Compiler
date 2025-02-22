package cefet;

import cefet.lexical.Lexer;
import cefet.syntatic.SyntaticAnalysis;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer(args[0]);
        SyntaticAnalysis syntaticAnalysis = new SyntaticAnalysis(lexer);

        
        try {
            syntaticAnalysis.procProgram();
            System.out.println("Programa semanticamente correto.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
