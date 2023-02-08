import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Nando
 */
public class Client {
    static Scanner eb = new Scanner(System.in);
    static Scanner fr = new Scanner(System.in);

    /**
     * Menú
     */
    public static String menu() {
        String opc;

        do {
            System.out.print(
                    "\n1. Enviar Persona Default\n" +
                            "2. Enviar Persona Personalitzada\n" +
                            "3. Eixir\n==================\n=> "
            );
            opc = eb.nextLine();
            if (!opc.equals("1") && !opc.equals("2") && !opc.equals("3"))
                System.out.println("[-] Opc no vàlida");
        } while (!opc.equals("1") && !opc.equals("2") && !opc.equals("3"));

        return opc;
    }

    public static void main(String[] args) {
        String opc;

        try {
            //se connectem al Servidor
            Socket clientSocket = new Socket("localhost", 60000);
            System.out.println("[+] Connectat");
            //enllacem tots els fluxos en el servidor
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            do {
                Persona persona, personaRebuda;
                //elegirem una opció del menú
                opc = menu();
                //enviarem l'opció al Servidor perquè sàpiga qué fer
                printWriter.println(opc);
                //depenent de l'opció el client farà una cosa o altra
                switch (Integer.parseInt(opc)) {
                    case 1:
                        //creem un objecte de la classe persona
                        persona = new Persona("Pepe", 25);
                        System.out.printf("=> Objecte a enviar: %s - %d\n", persona.getNom(), persona.getEdad());
                        //buidem el buffer
                        objectOutputStream.flush();
                        //li passem al servidor l'objecte
                        objectOutputStream.writeObject(persona);
                        System.out.println("[+] Enviat");

                        //rebem un objecte de la classe Persona que ens ha manat el servidor
                        personaRebuda = (Persona) objectInputStream.readObject();
                        System.out.printf("=> Objecte rebut: %s - %d\n", personaRebuda.getNom(), personaRebuda.getEdad());
                        break;
                    case 2:
                        //omplim les dades de l'objecte a crear
                        System.out.print("Nom => ");
                        String nom = eb.nextLine();
                        System.out.print("Edad => ");
                        int edad = fr.nextInt();

                        //creem l'objecte
                        persona = new Persona(nom, edad);
                        System.out.printf("=> Objecte a enviar: %s - %d\n", persona.getNom(), persona.getEdad());
                        //buidem el buffer
                        objectOutputStream.flush();
                        //pasem l'objecte
                        objectOutputStream.writeObject(persona);
                        System.out.println("[+] Enviat");

                        //rebem l'objecte del servidor
                        personaRebuda = (Persona) objectInputStream.readObject();
                        System.out.printf("=> Objecte rebut: %s - %d\n", personaRebuda.getNom(), personaRebuda.getEdad());
                        break;
                    case 3:
                        break;
                }
            } while (!(opc.contentEquals("3"))); //mentrestant no escollim l'opció "Eixir" no eixirem

            //hem eixit així que tanquem tot
            System.out.println("[+] Fi comunicació");
            printWriter.close();
            objectOutputStream.close();
            objectInputStream.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
