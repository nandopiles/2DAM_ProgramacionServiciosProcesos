
/**
 *
 * @author Nando
 */
public class Persona extends Thread {

    private String nom;
    private Compte compte;

    /**
     * constructor
     *
     * @param nom
     * @param compte
     */
    public Persona(String nom, Compte compte) {
        this.nom = nom;
        this.compte = compte;
    }

    /**
     *
     * @return num random del 1 al 500
     */
    public int random() {
        return (int) (Math.random() * 500 + 1);
    }

    /**
     * calcula si es possible fer un ingrés i la fa
     *
     * @return si la operació ha sigut exitosa
     */
    public boolean ferIngres() {
        int quantitatSumar;

        quantitatSumar = random();
        if (quantitatSumar + compte.getSaldoActual() <= compte.getMaxSaldo()) {
            compte.ingres(this.nom, quantitatSumar);
            return true;
        } else {
            System.out.printf("(-) \"%s\" vol INGRESAR %d €. Massa saldo! => MaxSaldo = %d €\n\t=>Saldo Actual: %d €\n\n", this.nom, quantitatSumar, compte.getMaxSaldo(), compte.getSaldoActual());
            return false;
        }
    }

    /**
     * calcula si es possible fer una retirada i la fa
     *
     * @return si la operació ha sigut exitosa
     */
    public boolean ferReintegrament() {
        int quantitatRestar;

        quantitatRestar = random();
        if (compte.getSaldoActual() - quantitatRestar >= 0) {
            compte.reintegrament(this.nom, quantitatRestar);
            return true;
        } else {
            System.out.printf("(-) \"%s\" vol RETIRAR %d €. No hi ha saldo!\n\t=>Saldo Actual: %d €\n\n", this.nom, quantitatRestar, compte.getSaldoActual());
            return false;
        }
    }

    @Override
    public void run() {
        synchronized (compte) {
            if (ferIngres()) {
                compte.notify();
                if (ferReintegrament()) {
                    compte.notify();
                    if (ferIngres()) {
                        compte.notify();
                        if (ferReintegrament()) {
                            compte.notify();
                            compte.notifyAll(); //despertem a tots els waits
                        } else {
                            try {
                                compte.wait();
                            } catch (InterruptedException e) {
                                e.getMessage();
                            }
                        }
                    } else {
                        try {
                            compte.wait();
                        } catch (InterruptedException e) {
                            e.getMessage();
                        }
                    }
                } else {
                    try {
                        compte.wait();
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }
                }
            } else {
                try {
                    compte.wait();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
        }//syncronized
    }
}
