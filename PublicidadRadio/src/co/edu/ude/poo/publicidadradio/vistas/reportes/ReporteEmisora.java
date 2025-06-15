// Ubicación: co/edu/ude/poo/publicidadradio/vistas/reportes/ReporteEmisora.java
package co.edu.ude.poo.publicidadradio.vistas.reportes;

import co.edu.ude.poo.publicidadradio.modelo.crud.EmisoraCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Emisora;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase encargada de generar y mostrar el reporte de Emisoras.
 * Contiene la lógica para consultar los datos y preparar el modelo de la tabla.
 * @author Eduard David Barrios Padilla
 */
public class ReporteEmisora {

    private EmisoraCrud emisoraCrud = new EmisoraCrud();

    /**
     * Prepara los datos y muestra la ventana del reporte de emisoras.
     * @param owner El JFrame que será el padre de la ventana de reporte.
     */
    public void mostrarReporte(JFrame owner) {
        try {
            // 1. Obtener la lista de todas las emisoras usando el CRUD correspondiente.
            List<Emisora> lista = emisoraCrud.listarTodo();

            // 2. Validar si la lista está vacía.
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(owner, "No hay emisoras para mostrar.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 3. Definir los nombres de las columnas para la tabla.
            String[] columnas = {"NIF", "Nombre", "Director", "Banda Hertziana", "Cadena", "N° Programas"};

            // 4. Crear un modelo de tabla por defecto.
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            // 5. Llenar el modelo con los datos de la lista de Emisoras.
            for (Emisora emisora : lista) {
                Object[] fila = {
                        emisora.getNif(),
                        emisora.getNombre(),
                        emisora.getDirector(),
                        emisora.getBandaHertziana(),
                        // Si la emisora no pertenece a una cadena, se muestra "Independiente".
                        emisora.getCadena() != null ? emisora.getCadena().getNombre() : "Independiente",
                        emisora.getProgramas() != null ? emisora.getProgramas().size() : 0
                };
                modelo.addRow(fila);
            }

            // 6. Crear una instancia de la ventana genérica con el título y modelo correctos.
            VentanaReporte ventana = new VentanaReporte(owner, "Reporte de Emisoras", modelo);

            // 7. Hacer visible la ventana del reporte.
            ventana.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(owner, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}