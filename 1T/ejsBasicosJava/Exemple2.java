import java.io.*;
public class Exemple2{
public static void main(String[] args) {
	Runtime r=Runtime.getRuntime();
	String comando="java Unasalutacio \"Hola Món!!\">exemple.txt";
//	String comando="ls";
//	String comando="cmd /c dir"; //Windows	
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
