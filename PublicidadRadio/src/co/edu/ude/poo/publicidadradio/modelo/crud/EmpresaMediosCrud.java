package co.edu.ude.poo.publicidadradio.modelo.crud;

import co.edu.ude.poo.publicidadradio.modelo.entidades.EmpresaMedios;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * de la entidad EmpresaMedios. Simula una base de datos en memoria usando
 * una lista estática.
 * @author Eduard David Barrios Padilla
 */
public class EmpresaMediosCrud {

    private static final List<EmpresaMedios> empresas = new ArrayList<>();

    /**
     * Agrega una nueva empresa de medios a la lista.
     * No permite agregar empresas si ya existe una con el mismo NIF.
     * @param empresa El objeto EmpresaMedios que se va a agregar.
     * @throws Exception Si ya existe una empresa con el mismo NIF.
     */
    public void agregar(EmpresaMedios empresa) throws Exception {
        if (empresas.stream().anyMatch(e -> e.getNif().equals(empresa.getNif()))) {
            throw new Exception("Error: Ya existe una empresa con el NIF " + empresa.getNif());
        }
        empresas.add(empresa);
        System.out.println("INFO: Empresa de medios agregada exitosamente.");
    }

    /**
     * Busca una empresa de medios en la lista por su NIF.
     * @param nif El identificador (String) de la empresa a buscar.
     * @return El objeto EmpresaMedios si se encuentra.
     * @throws Exception Si no se encuentra ninguna empresa con ese NIF.
     */
    public EmpresaMedios buscar(String nif) throws Exception {
        return empresas.stream()
                .filter(e -> e.getNif().equals(nif))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: No se encontró una empresa con el NIF " + nif));
    }

    /**
     * Actualiza los datos de una empresa existente en la lista.
     * Busca la empresa por su NIF y actualiza sus propiedades.
     * @param empresaModificada El objeto EmpresaMedios con los datos ya actualizados.
     * @throws Exception Si la empresa a editar no se encuentra en la lista.
     */
    public void editar(EmpresaMedios empresaModificada) throws Exception {
        EmpresaMedios empresaExistente = buscar(empresaModificada.getNif());
        empresaExistente.setNombre(empresaModificada.getNombre());
        empresaExistente.setDirector(empresaModificada.getDirector());
        empresaExistente.setDireccionPostal(empresaModificada.getDireccionPostal());
        System.out.println("INFO: Empresa de medios editada exitosamente.");
    }

    /**
     * Elimina una empresa de la lista usando su NIF.
     * @param nif El identificador (String) de la empresa que se va a eliminar.
     * @throws Exception Si la empresa a eliminar no existe en la lista.
     */
    public void eliminar(String nif) throws Exception {
        EmpresaMedios empresaAEliminar = buscar(nif);
        empresas.remove(empresaAEliminar);
        System.out.println("INFO: Empresa de medios eliminada exitosamente.");
    }

    /**
     * Devuelve la lista completa de todas las empresas registradas.
     * @return Una lista (List) de objetos EmpresaMedios.
     * @throws Exception Si la lista está vacía.
     */
    public List<EmpresaMedios> listarTodo() throws Exception {
        if (empresas.isEmpty()) {
            throw new Exception("Error: No hay empresas de medios para listar.");
        }
        return empresas;
    }

    /**
     * Retorna la cantidad total de empresas que hay en la lista.
     * @return Un valor entero (int) con el número de empresas.
     */
    public int contar() {
        return empresas.size();
    }
}