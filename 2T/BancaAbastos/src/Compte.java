
/**
 *
 * @author Nando
 */
public class Compte {

    private int saldo;
    private final int maxSaldo;

    /**
     * constructor
     *
     * @param saldo
     * @param maxSaldo
     */
    public Compte(int saldo, int maxSaldo) {
        this.saldo = saldo;
        this.maxSaldo = maxSaldo;
    }

    /**
     *
     * @return el Màxim de saldo que pot tindre el compte
     */
    public int getMaxSaldo() {
        return maxSaldo;
    }

    /**
     *
     * @return el saldo actual que te el compte
     */
    public int getSaldoActual() {
        return this.saldo;
    }

    /**
     *
     * @param saldo nou saldo
     */
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    /**
     * ingresa la quantitat que se li passa per paràmetre amb el nom de la
     * persona que ho fa. Si no se pot fer l'operació esperarà fins que algú el
     * notifique
     *
     * @param nom nom del usuari
     * @param quantitat quantitat a ingresar
     */
    public synchronized void ingres(String nom, int quantitat) {
        try {
            if (getSaldoActual() + quantitat > getMaxSaldo()) {
                do {
                    System.out.printf("(-) \"%s\" vol INGRESAR %d €. Massa saldo! => MaxSaldo = %d €\n\t=>Saldo Actual: %d €\n\n", nom, quantitat, getMaxSaldo(), getSaldoActual());
                    wait();
                    System.out.printf("----(%s) torna a comprovar INGRES de %d €\n", nom.toUpperCase(), quantitat);
                } while (getSaldoActual() + quantitat > getMaxSaldo()); //fins que no puga fer l'operació no pararà d'esperar
            }
            setSaldo(getSaldoActual() + quantitat);
            System.out.printf("**(%s) => \"Ingresa\" %d € (Saldo Actual: %d €)\n", nom, quantitat, getSaldoActual());
            notify(); //crida a vore si ni ha algú que puga fer l'operació que antes no ha pogut fer
        } catch (InterruptedException e) {
            e.getMessage();
            System.out.println("\t(-) Error fent l'INGRÉS...");
        }
    }

    /**
     * retira la quantitat que se li passa per paràmetre amb el nom de la
     * persona que ho fa. Si no se pot fer l'operació esperarà fins que algú el
     * notifique
     *
     * @param nom nom del usuari
     * @param quantitat quantitat a retirar
     */
    public synchronized void reintegrament(String nom, int quantitat) {
        try {
            if (getSaldoActual() - quantitat < 0) {
                do {
                    System.out.printf("(-) \"%s\" vol RETIRAR %d €. No hi ha saldo!\n\t=>Saldo Actual: %d €\n\n", nom, quantitat, getSaldoActual());
                    wait();
                    System.out.printf("-----(%s) torna a comprovar RETIRADA de %d €\n", nom.toUpperCase(), quantitat);
                } while (getSaldoActual() - quantitat < 0); //fins que no puga fer l'operació no pararà d'esperar
            }
            setSaldo(getSaldoActual() - quantitat);
            System.out.printf("**(%s) => \"Retira\" %d € (Saldo Actual: %d €)\n", nom, quantitat, getSaldoActual());
            notify(); //crida a vore si ni ha algú que puga fer l'operació que antes no ha pogut fer
        } catch (InterruptedException e) {
            e.getMessage();
            System.out.println("\t(-) Error fent la RETIRADA...");
        }
    }
}
