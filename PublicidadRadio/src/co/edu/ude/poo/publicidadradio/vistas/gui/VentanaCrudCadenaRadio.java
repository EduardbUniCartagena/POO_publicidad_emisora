package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.modelo.crud.CadenaRadioCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.EmisoraCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.EmpresaMediosCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.CadenaRadio;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Emisora;
import co.edu.ude.poo.publicidadradio.modelo.entidades.EmpresaMedios;
import co.edu.ude.poo.publicidadradio.vistas.reportes.ReporteCadenaRadio;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de diálogo para las operaciones CRUD de la entidad CadenaRadio.
 * Esta versión utiliza una estructura de 3 paneles y GridBagConstraints optimizados.
 * @author Eduard David Barrios Padilla
 */
public class VentanaCrudCadenaRadio extends JDialog {

    // --- Componentes Gráficos ---
    private JTextField txtNombre;
    private JComboBox<Emisora> cmbSedeCentral;
    private JTextField txtDirector;
    private JComboBox<EmpresaMedios> cmbEmpresaMedios;
    private JButton btnEditar;
    private JButton btnEliminar;

    // --- Lógica de Negocio ---
    private final CadenaRadioCrud cadenaCrud;
    private final EmisoraCrud emisoraCrud;
    private final EmpresaMediosCrud empresaMediosCrud;

    public VentanaCrudCadenaRadio(JFrame owner) {
        super(owner, true);
        cadenaCrud = new CadenaRadioCrud();
        emisoraCrud = new EmisoraCrud();
        empresaMediosCrud = new EmpresaMediosCrud();

        setTitle("Gestión de Cadenas de Radio");
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
        JLabel lblTitulo = new JLabel("Formulario Cadena de Radio", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel Superior (Imagen y Formulario) ---
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Sub-panel Izquierdo para la Imagen
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JLabel lblImagen = new JLabel(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/al-aire.png", 128, 128));
        panelIzquierdo.add(lblImagen);
        panelSuperior.add(panelIzquierdo, BorderLayout.WEST);

        // Sub-panel Derecho para el Formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());

        // 1. Creamos un objeto de restricciones PARA LAS ETIQUETAS (columna 0)
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.insets = new Insets(5, 5, 5, 5);
        gbcLabel.anchor = GridBagConstraints.EAST; // Alinea las etiquetas a la derecha

        // 2. Creamos otro objeto de restricciones PARA LOS CAMPOS (columna 1)
        GridBagConstraints gbcField = new GridBagConstraints();
        gbcField.insets = new Insets(5, 5, 5, 5);
        gbcField.anchor = GridBagConstraints.WEST;   // Alinea los campos a la izquierda
        gbcField.fill = GridBagConstraints.HORIZONTAL; // Hace que el campo se estire horizontalmente
        gbcField.weightx = 1.0; // Le da al campo todo el espacio extra disponible

        // --- Añadimos los componentes usando las restricciones correctas ---

        // Fila 0: Nombre
        gbcLabel.gridx = 0; gbcLabel.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 0;
        txtNombre = new JTextField(); panelFormulario.add(txtNombre, gbcField);

        // Fila 1: Sede Central
        gbcLabel.gridx = 0; gbcLabel.gridy = 1;
        panelFormulario.add(new JLabel("Sede Central:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 1;
        cmbSedeCentral = new JComboBox<>(); panelFormulario.add(cmbSedeCentral, gbcField);

        // Fila 2: Director
        gbcLabel.gridx = 0; gbcLabel.gridy = 2;
        panelFormulario.add(new JLabel("Director:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 2;
        txtDirector = new JTextField(); panelFormulario.add(txtDirector, gbcField);

        // Fila 3: Empresa de Medios
        gbcLabel.gridx = 0; gbcLabel.gridy = 3;
        panelFormulario.add(new JLabel("Empresa de Medios:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 3;
        cmbEmpresaMedios = new JComboBox<>(); panelFormulario.add(cmbEmpresaMedios, gbcField);



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
        btnAgregar.addActionListener(e -> agregarCadena());
        btnBuscar.addActionListener(e -> buscarCadena());
        btnEditar.addActionListener(e -> editarCadena());
        btnEliminar.addActionListener(e -> eliminarCadena());
        btnListar.addActionListener(e -> mostrarVentanaReporte());
    }

    private void cargarCombos() {
        try {
            cmbSedeCentral.removeAllItems();
            for (Emisora e : emisoraCrud.listarTodo()) {
                cmbSedeCentral.addItem(e);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar emisoras: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            cmbEmpresaMedios.removeAllItems();
            for (EmpresaMedios em : empresaMediosCrud.listarTodo()) {
                cmbEmpresaMedios.addItem(em);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar empresas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        if (cmbSedeCentral.getItemCount() > 0) cmbSedeCentral.setSelectedIndex(0);
        txtDirector.setText("");
        if (cmbEmpresaMedios.getItemCount() > 0) cmbEmpresaMedios.setSelectedIndex(0);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void agregarCadena() {
        if (txtNombre.getText().isEmpty() || cmbSedeCentral.getSelectedItem() == null || cmbEmpresaMedios.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Nombre, Sede Central y Empresa son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Guardar esta cadena de radio?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            CadenaRadio cadena = new CadenaRadio(
                    txtNombre.getText(),
                    (Emisora) cmbSedeCentral.getSelectedItem(),
                    txtDirector.getText(),
                    (EmpresaMedios) cmbEmpresaMedios.getSelectedItem()
            );
            try {
                cadenaCrud.agregar(cadena);
                JOptionPane.showMessageDialog(this, "Cadena de Radio guardada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarCadena() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un Nombre para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            CadenaRadio cadena = cadenaCrud.buscar(txtNombre.getText());
            cmbSedeCentral.setSelectedItem(cadena.getSedeCentral());
            txtDirector.setText(cadena.getDirector());
            cmbEmpresaMedios.setSelectedItem(cadena.getEmpresaMedios());
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    private void editarCadena() {
        if (JOptionPane.showConfirmDialog(this, "¿Modificar esta cadena?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            CadenaRadio cadenaModificada = new CadenaRadio(
                    txtNombre.getText(),
                    (Emisora) cmbSedeCentral.getSelectedItem(),
                    txtDirector.getText(),
                    (EmpresaMedios) cmbEmpresaMedios.getSelectedItem()
            );
            try {
                cadenaCrud.editar(cadenaModificada);
                JOptionPane.showMessageDialog(this, "Cadena de Radio modificada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarCadena() {
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar esta cadena?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                cadenaCrud.eliminar(txtNombre.getText());
                JOptionPane.showMessageDialog(this, "Cadena de Radio eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaReporte() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.dispose();
        new ReporteCadenaRadio().mostrarReporte(owner);
    }
}