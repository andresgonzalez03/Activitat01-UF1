package src;
public class UtilString {
    // mètode que normalitza un String (usualment un nom) i li treu els espais en blanc, convertint-los només en un espai en blanc
    public static String normalitzaString(String cadena) {
        for(char c : cadena.toCharArray()) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("La cadena ha de contenir només lletres");
            }
        }
        if (cadena == null || cadena.strip().isEmpty()) {
            throw new IllegalArgumentException("La cadena no és vàlida, sisplau ingresa una altra cadena");
        }
        return cadena.replaceAll("\\s+", " ").strip();
    }
}
