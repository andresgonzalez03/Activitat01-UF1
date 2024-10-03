public class UtilString {
    public static String normalitzaString(String cadena) {
        if (cadena == null || cadena.strip().isEmpty()) {
            return "cadena no vàlid, sisplau ingresa un altre cadena.";
        }
        return cadena.replaceAll("\\s+", " ").strip();
    }
}
