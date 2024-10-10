package src;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        while(true) {
            mostrarMenu();
        }
    }
    private static void mostrarMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Selecciona una opción:");
        System.out.println("1. Generar un nou encàrrec");
        System.out.println("2. Mostrar un encàrrec");
        System.out.println("3. Sortir");
        String resposta = reader.readLine();

        switch(resposta) {
            case "1" -> generarEncarrec();
            

        }
    }
    private static void generarEncarrec() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Encarrec encarrec = new Encarrec();
        while(true) {
            try {
                System.out.println("Introdueix el seu nom:");
                String nom = reader.readLine();
                encarrec.setNomClient(nom);
                System.out.println("Introdueix el seu telèfon:");
                String telefon = reader.readLine();
                encarrec.setTelefonClient(telefon);
                System.out.println("La data en la que vol l'encàrrec (dd/MM/yyyy):");
                String dataStr = reader.readLine();
                encarrec.setData(encarrec.formatejarData(dataStr));
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        demanaArticles();
    }
    private static ArrayList<Article> demanaArticles() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        ArrayList<Article> articles = new ArrayList<>();
        while (true) {
            System.out.println("Introdueix el nom de l'article:");
            String nomArticle = reader.readLine();
            while(true) {
                System.out.println("Introdueix la quantitat que vulguis:");
                double quantitat = 0;
                try {
                    quantitat = Double.parseDouble(reader.readLine());
                } catch (Exception e) {
                    System.out.println("Quantitat no vàlida");
                }
            }
        while(true) {
            System.out.println("Introdueix una unitat: Kilogram (Kg), Litre (L), Unitat (Ud), Ampolles (ampolles) o Gram (G)");
            Unitat unitat = Unitat.fromString(reader.readLine());
            System.out.println("Vols introduir un altre article? (sí o no)");
            String resposta =  reader.readLine();
            if(resposta.equals("no")) break;
            }
        }
        Article article = new Article(nomArticle, unitat, quantitat);
        articles.add(article);
        System.out.println("Article afegit!\n");
        return articles;
    }
    private static void mostraEncarrec() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    }
}