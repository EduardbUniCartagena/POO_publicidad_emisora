package co.edu.ude.poo.publicidadradio.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un patrocinador o cliente.
 * Es la entidad que paga por los servicios de publicidad y firma los contratos.
 * @author Eduard David Barrios Padilla
 */
public class Patrocinador {
    // Propiedades de la clase
    private String nombre;
    private String identificador; // Puede ser NIF, CIF, NIT, etc.
    private List<ContratoPublicidad> contratos;

    /**
     * Constructor por defecto.
     * Inicializa la lista de contratos.
     */
    public Patrocinador() {
        this.contratos = new ArrayList<>();
    }

    /**
     * Constructor con parámetros para inicializar las propiedades básicas.
     * @param nombre         El nombre comercial del patrocinador.
     * @param identificador  El identificador fiscal o comercial del patrocinador.
     */
    public Patrocinador(String nombre, String identificador) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.contratos = new ArrayList<>();
    }

    // --- Métodos Get y Set ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public List<ContratoPublicidad> getContratos() {
        return contratos;
    }

    public void setContratos(List<ContratoPublicidad> contratos) {
        this.contratos = contratos;
    }

    /**
     * Sobrescritura del método toString para una representación textual del objeto.
     * @return una cadena con los detalles del patrocinador.
     */
    @Override
    public String toString() {
        return "Patrocinador{" +
                "nombre='" + nombre + '\'' +
                ", identificador='" + identificador + '\'' +
                ", numeroDeContratos=" + (contratos != null ? contratos.size() : 0) +
                '}';
    }
}
