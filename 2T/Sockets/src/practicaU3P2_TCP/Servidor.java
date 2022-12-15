package practicaU3P2_TCP;

import java.io.*;
import java.net.*;

//SERVIDOR ESPERA CONNEXIONS A LES QUE ATENDRÀ
//SERVIDOR SALUDARÀ AMB LA FRASE "Hola client"
class Servidor {

    // PORT EN EL QUE ESPERA CONNEXIONS
    static final int PORT = 5000;

    public Servidor() {

        try {
            // CREA SOCKET I ESPERA
            ServerSocket ssServidor = new ServerSocket(PORT);
            System.out.println("Escolte al port " + PORT);

            while (true) {
                // METODE accept() CREA UN NOU Socket PER A COMUNICAR-SE AMB EL CLIENT
                Socket sCliente = ssServidor.accept();
                System.out.println("Serveisc al client");

                // CREE ELS FLUXOS D'ENTRADA I EIXIDA PER AL SOCKET
                DataInputStream disEntrada = new DataInputStream(sCliente.getInputStream());
                // ASSOCIE FLUX EIXIDA DE DADES AL SOCKET DEL CLIENT
                DataOutputStream dosEixida = new DataOutputStream(sCliente.getOutputStream());

                String cadena;
                do {
                    cadena = disEntrada.readUTF();
                    System.out.println("Client: " + cadena);
                    switch (cadena) {
                        case "Hola":
                            dosEixida.writeUTF("Hola soc el servidor");
                            break;
                        case "Com estas":
                            dosEixida.writeUTF("Molt be");
                            break;
                        case "Adeu":
                            dosEixida.writeUTF("Fins despres");
                            break;
                        default:
                            dosEixida.writeUTF("Cadena no reconeguda pel servidor");
                            break;
                    }
                } while (!cadena.equals("Adeu"));

                // TANQUE CONNEXIONS
                System.out.println("Tancant connexio...");
                sCliente.close();
                ssServidor.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        new Servidor();
    }
}
