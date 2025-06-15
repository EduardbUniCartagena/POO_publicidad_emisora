// Ubicación: co/edu/ude/poo/publicidadradio/vistas/reportes/ReporteEmision.java
package co.edu.ude.poo.publicidadradio.vistas.reportes;

import co.edu.ude.poo.publicidadradio.modelo.crud.EmisionCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Emision;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase encargada de generar y mostrar el reporte de Emisiones.
 * Contiene la lógica para consultar los datos y preparar el modelo de la tabla.
 * @author Eduard David Barrios Padilla
 */
public class ReporteEmision {

    private EmisionCrud emisionCrud = new EmisionCrud();

    /**
     * Prepara los datos y muestra la ventana del reporte de emisiones.
     * @param owner El JFrame que será el padre de la ventana de reporte.
     */
    public void mostrarReporte(JFrame owner) {
        try {
            // 1. Obtener la lista de todas las emisiones usando el CRUD correspondiente.
            List<Emision> lista = emisionCrud.listarTodo();

            // 2. Validar si la lista está vacía.
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(owner, "No hay emisiones para mostrar.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 3. Definir los nombres de las columnas para la tabla.
            String[] columnas = {"Programa", "Día de la Semana", "Hora de Inicio", "Duración (Minutos)"};

            // 4. Crear un modelo de tabla por defecto.
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            // 5. Llenar el modelo con los datos de la lista de Emisiones.
            for (Emision emision : lista) {
                Object[] fila = {
                        // Validamos que el programa no sea nulo antes de obtener su nombre.
                        emision.getPrograma() != null ? emision.getPrograma().getNombre() : "N/A",
                        emision.getDiaSemana(),
                        emision.getHoraInicio(),
                        emision.getDuracionMinutos()
                };
                modelo.addRow(fila);
            }

            // 6. Crear una instancia de la ventana genérica con el título y modelo correctos.
            VentanaReporte ventana = new VentanaReporte(owner, "Reporte de Emisiones de Programas", modelo);

            // 7. Hacer visible la ventana del reporte.
            ventana.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(owner, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}