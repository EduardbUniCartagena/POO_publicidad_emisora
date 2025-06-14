package co.edu.ude.poo.publicidadradio.modelo.crud;

import co.edu.ude.poo.publicidadradio.modelo.entidades.Programa;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar las operaciones CRUD de la entidad Programa.
 * @author Eduard David Barrios Padilla
 */
public class ProgramaCrud {

    private static final List<Programa> programas = new ArrayList<>();

    public void agregar(Programa programa) throws Exception {
        if (programas.stream().anyMatch(p -> p.getNombre().equals(programa.getNombre()))) {
            throw new Exception("Error: Ya existe un programa con el nombre " + programa.getNombre());
        }
        programas.add(programa);
        System.out.println("INFO: Programa agregado exitosamente.");
    }

    public Programa buscar(String nombre) throws Exception {
        return programas.stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: No se encontró un programa con el nombre " + nombre));
    }

    public void editar(Programa programaModificado) throws Exception {
        Programa programaExistente = buscar(programaModificado.getNombre());
        programaExistente.setResponsable(programaModificado.getResponsable());
        programaExistente.setDiaSemana(programaModificado.getDiaSemana());
        programaExistente.setHoraInicio(programaModificado.getHoraInicio());
        programaExistente.setDuracionMinutos(programaModificado.getDuracionMinutos());
        System.out.println("INFO: Programa editado exitosamente.");
    }

    public void eliminar(String nombre) throws Exception {
        Programa programaAEliminar = buscar(nombre);
        programas.remove(programaAEliminar);
        System.out.println("INFO: Programa eliminado exitosamente.");
    }

    public List<Programa> listarTodo() throws Exception {
        if (programas.isEmpty()) {
            throw new Exception("Error: No hay programas para listar.");
        }
        return programas;
    }

    public int contar() {
        return programas.size();
    }
}