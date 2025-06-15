// Ubicación: co/edu/ude/poo/publicidadradio/vistas/reportes/ReporteCadenaRadio.java
package co.edu.ude.poo.publicidadradio.vistas.reportes;

import co.edu.ude.poo.publicidadradio.modelo.crud.CadenaRadioCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.CadenaRadio;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase encargada de generar y mostrar el reporte de Cadenas de Radio.
 * Contiene la lógica para consultar los datos y preparar el modelo de la tabla.
 * @author Eduard David Barrios Padilla
 */
public class ReporteCadenaRadio {

    private CadenaRadioCrud cadenaCrud = new CadenaRadioCrud();

    /**
     * Prepara los datos y muestra la ventana del reporte de cadenas de radio.
     * @param owner El JFrame que será el padre de la ventana de reporte.
     */
    public void mostrarReporte(JFrame owner) {
        try {
            // 1. Obtener la lista de todas las cadenas usando el CRUD correspondiente.
            List<CadenaRadio> lista = cadenaCrud.listarTodo();

            // 2. Validar si la lista está vacía.
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(owner, "No hay cadenas de radio para mostrar.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 3. Definir los nombres de las columnas para la tabla.
            String[] columnas = {"Nombre", "Director", "Sede Central", "Empresa Propietaria", "N° Emisoras"};

            // 4. Crear un modelo de tabla por defecto.
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            // 5. Llenar el modelo con los datos de la lista de Cadenas de Radio.
            for (CadenaRadio cadena : lista) {
                Object[] fila = {
                        cadena.getNombre(),
                        cadena.getDirector(),
                        // Hacemos una validación para evitar errores si un objeto relacionado es nulo.
                        cadena.getSedeCentral() != null ? cadena.getSedeCentral().getNombre() : "N/A",
                        cadena.getEmpresaMedios() != null ? cadena.getEmpresaMedios().getNombre() : "N/A",
                        cadena.getEmisoras() != null ? cadena.getEmisoras().size() : 0
                };
                modelo.addRow(fila);
            }

            // 6. Crear una instancia de la ventana genérica con el título y modelo correctos.
            VentanaReporte ventana = new VentanaReporte(owner, "Reporte de Cadenas de Radio", modelo);

            // 7. Hacer visible la ventana del reporte.
            ventana.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(owner, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}