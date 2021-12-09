package grupo7.egg.nutrividas.enums;

public enum Sexo {
    FEMENINO("Femenino"),
    MASCULINO("Masculino");

    private String valor;

    Sexo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
