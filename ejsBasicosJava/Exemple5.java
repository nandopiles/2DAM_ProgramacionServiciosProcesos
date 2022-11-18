import java.io.*;
public class Exemple5 {
	public static void main(String[] args){
	Runtime r=Runtime.getRuntime();
	//Linux. Canvi de contrasenya
	String comando="passwd"; //comando a executar
	//Windows. Canvi de data
	//String comando="CMD /C DATE"; //comando a executar
	Process p=null;
	
	try{
		p=r.exec(comando);
		//escriptura - envie entrada a "date"
		OutputStream os=p.getOutputStream();
		//Linux
		os.write("contrasenyaactual\n".getBytes()); //Contrasenya actual
		os.flush(); //buide buffer
		os.write("contrasenyanova\n".getBytes()); //Contrasenya nova
		os.flush(); //buide buffer
		os.write("contrasenyanova\n".getBytes()); //Repetir contrasenya nova
		os.flush(); //buide buffer
		//Windows
		/*os.write("10-10-13".getBytes());
		os.flush(); //buide buffer*/
		
		//lectura - obté l'eixida de "date"
		InputStream is=p.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader (is));
		String linea;
		while ((linea=br.readLine())!=null)//llig una línia
			System.out.println(linea);
		br.close();
		} catch(Exception e) {
			System.out.println ("Error en "+comando);
			e.printStackTrace();
			}
		
	//comprovació d'error: 0 bé - 1 malament
	int exitVal;
	try {
		exitVal=p.waitFor();
		System.out.println("Valor d'eixida "+exitVal);
		} catch(InterruptedException e){
			e.printStackTrace();
			}

	}//fi main
}//fi exemple5
