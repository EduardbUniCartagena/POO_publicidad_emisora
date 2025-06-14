package co.edu.ude.poo.publicidadradio.modelo.crud;

import co.edu.ude.poo.publicidadradio.modelo.entidades.ContratoPublicidad;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * de la entidad ContratoPublicidad. Simula una base de datos en memoria usando
 * una lista estática.
 * @author Eduard David Barrios Padilla
 */
public class ContratoPublicidadCrud {

    private static final List<ContratoPublicidad> contratos = new ArrayList<>();

    /**
     * Agrega un nuevo contrato a la lista.
     * No permite agregar contratos si ya existe uno con el mismo número de contrato.
     * @param contrato El objeto ContratoPublicidad que se va a agregar.
     * @throws Exception Si ya existe un contrato con el mismo número.
     */
    public void agregar(ContratoPublicidad contrato) throws Exception {
        if (contratos.stream().anyMatch(c -> c.getNumeroContrato().equals(contrato.getNumeroContrato()))) {
            throw new Exception("Error: Ya existe un contrato con el número " + contrato.getNumeroContrato());
        }
        contratos.add(contrato);
        System.out.println("INFO: Contrato agregado exitosamente.");
    }

    /**
     * Busca un contrato en la lista por su número de contrato.
     * @param numeroContrato El identificador (String) del contrato a buscar.
     * @return El objeto ContratoPublicidad si se encuentra.
     * @throws Exception Si no se encuentra ningún contrato con ese número.
     */
    public ContratoPublicidad buscar(String numeroContrato) throws Exception {
        return contratos.stream()
                .filter(c -> c.getNumeroContrato().equals(numeroContrato))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: No se encontró un contrato con el número " + numeroContrato));
    }

    /**
     * Actualiza los datos de un contrato existente en la lista.
     * Busca el contrato por su número y actualiza sus propiedades.
     * @param contratoModificado El objeto ContratoPublicidad con los datos ya actualizados.
     * @throws Exception Si el contrato a editar no se encuentra en la lista.
     */
    public void editar(ContratoPublicidad contratoModificado) throws Exception {
        ContratoPublicidad contratoExistente = buscar(contratoModificado.getNumeroContrato());
        contratoExistente.setPatrocinador(contratoModificado.getPatrocinador());
        contratoExistente.setDuracionSemanas(contratoModificado.getDuracionSemanas());
        contratoExistente.setImporteTotal(contratoModificado.getImporteTotal());
        System.out.println("INFO: Contrato editado exitosamente.");
    }

    /**
     * Elimina un contrato de la lista usando su número de contrato.
     * @param numeroContrato El identificador (String) del contrato que se va a eliminar.
     * @throws Exception Si el contrato a eliminar no existe en la lista.
     */
    public void eliminar(String numeroContrato) throws Exception {
        ContratoPublicidad contratoAEliminar = buscar(numeroContrato);
        contratos.remove(contratoAEliminar);
        System.out.println("INFO: Contrato eliminado exitosamente.");
    }

    /**
     * Devuelve la lista completa de todos los contratos registrados.
     * @return Una lista (List) de objetos ContratoPublicidad.
     * @throws Exception Si la lista está vacía.
     */
    public List<ContratoPublicidad> listarTodo() throws Exception {
        if (contratos.isEmpty()) {
            throw new Exception("Error: No hay contratos para listar.");
        }
        return contratos;
    }

    /**
     * Retorna la cantidad total de contratos que hay en la lista.
     * @return Un valor entero (int) con el número de contratos.
     */
    public int contar() {
        return contratos.size();
    }
}