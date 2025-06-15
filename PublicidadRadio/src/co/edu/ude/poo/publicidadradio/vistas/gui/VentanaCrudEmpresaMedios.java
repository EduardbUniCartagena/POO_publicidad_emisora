package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.modelo.crud.EmpresaMediosCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.EmpresaMedios;
import co.edu.ude.poo.publicidadradio.vistas.reportes.ReporteEmpresaMedios;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de diálogo para las operaciones CRUD de la entidad EmpresaMedios.
 * Esta versión utiliza una estructura de 3 paneles para un layout robusto.
 * @author Eduard David Barrios Padilla
 */
public class VentanaCrudEmpresaMedios extends JDialog {

    private JTextField txtNif;
    private JTextField txtNombre;
    private JTextField txtDirector;
    private JTextField txtDireccion;
    private JButton btnEditar;
    private JButton btnEliminar;
    private final EmpresaMediosCrud empresaCrud;

    public VentanaCrudEmpresaMedios(JFrame owner) {
        super(owner, true);
        empresaCrud = new EmpresaMediosCrud();
        setTitle("Gestión de Empresas de Medios");
        setSize(800, 500);
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
        // --- Panel del Título (se mantiene en la parte superior de la ventana principal) ---
        JLabel lblTitulo = new JLabel("Formulario Empresa de Medios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);


        // 1. ÁREA SUPERIOR: Un panel que contendrá la imagen y el formulario.
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0)); // Espacio de 20px entre imagen y form
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // 1a. Sub-panel Izquierdo para la Imagen (dentro del panel superior)
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JLabel lblImagen = new JLabel(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/enterprise.png", 128, 128));
        panelIzquierdo.add(lblImagen);
        panelSuperior.add(panelIzquierdo, BorderLayout.WEST);

        // 1b. Sub-panel Derecho para el Formulario (dentro del panel superior)
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Hace que el JTextField ocupe el ancho
        gbc.weightx = 1.0; // Permite que el campo de texto se expanda horizontalmente

        gbc.gridx = 0; gbc.gridy = 0; panelFormulario.add(new JLabel("NIF:"), gbc);
        gbc.gridx = 1; txtNif = new JTextField(); panelFormulario.add(txtNif, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; txtNombre = new JTextField(); panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panelFormulario.add(new JLabel("Director:"), gbc);
        gbc.gridx = 1; txtDirector = new JTextField(); panelFormulario.add(txtDirector, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panelFormulario.add(new JLabel("Dirección Postal:"), gbc);
        gbc.gridx = 1; txtDireccion = new JTextField(); panelFormulario.add(txtDireccion, gbc);

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        // Añadimos el panel superior completo a la zona central de la ventana.
        add(panelSuperior, BorderLayout.CENTER);

        // 2. ÁREA INFERIOR: Un panel que contendrá solo los botones.
        //    Ocupará todo el ancho de la ventana.
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

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

        // Añadimos el panel de botones a la zona sur de la ventana.
        add(panelBotones, BorderLayout.SOUTH);

        // --- Lógica de los Botones (Action Listeners) ---
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnAgregar.addActionListener(e -> agregarEmpresa());
        btnBuscar.addActionListener(e -> buscarEmpresa());
        btnEditar.addActionListener(e -> editarEmpresa());
        btnEliminar.addActionListener(e -> eliminarEmpresa());
        btnListar.addActionListener(e -> mostrarVentanaReporte());
    }


    private void limpiarCampos() {
        txtNif.setText("");
        txtNombre.setText("");
        txtDirector.setText("");
        txtDireccion.setText("");
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void agregarEmpresa() {
        if (txtNif.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos NIF y Nombre son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea guardar esta empresa?", "Confirmar Guardado", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            EmpresaMedios empresa = new EmpresaMedios(txtNif.getText(), txtNombre.getText(), txtDirector.getText(), txtDireccion.getText());
            try {
                empresaCrud.agregar(empresa);
                JOptionPane.showMessageDialog(this, "Empresa guardada con éxito.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al Guardar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarEmpresa() {
        if (txtNif.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un NIF para poder buscar.", "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            EmpresaMedios empresa = empresaCrud.buscar(txtNif.getText());
            txtNombre.setText(empresa.getNombre());
            txtDirector.setText(empresa.getDirector());
            txtDireccion.setText(empresa.getDireccionPostal());
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en la Búsqueda", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    private void editarEmpresa() {
        if (JOptionPane.showConfirmDialog(this, "¿Modificar esta empresa?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            EmpresaMedios empresa = new EmpresaMedios(txtNif.getText(), txtNombre.getText(), txtDirector.getText(), txtDireccion.getText());
            try {
                empresaCrud.editar(empresa);
                JOptionPane.showMessageDialog(this, "Empresa modificada con éxito.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al Editar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarEmpresa() {
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar esta empresa?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                empresaCrud.eliminar(txtNif.getText());
                JOptionPane.showMessageDialog(this, "Empresa eliminada con éxito.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al Eliminar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaReporte() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.dispose();
        new ReporteEmpresaMedios().mostrarReporte(owner);
    }
}