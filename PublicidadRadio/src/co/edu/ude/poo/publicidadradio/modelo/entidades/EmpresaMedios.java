package co.edu.ude.poo.publicidadradio.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a una empresa de medios de comunicación.
 * Cada empresa puede poseer o administrar una o varias cadenas de radio.
 * @author Eduard David Barrios Padilla
 */
public class EmpresaMedios {
    // Propiedades de la clase
    private String nif;
    private String nombre;
    private String director;
    private String direccionPostal;
    private List<CadenaRadio> cadenas; // Relación 1 a * con CadenaRadio

    /**
     * Constructor por defecto.
     * Inicializa la lista de cadenas de radio.
     */
    public EmpresaMedios() {
        this.cadenas = new ArrayList<>();
    }

    /**
     * Constructor con parámetros para inicializar las propiedades básicas.
     * @param nif             El NIF de la empresa.
     * @param nombre          El nombre de la empresa.
     * @param director        El director de la empresa.
     * @param direccionPostal La dirección postal de la empresa.
     */
    public EmpresaMedios(String nif, String nombre, String director, String direccionPostal) {
        this.nif = nif;
        this.nombre = nombre;
        this.director = director;
        this.direccionPostal = direccionPostal;
        this.cadenas = new ArrayList<>();
    }

    // Métodos Get y Set

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public List<CadenaRadio> getCadenas() {
        return cadenas;
    }

    public void setCadenas(List<CadenaRadio> cadenas) {
        this.cadenas = cadenas;
    }

    /**
     * Sobrescritura del método toString para representar el objeto como una cadena.
     * @return una cadena con la información detallada de la empresa.
     */
    @Override
    public String toString() {
        return "EmpresaMedios{" +
                "nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", director='" + director + '\'' +
                ", direccionPostal='" + direccionPostal + '\'' +
                ", numeroCadenas=" + (cadenas != null ? cadenas.size() : 0) +
                '}';
    }
}