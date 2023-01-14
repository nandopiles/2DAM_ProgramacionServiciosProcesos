import java.io.Serializable;

public class Vehicul implements Serializable {
    private String dniClient;
    private String matricula;
    private String marca;
    private String model;
    private String combustible;
    private int any;
    private static final long serialVersionUID = 1993L;

    public Vehicul(String dniClient, String matricula, String marca, String model, String combustible, int any) {
        this.dniClient = dniClient;
        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
        this.combustible = combustible;
        this.any = any;
    }

    public void setDniClient(String dniClient) {
        this.dniClient = dniClient;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getDniClient() {
        return dniClient;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public String getCombustible() {
        return combustible;
    }

    public int getAny() {
        return any;
    }

    @Override
    public String toString() {
        return "{ dniClient = " + dniClient +
                ", matricula = " + matricula +
                ", marca = " + marca +
                ", model = " + model +
                ", combustible = " + combustible +
                ", any = " + any +
                " }";
    }
}
