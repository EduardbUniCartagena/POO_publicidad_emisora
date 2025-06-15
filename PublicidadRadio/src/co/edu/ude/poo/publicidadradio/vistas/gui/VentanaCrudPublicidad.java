package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.modelo.crud.ContratoPublicidadCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.ProgramaCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.PublicidadCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.ContratoPublicidad;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Programa;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Publicidad;
import co.edu.ude.poo.publicidadradio.vistas.reportes.ReportePublicidad; // Importamos la clase de reporte

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de diálogo para las operaciones CRUD de la entidad Publicidad.
 * Esta versión utiliza una estructura de 3 paneles para un layout robusto.
 * @author Eduard David Barrios Padilla
 */
public class VentanaCrudPublicidad extends JDialog {

    // --- Componentes Gráficos ---
    private JComboBox<ContratoPublicidad> cmbContrato;
    private JComboBox<Programa> cmbPrograma;
    private JSpinner spinnerDuracionSegundos;
    private JTextField txtPrecioPorSegundo;
    private JButton btnEditar;
    private JButton btnEliminar;

    // --- Lógica de Negocio ---
    private final PublicidadCrud publicidadCrud;
    private final ContratoPublicidadCrud contratoCrud;
    private final ProgramaCrud programaCrud;

    public VentanaCrudPublicidad(JFrame owner) {
        super(owner, true);
        this.publicidadCrud = new PublicidadCrud();
        this.contratoCrud = new ContratoPublicidadCrud();
        this.programaCrud = new ProgramaCrud();

        setTitle("Gestión de Publicidad");
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
        JLabel lblTitulo = new JLabel("Formulario Publicidad", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel Superior (Imagen y Formulario) ---
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Sub-panel Izquierdo para la Imagen
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JLabel lblImagen = new JLabel(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/publicidad.png", 128, 128));
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

        // Fila 0: Contrato
        gbcLabel.gridx = 0; gbcLabel.gridy = 0;
        panelFormulario.add(new JLabel("Contrato:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 0;
        cmbContrato = new JComboBox<>(); panelFormulario.add(cmbContrato, gbcField);

        // Fila 1: Programa
        gbcLabel.gridx = 0; gbcLabel.gridy = 1;
        panelFormulario.add(new JLabel("Programa:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 1;
        cmbPrograma = new JComboBox<>(); panelFormulario.add(cmbPrograma, gbcField);

        // Fila 2: Duración
        gbcLabel.gridx = 0; gbcLabel.gridy = 2;
        panelFormulario.add(new JLabel("Duración (seg/sem):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 2;
        spinnerDuracionSegundos = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1)); panelFormulario.add(spinnerDuracionSegundos, gbcField);

        // Fila 3: Precio por Segundo
        gbcLabel.gridx = 0; gbcLabel.gridy = 3;
        panelFormulario.add(new JLabel("Precio por Segundo:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 3;
        txtPrecioPorSegundo = new JTextField("0.0"); panelFormulario.add(txtPrecioPorSegundo, gbcField);

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
        btnAgregar.addActionListener(e -> agregarPublicidad());
        btnBuscar.addActionListener(e -> buscarPublicidad());
        btnEditar.addActionListener(e -> editarPublicidad());
        btnEliminar.addActionListener(e -> eliminarPublicidad());
        btnListar.addActionListener(e -> mostrarVentanaReporte());
    }

    private void cargarCombos() {
        try {
            cmbContrato.removeAllItems();
            for (ContratoPublicidad c : contratoCrud.listarTodo()) {
                cmbContrato.addItem(c);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar contratos: " + ex.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
        try {
            cmbPrograma.removeAllItems();
            for (Programa p : programaCrud.listarTodo()) {
                cmbPrograma.addItem(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar programas: " + ex.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generarIdSintetico() {
        ContratoPublicidad c = (ContratoPublicidad) cmbContrato.getSelectedItem();
        Programa p = (Programa) cmbPrograma.getSelectedItem();
        if (c == null || p == null) return null;
        return c.getNumeroContrato() + "-" + p.getNombre();
    }

    private void limpiarCampos() {
        if (cmbContrato.getItemCount() > 0) cmbContrato.setSelectedIndex(0);
        if (cmbPrograma.getItemCount() > 0) cmbPrograma.setSelectedIndex(0);
        spinnerDuracionSegundos.setValue(1);
        txtPrecioPorSegundo.setText("0.0");
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void agregarPublicidad() {
        if (cmbContrato.getSelectedItem() == null || cmbPrograma.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Contrato y un Programa.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Guardar esta publicidad?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                Publicidad p = new Publicidad(
                        (int) spinnerDuracionSegundos.getValue(),
                        Double.parseDouble(txtPrecioPorSegundo.getText()),
                        (Programa) cmbPrograma.getSelectedItem(),
                        (ContratoPublicidad) cmbContrato.getSelectedItem()
                );
                publicidadCrud.agregar(p);
                JOptionPane.showMessageDialog(this, "Publicidad guardada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "El Precio por Segundo debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarPublicidad() {
        String id = generarIdSintetico();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar Contrato y Programa para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Publicidad p = publicidadCrud.buscar(id);
            spinnerDuracionSegundos.setValue(p.getDuracionSegundosSemana());
            txtPrecioPorSegundo.setText(String.valueOf(p.getPrecioPorSegundo()));
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }
    }

    private void editarPublicidad() {
        if (JOptionPane.showConfirmDialog(this, "¿Modificar esta publicidad?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                Publicidad p = new Publicidad(
                        (int) spinnerDuracionSegundos.getValue(),
                        Double.parseDouble(txtPrecioPorSegundo.getText()),
                        (Programa) cmbPrograma.getSelectedItem(),
                        (ContratoPublicidad) cmbContrato.getSelectedItem()
                );
                publicidadCrud.editar(p);
                JOptionPane.showMessageDialog(this, "Publicidad modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "El Precio por Segundo debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarPublicidad() {
        String id = generarIdSintetico();
        if (id == null) return;

        if (JOptionPane.showConfirmDialog(this, "¿Eliminar esta publicidad?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                publicidadCrud.eliminar(id);
                JOptionPane.showMessageDialog(this, "Publicidad eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaReporte() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.dispose();
        new ReportePublicidad().mostrarReporte(owner);
    }
}