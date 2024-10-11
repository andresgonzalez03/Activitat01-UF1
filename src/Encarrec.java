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
            
        } else if (!data.isAfter(LocalDate.now())) {
            System.out.println("Has d'indicar una data posterior a la actual.");
        } else {
            this.data = data;
        }
    }
    public ArrayList<Article> getArticles() {return articles;}
    public LocalDate getData() {return data;}
    public void setArticles(ArrayList<Article> articles) {
        if(articles == null || articles.isEmpty()) {
            System.out.println("Com a mínim pots fer encàrrecs d'un article.\n Sisplau, intenta-ho de nou");
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
        sb.append("Data de l'encàrrec: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
        sb.append(String.format("%-10s %-10s %s%n", "Quantitat", "Unitats", "Article"));
        sb.append(String.format("%-10s %-10s %s%n", "=========", "=======", "======="));
        for (Article a : articles) {
            sb.append(String.format("%-10s %-10s %s%n", a.getQuantitat(), a.getUnitat(), a.getNom()));
        }
            return sb.toString();
    }
    public String generarCSV() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatejada = data.format(formatter);
        sb.append(nomClient + ";" + telefonClient + ";" + dataFormatejada + ";");
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
            System.out.println("Sisplau, indica una data correcta en format dd/mm/aaaa");
            return null;
        }
    }
    public void setNomClient(String nomClient) {
        for(int i = 0; i < nomClient.length(); i++) {
            char c = nomClient.charAt(i);
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("El nom ha de contenir només lletres");
            } 
        }
        this.nomClient = UtilString.normalitzaString(nomClient);
    }
    public void setTelefonClient(String telefonClient) {
        if (telefonClient == null || telefonClient.length() != 9) {
            System.out.println("El telèfon ha de ser de nou dígits");
            return;
        }
        for(int i = 0; i < telefonClient.length(); i++) {
            if(!Character.isDigit(telefonClient.charAt(i))) {
                System.out.println("El telèfon només pot contenir números");
                return;
            }
        }
        this.telefonClient = telefonClient;
    }
}