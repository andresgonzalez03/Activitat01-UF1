public enum Unitat {
    KILO("Kg"),
    LITRE("L"),
    UNITAT("Ud"),
    GRAM("Gr");

    private final String nom;

    Unitat(String nom) {
        this.nom = nom;
    }
    @Override
    public String toString() {
        return nom;
    }
    public static Unitat fromString(String nom) {
        if(nom == null || nom.isBlank()) return null;
        nom = UtilString.normalitzaString(nom);
        for(Unitat unitat : Unitat.values()) {
            if(unitat.nom.equalsIgnoreCase(nom) || unitat.toString().toLowerCase())
        }
    }
}
