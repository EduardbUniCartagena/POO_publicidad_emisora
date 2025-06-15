// Ubicación: co/edu/ude/poo/publicidadradio/vistas/reportes/ReporteEmpresaMedios.java
package co.edu.ude.poo.publicidadradio.vistas.reportes;

import co.edu.ude.poo.publicidadradio.modelo.crud.EmpresaMediosCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.EmpresaMedios;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase encargada de generar y mostrar el reporte de Empresas de Medios.
 * Contiene la lógica para consultar los datos y preparar el modelo de la tabla.
 * @author Eduard David Barrios Padilla
 */
public class ReporteEmpresaMedios {

    private EmpresaMediosCrud empresaCrud = new EmpresaMediosCrud();

    /**
     * Prepara los datos y muestra la ventana del reporte de empresas.
     * @param owner El JFrame que será el padre de la ventana de reporte.
     */
    public void mostrarReporte(JFrame owner) {
        try {
            // 1. Obtener la lista de todas las empresas usando el CRUD.
            List<EmpresaMedios> lista = empresaCrud.listarTodo();

            // 2. Validar si la lista está vacía, como pide el documento.
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(owner, "No hay empresas de medios para mostrar.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
                return; // No continuamos si no hay datos.
            }

            // 3. Definir los nombres de las columnas para la tabla.
            String[] columnas = {"NIF", "Nombre", "Director", "Dirección", "N° Cadenas"};

            // 4. Crear un modelo de tabla por defecto, inicialmente sin filas.
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            // 5. Llenar el modelo con los datos de la lista.
            for (EmpresaMedios empresa : lista) {
                Object[] fila = {
                        empresa.getNif(),
                        empresa.getNombre(),
                        empresa.getDirector(),
                        empresa.getDireccionPostal(),
                        empresa.getCadenas() != null ? empresa.getCadenas().size() : 0
                };
                modelo.addRow(fila); // Agregamos la fila al modelo.
            }

            // 6. Crear una instancia de nuestra ventana genérica y pasarle los datos.
            VentanaReporte ventana = new VentanaReporte(owner, "Reporte de Empresas de Medios", modelo);

            // 7. Hacer visible la ventana del reporte.
            ventana.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(owner, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}