
import java.util.ArrayList;

public class Client {
    private String nom;
    private String telefon;
    private ArrayList<Encarrec> encarrecs;
    
    
    public Client(String nom, String telefon, ArrayList<Encarrec> encarrecs) {
        this.nom = UtilString.normalitzaString(nom);
        this.telefon = UtilString.normalitzaString(telefon);
        setEncarrecs(encarrecs);
    }
    public String getNom() {return nom;}
    public String getTelefon() {return telefon;}
    public ArrayList<Encarrec> getEncarrecs() {return encarrecs;} 
    public void setEncarrecs(ArrayList<Encarrec> encarrecs) {
        if(encarrecs == null) {
            this.encarrecs = new ArrayList<>();
        } else {
            this.encarrecs = encarrecs;
        }
    }
}
