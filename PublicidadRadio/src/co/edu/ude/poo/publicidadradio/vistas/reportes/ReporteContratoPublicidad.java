// Ubicación: co/edu/ude/poo/publicidadradio/vistas/reportes/ReporteContratoPublicidad.java
package co.edu.ude.poo.publicidadradio.vistas.reportes;

import co.edu.ude.poo.publicidadradio.modelo.crud.ContratoPublicidadCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.ContratoPublicidad;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase encargada de generar y mostrar el reporte de Contratos de Publicidad.
 * Contiene la lógica para consultar los datos y preparar el modelo de la tabla.
 * @author Eduard David Barrios Padilla
 */
public class ReporteContratoPublicidad {

    private ContratoPublicidadCrud contratoCrud = new ContratoPublicidadCrud();

    /**
     * Prepara los datos y muestra la ventana del reporte de contratos.
     * @param owner El JFrame que será el padre de la ventana de reporte.
     */
    public void mostrarReporte(JFrame owner) {
        try {
            // 1. Obtener la lista de todos los contratos usando el CRUD correspondiente.
            List<ContratoPublicidad> lista = contratoCrud.listarTodo();

            // 2. Validar si la lista está vacía.
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(owner, "No hay contratos de publicidad para mostrar.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 3. Definir los nombres de las columnas para la tabla.
            String[] columnas = {"Número Contrato", "Patrocinador", "Duración (Semanas)", "Importe Total", "N° Publicidades"};

            // 4. Crear un modelo de tabla por defecto.
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            // 5. Llenar el modelo con los datos de la lista de Contratos de Publicidad.
            for (ContratoPublicidad contrato : lista) {
                Object[] fila = {
                        contrato.getNumeroContrato(),
                        // Validamos que el patrocinador no sea nulo antes de obtener su nombre.
                        contrato.getPatrocinador() != null ? contrato.getPatrocinador().getNombre() : "N/A",
                        contrato.getDuracionSemanas(),
                        // Formateamos el valor monetario para que se vea mejor.
                        String.format("$ %,.2f", contrato.getImporteTotal()),
                        contrato.getPublicidades() != null ? contrato.getPublicidades().size() : 0
                };
                modelo.addRow(fila);
            }

            // 6. Crear una instancia de la ventana genérica con el título y modelo correctos.
            VentanaReporte ventana = new VentanaReporte(owner, "Reporte de Contratos de Publicidad", modelo);

            // 7. Hacer visible la ventana del reporte.
            ventana.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(owner, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}