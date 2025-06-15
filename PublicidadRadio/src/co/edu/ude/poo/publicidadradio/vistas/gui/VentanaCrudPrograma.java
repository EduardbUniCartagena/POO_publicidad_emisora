package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.modelo.crud.CadenaRadioCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.EmisoraCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.ProgramaCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.CadenaRadio;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Emisora;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Programa;
import co.edu.ude.poo.publicidadradio.vistas.reportes.ReportePrograma; // Importamos la clase de reporte

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de diálogo para las operaciones CRUD de la entidad Programa.
 * Esta versión utiliza una estructura de 3 paneles para un layout robusto.
 * @author Eduard David Barrios Padilla
 */
public class VentanaCrudPrograma extends JDialog {

    // --- Componentes Gráficos ---
    private JTextField txtNombre;
    private JTextField txtResponsable;
    private JTextField txtDiaSemana;
    private JTextField txtHoraInicio;
    private JSpinner spinnerDuracion;
    private JComboBox<Object> cmbEmisora;
    private JComboBox<Object> cmbCadena;
    private JButton btnEditar;
    private JButton btnEliminar;

    // --- Lógica de Negocio ---
    private final ProgramaCrud programaCrud;
    private final EmisoraCrud emisoraCrud;
    private final CadenaRadioCrud cadenaRadioCrud;

    public VentanaCrudPrograma(JFrame owner) {
        super(owner, true);
        this.programaCrud = new ProgramaCrud();
        this.emisoraCrud = new EmisoraCrud();
        this.cadenaRadioCrud = new CadenaRadioCrud();

        setTitle("Gestión de Programas");
        setSize(800, 550); // Un poco más alto para todos los campos
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
        JLabel lblTitulo = new JLabel("Formulario Programa", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel Superior (Imagen y Formulario) ---
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Sub-panel Izquierdo para la Imagen
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JLabel lblImagen = new JLabel(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/programa.png", 128, 128));
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

        // Fila 0: Nombre
        gbcLabel.gridx = 0; gbcLabel.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 0;
        txtNombre = new JTextField(); panelFormulario.add(txtNombre, gbcField);

        // Fila 1: Responsable
        gbcLabel.gridx = 0; gbcLabel.gridy = 1;
        panelFormulario.add(new JLabel("Responsable:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 1;
        txtResponsable = new JTextField(); panelFormulario.add(txtResponsable, gbcField);

        // Fila 2: Día Semana
        gbcLabel.gridx = 0; gbcLabel.gridy = 2;
        panelFormulario.add(new JLabel("Día(s) Semana:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 2;
        txtDiaSemana = new JTextField(); panelFormulario.add(txtDiaSemana, gbcField);

        // Fila 3: Hora Inicio
        gbcLabel.gridx = 0; gbcLabel.gridy = 3;
        panelFormulario.add(new JLabel("Hora Inicio (HH:mm):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 3;
        txtHoraInicio = new JTextField(); panelFormulario.add(txtHoraInicio, gbcField);

        // Fila 4: Duración
        gbcLabel.gridx = 0; gbcLabel.gridy = 4;
        panelFormulario.add(new JLabel("Duración (min):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 4;
        spinnerDuracion = new JSpinner(new SpinnerNumberModel(0, 0, 1440, 1)); panelFormulario.add(spinnerDuracion, gbcField);

        // Fila 5: Emisora
        gbcLabel.gridx = 0; gbcLabel.gridy = 5;
        panelFormulario.add(new JLabel("Emisora (Opcional):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 5;
        cmbEmisora = new JComboBox<>(); panelFormulario.add(cmbEmisora, gbcField);

        // Fila 6: Cadena
        gbcLabel.gridx = 0; gbcLabel.gridy = 6;
        panelFormulario.add(new JLabel("Cadena (Opcional):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 6;
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
        btnAgregar.addActionListener(e -> agregarPrograma());
        btnBuscar.addActionListener(e -> buscarPrograma());
        btnEditar.addActionListener(e -> editarPrograma());
        btnEliminar.addActionListener(e -> eliminarPrograma());
        btnListar.addActionListener(e -> mostrarVentanaReporte());
    }

    private void cargarCombos() {
        try {
            cmbEmisora.removeAllItems();
            cmbEmisora.addItem("Ninguna");
            for (Emisora e : emisoraCrud.listarTodo()) {
                cmbEmisora.addItem(e);
            }
        } catch (Exception ex) {
            System.err.println("Advertencia: No se pudieron cargar las emisoras.");
        }
        try {
            cmbCadena.removeAllItems();
            cmbCadena.addItem("Ninguna");
            for (CadenaRadio c : cadenaRadioCrud.listarTodo()) {
                cmbCadena.addItem(c);
            }
        } catch (Exception ex) {
            System.err.println("Advertencia: No se pudieron cargar las cadenas.");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtResponsable.setText("");
        txtDiaSemana.setText("");
        txtHoraInicio.setText("");
        spinnerDuracion.setValue(0);
        if (cmbEmisora.getItemCount() > 0) cmbEmisora.setSelectedIndex(0);
        if (cmbCadena.getItemCount() > 0) cmbCadena.setSelectedIndex(0);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void agregarPrograma() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Guardar este programa?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Programa programa = new Programa(
                    txtNombre.getText(),
                    txtResponsable.getText(),
                    txtDiaSemana.getText(),
                    txtHoraInicio.getText(),
                    (int) spinnerDuracion.getValue()
            );
            if (cmbEmisora.getSelectedItem() instanceof Emisora) {
                programa.setEmisora((Emisora) cmbEmisora.getSelectedItem());
            }
            if (cmbCadena.getSelectedItem() instanceof CadenaRadio) {
                programa.setCadena((CadenaRadio) cmbCadena.getSelectedItem());
            }
            try {
                programaCrud.agregar(programa);
                JOptionPane.showMessageDialog(this, "Programa guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarPrograma() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un Nombre para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Programa programa = programaCrud.buscar(txtNombre.getText());
            txtResponsable.setText(programa.getResponsable());
            txtDiaSemana.setText(programa.getDiaSemana());
            txtHoraInicio.setText(programa.getHoraInicio());
            spinnerDuracion.setValue(programa.getDuracionMinutos());

            if (programa.getEmisora() != null) {
                cmbEmisora.setSelectedItem(programa.getEmisora());
            } else {
                cmbEmisora.setSelectedIndex(0); // "Ninguna"
            }
            if (programa.getCadena() != null) {
                cmbCadena.setSelectedItem(programa.getCadena());
            } else {
                cmbCadena.setSelectedIndex(0); // "Ninguna"
            }

            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    private void editarPrograma() {
        if (JOptionPane.showConfirmDialog(this, "¿Modificar este programa?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Programa programa = new Programa(
                    txtNombre.getText(),
                    txtResponsable.getText(),
                    txtDiaSemana.getText(),
                    txtHoraInicio.getText(),
                    (int) spinnerDuracion.getValue()
            );
            if (cmbEmisora.getSelectedItem() instanceof Emisora) {
                programa.setEmisora((Emisora) cmbEmisora.getSelectedItem());
            }
            if (cmbCadena.getSelectedItem() instanceof CadenaRadio) {
                programa.setCadena((CadenaRadio) cmbCadena.getSelectedItem());
            }
            try {
                programaCrud.editar(programa);
                JOptionPane.showMessageDialog(this, "Programa modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarPrograma() {
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar este programa?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                programaCrud.eliminar(txtNombre.getText());
                JOptionPane.showMessageDialog(this, "Programa eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaReporte() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.dispose();
        new ReportePrograma().mostrarReporte(owner);
    }
}