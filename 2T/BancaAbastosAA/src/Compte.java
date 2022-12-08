
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
     * @return el saldo que te el compte
     */
    public int getSaldoActual() {
        return this.saldo;
    }

    /**
     * ingresa la quantitat que se li passa per paràmetre amb el nom de la
     * persona que ho fa
     *
     * @param nom
     * @param quantitat
     */
    public void ingres(String nom, int quantitat) {
        this.saldo += quantitat;
        System.out.printf("**(%s) => \"Ingresa\" %d € (Saldo Actual: %d €)\n", nom, quantitat, getSaldoActual());
    }

    /**
     * retira la quantitat que se li passa per paràmetre amb el nom de la
     * persona que ho fa
     *
     * @param nom
     * @param quantitat
     */
    public void reintegrament(String nom, int quantitat) {
        this.saldo -= quantitat;
        System.out.printf("**(%s) => \"Retira\" %d € (Saldo Actual: %d €)\n", nom, quantitat, getSaldoActual());
    }
}
