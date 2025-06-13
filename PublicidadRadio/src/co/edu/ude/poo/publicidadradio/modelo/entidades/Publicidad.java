package co.edu.ude.poo.publicidadradio.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la publicidad emitida en un programa.
 * Su costo total se calcula automáticamente.
 * @author Eduard David Barrios Padilla
 */
public class Publicidad {
    // Propiedades de la clase
    private int duracionSegundosSemana;
    private double precioPorSegundo;
    private double costoTotal; // Atributo calculado
    private Programa programa;
    private ContratoPublicidad contrato;

    /**
     * Constructor por defecto.
     */
    public Publicidad() {
    }

    /**
     * Constructor con parámetros.
     * Calcula el costo total al momento de la creación.
     * @param duracionSegundosSemana Duración semanal del anuncio en segundos.
     * @param precioPorSegundo Precio por segundo del espacio.
     * @param programa Programa donde se emite.
     * @param contrato Contrato al que pertenece.
     */
    public Publicidad(int duracionSegundosSemana, double precioPorSegundo, Programa programa, ContratoPublicidad contrato) {
        this.duracionSegundosSemana = duracionSegundosSemana;
        this.precioPorSegundo = precioPorSegundo;
        this.programa = programa;
        this.contrato = contrato;
        calcularCostoTotal(); // Cálculo inicial
    }

    /**
     * Método privado para calcular el costo total de la publicidad.
     * Se invoca cuando cambian los valores que lo afectan.
     */
    private void calcularCostoTotal() {
        this.costoTotal = this.duracionSegundosSemana * this.precioPorSegundo;
    }

    // Métodos Get y Set

    public int getDuracionSegundosSemana() {
        return duracionSegundosSemana;
    }

    public void setDuracionSegundosSemana(int duracionSegundosSemana) {
        this.duracionSegundosSemana = duracionSegundosSemana;
        calcularCostoTotal(); // Recalcular si cambia la duración
    }

    public double getPrecioPorSegundo() {
        return precioPorSegundo;
    }

    public void setPrecioPorSegundo(double precioPorSegundo) {
        this.precioPorSegundo = precioPorSegundo;
        calcularCostoTotal(); // Recalcular si cambia el precio
    }

    /**
     * Retorna el costo total ya calculado.
     * @return el costo total.
     */
    public double getCostoTotal() {
        return costoTotal;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public ContratoPublicidad getContrato() {
        return contrato;
    }

    public void setContrato(ContratoPublicidad contrato) {
        this.contrato = contrato;
    }

    /**
     * Sobrescritura del método toString.
     * @return una representación textual del objeto.
     */
    @Override
    public String toString() {
        return "Publicidad{" +
                "programa='" + (programa != null ? programa.getNombre() : "N/A") + '\'' +
                ", duracionSegundosSemana=" + duracionSegundosSemana +
                ", precioPorSegundo=" + String.format("%.2f", precioPorSegundo) +
                ", costoTotal=" + String.format("%.2f", costoTotal) +
                '}';
    }
}