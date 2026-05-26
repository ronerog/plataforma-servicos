package br.com.ronero.plataformaservicos.shared.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
