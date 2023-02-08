import java.io.*;
import java.net.Socket;

/**
 * @author Nando
 */
public class FilServidor extends Thread {
    private final Socket clientSocket;
    private final BufferedReader bufferedReader;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public FilServidor(Socket clientSocket) throws IOException {
        //client que anem a administrar
        this.clientSocket = clientSocket;

        //fluxos
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

        System.out.println("\n[+] Cree FilServidor");
    }

    @Override
    public void run() {
        String opc = "";
        boolean disconnected = false;

        do {
            try {
                System.out.println("\n[+] En fil, comunique en => " + clientSocket);
                //el client ens mana l'opció que ha escollit
                opc = bufferedReader.readLine();
                switch (Integer.parseInt(opc)) {
                    case 1:
                    case 2:
                        try {
                            //el client ens mana l'objecte de la classe Persona que haurem de modificar
                            Persona persona = (Persona) objectInputStream.readObject();
                            System.out.println("[+] Persona aplegada => " + persona);
                            //modifiquem l'objecte
                            persona.setNom(persona.getNom() + " ha estat al servidor!");
                            System.out.printf("[*] Objecte modificat: %s - %d\n", persona.getNom(), persona.getEdad());
                            //manem al client l'objecte MODIFICAT
                            objectOutputStream.writeObject(persona);
                            System.out.println("[+] Enviat");
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 3:
                        break;
                }
            } catch (IOException e) {
                //per si el client es desconnecta sense ficar l'opció que vol desconnectar-se
                //(s'eixirà del bucle com si hi haguera escrit l'opció "Eixir")
                System.out.println("\n[-] CLIENT DESCONECTAT");
                disconnected = true;
            }
        } while (!opc.equals("3") && !disconnected);

        //tanquem tots els fluxos
        System.out.println("[+] FI AMB " + clientSocket);
        try {
            bufferedReader.close();
            objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
