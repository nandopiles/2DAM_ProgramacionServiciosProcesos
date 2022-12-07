
/**
 *
 * @author Nando
 */
public class BPA {

    public static void main(String[] args) {
        //compte
        Compte compte1 = new Compte(40, 500);
        //Persones asociades al compte
        Persona persona1 = new Persona("Mimi", compte1);
        Persona persona2 = new Persona("Rudolf", compte1);

        //execució
        System.out.printf("(Saldo Inicial: %d €)\n_____________________\n\n", compte1.getSaldoActual());
        persona1.start();
        persona2.start();
    }
}
