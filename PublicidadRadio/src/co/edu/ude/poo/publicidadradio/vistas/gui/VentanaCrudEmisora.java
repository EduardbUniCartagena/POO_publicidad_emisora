package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.modelo.crud.CadenaRadioCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.EmisoraCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.CadenaRadio;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Emisora;
import co.edu.ude.poo.publicidadradio.vistas.reportes.ReporteEmisora; // Importamos la clase de reporte

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de diálogo para las operaciones CRUD de la entidad Emisora.
 * Esta versión utiliza una estructura de 3 paneles para un layout robusto.
 * @author Eduard David Barrios Padilla
 */
public class VentanaCrudEmisora extends JDialog {

    // --- Componentes Gráficos ---
    private JTextField txtNif;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtDirector;
    private JTextField txtBanda;
    private JComboBox<Object> cmbCadena; // JComboBox para la relación opcional con CadenaRadio
    private JButton btnEditar;
    private JButton btnEliminar;

    // --- Lógica de Negocio ---
    private final EmisoraCrud emisoraCrud;
    private final CadenaRadioCrud cadenaRadioCrud;

    public VentanaCrudEmisora(JFrame owner) {
        super(owner, true);
        emisoraCrud = new EmisoraCrud();
        cadenaRadioCrud = new CadenaRadioCrud();

        setTitle("Gestión de Emisoras");
        setSize(800, 500);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        initComponents();
        cargarCombos();
    }

    private ImageIcon cargarIcono(String path, int width, int height) {
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(path));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.err.println("No se pudo cargar o escalar el icono: " + path);
            return null;
        }
    }

    private void initComponents() {
        // --- Panel del Título ---
        JLabel lblTitulo = new JLabel("Formulario Emisora", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel Superior (Imagen y Formulario) ---
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Sub-panel Izquierdo para la Imagen
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JLabel lblImagen = new JLabel(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/emisora.png", 128, 128));
        panelIzquierdo.add(lblImagen);
        panelSuperior.add(panelIzquierdo, BorderLayout.WEST);

        // Sub-panel Derecho para el Formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.insets = new Insets(5, 5, 5, 5);
        gbcLabel.anchor = GridBagConstraints.EAST;

        GridBagConstraints gbcField = new GridBagConstraints();
        gbcField.insets = new Insets(5, 5, 5, 5);
        gbcField.anchor = GridBagConstraints.WEST;
        gbcField.fill = GridBagConstraints.HORIZONTAL;
        gbcField.weightx = 1.0;

        // Fila 0: NIF
        gbcLabel.gridx = 0; gbcLabel.gridy = 0;
        panelFormulario.add(new JLabel("NIF:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 0;
        txtNif = new JTextField(); panelFormulario.add(txtNif, gbcField);

        // Fila 1: Nombre
        gbcLabel.gridx = 0; gbcLabel.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 1;
        txtNombre = new JTextField(); panelFormulario.add(txtNombre, gbcField);

        // Fila 2: Dirección
        gbcLabel.gridx = 0; gbcLabel.gridy = 2;
        panelFormulario.add(new JLabel("Dirección Postal:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 2;
        txtDireccion = new JTextField(); panelFormulario.add(txtDireccion, gbcField);

        // Fila 3: Director
        gbcLabel.gridx = 0; gbcLabel.gridy = 3;
        panelFormulario.add(new JLabel("Director:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 3;
        txtDirector = new JTextField(); panelFormulario.add(txtDirector, gbcField);

        // Fila 4: Banda Hertziana
        gbcLabel.gridx = 0; gbcLabel.gridy = 4;
        panelFormulario.add(new JLabel("Banda Hertziana:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 4;
        txtBanda = new JTextField(); panelFormulario.add(txtBanda, gbcField);

        // Fila 5: Cadena (Opcional)
        gbcLabel.gridx = 0; gbcLabel.gridy = 5;
        panelFormulario.add(new JLabel("Cadena (Opcional):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 5;
        cmbCadena = new JComboBox<>(); panelFormulario.add(cmbCadena, gbcField);

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.CENTER);

        // --- Panel Inferior para Botones ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JButton btnAgregar = new JButton("Agregar", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        JButton btnBuscar = new JButton("Buscar", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        btnEditar = new JButton("Editar", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        btnEliminar = new JButton("Eliminar", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnListar = new JButton("Listar", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));

        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnListar);

        add(panelBotones, BorderLayout.SOUTH);

        // --- Listeners ---
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnAgregar.addActionListener(e -> agregarEmisora());
        btnBuscar.addActionListener(e -> buscarEmisora());
        btnEditar.addActionListener(e -> editarEmisora());
        btnEliminar.addActionListener(e -> eliminarEmisora());
        btnListar.addActionListener(e -> mostrarVentanaReporte());
    }

    private void cargarCombos() {
        try {
            cmbCadena.removeAllItems();
            // Añadimos un String para la opción "Independiente"
            cmbCadena.addItem("Independiente");
            for (CadenaRadio cr : cadenaRadioCrud.listarTodo()) {
                cmbCadena.addItem(cr);
            }
        } catch (Exception ex) {
            System.err.println("Advertencia: No se pudieron cargar las cadenas de radio.");
        }
    }

    private void limpiarCampos() {
        txtNif.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtDirector.setText("");
        txtBanda.setText("");
        if (cmbCadena.getItemCount() > 0) cmbCadena.setSelectedIndex(0);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void agregarEmisora() {
        if (txtNif.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIF y Nombre son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Guardar esta emisora?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Emisora emisora = new Emisora(
                    txtNif.getText(),
                    txtNombre.getText(),
                    txtDireccion.getText(),
                    txtDirector.getText(),
                    txtBanda.getText()
            );
            // Si el item seleccionado es un objeto CadenaRadio, lo asignamos.
            if (cmbCadena.getSelectedItem() instanceof CadenaRadio) {
                emisora.setCadena((CadenaRadio) cmbCadena.getSelectedItem());
            }
            try {
                emisoraCrud.agregar(emisora);
                JOptionPane.showMessageDialog(this, "Emisora guardada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarEmisora() {
        if (txtNif.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un NIF para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Emisora emisora = emisoraCrud.buscar(txtNif.getText());
            txtNombre.setText(emisora.getNombre());
            txtDireccion.setText(emisora.getDireccionPostal());
            txtDirector.setText(emisora.getDirector());
            txtBanda.setText(emisora.getBandaHertziana());

            if (emisora.getCadena() != null) {
                cmbCadena.setSelectedItem(emisora.getCadena());
            } else {
                cmbCadena.setSelectedIndex(0); // "Independiente"
            }
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    private void editarEmisora() {
        if (JOptionPane.showConfirmDialog(this, "¿Modificar esta emisora?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Emisora emisora = new Emisora(
                    txtNif.getText(),
                    txtNombre.getText(),
                    txtDireccion.getText(),
                    txtDirector.getText(),
                    txtBanda.getText()
            );
            if (cmbCadena.getSelectedItem() instanceof CadenaRadio) {
                emisora.setCadena((CadenaRadio) cmbCadena.getSelectedItem());
            }
            try {
                emisoraCrud.editar(emisora);
                JOptionPane.showMessageDialog(this, "Emisora modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarEmisora() {
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar esta emisora?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                emisoraCrud.eliminar(txtNif.getText());
                JOptionPane.showMessageDialog(this, "Emisora eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaReporte() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.dispose();
        new ReporteEmisora().mostrarReporte(owner);
    }
}