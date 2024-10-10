package src;
import java.io.*;

public class Gestor {
    public static void crearDirectorisFactures() throws IOException {
        String directoris[] = {"/home/this_andres/Activitat01-UF1/factures","/home/this_andres/Activitat01-UF1/fitxersCSV", "/home/this_andres/Activitat01-UF1/fitxersBinaris"};
        File carpeta[] = new File[directoris.length];
        for (int i = 0; i < carpeta.length; i++) {
            carpeta[i] = new File(directoris[i]);
        }
        for(File f : carpeta) {
            if(!f.exists()) {
                if(f.mkdir()) {
                    System.out.println("Directori creat.");
                } else {
                    System.out.println("No s'ha pogut fer el directori.");
                }
            } else {
                System.out.println("El directori ja existeix.");
            }
        }
    }
    public static void EscriureAlbara(Encarrec encarrec)  throws IOException {
        crearDirectorisFactures();
        String ruta = "/home/this_andres/Activitat01-UF1/factures";
        String nomArxiu = ruta + "encarrecs_client_" + encarrec.getNomClient() + "_" + System.currentTimeMillis() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomArxiu))) {
            writer.write(encarrec.generarAlbara());
            System.out.println("Albarà creat.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void EscriureCSV(Encarrec encarrec) throws IOException {
        crearDirectorisFactures();
        String ruta = "/home/this_andres/Activitat01-UF1/fitxersCSV";
        String nomArxiu = ruta + "encarrecs_client_" + encarrec.getNomClient() + "_" + System.currentTimeMillis() + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomArxiu))) {
            writer.write(encarrec.generarCSV());
            System.out.println("CSV creat.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void EscriureBinari(Encarrec encarrec) throws IOException {
        crearDirectorisFactures();
        String ruta = "/home/this_andres/Activitat01-UF1/fitxersBinaris";
        String nomArxiu = ruta + "encarrecs_client_" + encarrec.getNomClient() + "_" + System.currentTimeMillis() + ".bin";
        try {
            FileOutputStream file1 = new FileOutputStream(nomArxiu);
            DataOutputStream str1 = new DataOutputStream(file1);
            str1.writeUTF(encarrec.getNomClient());
            str1.writeUTF(encarrec.getTelefonClient());
            str1.writeUTF(encarrec.getData().toString());
            for(Article a : encarrec.getArticles()) {
                str1.writeUTF(a.getNom());
                str1.writeDouble(a.getQuantitat());
                str1.writeUTF(a.getUnitat().toString());
            }
            str1.close();
            file1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void llegirBinari(String ruta) {
        try {
            FileInputStream fileInpStream1 = new FileInputStream(ruta);
            DataInputStream str2 = new DataInputStream(fileInpStream1);
            String nomClient = str2.readUTF();
            String telefonClient = str2.readUTF();
            String data = str2.readUTF();

            Encarrec encarrec = new Encarrec();
            encarrec.setNomClient(nomClient);
            encarrec.setTelefonClient(telefonClient);
            encarrec.setData(encarrec.formatejarData(data));
            System.out.println(encarrec.generarAlbara());

            while(str2.available() > 0) {
                String nomArticle = str2.readUTF();
                double quantitat = str2.readDouble();
                String unitat = str2.readUTF();
                Article article = new Article(nomArticle, Unitat.fromString(unitat), quantitat);
                encarrec.afegirArticle(article);
            }
            System.out.println(encarrec.generarAlbara());
            str2.close();
            fileInpStream1.close();
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
}