/*
Realitza un programa Java usant Runtime i Process que reba
des de la línia de comandos un nom de comando i l'execute.
 */
import java.util.Scanner;
public class u2p1e1Quasibash {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        System.out.println("Introdueix el comando que vulgues executar:");
		Scanner in = new Scanner(System.in);
        String comando = in.nextLine();
        Process p;
        try {
            p = r.exec(comando);
        } catch (Exception e) {
            System.out.println("Error en " + comando);
            e.printStackTrace();
        }
    }
}
