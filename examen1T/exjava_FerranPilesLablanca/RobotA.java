
public class RobotA extends Thread {

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
    public RobotA(Cinta cinta, int cantitat, int temps) {
        this.cinta = cinta;
        this.cantitat = cantitat;
        this.temps = temps * 1000;
    }

    /**
     * Incrementar càrrega
     */
    public void run() {
        boolean continuar = true;

        synchronized (cinta) {
            while (continuar) { //infinit
                cinta.incrementa(cantitat);
                System.out.printf("->Se fiquen: %d kilos. Queden: %d kilos a la cinta\n", cantitat, cinta.carregaActual());
                cinta.notify(); //crida a vore si ni ha altre fil que vol entrar
                try {
                    cinta.wait(); //s'espera fins que li tornen a cridar
                } catch (InterruptedException ex) {
                    System.out.println("(-) Error incrementant càrrega...");
                }
                try {
                    sleep(temps);
                } catch (InterruptedException e) {
                    System.out.println("(-) Error fent sleep...");
                }
            }
            cinta.notifyAll(); //despertem a tots per a finalitzar
        }
    }
}
