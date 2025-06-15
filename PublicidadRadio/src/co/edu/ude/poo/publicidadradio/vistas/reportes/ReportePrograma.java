// Ubicación: co/edu/ude/poo/publicidadradio/vistas/reportes/ReportePrograma.java
package co.edu.ude.poo.publicidadradio.vistas.reportes;

import co.edu.ude.poo.publicidadradio.modelo.crud.ProgramaCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Programa;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase encargada de generar y mostrar el reporte de Programas.
 * Contiene la lógica para consultar los datos y preparar el modelo de la tabla.
 * @author Eduard David Barrios Padilla
 */
public class ReportePrograma {

    private ProgramaCrud programaCrud = new ProgramaCrud();

    /**
     * Prepara los datos y muestra la ventana del reporte de programas.
     * @param owner El JFrame que será el padre de la ventana de reporte.
     */
    public void mostrarReporte(JFrame owner) {
        try {
            // 1. Obtener la lista de todos los programas usando el CRUD correspondiente.
            List<Programa> lista = programaCrud.listarTodo();

            // 2. Validar si la lista está vacía.
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(owner, "No hay programas para mostrar.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 3. Definir los nombres de las columnas para la tabla.
            String[] columnas = {"Nombre", "Responsable", "Días de Emisión", "Hora Inicio", "Duración (Min)", "Transmitido por", "N° Publicidades"};

            // 4. Crear un modelo de tabla por defecto.
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            // 5. Llenar el modelo con los datos de la lista de Programas.
            for (Programa programa : lista) {

                // Lógica para determinar si el programa es de una cadena o de una sola emisora.
                String transmitidoPor = "N/A";
                if (programa.getCadena() != null) {
                    transmitidoPor = "Cadena: " + programa.getCadena().getNombre();
                } else if (programa.getEmisora() != null) {
                    transmitidoPor = "Emisora: " + programa.getEmisora().getNombre();
                }

                Object[] fila = {
                        programa.getNombre(),
                        programa.getResponsable(),
                        programa.getDiaSemana(),
                        programa.getHoraInicio(),
                        programa.getDuracionMinutos(),
                        transmitidoPor,
                        programa.getPublicidades() != null ? programa.getPublicidades().size() : 0
                };
                modelo.addRow(fila);
            }

            // 6. Crear una instancia de la ventana genérica con el título y modelo correctos.
            VentanaReporte ventana = new VentanaReporte(owner, "Reporte de Programas", modelo);

            // 7. Hacer visible la ventana del reporte.
            ventana.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(owner, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}