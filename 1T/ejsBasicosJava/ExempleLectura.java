import java.io.*;
public class ExempleLectura{
	public static void main (String[] args){
	InputStreamReader in=new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader (in);
	String texto;
	
	try{
		System.out.println("Introdueix una cadena...");
		texto=br.readLine();
		System.out.println("Cadena escrita: "+texto);
		in.close();
		}catch (Exception e) {e.printStackTrace();}

	}//fi main
}//fi ExempleLectura
