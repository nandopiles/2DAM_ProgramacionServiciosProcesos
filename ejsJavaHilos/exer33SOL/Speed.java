import java.util.Random;

public class Speed extends Thread{
	private Autobus a;
	private String operacion;

	public Speed(Autobus a, String operacion) {
		this.a = a;
		this.operacion = operacion;
	}

	public String getoperacion() {
		return operacion;
	}

	public void run() {
		int nuevavelo = 0;
		int velobus = 0;

		synchronized(a){
			while (velobus < 100) {
				Random r = new Random();
				nuevavelo = (int)(r.nextDouble()*10 + 1);
				velobus = a.getVelocidad();

				if (operacion.equals("accelerar")) { //aceleramos hasta superar los 80 Km./h
					velobus = velobus + nuevavelo;
					if (velobus < 80) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {e1.printStackTrace();}
						System.out.println("Accelere "+nuevavelo+"km/h. Ara anem a "+velobus+"km/h");
						a.setVelocidad(velobus);
					}else {
						a.notify();
						try {
							a.wait();
						} catch (InterruptedException e) {e.printStackTrace();}
					}
				}else {
					//frenamos hasta llegar a los 50 Km./h
					velobus = velobus - nuevavelo;
					if (velobus > 50) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {e1.printStackTrace();}
						System.out.println("Frene "+nuevavelo+"km/h. Ara anem a "+velobus+"km/h");
						a.setVelocidad(velobus);
					}else {
						a.notify();
						try {
							a.wait();
						} catch (InterruptedException e) {e.printStackTrace();}
					}
				}
			}//while
		}//synchronized
	}
}
