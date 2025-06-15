package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.modelo.crud.PatrocinadorCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Patrocinador;
import co.edu.ude.poo.publicidadradio.vistas.reportes.ReportePatrocinador; // Importamos la clase de reporte

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de diálogo para las operaciones CRUD de la entidad Patrocinador.
 * Esta versión utiliza una estructura de 3 paneles para un layout robusto.
 * @author Eduard David Barrios Padilla
 */
public class VentanaCrudPatrocinador extends JDialog {

    // --- Componentes Gráficos ---
    private JTextField txtIdentificador;
    private JTextField txtNombre;
    private JButton btnEditar;
    private JButton btnEliminar;

    // --- Lógica de Negocio ---
    private final PatrocinadorCrud patrocinadorCrud;

    public VentanaCrudPatrocinador(JFrame owner) {
        super(owner, true);
        patrocinadorCrud = new PatrocinadorCrud();

        setTitle("Gestión de Patrocinadores");
        setSize(800, 400); // Ajustamos el alto al tener menos campos
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        initComponents();
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
        JLabel lblTitulo = new JLabel("Formulario Patrocinador", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel Superior (Imagen y Formulario) ---
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Sub-panel Izquierdo para la Imagen
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JLabel lblImagen = new JLabel(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/patrocinador.png", 128, 128));
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

        // Fila 0: Identificador
        gbcLabel.gridx = 0; gbcLabel.gridy = 0;
        panelFormulario.add(new JLabel("Identificador (NIT):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 0;
        txtIdentificador = new JTextField(); panelFormulario.add(txtIdentificador, gbcField);

        // Fila 1: Nombre
        gbcLabel.gridx = 0; gbcLabel.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 1;
        txtNombre = new JTextField(); panelFormulario.add(txtNombre, gbcField);

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
        btnAgregar.addActionListener(e -> agregarPatrocinador());
        btnBuscar.addActionListener(e -> buscarPatrocinador());
        btnEditar.addActionListener(e -> editarPatrocinador());
        btnEliminar.addActionListener(e -> eliminarPatrocinador());
        btnListar.addActionListener(e -> mostrarVentanaReporte());
    }

    private void limpiarCampos() {
        txtIdentificador.setText("");
        txtNombre.setText("");
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void agregarPatrocinador() {
        if (txtIdentificador.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Guardar este patrocinador?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // El constructor de Patrocinador es (nombre, identificador)
            Patrocinador p = new Patrocinador(txtNombre.getText(), txtIdentificador.getText());
            try {
                patrocinadorCrud.agregar(p);
                JOptionPane.showMessageDialog(this, "Patrocinador guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarPatrocinador() {
        if (txtIdentificador.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un Identificador para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Patrocinador p = patrocinadorCrud.buscar(txtIdentificador.getText());
            txtNombre.setText(p.getNombre());
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    private void editarPatrocinador() {
        if (JOptionPane.showConfirmDialog(this, "¿Modificar este patrocinador?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Patrocinador pModificado = new Patrocinador(txtNombre.getText(), txtIdentificador.getText());
            try {
                patrocinadorCrud.editar(pModificado);
                JOptionPane.showMessageDialog(this, "Patrocinador modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarPatrocinador() {
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar este patrocinador?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                patrocinadorCrud.eliminar(txtIdentificador.getText());
                JOptionPane.showMessageDialog(this, "Patrocinador eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaReporte() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.dispose();
        new ReportePatrocinador().mostrarReporte(owner);
    }
}