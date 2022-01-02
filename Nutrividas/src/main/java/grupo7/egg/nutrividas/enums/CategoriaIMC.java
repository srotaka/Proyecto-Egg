package grupo7.egg.nutrividas.enums;

public enum CategoriaIMC {
    BAJO("Bajo","#F3C892"),
    NORMAL("Normal","#A3DA8D"),
    SOBREPESO("Sobrepeso","#FFF1BD"),
    OBESIDAD("Obesidad","#FFF1BD");

    private String valor;
    private String color;
    private Double porcentaje;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    CategoriaIMC(String valor, String color) {
        this.valor = valor;
        this.color = color;
    }
}
