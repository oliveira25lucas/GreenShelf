package br.com.oliveira25lucas.greenShelf.request.converters;

public class NumberConverter {

    public static Double covertToDouble(String strNumber) {
    if (strNumber == null) return 0d;
    String number = strNumber.replaceAll(",", ".");
    return Double.parseDouble(number);
}

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}

