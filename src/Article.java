package src;
/* Classe que conté les propietats d'un article dintre d'una comanda d'un client. */

public class Article {
    private String nom;
    private Unitat unitat;
    private double quantitat;

    public Article (String nom, Unitat unitat, double quantitat) {
        this.nom = UtilString.normalitzaString(nom);
        this.unitat = unitat;
        setQuantitat(quantitat);
    }
    public String getNom() {return nom;}
    public Unitat getUnitat() {return unitat;}
    public double getQuantitat() {return quantitat;}
    public void setQuantitat(double quantitat) {
        if (quantitat >= 0) {
            this.quantitat = quantitat;
        } else {
            throw new IllegalArgumentException("Has d'indicar almenys una unitat.");
        }
    }
    @Override
    public String toString() {
        return String.format("%.2f\t%s\t%s", quantitat, unitat, nom);
    }
    public String toCSV() {
        return nom + ";" + quantitat + ";" + unitat + ";";
    }
}
