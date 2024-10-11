package src;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Encarrec {
    private String nomClient;
    private String telefonClient;
    private LocalDate data;
    private ArrayList<Article> articles;

    public Encarrec() {
        this.articles = new ArrayList<>();
    }
    public Encarrec(String nomClient, String telefonClient, LocalDate data, ArrayList<Article> articles) {
        setData(data);
        setArticles(articles);
        setNomClient(nomClient);
        this.telefonClient = telefonClient;
    }
    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Sisplau, indica una data correcta en format dd/mm/aaaa");
        }
        this.data = data;
    }
    public ArrayList<Article> getArticles() {return articles;}
    public LocalDate getData() {return data;}
    public void setArticles(ArrayList<Article> articles) {
        if(articles == null || articles.isEmpty()) {
            throw new IllegalArgumentException("Com a mínim pots fer encàrrecs d'un article.");
        }
        this.articles = new ArrayList<>();
    }
    public String getNomClient() {return nomClient;}
    public String getTelefonClient() {return telefonClient;}
    public void afegirArticle (Article article) {
        this.articles.add(article);
    }
    public String generarAlbara() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom del client: " + nomClient + "\n");
        sb.append("Telefon del client: " + telefonClient + "\n");
        sb.append("Data de l'encàrrec: " + data + "\n");
        sb.append("Quantitat\tUnitats\tArticle\n");
        sb.append("=========\t=======\t=========\n");
        for(Article a : articles) {
            sb.append(a.toString() + "\t\t"+ " " + "\n");
        }
        return sb.toString();
    }
    public String generarCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(nomClient + ";" + telefonClient + ";" + data + ";");
        for (Article a : articles) {
            sb.append(a.toCSV());
        }
        return sb.toString();
    }
    public LocalDate formatejarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Sisplau, indica una data correcta en format dd/mm/aaaa");
        }
    }
    public void setNomClient(String nomClient) {
        this.nomClient = UtilString.normalitzaString(nomClient);
    }
    public void setTelefonClient(String telefonClient) {
        if (telefonClient == null || telefonClient.length() != 9) {
            throw new IllegalArgumentException("El telèfon ha de ser de nou dígits.");
        }
        for(int i = 0; i < telefonClient.length(); i++) {
            if(!Character.isDigit(telefonClient.charAt(i))) {
                throw new IllegalArgumentException("El telèfon només pot contenir números.");
            }
        }
        this.telefonClient = telefonClient;
    }
}
