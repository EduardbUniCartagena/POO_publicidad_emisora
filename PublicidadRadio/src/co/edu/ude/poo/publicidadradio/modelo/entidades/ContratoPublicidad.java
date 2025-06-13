package co.edu.ude.poo.publicidadradio.modelo.entidades;


import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un contrato de publicidad.
 * Establece la relación entre un patrocinador y los espacios publicitarios.
 * @author Eduard David Barrios Padilla
 */
public class ContratoPublicidad {
    // Propiedades de la clase
    private String numeroContrato;
    private Patrocinador patrocinador;
    private int duracionSemanas;
    private double importeTotal;
    private List<Publicidad> publicidades;

    /**
     * Constructor por defecto.
     */
    public ContratoPublicidad() {
        this.publicidades = new ArrayList<>();
    }

    /**
     * Constructor con parámetros.
     * @param numeroContrato Identificador único del contrato.
     * @param patrocinador El patrocinador asociado a este contrato.
     * @param duracionSemanas Duración del contrato en semanas.
     * @param importeTotal Importe monetario total del contrato.
     */
    public ContratoPublicidad(String numeroContrato, Patrocinador patrocinador, int duracionSemanas, double importeTotal) {
        this.numeroContrato = numeroContrato;
        this.patrocinador = patrocinador;
        this.duracionSemanas = duracionSemanas;
        this.importeTotal = importeTotal;
        this.publicidades = new ArrayList<>();
    }

    // Métodos Get y Set

    public String getNumeroContrato() { return numeroContrato; }
    public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }
    public Patrocinador getPatrocinador() { return patrocinador; }
    public void setPatrocinador(Patrocinador patrocinador) { this.patrocinador = patrocinador; }
    public int getDuracionSemanas() { return duracionSemanas; }
    public void setDuracionSemanas(int duracionSemanas) { this.duracionSemanas = duracionSemanas; }
    public double getImporteTotal() { return importeTotal; }
    public void setImporteTotal(double importeTotal) { this.importeTotal = importeTotal; }
    public List<Publicidad> getPublicidades() { return publicidades; }
    public void setPublicidades(List<Publicidad> publicidades) { this.publicidades = publicidades; }

    /**
     * Sobrescritura del método toString.
     * @return una cadena con los detalles del contrato.
     */
    @Override
    public String toString() {
        return "ContratoPublicidad{" +
                "numeroContrato='" + numeroContrato + '\'' +
                ", patrocinador=" + (patrocinador != null ? patrocinador.getNombre() : "N/A") +
                ", importeTotal=" + String.format("%.2f", importeTotal) +
                '}';
    }
}