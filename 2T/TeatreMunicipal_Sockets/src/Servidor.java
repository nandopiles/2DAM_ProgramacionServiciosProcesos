import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Servidor {
    private static final int PORT = 5000;
    private static int gal_seients = 8, lat1_seients = 4, lat2_seients = 4, cen_seients = 54, vip1_seients = 3, vip2_seients = 3;

    /**
     * @return la informació de les butaques
     */
    private static String infoButaques() {
        int gal_preu = 150, lat1_preu = 100, lat2_preu = 100, cen_preu = 80, vip1_preu = 250, vip2_preu = 250;
        return "\n\tTipus butaca: VIP1. Preu: " + vip1_preu + "€. Disponibles: " + vip1_seients + ".\n" +
                "\tTipus butaca: VIP2. Preu: " + vip2_preu + "€. Disponibles: " + vip2_seients + ".\n" +
                "\tTipus butaca: LAT1. Preu: " + lat1_preu + "€. Disponibles: " + lat1_seients + ".\n" +
                "\tTipus butaca: LAT2. Preu: " + lat2_preu + "€. Disponibles: " + lat2_seients + ".\n" +
                "\tTipus butaca: CEN. Preu: " + cen_preu + "€. Disponibles: " + cen_seients + ".\n" +
                "\tTipus butaca: GAL. Preu: " + gal_preu + "€. Disponibles: " + gal_seients + ".\n";
    }

    public Servidor() throws IOException {
        Socket sCliente;
        DataInputStream disEntrada;
        DataOutputStream dosEixida;
        String cadena;
        boolean primeraVegada = true, disponibles;

        ServerSocket ssServidor = new ServerSocket(PORT);
        System.out.printf("(*) Escoltant al port %d...\n", PORT);
        while (true) {
            sCliente = ssServidor.accept();
            System.out.println("(+) Serveisc al client");

            disEntrada = new DataInputStream(sCliente.getInputStream());
            dosEixida = new DataOutputStream(sCliente.getOutputStream());

            do {
                if (primeraVegada) {
                    do {
                        cadena = disEntrada.readUTF();
                        if (!Objects.equals(cadena, "Hola")) {
                            dosEixida.writeUTF("(-) Primer hi ha que saludar MALCRIAT...");
                        }
                    } while (!Objects.equals(cadena, "Hola"));
                } else {
                    cadena = disEntrada.readUTF();
                }
                primeraVegada = false;
                System.out.printf("(Client): %s\n", cadena); //imprimix lo que diga el Client
                switch (cadena) {
                    case "Hola":
                        dosEixida.writeUTF("(+) Hola, sóc el servidor");
                        break;
                    case "Veure butaques":
                        dosEixida.writeUTF(infoButaques());
                        break;
                    case "VIP1":
                        if (vip1_seients > 0) {
                            --vip1_seients;
                            dosEixida.writeUTF("(+) Reserva " + cadena + " " + vip1_seients);
                        } else
                            dosEixida.writeUTF("(-) Butaques esgotades per a " + cadena);
                        break;
                    case "VIP2":
                        if (vip2_seients > 0) {
                            --vip2_seients;
                            dosEixida.writeUTF("(+) Reserva " + cadena + " " + vip2_seients);
                        } else
                            dosEixida.writeUTF("(-) Butaques esgotades per a " + cadena);
                        break;
                    case "LAT1":
                        if (lat1_seients > 0) {
                            --lat1_seients;
                            dosEixida.writeUTF("(+) Reserva " + cadena + " " + lat1_seients);
                        } else
                            dosEixida.writeUTF("(-) Butaques esgotades per a " + cadena);
                        break;
                    case "LAT2":
                        if (lat2_seients > 0) {
                            --lat2_seients;
                            dosEixida.writeUTF("(+) Reserva " + cadena + " " + lat2_seients);
                        } else
                            dosEixida.writeUTF("(-) Butaques esgotades per a " + cadena);
                        break;
                    case "CEN":
                        if (cen_seients > 0) {
                            --cen_seients;
                            dosEixida.writeUTF("(+) Reserva " + cadena + " " + cen_seients);
                        } else
                            dosEixida.writeUTF("(-) Butaques esgotades per a " + cadena);
                        break;
                    case "GAL":
                        if (gal_seients > 0) {
                            --gal_seients;
                            dosEixida.writeUTF("(+) Reserva " + cadena + " " + gal_seients);
                        } else
                            dosEixida.writeUTF("(-) Butaques esgotades per a " + cadena);
                        break;
                    case "Aw":
                        dosEixida.writeUTF("(+) Aw");
                        break;
                    default:
                        dosEixida.writeUTF("(-) Cadena no reconeguda pel servidor...");
                        break;
                }
            } while (!Objects.equals(cadena, "Aw"));
        }
    }

    public static void main(String[] args) throws IOException {
        new Servidor();
    }
}
