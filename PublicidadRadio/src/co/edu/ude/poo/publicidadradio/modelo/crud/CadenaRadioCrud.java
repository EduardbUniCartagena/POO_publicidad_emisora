package co.edu.ude.poo.publicidadradio.modelo.crud;

import co.edu.ude.poo.publicidadradio.modelo.entidades.CadenaRadio;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar las operaciones CRUD de la entidad CadenaRadio.
 * @author Eduard David Barrios Padilla
 */
public class CadenaRadioCrud {

    private static final List<CadenaRadio> cadenas = new ArrayList<>();

    public void agregar(CadenaRadio cadena) throws Exception {
        if (cadenas.stream().anyMatch(c -> c.getNombre().equals(cadena.getNombre()))) {
            throw new Exception("Error: Ya existe una cadena de radio con el nombre " + cadena.getNombre());
        }
        cadenas.add(cadena);
        System.out.println("INFO: Cadena de radio agregada exitosamente.");
    }

    public CadenaRadio buscar(String nombre) throws Exception {
        return cadenas.stream()
                .filter(c -> c.getNombre().equals(nombre))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: No se encontró una cadena de radio con el nombre " + nombre));
    }

    public void editar(CadenaRadio cadenaModificada) throws Exception {
        CadenaRadio cadenaExistente = buscar(cadenaModificada.getNombre());
        cadenaExistente.setDirector(cadenaModificada.getDirector());
        cadenaExistente.setSedeCentral(cadenaModificada.getSedeCentral());
        cadenaExistente.setEmpresaMedios(cadenaModificada.getEmpresaMedios());
        System.out.println("INFO: Cadena de radio editada exitosamente.");
    }

    public void eliminar(String nombre) throws Exception {
        CadenaRadio cadenaAEliminar = buscar(nombre);
        cadenas.remove(cadenaAEliminar);
        System.out.println("INFO: Cadena de radio eliminada exitosamente.");
    }

    public List<CadenaRadio> listarTodo() throws Exception {
        if (cadenas.isEmpty()) {
            throw new Exception("Error: No hay cadenas de radio para listar.");
        }
        return cadenas;
    }

    public int contar() {
        return cadenas.size();
    }
}