package co.edu.ude.poo.publicidadradio.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una cadena de radio.
 * Agrupa varias emisoras y pertenece a una EmpresaMedios.
 * @author Eduard David Barrios Padilla
 */
public class CadenaRadio {
    // Propiedades de la clase
    private String nombre;
    private Emisora sedeCentral;
    private String director;
    private EmpresaMedios empresaMedios;
    private List<Emisora> emisoras; // Relación 1 a * con Emisora

    /**
     * Constructor por defecto.
     */
    public CadenaRadio() {
        this.emisoras = new ArrayList<>();
    }

    /**
     * Constructor con parámetros.
     * @param nombre         Nombre de la cadena.
     * @param sedeCentral    Emisora que funciona como sede central.
     * @param director       Director de la cadena.
     * @param empresaMedios  Empresa de medios a la que pertenece.
     */
    public CadenaRadio(String nombre, Emisora sedeCentral, String director, EmpresaMedios empresaMedios) {
        this.nombre = nombre;
        this.sedeCentral = sedeCentral;
        this.director = director;
        this.empresaMedios = empresaMedios;
        this.emisoras = new ArrayList<>();
    }

    // Métodos Get y Set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Emisora getSedeCentral() {
        return sedeCentral;
    }

    public void setSedeCentral(Emisora sedeCentral) {
        this.sedeCentral = sedeCentral;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public EmpresaMedios getEmpresaMedios() {
        return empresaMedios;
    }

    public void setEmpresaMedios(EmpresaMedios empresaMedios) {
        this.empresaMedios = empresaMedios;
    }

    public List<Emisora> getEmisoras() {
        return emisoras;
    }

    public void setEmisoras(List<Emisora> emisoras) {
        this.emisoras = emisoras;
    }

    /**
     * Sobrescritura del método toString.
     * @return una cadena con la información detallada de la cadena de radio.
     */
    @Override
    public String toString() {
        return "CadenaRadio{" +
                "nombre='" + nombre + '\'' +
                ", sedeCentral=" + (sedeCentral != null ? sedeCentral.getNombre() : "N/A") +
                ", director='" + director + '\'' +
                ", empresaMedios=" + (empresaMedios != null ? empresaMedios.getNombre() : "N/A") +
                ", numeroEmisoras=" + (emisoras != null ? emisoras.size() : 0) +
                '}';
    }
}