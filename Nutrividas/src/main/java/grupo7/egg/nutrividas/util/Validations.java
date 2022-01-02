package grupo7.egg.nutrividas.util;

import grupo7.egg.nutrividas.enums.Categoria;
import grupo7.egg.nutrividas.enums.CategoriaIMC;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.exeptions.InvalidDataException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validations {

    public  static void validNotEmptyfield(String field, String name){
        if (field.trim().isEmpty()) {
            throw new FieldInvalidException("El campo '"+name+"'no puede ser nulo");
        }
    }

    public static void validString(String field) throws Exception {
        validNotEmptyfield(field,"nombre");

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

    public static String formatText(String field){
        return field.toUpperCase().charAt(0) + field.substring(1, field.length()).toLowerCase();
    }

    public static void validDateBirth(LocalDate dateBirth){
        if(dateBirth.getYear()< LocalDate.now().getYear()-100 || dateBirth.isAfter(LocalDate.now())){
            throw new FieldInvalidException("La fecha de nacimiento ingresada es inválida");
        }
    }

    private static final String DOCUMENT_REGEX  = "\\d{8}";
    private static final String MATRICULA_REGEX  = "\\d{12}";

    public static void validDocument(Long document){
        String parse = String.valueOf(document);
        validNotEmptyfield(parse,"documento");

        if (!parse.matches(DOCUMENT_REGEX)) {
            throw new FieldInvalidException("El documento debe contener 8 dígitos");
        }
    }

    public static void validMatricula(Long document) throws Exception {
        String parse = String.valueOf(document);
        validNotEmptyfield(parse,"matricula");

        if (!parse.matches(DOCUMENT_REGEX)) {
            throw new Exception("La matricula debe contener 12 dígitos");
        }
    }


    public static Categoria getCategoria(String categoria){
        return Arrays.stream(Categoria.values())
                .filter(f -> f.getValor().toLowerCase().equals(categoria.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new FieldInvalidException("La categoría ingresada no existe"));
    }

    public static CategoriaIMC getCategoriaIMC(String categoriaIMC){
        return Arrays.stream(CategoriaIMC.values())
                .filter(f -> f.getValor().toLowerCase().equals(categoriaIMC.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new FieldInvalidException("La categoría ingresada no existe"));
    }
}
