package co.edu.ude.poo.publicidadradio.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una emisora de radio.
 * Puede ser local o pertenecer a una CadenaRadio.
 * @author Eduard David Barrios Padilla
 */
public class Emisora {
    // Propiedades de la clase
    private String nif;
    private String nombre;
    private String direccionPostal;
    private String director;
    private String bandaHertziana;
    private CadenaRadio cadena; // Opcional
    private List<Programa> programas; // Relación 1 a * con Programa

    /**
     * Constructor por defecto.
     */
    public Emisora() {
        this.programas = new ArrayList<>();
    }

    /**
     * Constructor con parámetros.
     * @param nif NIF de la emisora.
     * @param nombre Nombre de la emisora.
     * @param direccionPostal Dirección de la emisora.
     * @param director Director de la emisora.
     * @param bandaHertziana Banda hertziana en la que opera.
     */
    public Emisora(String nif, String nombre, String direccionPostal, String director, String bandaHertziana) {
        this.nif = nif;
        this.nombre = nombre;
        this.direccionPostal = direccionPostal;
        this.director = director;
        this.bandaHertziana = bandaHertziana;
        this.programas = new ArrayList<>();
    }

    // Métodos Get y Set

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccionPostal() { return direccionPostal; }
    public void setDireccionPostal(String direccionPostal) { this.direccionPostal = direccionPostal; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getBandaHertziana() { return bandaHertziana; }
    public void setBandaHertziana(String bandaHertziana) { this.bandaHertziana = bandaHertziana; }
    public CadenaRadio getCadena() { return cadena; }
    public void setCadena(CadenaRadio cadena) { this.cadena = cadena; }
    public List<Programa> getProgramas() { return programas; }
    public void setProgramas(List<Programa> programas) { this.programas = programas; }

    /**
     * Sobrescritura del método toString.
     * @return una representación textual completa del objeto.
     */
    @Override
    public String toString() {
        return "Emisora{" +
                "nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", director='" + director + '\'' +
                ", bandaHertziana='" + bandaHertziana + '\'' +
                ", cadena=" + (cadena != null ? cadena.getNombre() : "Independiente") +
                '}';
    }
}
