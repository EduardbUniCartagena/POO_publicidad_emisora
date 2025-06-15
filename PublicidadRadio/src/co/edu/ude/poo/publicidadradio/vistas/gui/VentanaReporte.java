// Ubicación: co/edu/ude/poo/publicidadradio/vistas/gui/VentanaReporte.java
package co.edu.ude.poo.publicidadradio.vistas.gui;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Una ventana de diálogo genérica y reutilizable para mostrar datos en una JTable.
 * Sirve como la vista para cualquier tipo de reporte en la aplicación.
 * @author Eduard David Barrios Padilla
 */
public class VentanaReporte extends JDialog {

    private JTable tablaReporte;
    private JScrollPane scrollPane;

    /**
     * Constructor que crea la ventana del reporte.
     * @param owner La ventana padre (normalmente la VentanaPrincipal).
     * @param titulo El título que aparecerá en la barra de la ventana y en la parte superior.
     * @param modeloTabla El modelo de datos que contiene la información a mostrar en la tabla.
     */
    public VentanaReporte(JFrame owner, String titulo, TableModel modeloTabla) {
        super(owner, true); // true la hace modal
        setTitle(titulo);
        setSize(800, 500);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10)); // Usamos BorderLayout para distribuir los componentes.

        // Título dentro de la ventana
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla de Datos
        tablaReporte = new JTable(modeloTabla);
        tablaReporte.setFillsViewportHeight(true); // La tabla usa todo el espacio vertical disponible.
        tablaReporte.setRowHeight(25);
        tablaReporte.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaReporte.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Encabezado en negrita

        // Panel con Scroll para la tabla
        scrollPane = new JScrollPane(tablaReporte);
        add(scrollPane, BorderLayout.CENTER);
    }
}