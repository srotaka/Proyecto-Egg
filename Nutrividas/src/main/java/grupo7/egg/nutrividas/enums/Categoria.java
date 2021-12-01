package grupo7.egg.nutrividas.enums;

public enum Categoria {

    ACEITES("Aceites"),
    ARROZ("Arroz"),
    ADHEREZOS("Aderezos"),
    BEBIDAS("Bebidas"),
    CARNICERIA("Carnicería"),
    CEREALES("Cereales"),
    CONDIMENTOS("Condimentos"),
    CONGELADOS("Congelados"),
    CONSERVAS("Conservas"),
    ENDULZANTES("Endulzantes"),
    FIAMBRERIA("Fiambreria"),
    FRUTAS("Frutas"),
    INFUSIONES("Infusiones"),
    HARINAS("Harinas"),
    LACTEOS("Lacteos"),
    LEGUMBRES("Legumbres"),
    PANADERIA("Panadería"),
    PASTAS("Pastas"),
    VERDURAS("Verduras");

    private String valor;

    Categoria(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
