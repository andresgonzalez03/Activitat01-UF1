package src;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    private Client client;
    // private final String albaran = "../factures/" + "encarrecs_client_" + client.getNom() + "_" + System.currentTimeMillis() + ".txt";
    // private final String csv = "../factures/" + "encarrecscd_client_" + client.getNom() + "_" + System.currentTimeMillis() + ".csv";
    // private final String binari = "../factures/" + "encarrecs_client_" + client.getNom() + "_" + System.currentTimeMillis() + ".dat";
   
    public static void main(String[] args) {
        Main main = new Main();
        mostrarBenvinguda();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {  // try catch per controlar les possibles excepcions generades si s'ingresa una cadena incorrecta al reader.
            mostraOpcions();
            while(true) {
                System.out.println("Escriu la teva opció:");
                String opcio = reader.readLine();
                if(opcio.isEmpty()) continue;
                if(opcio.equals("4")) {
                    System.out.println("Adéu!");
                    break;
                }
                switch (opcio) { 
                    case "1":
                        mostraAjuda(); 
                        break;
                    case "2":
                        main.nouEncarrecFactura();
                        main.nouEncarrecCSV();
                        main.nouEncarrecBinari();
                        break;
                    case "3":
                        main.veureEncarrecCSV();
                        main.veureEncarrecBinari();
                        break;
                    default :
                        System.out.println("Sisplau, utilitza les opcions disponibles.");
                        System.out.println("Tens aquestes opcions:");
                        mostraAjuda();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void mostrarBenvinguda() {
        System.out.println("Hola, benvingut! Escriu surt per sortir del programa.\n");
    }
    public static void mostraOpcions() {
        System.out.println("Qué vols fer? \n" + "1. Ajuda (1) \n" + "2. Generar un nou encàrrec (2) \n" + "3. Mostrar un encàrrec (3) \n" + "4. Sortir (4) \n");
    }
    public static void mostraAjuda() {
        System.out.println("- Ajuda escrivint 1");
        System.out.println("- Fer un encàrrec escrivint 2");
        System.out.println("- Veure un encàrrec escrivint 3");
        System.out.println("- Sortir escrivint 4\n");
    }
    public static void errorComanda() {
        System.out.println("Opció desconeguda, sisplau utilitza Ajuda per veure les comandes disponibles.");
    }
    // Aquest mètode genera el fitxer en format d'albarà.
    public void nouEncarrecFactura() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while(true) {
                System.out.println("Benvingut, el seu nom?");
                String nom = reader.readLine();
                System.out.println("Perfecte, el seu telèfon?");
                String telefon = reader.readLine();
                System.out.println("Bé, ara demana allò que vulguis. Per veure la llista de articles escriu articles");
                String comanda = reader.readLine();
                ArrayList<Article> articles = new ArrayList<>();
                if (comanda.equalsIgnoreCase("articles")) {
                    System.out.println("Fruites i verdures\n" +
                                "- Pomes\n" +
                                "- Peres\n" +
                                "- Plàtans\n" +
                                "- Tomàquets\n" +
                                "- Enciams\n\n" +
                                
                                "Carns i peixos:\n" +
                                "- Fuet\n" +
                                "- Pollastre\n" +
                                "- Peix\n" +
                                "- Xai\n" +
                                "- Embotits\n\n" +
                                
                                "Begudes:\n" +
                                "- Vi rosat\n" +
                                "- Cervesa\n" +
                                "- Aigua mineral\n" +
                                "- Sucs de fruita)\n");
                }
                System.out.println("Introdueix el nom de l'article:");
                String nomArticle = reader.readLine();
                System.out.println("Introdueix la quantitat:");
                double quantitat = Double.parseDouble(reader.readLine());
                System.out.println("Introdueix el tipus d'unitat (u, ampolles, kg, etc.):");
                String tipusUnitat = reader.readLine();
                Unitat unitat = Unitat.fromString(tipusUnitat);
                articles.add(new Article(nomArticle, unitat, quantitat));
                System.out.println("Vols afegir un altre article? (sí/no)");
                String resposta1 =  reader.readLine();
                if (resposta1.equalsIgnoreCase("no")) {
                    System.out.println("Adéu!");
                    break;
                }
                LocalDate dataEncarrec = LocalDate.now().plusDays(3); // serveix per obtenir la data actual i afegir-li 3 dias, diguem que en 3 dias el client tindrà la seva comanda.
                


                System.out.println("Encàrrec creat:");
                System.out.println("Nom: " + nom);
                System.out.println("Telèfon: " + telefon);
                System.out.println("Data d'entrega: " + dataEncarrec);

                System.out.println("Vols fer un altre encàrrec? (sí/no)");
                String resposta2 = reader.readLine();
                if (resposta2.equalsIgnoreCase("no")) {
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } 
    }
    // Aquest mètode genera el fitxer CSV, separat per punt i coma (;)
    public void nouEncarrecCSV() {

    }
    // Aquest mètode genera el fitxer en format binari.
    public void nouEncarrecBinari() {

    }
    // Aquest mètode et mostra el contingut del fitxer CSV.
    public void veureEncarrecCSV() {

    }
    // Aquest mètode et mostra el contingut del fitxer en binari.
    public void veureEncarrecBinari() {

    }
}