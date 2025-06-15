// Ubicación: co/edu/ude/poo/publicidadradio/vistas/reportes/ReportePatrocinador.java
package co.edu.ude.poo.publicidadradio.vistas.reportes;

import co.edu.ude.poo.publicidadradio.modelo.crud.PatrocinadorCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Patrocinador;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase encargada de generar y mostrar el reporte de Patrocinadores.
 * Contiene la lógica para consultar los datos y preparar el modelo de la tabla.
 * @author Eduard David Barrios Padilla
 */
public class ReportePatrocinador {

    private PatrocinadorCrud patrocinadorCrud = new PatrocinadorCrud();

    /**
     * Prepara los datos y muestra la ventana del reporte de patrocinadores.
     * @param owner El JFrame que será el padre de la ventana de reporte.
     */
    public void mostrarReporte(JFrame owner) {
        try {
            // 1. Obtener la lista de todos los patrocinadores usando el CRUD correspondiente.
            List<Patrocinador> lista = patrocinadorCrud.listarTodo();

            // 2. Validar si la lista está vacía.
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(owner, "No hay patrocinadores para mostrar.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 3. Definir los nombres de las columnas para la tabla.
            String[] columnas = {"Identificador", "Nombre", "N° Contratos"};

            // 4. Crear un modelo de tabla por defecto.
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            // 5. Llenar el modelo con los datos de la lista de Patrocinadores.
            for (Patrocinador patrocinador : lista) {
                Object[] fila = {
                        patrocinador.getIdentificador(),
                        patrocinador.getNombre(),
                        // Validamos que la lista de contratos no sea nula.
                        patrocinador.getContratos() != null ? patrocinador.getContratos().size() : 0
                };
                modelo.addRow(fila);
            }

            // 6. Crear una instancia de la ventana genérica con el título y modelo correctos.
            VentanaReporte ventana = new VentanaReporte(owner, "Reporte de Patrocinadores", modelo);

            // 7. Hacer visible la ventana del reporte.
            ventana.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(owner, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}