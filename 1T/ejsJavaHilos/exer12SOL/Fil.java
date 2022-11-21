import java.io.*;
import java.util.*;

public class Fil extends Thread {
    private FileWriter file_primers;
    private FileWriter file_segons;
    private FileWriter file_postres;

    public Fil(FileWriter file_primers, FileWriter file_segons, FileWriter file_postres) {
        this.file_primers = file_primers;
        this.file_segons = file_segons;
        this.file_postres = file_postres;
    }

    public void run() {
        File file_entrada = new File("menu.txt");
        Scanner sc = null;
        boolean prim_primers = true;
        boolean prim_segons = true;
        boolean prim_postres = true;
        String cadena_leida = "";

        // Definim scanner sobre el fitxer d'entrada
        try {
            sc = new Scanner(file_entrada);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        // Bucle principal que ejecutaran tots 3 Fils, cadascun escriurà en el seu fitxer
        try {
            while (sc.hasNext()) {
                cadena_leida = sc.nextLine();
                String[] partes = cadena_leida.split("-");
                switch (partes[0]) {
                case "1":
                    if (currentThread().getName().equals("Fil1")) {
                        if (prim_primers) {
                            prim_primers = false;
                        } else {
                            file_primers.write(partes[1] + "\n");
                            System.out.println("El Fil1 escribe..." + partes[1]);
                        }
                    }
                    ;
                    break;
                case "2":
                    if (currentThread().getName().equals("Fil2")) {
                        if (prim_segons) {
                            prim_segons = false;
                        } else {
                            file_segons.write(partes[1] + "\n");
                            System.out.println("El Fil2 escriu..." + partes[1]);
                        }
                    }
                    ;
                    break;
                case "3":
                    if (currentThread().getName().equals("Fil3")) {
                        if (prim_postres) {
                            prim_postres = false;
                        } else {
                            file_postres.write(partes[1] + "\n");
                            System.out.println("El Fil3 escriu..." + partes[1]);
                        }
                    }
                    ;
                    break;

                default:

                    System.out.println("Fil desconegut");
                    break;
                }// switch*/
            } // while
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Se cierran los ficheros de salida y el escaner
        try {
            file_primers.close();
            file_segons.close();
            file_postres.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
