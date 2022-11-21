public class Principal {

	public static void main(String[] args) {
		Autobus autobus = new Autobus("3287-SPD");

		Speed hilo1 = new Speed(autobus, "accelerar");
		Speed hilo2 = new Speed(autobus, "frenar");

		hilo1.start();

		//Nos esperamos 1 segundo antes de lanzar el hilo que frena, para asegurarnos que
		//hilo que acelera entra primero en la CPU

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		hilo2.start();
	}

}
