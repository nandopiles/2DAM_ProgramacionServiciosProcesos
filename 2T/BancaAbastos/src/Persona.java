
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
     * @return nom de la persona
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return num random del 1 al 500
     */
    public int random() {
        return (int) (Math.random() * 500 + 1);
    }

    /**
     * Se fa un ingres amb el nom y quantitat ficada
     */
    public void ferIngres() {
        int quantitatSumar = random();
        compte.ingres(getNom(), quantitatSumar);
    }

    /**
     * Se fa una retirada amb el nom y quantitat ficada
     */
    public void ferRetirada() {
        int quantitatRestar = random();
        compte.reintegrament(getNom(), quantitatRestar);
    }

    @Override
    public void run() {
        ferIngres();
        ferRetirada();
        ferIngres();
        ferRetirada();
    }
}
