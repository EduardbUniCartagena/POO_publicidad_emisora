package co.edu.ude.poo.publicidadradio.modelo.crud;

import co.edu.ude.poo.publicidadradio.modelo.entidades.Emision;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase para gestionar las operaciones CRUD de la entidad Emision.
 * NOTA: La clase Emision no tiene un ID único. Para este ejercicio, se usará
 * una combinación del nombre del programa y la hora de inicio como ID sintético.
 * @author Eduard David Barrios Padilla
 */
public class EmisionCrud {

    private static final List<Emision> emisiones = new ArrayList<>();

    // Método auxiliar para generar el ID sintético
    private String generarId(Emision emision) {
        return emision.getPrograma().getNombre() + "-" + emision.getHoraInicio();
    }

    public void agregar(Emision emision) throws Exception {
        String idSintetico = generarId(emision);
        if (emisiones.stream().anyMatch(e -> generarId(e).equals(idSintetico))) {
            throw new Exception("Error: Ya existe una emisión para el programa '" + emision.getPrograma().getNombre() + "' a las " + emision.getHoraInicio());
        }
        emisiones.add(emision);
        System.out.println("INFO: Emisión agregada exitosamente.");
    }

    public Emision buscar(String idSintetico) throws Exception {
        return emisiones.stream()
                .filter(e -> generarId(e).equals(idSintetico))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: No se encontró una emisión con el ID '" + idSintetico + "'"));
    }

    public void editar(Emision emisionModificada) throws Exception {
        Emision emisionExistente = buscar(generarId(emisionModificada));
        emisionExistente.setDiaSemana(emisionModificada.getDiaSemana());
        emisionExistente.setDuracionMinutos(emisionModificada.getDuracionMinutos());
        // El programa y la hora de inicio no se deberían cambiar, ya que forman el ID.
        System.out.println("INFO: Emisión editada exitosamente.");
    }

    public void eliminar(String idSintetico) throws Exception {
        Emision emisionAEliminar = buscar(idSintetico);
        emisiones.remove(emisionAEliminar);
        System.out.println("INFO: Emisión eliminada exitosamente.");
    }

    public List<Emision> listarTodo() throws Exception {
        if (emisiones.isEmpty()) {
            throw new Exception("Error: No hay emisiones para listar.");
        }
        return emisiones;
    }

    public int contar() {
        return emisiones.size();
    }
}