
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
     * Se fa un ingrés amb el nom y quantitat ficada
     */
    public void ferIngres() {
        int quantitatSumar = random();
        compte.ingres(this.nom, quantitatSumar);
    }

    /**
     * Se fa una retirada amb el nom y quantitat ficada
     */
    public void ferRetirada() {
        int quantitatRestar = random();
        compte.reintegrament(this.nom, quantitatRestar);
    }

    @Override
    public void run() {
        ferIngres();
        ferRetirada();
        ferIngres();
        ferRetirada();
    }
}
