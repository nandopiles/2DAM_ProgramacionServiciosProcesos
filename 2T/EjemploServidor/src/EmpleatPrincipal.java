import java.io.*;

public class EmpleatPrincipal {
    public static void main(String[] args) {
        try {
            Empleat empleatGuardar = new Empleat();
            empleatGuardar.setId(1);
            empleatGuardar.setNom("Nando");
            empleatGuardar.setDepartament("Informática");
            empleatGuardar.setNivell(5.0);

            System.out.printf("Valors de l'objecte a serializar = %d, %s, %s, %f\n",
                    empleatGuardar.getId(), empleatGuardar.getNom(), empleatGuardar.getDepartament(), empleatGuardar.getNivell());

            File file = new File("Serialitzacio_A_Fitxer.txt");
            ObjectOutputStream osStream = new ObjectOutputStream(new FileOutputStream(file));
            osStream.writeObject(empleatGuardar);

            ObjectInputStream isStream = new ObjectInputStream(new FileInputStream(file));
            Empleat empleatRecuperar = (Empleat) isStream.readObject();
            System.out.printf("Valors de l'objecte a deserialitzar = %d, %s, %s, %f\n",
                    empleatRecuperar.getId(), empleatRecuperar.getNom(), empleatRecuperar.getDepartament(), empleatRecuperar.getNivell());

            osStream.close();
            isStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
