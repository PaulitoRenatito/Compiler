package cefet.generator;

import cefet.lexical.token.TokenType;
import cefet.semantic.SymbolTable;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
    private final SymbolTable symbolTable;
    private final Map<String, Integer> varIndices = new HashMap<>();
    private int currentLocalIndex = 0;
    @Getter
    private final StringBuilder code = new StringBuilder();
    private int labelCounter = 0;

    public CodeGenerator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void initializeVarIndices() {
        varIndices.clear();
        currentLocalIndex = 0;
        for (String var : symbolTable.getSymbols().keySet()) {
            varIndices.put(var, currentLocalIndex++);
        }
    }

    public String generate() {
        return ".class public Main\n" +
                ".super java/lang/Object\n\n" +
                ".method public static main([Ljava/lang/String;)V\n" +
                "    .limit stack 100\n" +
                "    .limit locals " + (currentLocalIndex + 1) + "\n\n" + // Ajuste dinâmico
                code +
                "    return\n" +
                ".end method";
    }

    // Geração de atribuição (ex: a = 10)
    public void generateAssignment(String varName, TokenType exprType) {
        Integer index = varIndices.get(varName);
        String storeOp = getStoreInstruction(exprType);

        code.append("    ").append(storeOp).append(" ").append(index).append("\n");
    }

    // Geração de operações aritméticas
    public void generateArithmetic(TokenType op, TokenType type) {
        String instruction = switch (op) {
            case PLUS -> type == TokenType.INT ? "iadd" : "fadd";
            case MINUS -> type == TokenType.INT ? "isub" : "fsub";
            case MULTIPLY -> type == TokenType.INT ? "imul" : "fmul";
            case DIVIDE -> type == TokenType.INT ? "idiv" : "fdiv";
            case MOD -> "irem"; // Operador %
            default -> throw new IllegalArgumentException("Operador inválido: " + op);
        };
        code.append("    ").append(instruction).append("\n");
    }

    // Geração de condicional (if-else)
    public String generateIfElse() {
        String elseLabel = "L" + labelCounter++;
        String endLabel = "L" + labelCounter++;
        code.append("    ifeq ").append(elseLabel).append("\n");
        return elseLabel + "," + endLabel;
    }

    // Geração de loop (while)
    public String generateWhile() {
        String startLabel = "L" + labelCounter++;
        String endLabel = "L" + labelCounter++;
        code.append("    ").append(startLabel).append(":\n");
        return startLabel + "," + endLabel;
    }

    private String getStoreInstruction(TokenType type) {
        return switch (type) {
            case INT -> "istore";
            case FLOAT -> "fstore";
            case STRING -> "astore";
            default -> throw new IllegalArgumentException("Tipo inválido: " + type);
        };
    }
}