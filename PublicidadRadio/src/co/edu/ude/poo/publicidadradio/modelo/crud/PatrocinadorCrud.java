package co.edu.ude.poo.publicidadradio.modelo.crud;

import co.edu.ude.poo.publicidadradio.modelo.entidades.Patrocinador;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar las operaciones CRUD de la entidad Patrocinador.
 * Utiliza una lista estática para simular una base de datos en memoria.
 * @author Eduard David Barrios Padilla
 */
public class PatrocinadorCrud {

    // Lista estática para almacenar los patrocinadores.
    private static final List<Patrocinador> patrocinadores = new ArrayList<>();

    /**
     * Agrega un nuevo patrocinador a la lista si no existe uno con el mismo identificador.
     * @param patrocinador El objeto Patrocinador a agregar.
     * @throws Exception si ya existe un patrocinador con ese identificador.
     */
    public void agregar(Patrocinador patrocinador) throws Exception {
        // Verificamos si ya existe un patrocinador con el mismo identificador
        if (patrocinadores.stream().anyMatch(p -> p.getIdentificador().equals(patrocinador.getIdentificador()))) {
            throw new Exception("Error: Ya existe un patrocinador con el identificador " + patrocinador.getIdentificador());
        }
        patrocinadores.add(patrocinador);
        System.out.println("INFO: Patrocinador agregado exitosamente.");
    }

    /**
     * Busca un patrocinador por su identificador.
     * @param identificador El identificador (String) del patrocinador a buscar.
     * @return El objeto Patrocinador si se encuentra.
     * @throws Exception si no se encuentra ningún patrocinador con ese identificador.
     */
    public Patrocinador buscar(String identificador) throws Exception {
        return patrocinadores.stream()
                .filter(p -> p.getIdentificador().equals(identificador))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: No se encontró un patrocinador con el identificador " + identificador));
    }

    /**
     * Edita un patrocinador existente en la lista.
     * @param patrocinadorModificado El objeto Patrocinador con los datos actualizados.
     * @throws Exception si el patrocinador a editar no se encuentra en la lista.
     */
    public void editar(Patrocinador patrocinadorModificado) throws Exception {
        Patrocinador patrocinadorExistente = buscar(patrocinadorModificado.getIdentificador());
        // Actualizamos los datos del patrocinador existente
        patrocinadorExistente.setNombre(patrocinadorModificado.getNombre());
        // Se podrían actualizar más campos si los hubiera...
        System.out.println("INFO: Patrocinador editado exitosamente.");
    }

    /**
     * Elimina un patrocinador de la lista por su identificador.
     * @param identificador El identificador (String) del patrocinador a eliminar.
     * @throws Exception si el patrocinador a eliminar no existe.
     */
    public void eliminar(String identificador) throws Exception {
        Patrocinador patrocinadorAEliminar = buscar(identificador);
        patrocinadores.remove(patrocinadorAEliminar);
        System.out.println("INFO: Patrocinador eliminado exitosamente.");
    }

    /**
     * Devuelve la lista completa de patrocinadores.
     * @return Una lista de todos los patrocinadores.
     * @throws Exception si la lista está vacía.
     */
    public List<Patrocinador> listarTodo() throws Exception {
        if (patrocinadores.isEmpty()) {
            throw new Exception("Error: No hay patrocinadores para listar.");
        }
        return patrocinadores;
    }

    /**
     * Cuenta el número total de patrocinadores en la lista.
     * @return El número de patrocinadores.
     */
    public int contar() {
        return patrocinadores.size();
    }
}