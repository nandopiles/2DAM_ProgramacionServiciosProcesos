
public class Cinta {

    //var
    private int carrega = 0;

    /**
     * Constructor
     */
    public Cinta() {
    }

    /**
     * Incrementa la càrrega de la cinta
     *
     * @param increment quantitat a pujar
     */
    public void incrementa(int increment) {
        carrega += increment;
    }

    /**
     * Decrementa la càrrega
     *
     * @param decrement quantitat a baixar
     */
    public void decrementa(int decrement) {
        carrega -= decrement;
    }

    /**
     * Càrrega actual que té la cinta
     *
     * @return
     */
    public int carregaActual() {
        return carrega;
    }

    /**
     * Continuar
     *
     * @return missatge
     */
    public String seguir() {
        return "**** Ja puc continuar traguent";
    }
}
