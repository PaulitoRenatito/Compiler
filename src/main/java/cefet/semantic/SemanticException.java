package cefet.semantic;

public class SemanticException extends RuntimeException {

    public SemanticException ( int line, String reason ) {
        super (String.format("Erro na linha %02d -> %s", line, reason));
    }

}
