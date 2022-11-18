import java.io.*;
public class Exemple2_error{
public static void main(String[] args) {
	Runtime r=Runtime.getRuntime();
	//String comando="java Unasalutacio \"Hola Món!!\">exemple.txt";
	String comando="ls /asdasd";
	//String comando="cmd /c dirr"; //Windows	
	Process p=null;

	try{
		p=r.exec(comando);
		InputStream is=p.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader (is));
		String linea;
		while ((linea=br.readLine())!=null)//llegig una línia
			System.out.println(linea);
		br.close();
		} catch(Exception e) {
			System.out.println ("Error en "+comando);
			e.printStackTrace();
			}

	try {
			InputStream er = p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			while ((liner = brer.readLine()) != null)
				System.out.println("ERROR >" + liner);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	//comprobació d'error: 0 bé - 1 mal
	int exitVal;
	try {
		exitVal=p.waitFor();
		System.out.println("Valor de salida "+exitVal);
		} catch(InterruptedException e){
			e.printStackTrace();
			}
	
	} //del main
}//Exemple2
