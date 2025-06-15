package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.modelo.crud.ContratoPublicidadCrud;
import co.edu.ude.poo.publicidadradio.modelo.crud.PatrocinadorCrud;
import co.edu.ude.poo.publicidadradio.modelo.entidades.ContratoPublicidad;
import co.edu.ude.poo.publicidadradio.modelo.entidades.Patrocinador;
import co.edu.ude.poo.publicidadradio.vistas.reportes.ReporteContratoPublicidad; // Importamos la clase de reporte

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de diálogo para las operaciones CRUD de la entidad ContratoPublicidad.
 * Esta versión utiliza una estructura de 3 paneles para un layout robusto.
 * @author Eduard David Barrios Padilla
 */
public class VentanaCrudContratoPublicidad extends JDialog {

    // --- Componentes Gráficos ---
    private JTextField txtNumeroContrato;
    private JComboBox<Patrocinador> cmbPatrocinador;
    private JSpinner spinnerDuracionSemanas;
    private JTextField txtImporteTotal;
    private JButton btnEditar;
    private JButton btnEliminar;

    // --- Lógica de Negocio ---
    private final ContratoPublicidadCrud contratoCrud;
    private final PatrocinadorCrud patrocinadorCrud;

    public VentanaCrudContratoPublicidad(JFrame owner) {
        super(owner, true);
        contratoCrud = new ContratoPublicidadCrud();
        patrocinadorCrud = new PatrocinadorCrud();

        setTitle("Gestión de Contratos de Publicidad");
        setSize(800, 500);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        initComponents();
        cargarComboPatrocinador();
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
        JLabel lblTitulo = new JLabel("Formulario Contrato de Publicidad", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel Superior (Imagen y Formulario) ---
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Sub-panel Izquierdo para la Imagen
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        JLabel lblImagen = new JLabel(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/contrato.png", 128, 128));
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

        // Fila 0: Número de Contrato
        gbcLabel.gridx = 0; gbcLabel.gridy = 0;
        panelFormulario.add(new JLabel("Número de Contrato:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 0;
        txtNumeroContrato = new JTextField(); panelFormulario.add(txtNumeroContrato, gbcField);

        // Fila 1: Patrocinador
        gbcLabel.gridx = 0; gbcLabel.gridy = 1;
        panelFormulario.add(new JLabel("Patrocinador:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 1;
        cmbPatrocinador = new JComboBox<>(); panelFormulario.add(cmbPatrocinador, gbcField);

        // Fila 2: Duración y Importe
        gbcLabel.gridx = 0; gbcLabel.gridy = 2;
        panelFormulario.add(new JLabel("Duración (Semanas):"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 2;
        spinnerDuracionSemanas = new JSpinner(new SpinnerNumberModel(1, 1, 520, 1)); panelFormulario.add(spinnerDuracionSemanas, gbcField);

        gbcLabel.gridx = 0; gbcLabel.gridy = 3;
        panelFormulario.add(new JLabel("Importe Total:"), gbcLabel);
        gbcField.gridx = 1; gbcField.gridy = 3;
        txtImporteTotal = new JTextField("0.0"); panelFormulario.add(txtImporteTotal, gbcField);

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
        btnAgregar.addActionListener(e -> agregarContrato());
        btnBuscar.addActionListener(e -> buscarContrato());
        btnEditar.addActionListener(e -> editarContrato());
        btnEliminar.addActionListener(e -> eliminarContrato());
        btnListar.addActionListener(e -> mostrarVentanaReporte());
    }

    private void cargarComboPatrocinador() {
        try {
            cmbPatrocinador.removeAllItems();
            for (Patrocinador p : patrocinadorCrud.listarTodo()) {
                cmbPatrocinador.addItem(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los patrocinadores: " + ex.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNumeroContrato.setText("");
        if (cmbPatrocinador.getItemCount() > 0) cmbPatrocinador.setSelectedIndex(0);
        spinnerDuracionSemanas.setValue(1);
        txtImporteTotal.setText("0.0");
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void agregarContrato() {
        if (txtNumeroContrato.getText().isEmpty() || cmbPatrocinador.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Número de Contrato y Patrocinador son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Guardar este contrato?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                ContratoPublicidad contrato = new ContratoPublicidad(
                        txtNumeroContrato.getText(),
                        (Patrocinador) cmbPatrocinador.getSelectedItem(),
                        (int) spinnerDuracionSemanas.getValue(),
                        Double.parseDouble(txtImporteTotal.getText())
                );
                contratoCrud.agregar(contrato);
                JOptionPane.showMessageDialog(this, "Contrato guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "El Importe Total debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarContrato() {
        if (txtNumeroContrato.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un Número de Contrato para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            ContratoPublicidad contrato = contratoCrud.buscar(txtNumeroContrato.getText());
            cmbPatrocinador.setSelectedItem(contrato.getPatrocinador());
            spinnerDuracionSemanas.setValue(contrato.getDuracionSemanas());
            txtImporteTotal.setText(String.valueOf(contrato.getImporteTotal()));
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    private void editarContrato() {
        if (JOptionPane.showConfirmDialog(this, "¿Modificar este contrato?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                ContratoPublicidad contratoModificado = new ContratoPublicidad(
                        txtNumeroContrato.getText(),
                        (Patrocinador) cmbPatrocinador.getSelectedItem(),
                        (int) spinnerDuracionSemanas.getValue(),
                        Double.parseDouble(txtImporteTotal.getText())
                );
                contratoCrud.editar(contratoModificado);
                JOptionPane.showMessageDialog(this, "Contrato modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "El Importe Total debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarContrato() {
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar este contrato?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                contratoCrud.eliminar(txtNumeroContrato.getText());
                JOptionPane.showMessageDialog(this, "Contrato eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaReporte() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.dispose();
        new ReporteContratoPublicidad().mostrarReporte(owner);
    }
}