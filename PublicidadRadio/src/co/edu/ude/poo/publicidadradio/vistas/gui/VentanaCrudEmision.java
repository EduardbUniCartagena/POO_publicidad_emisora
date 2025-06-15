package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.modelo.crud.EmisionCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.ProgramaCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Emision;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Programa;
import co.edu.ude.poo.publicidadradio.vistas.reportes.ReporteEmision; // Importamos la clase de reporte

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de diálogo para las operaciones CRUD de la entidad Emision.
 * Esta versión utiliza una estructura de 3 paneles para un layout robusto.
 * @author Eduard David Barrios Padilla
 */
public class VentanaCrudEmision extends JDialog {

    // --- Componentes Gráficos ---
    private JComboBox<Programa> cmbPrograma;
    private JTextField txtDiaSemana;
    private JTextField txtHoraInicio;
    private JSpinner spinnerDuracion;
    private JButton btnEditar;
    private JButton btnEliminar;

    // --- Lógica de Negocio ---
    private final EmisionCrud emisionCrud;
    private final ProgramaCrud programaCrud;

    public VentanaCrudEmision(JFrame owner) {
        super(owner, true);
        emisionCrud = new EmisionCrud();
        programaCrud = new ProgramaCrud();

        setTitle("Gestión de Emisiones");
        setSize(800, 500);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        initComponents();
        cargarComboPrograma();
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
        JLabel lblTitulo = new JLabel("Formulario Emisión de Programa", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel Superior (Imagen y Formulario) ---
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Sub-panel Izquierdo para la Imagen
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JLabel lblImagen = new JLabel(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/emision.png", 128, 128));
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

        // Fila 0: Programa
        gbcLabel.gridx = 0; gbcLabel.gridy = 0;
        panelFormulario.add(new JLabel("Programa:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 0;
        cmbPrograma = new JComboBox<>(); panelFormulario.add(cmbPrograma, gbcField);

        // Fila 1: Día de la Semana
        gbcLabel.gridx = 0; gbcLabel.gridy = 1;
        panelFormulario.add(new JLabel("Día(s) Semana:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 1;
        txtDiaSemana = new JTextField(); panelFormulario.add(txtDiaSemana, gbcField);

        // Fila 2: Hora de Inicio
        gbcLabel.gridx = 0; gbcLabel.gridy = 2;
        panelFormulario.add(new JLabel("Hora Inicio (HH:mm):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 2;
        txtHoraInicio = new JTextField(); panelFormulario.add(txtHoraInicio, gbcField);

        // Fila 3: Duración
        gbcLabel.gridx = 0; gbcLabel.gridy = 3;
        panelFormulario.add(new JLabel("Duración (min):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 3;
        spinnerDuracion = new JSpinner(new SpinnerNumberModel(0, 0, 1440, 1)); panelFormulario.add(spinnerDuracion, gbcField);

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
        btnAgregar.addActionListener(e -> agregarEmision());
        btnBuscar.addActionListener(e -> buscarEmision());
        btnEditar.addActionListener(e -> editarEmision());
        btnEliminar.addActionListener(e -> eliminarEmision());
        btnListar.addActionListener(e -> mostrarVentanaReporte());
    }

    private void cargarComboPrograma() {
        try {
            cmbPrograma.removeAllItems();
            for (Programa p : programaCrud.listarTodo()) {
                cmbPrograma.addItem(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los programas: " + ex.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generarIdSinteticoDesdeFormulario() {
        Programa programaSeleccionado = (Programa) cmbPrograma.getSelectedItem();
        String horaInicio = txtHoraInicio.getText();
        if (programaSeleccionado == null || horaInicio.isEmpty()) {
            return null;
        }
        return programaSeleccionado.getNombre() + "-" + horaInicio;
    }

    private void limpiarCampos() {
        if (cmbPrograma.getItemCount() > 0) cmbPrograma.setSelectedIndex(0);
        txtHoraInicio.setText("");
        txtDiaSemana.setText("");
        spinnerDuracion.setValue(0);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void agregarEmision() {
        if (cmbPrograma.getSelectedItem() == null || txtHoraInicio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Programa e ingresar una Hora de Inicio.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Guardar esta emisión?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Emision emision = new Emision(
                    txtDiaSemana.getText(),
                    txtHoraInicio.getText(),
                    (int) spinnerDuracion.getValue(),
                    (Programa) cmbPrograma.getSelectedItem()
            );
            try {
                emisionCrud.agregar(emision);
                JOptionPane.showMessageDialog(this, "Emisión guardada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarEmision() {
        String idSintetico = generarIdSinteticoDesdeFormulario();
        if (idSintetico == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar Programa y Hora de Inicio para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Emision emision = emisionCrud.buscar(idSintetico);
            txtDiaSemana.setText(emision.getDiaSemana());
            spinnerDuracion.setValue(emision.getDuracionMinutos());
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            txtDiaSemana.setText("");
            spinnerDuracion.setValue(0);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }
    }

    private void editarEmision() {
        if (JOptionPane.showConfirmDialog(this, "¿Modificar esta emisión?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Emision emisionModificada = new Emision(
                    txtDiaSemana.getText(),
                    txtHoraInicio.getText(),
                    (int) spinnerDuracion.getValue(),
                    (Programa) cmbPrograma.getSelectedItem()
            );
            try {
                emisionCrud.editar(emisionModificada);
                JOptionPane.showMessageDialog(this, "Emisión modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarEmision() {
        String idSintetico = generarIdSinteticoDesdeFormulario();
        if (idSintetico == null) {
            JOptionPane.showMessageDialog(this, "No hay una emisión seleccionada para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar esta emisión?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                emisionCrud.eliminar(idSintetico);
                JOptionPane.showMessageDialog(this, "Emisión eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaReporte() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.dispose();
        new ReporteEmision().mostrarReporte(owner);
    }
}