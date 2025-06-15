package co.edu.ude.poo.publicidadradio.vistas.gui;

import co.edu.ude.poo.publicidadradio.vistas.reportes.*;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación. Contiene la barra de menú para acceder
 * a todas las funcionalidades del sistema de gestión de publicidad.
 * @author Eduard David Barrios Padilla
 */
public class VentanaPrincipal extends JFrame {

    // Se declaran todos los JMenuItem para que sean accesibles en toda la clase.
    private JMenuItem itemAgregarEmpresa, itemBuscarEmpresa, itemEditarEmpresa, itemEliminarEmpresa, itemReporteEmpresa;
    private JMenuItem itemAgregarCadena, itemBuscarCadena, itemEditarCadena, itemEliminarCadena, itemReporteCadena;
    private JMenuItem itemAgregarEmisora, itemBuscarEmisora, itemEditarEmisora, itemEliminarEmisora, itemReporteEmisora;
    private JMenuItem itemAgregarPrograma, itemBuscarPrograma, itemEditarPrograma, itemEliminarPrograma, itemReportePrograma;
    private JMenuItem itemAgregarPatrocinador, itemBuscarPatrocinador, itemEditarPatrocinador, itemEliminarPatrocinador, itemReportePatrocinador;
    private JMenuItem itemAgregarContrato, itemBuscarContrato, itemEditarContrato, itemEliminarContrato, itemReporteContrato;
    private JMenuItem itemAgregarPublicidad, itemBuscarPublicidad, itemEditarPublicidad, itemEliminarPublicidad, itemReportePublicidad;
    private JMenuItem itemAgregarEmision, itemBuscarEmision, itemEditarEmision, itemEliminarEmision, itemReporteEmision;

    public VentanaPrincipal() {
        // --- Configuración del Panel de Contenido con Fondo ---
        // Se crea el panel con la imagen de portada.
        PanelConFondo panelFondo = new PanelConFondo("/co/edu/ude/poo/publicidadradio/vistas/iconos/portada.jpg");
        panelFondo.setLayout(new BorderLayout()); // El panel de fondo también necesita un layout.
        setContentPane(panelFondo); // ¡Este es el paso clave!

        // Ahora, inicializamos los componentes que irán SOBRE el fondo.
        initComponents();
        initListeners();

        setTitle("Sistema de Gestión de Publicidad en Radio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private ImageIcon cargarIcono(String path, int width, int height) {
        try {
            return new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.err.println("No se pudo cargar o escalar el icono: " + path);
            return null;
        }
    }

    private void initComponents() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // --- Menú Empresa de Medios ---
        JMenu menuEmpresaMedios = new JMenu("Empresas");
        itemAgregarEmpresa = new JMenuItem("Agregar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        itemBuscarEmpresa = new JMenuItem("Buscar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        itemEditarEmpresa = new JMenuItem("Modificar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        itemEliminarEmpresa = new JMenuItem("Eliminar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JMenu subMenuReportesEmpresa = new JMenu("Reportes...");
        subMenuReportesEmpresa.setIcon(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));
        itemReporteEmpresa = new JMenuItem("Listado General");
        subMenuReportesEmpresa.add(itemReporteEmpresa);
        menuEmpresaMedios.add(itemAgregarEmpresa);
        menuEmpresaMedios.add(itemBuscarEmpresa);
        menuEmpresaMedios.add(itemEditarEmpresa);
        menuEmpresaMedios.add(itemEliminarEmpresa);
        menuEmpresaMedios.addSeparator();
        menuEmpresaMedios.add(subMenuReportesEmpresa);
        menuBar.add(menuEmpresaMedios);

        // --- Menú Cadena de Radio ---
        JMenu menuCadenaRadio = new JMenu("Cadenas");
        itemAgregarCadena = new JMenuItem("Agregar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        itemBuscarCadena = new JMenuItem("Buscar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        itemEditarCadena = new JMenuItem("Modificar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        itemEliminarCadena = new JMenuItem("Eliminar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JMenu subMenuReportesCadena = new JMenu("Reportes...");
        subMenuReportesCadena.setIcon(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));
        itemReporteCadena = new JMenuItem("Listado General");
        subMenuReportesCadena.add(itemReporteCadena);
        menuCadenaRadio.add(itemAgregarCadena);
        menuCadenaRadio.add(itemBuscarCadena);
        menuCadenaRadio.add(itemEditarCadena);
        menuCadenaRadio.add(itemEliminarCadena);
        menuCadenaRadio.addSeparator();
        menuCadenaRadio.add(subMenuReportesCadena);
        menuBar.add(menuCadenaRadio);

        // --- Menú Emisora ---
        JMenu menuEmisora = new JMenu("Emisoras");
        itemAgregarEmisora = new JMenuItem("Agregar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        itemBuscarEmisora = new JMenuItem("Buscar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        itemEditarEmisora = new JMenuItem("Modificar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        itemEliminarEmisora = new JMenuItem("Eliminar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JMenu subMenuReportesEmisora = new JMenu("Reportes...");
        subMenuReportesEmisora.setIcon(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));
        itemReporteEmisora = new JMenuItem("Listado General");
        subMenuReportesEmisora.add(itemReporteEmisora);
        menuEmisora.add(itemAgregarEmisora);
        menuEmisora.add(itemBuscarEmisora);
        menuEmisora.add(itemEditarEmisora);
        menuEmisora.add(itemEliminarEmisora);
        menuEmisora.addSeparator();
        menuEmisora.add(subMenuReportesEmisora);
        menuBar.add(menuEmisora);

        // --- Menú Programa ---
        JMenu menuPrograma = new JMenu("Programas");
        itemAgregarPrograma = new JMenuItem("Agregar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        itemBuscarPrograma = new JMenuItem("Buscar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        itemEditarPrograma = new JMenuItem("Modificar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        itemEliminarPrograma = new JMenuItem("Eliminar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JMenu subMenuReportesPrograma = new JMenu("Reportes...");
        subMenuReportesPrograma.setIcon(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));
        itemReportePrograma = new JMenuItem("Listado General");
        subMenuReportesPrograma.add(itemReportePrograma);
        menuPrograma.add(itemAgregarPrograma);
        menuPrograma.add(itemBuscarPrograma);
        menuPrograma.add(itemEditarPrograma);
        menuPrograma.add(itemEliminarPrograma);
        menuPrograma.addSeparator();
        menuPrograma.add(subMenuReportesPrograma);
        menuBar.add(menuPrograma);

        // --- Menú Patrocinador ---
        JMenu menuPatrocinador = new JMenu("Patrocinadores");
        itemAgregarPatrocinador = new JMenuItem("Agregar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        itemBuscarPatrocinador = new JMenuItem("Buscar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        itemEditarPatrocinador = new JMenuItem("Modificar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        itemEliminarPatrocinador = new JMenuItem("Eliminar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JMenu subMenuReportesPatrocinador = new JMenu("Reportes...");
        subMenuReportesPatrocinador.setIcon(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));
        itemReportePatrocinador = new JMenuItem("Listado General");
        subMenuReportesPatrocinador.add(itemReportePatrocinador);
        menuPatrocinador.add(itemAgregarPatrocinador);
        menuPatrocinador.add(itemBuscarPatrocinador);
        menuPatrocinador.add(itemEditarPatrocinador);
        menuPatrocinador.add(itemEliminarPatrocinador);
        menuPatrocinador.addSeparator();
        menuPatrocinador.add(subMenuReportesPatrocinador);
        menuBar.add(menuPatrocinador);

        // --- Menú Contrato ---
        JMenu menuContrato = new JMenu("Contratos");
        itemAgregarContrato = new JMenuItem("Agregar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        itemBuscarContrato = new JMenuItem("Buscar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        itemEditarContrato = new JMenuItem("Modificar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        itemEliminarContrato = new JMenuItem("Eliminar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JMenu subMenuReportesContrato = new JMenu("Reportes...");
        subMenuReportesContrato.setIcon(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));
        itemReporteContrato = new JMenuItem("Listado General");
        subMenuReportesContrato.add(itemReporteContrato);
        menuContrato.add(itemAgregarContrato);
        menuContrato.add(itemBuscarContrato);
        menuContrato.add(itemEditarContrato);
        menuContrato.add(itemEliminarContrato);
        menuContrato.addSeparator();
        menuContrato.add(subMenuReportesContrato);
        menuBar.add(menuContrato);

        // --- Menú Publicidad ---
        JMenu menuPublicidad = new JMenu("Publicidad");
        itemAgregarPublicidad = new JMenuItem("Agregar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        itemBuscarPublicidad = new JMenuItem("Buscar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        itemEditarPublicidad = new JMenuItem("Modificar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        itemEliminarPublicidad = new JMenuItem("Eliminar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JMenu subMenuReportesPublicidad = new JMenu("Reportes...");
        subMenuReportesPublicidad.setIcon(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));
        itemReportePublicidad = new JMenuItem("Listado General");
        subMenuReportesPublicidad.add(itemReportePublicidad);
        menuPublicidad.add(itemAgregarPublicidad);
        menuPublicidad.add(itemBuscarPublicidad);
        menuPublicidad.add(itemEditarPublicidad);
        menuPublicidad.add(itemEliminarPublicidad);
        menuPublicidad.addSeparator();
        menuPublicidad.add(subMenuReportesPublicidad);
        menuBar.add(menuPublicidad);

        // --- Menú Emision ---
        JMenu menuEmision = new JMenu("Emisiones");
        itemAgregarEmision = new JMenuItem("Agregar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/guardar.png", 16, 16));
        itemBuscarEmision = new JMenuItem("Buscar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/buscar.png", 16, 16));
        itemEditarEmision = new JMenuItem("Modificar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lapiz.png", 16, 16));
        itemEliminarEmision = new JMenuItem("Eliminar...", cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/eliminar.png", 16, 16));
        JMenu subMenuReportesEmision = new JMenu("Reportes...");
        subMenuReportesEmision.setIcon(cargarIcono("/co/edu/ude/poo/publicidadradio/vistas/iconos/lista.png", 16, 16));
        itemReporteEmision = new JMenuItem("Listado General");
        subMenuReportesEmision.add(itemReporteEmision);
        menuEmision.add(itemAgregarEmision);
        menuEmision.add(itemBuscarEmision);
        menuEmision.add(itemEditarEmision);
        menuEmision.add(itemEliminarEmision);
        menuEmision.addSeparator();
        menuEmision.add(subMenuReportesEmision);
        menuBar.add(menuEmision);

        // --- Menús Alineados a la Derecha ---
        menuBar.add(Box.createHorizontalGlue());
        JMenu menuUsuario = new JMenu("Usuario");
        menuBar.add(menuUsuario);
        JMenu menuAyuda = new JMenu("Ayuda");
        menuBar.add(menuAyuda);
    }

    private void initListeners() {
        // --- Listeners para EmpresaMedios ---
        itemAgregarEmpresa.addActionListener(e -> new VentanaCrudEmpresaMedios(this).setVisible(true));
        itemBuscarEmpresa.addActionListener(e -> new VentanaCrudEmpresaMedios(this).setVisible(true));
        itemEditarEmpresa.addActionListener(e -> new VentanaCrudEmpresaMedios(this).setVisible(true));
        itemEliminarEmpresa.addActionListener(e -> new VentanaCrudEmpresaMedios(this).setVisible(true));
        itemReporteEmpresa.addActionListener(e -> new ReporteEmpresaMedios().mostrarReporte(this));

        // --- Listeners para CadenaRadio ---
        itemAgregarCadena.addActionListener(e -> new VentanaCrudCadenaRadio(this).setVisible(true));
        itemBuscarCadena.addActionListener(e -> new VentanaCrudCadenaRadio(this).setVisible(true));
        itemEditarCadena.addActionListener(e -> new VentanaCrudCadenaRadio(this).setVisible(true));
        itemEliminarCadena.addActionListener(e -> new VentanaCrudCadenaRadio(this).setVisible(true));
        itemReporteCadena.addActionListener(e -> new ReporteCadenaRadio().mostrarReporte(this));

        // --- Listeners para Emisora ---
        itemAgregarEmisora.addActionListener(e -> new VentanaCrudEmisora(this).setVisible(true));
        itemBuscarEmisora.addActionListener(e -> new VentanaCrudEmisora(this).setVisible(true));
        itemEditarEmisora.addActionListener(e -> new VentanaCrudEmisora(this).setVisible(true));
        itemEliminarEmisora.addActionListener(e -> new VentanaCrudEmisora(this).setVisible(true));
        itemReporteEmisora.addActionListener(e -> new ReporteEmisora().mostrarReporte(this));

        // --- Listeners para Programa ---
        itemAgregarPrograma.addActionListener(e -> new VentanaCrudPrograma(this).setVisible(true));
        itemBuscarPrograma.addActionListener(e -> new VentanaCrudPrograma(this).setVisible(true));
        itemEditarPrograma.addActionListener(e -> new VentanaCrudPrograma(this).setVisible(true));
        itemEliminarPrograma.addActionListener(e -> new VentanaCrudPrograma(this).setVisible(true));
        itemReportePrograma.addActionListener(e -> new ReportePrograma().mostrarReporte(this));

        // --- Listeners para Patrocinador ---
        itemAgregarPatrocinador.addActionListener(e -> new VentanaCrudPatrocinador(this).setVisible(true));
        itemBuscarPatrocinador.addActionListener(e -> new VentanaCrudPatrocinador(this).setVisible(true));
        itemEditarPatrocinador.addActionListener(e -> new VentanaCrudPatrocinador(this).setVisible(true));
        itemEliminarPatrocinador.addActionListener(e -> new VentanaCrudPatrocinador(this).setVisible(true));
        itemReportePatrocinador.addActionListener(e -> new ReportePatrocinador().mostrarReporte(this));

        // --- Listeners para ContratoPublicidad ---
        itemAgregarContrato.addActionListener(e -> new VentanaCrudContratoPublicidad(this).setVisible(true));
        itemBuscarContrato.addActionListener(e -> new VentanaCrudContratoPublicidad(this).setVisible(true));
        itemEditarContrato.addActionListener(e -> new VentanaCrudContratoPublicidad(this).setVisible(true));
        itemEliminarContrato.addActionListener(e -> new VentanaCrudContratoPublicidad(this).setVisible(true));
        itemReporteContrato.addActionListener(e -> new ReporteContratoPublicidad().mostrarReporte(this));

        // --- Listeners para Publicidad ---
        itemAgregarPublicidad.addActionListener(e -> new VentanaCrudPublicidad(this).setVisible(true));
        itemBuscarPublicidad.addActionListener(e -> new VentanaCrudPublicidad(this).setVisible(true));
        itemEditarPublicidad.addActionListener(e -> new VentanaCrudPublicidad(this).setVisible(true));
        itemEliminarPublicidad.addActionListener(e -> new VentanaCrudPublicidad(this).setVisible(true));
        itemReportePublicidad.addActionListener(e -> new ReportePublicidad().mostrarReporte(this));

        // --- Listeners para Emision ---
        itemAgregarEmision.addActionListener(e -> new VentanaCrudEmision(this).setVisible(true));
        itemBuscarEmision.addActionListener(e -> new VentanaCrudEmision(this).setVisible(true));
        itemEditarEmision.addActionListener(e -> new VentanaCrudEmision(this).setVisible(true));
        itemEliminarEmision.addActionListener(e -> new VentanaCrudEmision(this).setVisible(true));
        itemReporteEmision.addActionListener(e -> new ReporteEmision().mostrarReporte(this));
    }

    /**
     * Una clase interna que representa un JPanel con una imagen de fondo que
     * se escala para llenar todo el espacio del panel.
     */
    class PanelConFondo extends JPanel {
        private Image imagenDeFondo;

        public PanelConFondo(String rutaImagen) {
            try {
                imagenDeFondo = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
            } catch (Exception e) {
                System.err.println("No se pudo cargar la imagen de fondo: " + rutaImagen);
                imagenDeFondo = null;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagenDeFondo != null) {
                // Dibuja la imagen para que ocupe todo el ancho y alto del panel
                g.drawImage(imagenDeFondo, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}