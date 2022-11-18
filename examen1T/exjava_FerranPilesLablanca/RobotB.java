
public class RobotB extends Thread {

    //var
    private Cinta cinta;
    private int cantitat, temps;

    /**
     * Constructor
     *
     * @param cinta
     * @param cantitat
     * @param temps
     */
    public RobotB(Cinta cinta, int cantitat, int temps) {
        this.cinta = cinta;
        this.cantitat = cantitat;
        this.temps = temps * 1000;
    }

    /**
     * Decrementar càrrega
     */
    public void run() {
        int decrement = 5;
        boolean continuar = true;

        synchronized (cinta) {
            while (continuar) { //infinit
                for (int i = 0; i < cantitat; i++) {
                    if (cinta.carregaActual() >= decrement) {
                        cinta.decrementa(decrement);
                        System.out.printf("-->Se trau una bolsa de: %d. Queden %d kilos a la cinta\n", decrement, cinta.carregaActual());
                    } else {
                        System.out.printf("**** No puc traure %d. Soles queden %d\n", decrement, cinta.carregaActual());
                        cinta.notify(); //mira si ni ha altre fil que vol entrar
                        try {
                            cinta.wait(); //s'espera fins que li tornen a cridar
                            System.out.println(cinta.seguir());
                        } catch (InterruptedException e) {
                            System.out.println("(-) Error decrementant càrrega...");
                        }
                    }
                    try {
                        sleep(temps);
                    } catch (InterruptedException e) {
                        System.out.println("(-) Error fent sleep...");
                    }
                }
            }
            cinta.notifyAll(); //despertem a tots
        }
    }
}
