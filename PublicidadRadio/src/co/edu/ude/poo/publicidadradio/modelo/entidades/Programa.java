package co.edu.ude.poo.publicidadradio.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un programa de radio.
 * Puede ser emitido por una emisora específica o por toda una cadena.
 * Contiene los espacios de publicidad.
 * @author Eduard David Barrios Padilla
 */
public class Programa {
    // Propiedades de la clase
    private String nombre;
    private String responsable;
    private String diaSemana;
    private String horaInicio;
    private int duracionMinutos;
    private Emisora emisora; // Opcional, si el programa es solo de una emisora
    private CadenaRadio cadena; // Opcional, si el programa es para toda la cadena
    private List<Publicidad> publicidades;

    /**
     * Constructor por defecto.
     */
    public Programa() {
        this.publicidades = new ArrayList<>();
    }

    /**
     * Constructor con parámetros.
     * @param nombre Nombre del programa.
     * @param responsable Persona responsable del programa.
     * @param diaSemana Días de la semana en que se emite.
     * @param horaInicio Hora de inicio del programa.
     * @param duracionMinutos Duración total en minutos.
     */
    public Programa(String nombre, String responsable, String diaSemana, String horaInicio, int duracionMinutos) {
        this.nombre = nombre;
        this.responsable = responsable;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.duracionMinutos = duracionMinutos;
        this.publicidades = new ArrayList<>();
    }

    // Métodos Get y Set

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public Emisora getEmisora() { return emisora; }
    public void setEmisora(Emisora emisora) { this.emisora = emisora; }
    public CadenaRadio getCadena() { return cadena; }
    public void setCadena(CadenaRadio cadena) { this.cadena = cadena; }
    public List<Publicidad> getPublicidades() { return publicidades; }
    public void setPublicidades(List<Publicidad> publicidades) { this.publicidades = publicidades; }

    /**
     * Sobrescritura del método toString para una representación textual.
     * @return una cadena con los detalles del programa.
     */
    @Override
    public String toString() {
        return "Programa{" +
                "nombre='" + nombre + '\'' +
                ", responsable='" + responsable + '\'' +
                ", diaSemana='" + diaSemana + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                '}';
    }
}