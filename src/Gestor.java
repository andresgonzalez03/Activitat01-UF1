package src;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gestor {
    private static final String PATH = System.getProperty("user.home");
    public static void crearDirectoris() throws IOException {
        String directoris[] = {PATH + "/factures/",PATH + "/fitxersCSV/", PATH +"/fitxersBinaris/"};
        File carpeta[] = new File[directoris.length];
        for (int i = 0; i < carpeta.length; i++) {
            carpeta[i] = new File(directoris[i]);
        }
        for(File f : carpeta) {
            if(!f.exists()) {
                if(f.mkdirs()) {
                    System.out.println("Directori creat: " + f.getPath());
                } else {
                    System.out.println("No s'ha pogut fer el directori: " + f.getPath());
                }
            } else {
                System.out.println("El directori ja existeix: " + f.getPath());
            }
        }
    }
    public static void EscriureAlbara(Encarrec encarrec)  throws IOException {
        crearDirectoris();
        String ruta = PATH + "/factures/";
        String nomArxiu = ruta + "encarrecs_client_" + encarrec.getNomClient() + "_" + new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date(System.currentTimeMillis())) + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomArxiu))) {
            writer.write(encarrec.generarAlbara());
            System.out.println("Albarà creat amb nom: " + nomArxiu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void EscriureCSV(Encarrec encarrec) throws IOException {
        crearDirectoris();
        String ruta =  PATH + "/fitxersCSV/";
        String nomArxiu = ruta + "encarrecs_client_" + encarrec.getNomClient() + "_" + new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date(System.currentTimeMillis())) + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomArxiu))) {
            writer.write(encarrec.generarCSV());
            System.out.println("CSV creat amb nom: " + nomArxiu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void EscriureBinari(Encarrec encarrec) throws IOException {
        crearDirectoris();
        String ruta = PATH + "/fitxersBinaris/";
        String nomArxiu = ruta + "encarrecs_client_" + encarrec.getNomClient() + "_" + new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date(System.currentTimeMillis())) + ".bin";
        try (DataOutputStream str1 = new DataOutputStream(new FileOutputStream(nomArxiu))){
            str1.writeUTF(encarrec.getNomClient());
            str1.writeUTF(encarrec.getTelefonClient());
            String dataFormatejada = encarrec.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            str1.writeUTF(dataFormatejada);
            for(Article a : encarrec.getArticles()) {
                str1.writeUTF(a.getNom());
                str1.writeDouble(a.getQuantitat());
                str1.writeUTF(a.getUnitat().toString());
            }
            System.out.println("Fitxer binari creat amb nom: " + nomArxiu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void llegirBinari(String ruta) {
        try(DataInputStream str2 = new DataInputStream(new FileInputStream(ruta))) {
            String nomClient = str2.readUTF();
            String telefonClient = str2.readUTF();
            String data = str2.readUTF();

            Encarrec encarrec = new Encarrec();
            encarrec.setNomClient(nomClient);
            encarrec.setTelefonClient(telefonClient);
            encarrec.setData(encarrec.formatejarData(data));

            while(str2.available() > 0) {
                String nomArticle = str2.readUTF();
                double quantitat = str2.readDouble();
                String unitat = str2.readUTF();
                Article article = new Article(nomArticle, Unitat.fromString(unitat), quantitat);
                encarrec.afegirArticle(article);
            }
            System.out.println(encarrec.generarAlbara());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void llegirCSV(String ruta) {
        Encarrec encarrec = new Encarrec();
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            while(true) {
                String linia = reader.readLine();
                if(linia == null) {
                    break;
                }
                String[] dadesClient = linia.split((";"));
                String nomClient = dadesClient[0];
                encarrec.setNomClient(nomClient);
                String telefonClient = dadesClient[1];
                encarrec.setTelefonClient(telefonClient);
                String data = dadesClient[2];
                encarrec.setData(encarrec.formatejarData(data));
                String nomArticle = dadesClient[3];
                String quantitat = dadesClient[4];
                String unitat = dadesClient[5];
                Article article = new Article(nomArticle, Unitat.fromString(unitat), Double.parseDouble(quantitat));
                encarrec.afegirArticle(article);
            }
            System.out.println(encarrec.generarAlbara());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void llegirAlbara(String ruta) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            while(true) {
                String linia = reader.readLine();
                if(linia == null) {
                    break;
                }
                System.out.println(linia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}