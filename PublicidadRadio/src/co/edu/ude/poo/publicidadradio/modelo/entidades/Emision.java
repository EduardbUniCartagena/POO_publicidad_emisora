package co.edu.ude.poo.publicidadradio.modelo.entidades;

/**
 * Clase que representa una emisión concreta de un programa.
 * Registra cuándo se transmitió un programa.
 * @author Eduard David Barrios Padilla
 */
public class Emision {
    // Propiedades de la clase
    private String diaSemana;
    private String horaInicio;
    private int duracionMinutos;
    private Programa programa;

    /**
     * Constructor por defecto.
     */
    public Emision() {
    }

    /**
     * Constructor con parámetros.
     * @param diaSemana Día de la semana de la emisión.
     * @param horaInicio Hora de inicio de la emisión.
     * @param duracionMinutos Duración real de la emisión en minutos.
     * @param programa El programa que fue emitido.
     */
    public Emision(String diaSemana, String horaInicio, int duracionMinutos, Programa programa) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.duracionMinutos = duracionMinutos;
        this.programa = programa;
    }

    // Métodos Get y Set

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public Programa getPrograma() { return programa; }
    public void setPrograma(Programa programa) { this.programa = programa; }

    /**
     * Sobrescritura del método toString.
     * @return una cadena con los detalles de la emisión.
     */
    @Override
    public String toString() {
        return "Emision{" +
                "programa=" + (programa != null ? programa.getNombre() : "N/A") +
                ", diaSemana='" + diaSemana + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                '}';
    }
}
