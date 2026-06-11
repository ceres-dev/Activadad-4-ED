package dev.cerez;

public record Expediente(
        long id,
        String numero,
        String titulo,
        Estado estado
) {

    public enum Estado {
        ABIERTO,
        EN_PROCESO,
        CERRADO
    }

}