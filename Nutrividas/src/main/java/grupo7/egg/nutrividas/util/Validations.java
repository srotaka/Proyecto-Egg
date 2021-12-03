package grupo7.egg.nutrividas.util;

import grupo7.egg.nutrividas.enums.Categoria;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validations {

    public  static void validNotEmptyfield(String field) throws Exception {
        if (field.trim().isEmpty()) {
            throw new Exception("The field can't be empty");
        }
    }

    public static void validString(String field) throws Exception {
        validNotEmptyfield(field);

        if (field.matches("^-?[0-9]+$")) {
            throw new Exception("Only characters are allowed");
        }
    }

    public static String formatNames(String field){
        StringBuffer strbf = new StringBuffer();
        Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(field);
        while(match.find()){
            match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
        }
        return match.appendTail(strbf).toString();
    }

    private static final String DOCUMENT_REGEX  = "\\d{8}";
    private static final String MATRICULA_REGEX  = "\\d{12}";

    public static void validDocument(Long document) throws Exception {
        String parse = String.valueOf(document);
        validNotEmptyfield(parse);

        if (!parse.matches(DOCUMENT_REGEX)) {
            throw new Exception("El documento debe contener 8 dígitos");
        }
    }

    public static void validMatricula(Long document) throws Exception {
        String parse = String.valueOf(document);
        validNotEmptyfield(parse);

        if (!parse.matches(DOCUMENT_REGEX)) {
            throw new Exception("La matricula debe contener 12 dígitos");
        }
    }


    public static Categoria getCategoria(String categoria) throws Exception {
        return Arrays.stream(Categoria.values())
                .filter(f -> f.getValor().toLowerCase().equals(categoria.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new Exception("La categoría ingresada no existe"));
    }
}
