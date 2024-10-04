package src;
public class UtilString {
    // mètode que normalitza un String qualsevol i li treu els espais en blanc, convertint-los només en un espai en blanc
    public static String normalitzaString(String cadena) {
        if (cadena == null || cadena.strip().isEmpty()) {
            throw new IllegalArgumentException("cadena no vàlid, sisplau ingresa un altre cadena.");
        }
        return cadena.replaceAll("\\s+", " ").strip();
    }
}
