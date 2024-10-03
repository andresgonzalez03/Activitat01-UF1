/* Classe que conté les propietats d'un article dintre d'una comanda d'un client. */


public class Article {
    private String nom;
    private String unitat;
    private int quantitat;

    public Article (String nom, String unitat, int quantitat) {
        this.nom = UtilString.normalitzaString(nom);
        this.unitat = UtilString.normalitzaString(unitat);
        setQuantitat(quantitat);
    }
    public String getNom() {return nom;}
    public String getUnitat() {return unitat;}
    public int getQuantitat() {return quantitat;}
    public void setQuantitat(int quantitat) {
        if (quantitat >= 0) {
            this.quantitat = quantitat;
        } else {
            throw new IllegalArgumentException("Has d'indicar almenys una unitat.");
        }
    }
    public void setTipusUnitat(String unitat) {
        if (unitat == null || unitat.strip().isEmpty()) {
            return;
        } else {
            this.unitat = UtilString.normalitzaString(unitat);
        }
    }
}
