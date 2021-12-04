package grupo7.egg.nutrividas.enums;

public enum Provincia {

    TIERRADELFUEGO("Tierra del Fuego"),
    BUENOSAIRES("Buenos Aires"),
    CABA("CABA"),
    CATAMARCA("Catamarca"),
    CHACO("Chaco"),
    CHUBUT("Chubut"),
    CORDOBA("Cordoba"),
    CORRIENTES("Corrientes"),
    ENTRERIOS("Entre Rios"),
    FORMOSA("Formosa"),
    JUJUY("Jujuy"),
    LAPAMPA("La Pampa"),
    LARIOJA("La Rioja"),
    MENDOZA("Mendoza"),
    MISIONES("Misiones"),
    NEUQUEN("Neuquen"),
    RIONEGRO("Rio Negro"),
    SALTA("Salta"),
    SANJUAN("San Juan"),
    SANLUIS("San Luis"),
    SANTAFE("Santa Fe"),
    SANTACRUZ("Santa Cruz"),
    TUCUMAN("Tucuman"),
    SANTIAGODELESTERO("Santiago del Estero");

    private String valor;

    Provincia(String valor) {
        this.valor = valor;
    }
}
