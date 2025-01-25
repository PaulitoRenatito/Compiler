package cefet.syntatic;

public class SyntaticException extends RuntimeException {
    public SyntaticException(int line, String reason) {
        super(String.format("Erro na linha %02d -> %s", line, reason));
    }
}
