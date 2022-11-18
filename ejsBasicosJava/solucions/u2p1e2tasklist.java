
import java.io.*;

public class u2p1e2tasklist {

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        String comando = "java u2p1e2tasklistGuarda fitxer.txt";
        Process p;
        try {
            p = r.exec(comando);
        } catch (Exception e) {
            System.out.println("Error en " + comando);
            e.printStackTrace();
        }
    }
}//u2p1e2tasklist
