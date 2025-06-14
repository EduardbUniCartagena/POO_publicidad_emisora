package co.edu.ude.poo.publicidadradio.modelo.crud;

import co.edu.ude.poo.publicidadradio.modelo.entidades.Publicidad;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar las operaciones CRUD de la entidad Publicidad.
 * NOTA: La clase Publicidad no tiene un ID único. Para este ejercicio, se usará
 * una combinación del número de contrato y el nombre del programa como ID sintético.
 * @author Eduard David Barrios Padilla
 */
public class PublicidadCrud {

    private static final List<Publicidad> publicidades = new ArrayList<>();

    // Método auxiliar para generar un ID sintético
    private String generarId(Publicidad publicidad) {
        return publicidad.getContrato().getNumeroContrato() + "-" + publicidad.getPrograma().getNombre();
    }

    public void agregar(Publicidad publicidad) throws Exception {
        String idSintetico = generarId(publicidad);
        if (publicidades.stream().anyMatch(p -> generarId(p).equals(idSintetico))) {
            throw new Exception("Error: Ya existe una publicidad para ese programa bajo el mismo contrato.");
        }
        publicidades.add(publicidad);
        System.out.println("INFO: Publicidad agregada exitosamente.");
    }

    public Publicidad buscar(String idSintetico) throws Exception {
        return publicidades.stream()
                .filter(p -> generarId(p).equals(idSintetico))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: No se encontró una publicidad con el ID '" + idSintetico + "'"));
    }

    public void editar(Publicidad publicidadModificada) throws Exception {
        Publicidad publicidadExistente = buscar(generarId(publicidadModificada));
        publicidadExistente.setDuracionSegundosSemana(publicidadModificada.getDuracionSegundosSemana());
        publicidadExistente.setPrecioPorSegundo(publicidadModificada.getPrecioPorSegundo());
        System.out.println("INFO: Publicidad editada exitosamente.");
    }

    public void eliminar(String idSintetico) throws Exception {
        Publicidad publicidadAEliminar = buscar(idSintetico);
        publicidades.remove(publicidadAEliminar);
        System.out.println("INFO: Publicidad eliminada exitosamente.");
    }

    public List<Publicidad> listarTodo() throws Exception {
        if (publicidades.isEmpty()) {
            throw new Exception("Error: No hay publicidades para listar.");
        }
        return publicidades;
    }

    public int contar() {
        return publicidades.size();
    }
}
