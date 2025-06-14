package co.edu.ude.poo.publicidadradio.modelo.crud;

import co.edu.ude.poo.publicidadradio.modelo.entidades.Emisora;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * de la entidad Emisora. Simula una base de datos en memoria usando
 * una lista estática.
 * @author Eduard David Barrios Padilla
 */
public class EmisoraCrud {

    private static final List<Emisora> emisoras = new ArrayList<>();

    /**
     * Agrega una nueva emisora a la lista.
     * No permite agregar emisoras si ya existe una con el mismo NIF.
     * @param emisora El objeto Emisora que se va a agregar.
     * @throws Exception Si ya existe una emisora con el mismo NIF.
     */
    public void agregar(Emisora emisora) throws Exception {
        // La llamada correcta es getNif() con 'n' minúscula
        if (emisoras.stream().anyMatch(e -> e.getNif().equals(emisora.getNif()))) {
            throw new Exception("Error: Ya existe una emisora con el NIF " + emisora.getNif());
        }
        emisoras.add(emisora);
        System.out.println("INFO: Emisora agregada exitosamente.");
    }

    /**
     * Busca una emisora en la lista por su NIF.
     * @param nif El identificador (String) de la emisora a buscar.
     * @return El objeto Emisora si se encuentra.
     * @throws Exception Si no se encuentra ninguna emisora con ese NIF.
     */
    public Emisora buscar(String nif) throws Exception {
        return emisoras.stream()
                .filter(e -> e.getNif().equals(nif))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: No se encontró una emisora con el NIF " + nif));
    }

    /**
     * Actualiza los datos de una emisora existente en la lista.
     * Busca la emisora por su NIF y actualiza sus propiedades.
     * @param emisoraModificada El objeto Emisora con los datos ya actualizados.
     * @throws Exception Si la emisora a editar no se encuentra en la lista.
     */
    public void editar(Emisora emisoraModificada) throws Exception {
        Emisora emisoraExistente = buscar(emisoraModificada.getNif());
        emisoraExistente.setNombre(emisoraModificada.getNombre());
        emisoraExistente.setDirector(emisoraModificada.getDirector());
        emisoraExistente.setDireccionPostal(emisoraModificada.getDireccionPostal());
        emisoraExistente.setBandaHertziana(emisoraModificada.getBandaHertziana());
        System.out.println("INFO: Emisora editada exitosamente.");
    }

    /**
     * Elimina una emisora de la lista usando su NIF.
     * @param nif El identificador (String) de la emisora que se va a eliminar.
     * @throws Exception Si la emisora a eliminar no existe en la lista.
     */
    public void eliminar(String nif) throws Exception {
        Emisora emisoraAEliminar = buscar(nif);
        emisoras.remove(emisoraAEliminar);
        System.out.println("INFO: Emisora eliminada exitosamente.");
    }

    /**
     * Devuelve la lista completa de todas las emisoras registradas.
     * @return Una lista (List) de objetos Emisora.
     * @throws Exception Si la lista está vacía.
     */
    public List<Emisora> listarTodo() throws Exception {
        if (emisoras.isEmpty()) {
            throw new Exception("Error: No hay emisoras para listar.");
        }
        return emisoras;
    }

    /**
     * Retorna la cantidad total de emisoras que hay en la lista.
     * @return Un valor entero (int) con el número de emisoras.
     */
    public int contar() {
        return emisoras.size();
    }
}