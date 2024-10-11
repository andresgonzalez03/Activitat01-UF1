package src;
import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        mostrarMenu();
    }
    private static void mostrarMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Generar un nou encàrrec");
            System.out.println("2. Mostrar un encàrrec");
            System.out.println("3. Sortir");
            String resposta = reader.readLine();
    
            switch(resposta) {
                case "1" -> {
                    generaEncarrec();
                    System.out.println("Encàrrec generat correctament");
                }
                case "2" -> mostraEncarrec();
                case "3" -> {
                    System.out.println("Adéu");
                    return;
                }
                default -> System.out.println("Opció no vàlida. Tria 1, 2 o 3");
            }
        }
    }
    private static void demanaDadesUsuari(Encarrec encarrec) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
            while(true) {
                System.out.println("Introdueix el seu nom:");
                try {
                    String nom = reader.readLine();
                    encarrec.setNomClient(nom);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
                String telefon;
                while(true) {
                    System.out.println("Introdueix el seu telèfon:");
                    telefon = reader.readLine();
                    encarrec.setTelefonClient(telefon);
                    if(encarrec.getTelefonClient() != null) {
                        break;
                    }
                }
                LocalDate data = null;
                while(data == null) {
                    System.out.println("La data en la que vol l'encàrrec (dd/MM/yyyy):");
                    String dataStr = reader.readLine();
                    encarrec.setData(encarrec.formatejarData(dataStr));
                    if(encarrec.getData() != null) {
                        break;
                    }
                }
                break;
            } catch (IOException e) {
                System.out.println("Error al llegir les dades, torna-ho a provar");
                e.printStackTrace();
            }
        }
    }
    private static void demanaArticles(Encarrec encarrec) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        while (true) {
            String nomArticle = "";
            while(true) {
                System.out.println("Introdueix el nom de l'article:");
                try {
                    nomArticle = UtilString.normalitzaString(reader.readLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
    
            double quantitat = 0;

            while (true) {
                System.out.println("Introdueix la quantitat que vulguis:");
                try {
                    quantitat = Double.parseDouble(reader.readLine());
                    if(quantitat <= 0) {
                        throw new IllegalArgumentException("Has d'indicar almenys una unitat");
                    } 
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Sisplau, indica un número vàlid");

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            Unitat unitat = null;
            while (true) {
                System.out.println("Introdueix una unitat: Kilogram (Kg), Litre (L), Unitat (Ud), Ampolles (ampolles) o Gram (G)");
                try {
                    unitat = Unitat.fromString(reader.readLine()); 
                    break; 
                } catch (IllegalArgumentException e) {
                   System.out.println(e.getMessage());
                }
            }
            Article article = new Article(nomArticle, unitat, quantitat);
            encarrec.afegirArticle(article); 

            System.out.println("Article afegit!\n");

            System.out.println("Vols introduir un altre article? (sí o no)");
            String resposta = reader.readLine();
            if (resposta.equalsIgnoreCase("no")) {
                System.out.println();
                break;
            }
        }  
    }
    private static void mostraEncarrec() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Vale, vols el albarà, el CSV o el binari?");
        System.out.println("1. Albarà\n" + "2. CSV\n" + "3. Binari");
        String resposta = reader.readLine();
        System.out.println("Vale, indica la ruta de l'arxiu que vols llegir:");
        String ruta = reader.readLine();
        switch (resposta) {
            case "1":
                System.out.println("Llegint l'albarà...\n");
                Gestor.llegirAlbara(ruta); 
                break;
            case "2":
                System.out.println("Llegint el fitxer CSV...\n");
                Gestor.llegirCSV(ruta);
                break;
            case "3":
                System.out.println("Llegint el fitxer binari...\n");
                Gestor.llegirBinari(ruta); 
                break;
            default:
                System.out.println("Opció no vàlida. Tria 1, 2 o 3.");
                break;
        }
    }
    private static void quinFitxer(Encarrec encarrec) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Vale, quin tipus de fitxer vols generar?");
        System.out.println("1. Albarà (Text)");
        System.out.println("2. CSV");
        System.out.println("3. Binari");
        String resposta = reader.readLine();
    
        switch (resposta) {
            case "1" -> {
                System.out.println("Generant fitxer Albarà (Text)...");
                Gestor.EscriureAlbara(encarrec);
            }
            case "2" -> {
                System.out.println("Generant fitxer CSV...");
                Gestor.EscriureCSV(encarrec);
            }
            case "3" -> {
                System.out.println("Generant fitxer Binari...");
                Gestor.EscriureBinari(encarrec);
            }
            default -> System.out.println("Opció no vàlida. Tria 1, 2 o 3");
        }
    }
    private static void generaEncarrec() throws IOException {
        Encarrec encarrec = new Encarrec();
        demanaDadesUsuari(encarrec);
        demanaArticles(encarrec);
        quinFitxer(encarrec);
    }
}