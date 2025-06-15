// Ubicación: co/edu/ude/poo/publicidadradio/vistas/reportes/ReportePublicidad.java
package co.edu.ude.poo.publicidadradio.vistas.reportes;

import co.edu.ude.poo.publicidadradio.modelo.crud.PublicidadCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Publicidad;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase encargada de generar y mostrar el reporte de Publicidades.
 * Contiene la lógica para consultar los datos y preparar el modelo de la tabla.
 * @author Eduard David Barrios Padilla
 */
public class ReportePublicidad {

    private PublicidadCrud publicidadCrud = new PublicidadCrud();

    /**
     * Prepara los datos y muestra la ventana del reporte de publicidades.
     * @param owner El JFrame que será el padre de la ventana de reporte.
     */
    public void mostrarReporte(JFrame owner) {
        try {
            // 1. Obtener la lista de todas las publicidades usando el CRUD correspondiente.
            List<Publicidad> lista = publicidadCrud.listarTodo();

            // 2. Validar si la lista está vacía.
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(owner, "No hay publicidades para mostrar.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 3. Definir los nombres de las columnas para la tabla.
            String[] columnas = {"Programa", "Contrato", "Patrocinador", "Duración (seg/sem)", "Precio x Segundo", "Costo Total Semanal"};

            // 4. Crear un modelo de tabla por defecto.
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            // 5. Llenar el modelo con los datos de la lista de Publicidades.
            for (Publicidad publicidad : lista) {

                // Lógica para obtener el nombre del patrocinador de forma segura, evitando errores.
                String patrocinadorNombre = "N/A";
                if (publicidad.getContrato() != null && publicidad.getContrato().getPatrocinador() != null) {
                    patrocinadorNombre = publicidad.getContrato().getPatrocinador().getNombre();
                }

                Object[] fila = {
                        publicidad.getPrograma() != null ? publicidad.getPrograma().getNombre() : "N/A",
                        publicidad.getContrato() != null ? publicidad.getContrato().getNumeroContrato() : "N/A",
                        patrocinadorNombre,
                        publicidad.getDuracionSegundosSemana(),
                        String.format("$ %,.2f", publicidad.getPrecioPorSegundo()),
                        String.format("$ %,.2f", publicidad.getCostoTotal())
                };
                modelo.addRow(fila);
            }

            // 6. Crear una instancia de la ventana genérica con el título y modelo correctos.
            VentanaReporte ventana = new VentanaReporte(owner, "Reporte de Publicidades", modelo);

            // 7. Hacer visible la ventana del reporte.
            ventana.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(owner, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}