package grupo7.egg.nutrividas.enums;

public enum Categoria {

    ARROZ("Arroz"),
    ADHEREZOS("Aderezos"),
    BEBIDAS("Bebidas"),
    CARNICERIA("Carnicería"),
    CONDIMENTOS("Condimentos"),
    CONGELADOS("Congelados"),
    CONSERVAS("Conservas"),
    ENLATADOS("Enlatados"),
    ENDULZANTES("Endulzantes"),
    FRUTAS("Frutas"),
    HARINAS("Harinas"),
    LACTEOS("Lacteos"),
    LEGUMBRES("Legumbres"),
    PASTAS("Pastas"),
    VERDURAS("Verduras");

    private String valor;

    Categoria(String valor) {
        this.valor = valor;
    }
}
