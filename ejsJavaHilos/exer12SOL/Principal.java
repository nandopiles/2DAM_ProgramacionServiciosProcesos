import java.io.*;
import java.util.*;

public class Principal {

    public static void main(String[] args) {
        File primers = new File("primers.txt");
        File segons = new File("segons.txt");
        File postres = new File("postres.txt");
        FileWriter file_primers = null;
        FileWriter file_segons = null;
        FileWriter file_postres = null;

        // Creación de ficheros de salida y del Scanner sobre el de entrada
        try {
            primers.createNewFile();
            file_primers = new FileWriter(primers);
            segons.createNewFile();
            file_segons = new FileWriter(segons);
            postres.createNewFile();
            file_postres = new FileWriter(postres);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ThreadGroup grupo = new ThreadGroup("Menu diario");

        Fil h = new Fil(file_primers, file_segons, file_postres);
        Thread h1 = new Thread(grupo, h, "Fil1");
        Thread h2 = new Thread(grupo, h, "Fil2");
        Thread h3 = new Thread(grupo, h, "Fil3");
        h1.start();
        h2.start();
        h3.start();

    }
}
