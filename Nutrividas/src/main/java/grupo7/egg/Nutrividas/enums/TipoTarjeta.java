package grupo7.egg.nutrividas.enums;

public enum TipoTarjeta {

    CREDITO("Crédito"),
    DEBITO("Débito");

    private String valor;

    TipoTarjeta(String valor) {
        this.valor = valor;
    }
}
