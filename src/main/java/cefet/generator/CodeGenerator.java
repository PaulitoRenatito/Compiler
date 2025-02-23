package cefet.generator;

import cefet.lexical.token.*;
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
            varIndices.put(var, currentLocalIndex);
            currentLocalIndex++;
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

        code.append("    ").append(storeOp).append(" ").append(index).append("\n\n");
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

    public void generateScan(Word word) {
        TokenType type = symbolTable.getType(word.getLexeme());
        Integer i = varIndices.get(word.getLexeme());

        code.append("    new java/util/Scanner\n");
        code.append("    dup\n");
        code.append("    getstatic java/lang/System/in Ljava/io/InputStream;\n");
        code.append("    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V\n");
        code.append("    invokevirtual java/util/Scanner/nextFloat()F\n");
        code.append("    ").append(getStoreInstruction(type)).append(" ").append(i).append("\n\n");
    }

    public void generatePrint(Word word) {
        Integer i = varIndices.get(word.getLexeme());

        if (i == null) {
            code.append("    getstatic java/lang/System/out Ljava/io/PrintStream;\n");
            code.append("    ").append("ldc ").append("\"").append(word.getLexeme()).append("\"").append("\n");
            code.append("    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V\n\n");
            return;
        }

        code.append("    getstatic java/lang/System/out Ljava/io/PrintStream;\n");
        code.append("    ").append("fload ").append(i).append("\n");
        code.append("    invokevirtual java/io/PrintStream/print(F)V\n\n");
    }

    private String getStoreInstruction(TokenType type) {
        return switch (type) {
            case INT -> "istore";
            case FLOAT -> "fstore";
            case STRING -> "astore";
            default -> throw new IllegalArgumentException("Tipo inválido: " + type);
        };
    }

    public void generatePushConstant(Token current) {
        if (current instanceof IntegerNumberToken intNumber) {
            code.append("    ldc ").append((intNumber.value)).append("\n");
        } else if (current instanceof FloatNumberToken floatNumber) {
            code.append("    ldc ").append((floatNumber.value)).append("\n");
        }
    }
}