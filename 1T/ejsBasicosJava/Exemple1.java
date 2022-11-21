
public class Exemple1{
public static void main(String[] args) {
	Runtime r=Runtime.getRuntime();
	String comando="gnome-text-editor"; //A Windows canviar per "notepad"
	Process p;

	try{
		p=r.exec(comando);
		} catch(Exception e) {
			System.out.println ("Error en "+comando);
			e.printStackTrace();
			}
	}

}//Exemple1
