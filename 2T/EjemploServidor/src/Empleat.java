import java.io.Serializable;

public class Empleat implements Serializable {
    private int id;
    private String nom;
    private String departament;
    private double nivell;

    private static final long serialVersionUID = 2L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public double getNivell() {
        return nivell;
    }

    public void setNivell(double nivell) {
        this.nivell = nivell;
    }

    @Override
    public String toString() {
        return "Empleat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", departament='" + departament + '\'' +
                ", nivell=" + nivell +
                '}';
    }
}
