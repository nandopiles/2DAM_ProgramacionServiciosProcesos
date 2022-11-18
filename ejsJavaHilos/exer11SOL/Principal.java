public class Principal {
	
	public static void main(String[] args) {
		Fil h1 = new Fil("Fil 1");
		Fil h2 = new Fil("Fil 2");
		Fil h3 = new Fil("Fil 3");

		h1.start();
		h2.start();
		h3.start();

		System.out.println("3 fils iniciats...");
	}
}
