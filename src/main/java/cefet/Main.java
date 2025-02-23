package cefet;

import cefet.lexical.Lexer;
import cefet.syntatic.SyntaticAnalysis;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer(args[0]);
        SyntaticAnalysis syntaticAnalysis = new SyntaticAnalysis(lexer);

        try {
            syntaticAnalysis.procProgram();
            System.out.println("Programa semanticamente correto.");
            String jasminCode = syntaticAnalysis.getCodeGenerator().generate();
            String fileName = Paths.get(args[0]).getFileName().toString().replace(".txt", ".j");
            String outputPath = "src/main/resources/generator/" + fileName;
            Files.write(Paths.get(outputPath), jasminCode.getBytes());
        } catch (Exception e) {
            Arrays.stream(e.getStackTrace()).forEach(System.err::println);
            System.err.println(e.getMessage());
        }
    }
}
