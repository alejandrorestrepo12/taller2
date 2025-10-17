import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * Interfaz gráfica para el sistema de gestión de envíos
 * Principio S (Single Responsibility): Solo maneja la presentación visual
 * Principio D (Dependency Inversion): Usa abstracciones (IServicioLogistica)
 */
public class InterfazLogistica extends JFrame {
    private final IServicioLogistica servicioLogistica;
    
    // Componentes de la interfaz
    private JTextField txtNumero;
    private JTextField txtCliente;
    private JTextField txtPeso;
    private JTextField txtDistancia;
    private JComboBox<String> cmbTipo;
    private JTable tablaEnvios;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar;
    
    /**
     * Constructor con inyección de dependencias
     * @param servicioLogistica Servicio de logística
     */
    public InterfazLogistica(IServicioLogistica servicioLogistica) {
        if (servicioLogistica == null) {
            throw new IllegalArgumentException("El servicio de logística no puede ser nulo");
        }
        this.servicioLogistica = servicioLogistica;
        inicializarComponentes();
        configurarVentana();
    }
    
    /**
     * Constructor sin parámetros (crea sus propias dependencias)
     */
    public InterfazLogistica() {
        this(new ServicioLogistica(new RepositorioEnvios()));
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Panel principal
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con iconos y formulario
        JPanel panelSuperior = new JPanel(new BorderLayout());
        
        // Panel de iconos (simulado)
        JPanel panelIconos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIconos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblAgregar = new JLabel("➕🚚", SwingConstants.CENTER);
        lblAgregar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblAgregar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        lblAgregar.setPreferredSize(new Dimension(70, 70));
        
        JLabel lblRetirar = new JLabel("➖🚚", SwingConstants.CENTER);
        lblRetirar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblRetirar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        lblRetirar.setPreferredSize(new Dimension(70, 70));
        
        panelIconos.add(lblAgregar);
        panelIconos.add(Box.createHorizontalStrut(10));
        panelIconos.add(lblRetirar);
        
        panelSuperior.add(panelIconos, BorderLayout.WEST);
        
        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Número
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        txtNumero = new JTextField(15);
        panelFormulario.add(txtNumero, gbc);
        
        // Tipo
        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 3;
        cmbTipo = new JComboBox<>(new String[]{"Terrestre", "Aereo", "Maritimo"});
        cmbTipo.setSelectedItem("Maritimo");
        panelFormulario.add(cmbTipo, gbc);
        
        // Cliente
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Cliente:"), gbc);
        gbc.gridx = 1;
        txtCliente = new JTextField(15);
        panelFormulario.add(txtCliente, gbc);
        
        // Distancia en Km
        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Distancia en Km:"), gbc);
        gbc.gridx = 3;
        txtDistancia = new JTextField(15);
        panelFormulario.add(txtDistancia, gbc);
        
        // Peso
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Peso:"), gbc);
        gbc.gridx = 1;
        txtPeso = new JTextField(15);
        panelFormulario.add(txtPeso, gbc);
        
        // Botones
        gbc.gridx = 2; gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAgregar = new JButton("Guardar");
        btnAgregar.setBackground(new Color(70, 130, 180));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(180, 180, 180));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        
        panelBotones.add(btnAgregar);
        panelBotones.add(btnCancelar);
        panelFormulario.add(panelBotones, gbc);
        
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);
        
        // Tabla de envíos
        String[] columnas = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaEnvios = new JTable(modeloTabla);
        tablaEnvios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEnvios.getTableHeader().setReorderingAllowed(false);
        
        // Configurar anchos de columnas
        tablaEnvios.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(1).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaEnvios.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(4).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        JScrollPane scrollTabla = new JScrollPane(tablaEnvios);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        add(scrollTabla, BorderLayout.CENTER);
        
        // Configurar eventos
        configurarEventos(btnCancelar);
    }
    
    /**
     * Configura los eventos de los botones
     */
    private void configurarEventos(JButton btnCancelar) {
        // Botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEnvio();
            }
        });
        
        // Botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });
        
        // Doble click en tabla para eliminar
        tablaEnvios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    retirarEnvioSeleccionado();
                }
            }
        });
    }
    
    /**
     * Agrega un nuevo envío
     */
    private void agregarEnvio() {
        try {
            String numero = txtNumero.getText().trim();
            String cliente = txtCliente.getText().trim();
            String pesoStr = txtPeso.getText().trim();
            String distanciaStr = txtDistancia.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();
            
            // Validaciones
            if (numero.isEmpty() || cliente.isEmpty() || pesoStr.isEmpty() || distanciaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Todos los campos son obligatorios", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double peso = Double.parseDouble(pesoStr);
            double distancia = Double.parseDouble(distanciaStr);
            
            if (peso <= 0 || distancia <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El peso y la distancia deben ser mayores a cero", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear envío usando la fábrica (DRY principle)
            Envio nuevoEnvio;
            try {
                nuevoEnvio = FabricaEnvios.crearEnvioDesdeString(tipo, cliente, numero, peso, distancia);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al crear envío: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Agregar al servicio de logística
            if (servicioLogistica.agregarEnvio(nuevoEnvio)) {
                // Agregar a la tabla
                modeloTabla.addRow(new Object[]{
                    nuevoEnvio.getTipoEnvio(),
                    nuevoEnvio.getCodigoEnvio(),
                    nuevoEnvio.getCliente(),
                    String.format("%.1f", nuevoEnvio.getPesoKg()),
                    String.format("%.1f", nuevoEnvio.getDistanciaKm()),
                    String.format("%.1f", nuevoEnvio.getCosto())
                });
                
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, 
                    "Envío agregado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Ya existe un envío con el código " + numero, 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "El peso y la distancia deben ser números válidos", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Retira el envío seleccionado en la tabla
     */
    private void retirarEnvioSeleccionado() {
        int filaSeleccionada = tablaEnvios.getSelectedRow();
        
        if (filaSeleccionada >= 0) {
            String codigo = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
            
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea retirar el envío " + codigo + "?", 
                "Confirmar", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (servicioLogistica.retirarEnvio(codigo)) {
                    modeloTabla.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(this, 
                        "Envío retirado exitosamente", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un envío de la tabla", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Limpia el formulario
     */
    private void limpiarFormulario() {
        txtNumero.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipo.setSelectedIndex(0);
        txtNumero.requestFocus();
    }
    
    /**
     * Configura la ventana principal
     */
    private void configurarVentana() {
        setTitle("Operador Logístico");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Agregar datos de ejemplo
        agregarDatosEjemplo();
    }
    
    /**
     * Agrega datos de ejemplo a la tabla
     */
    private void agregarDatosEjemplo() {
        Envio e1 = new Terrestre("Polímeros Col...", "10001", 1200.0, 400.0);
        Envio e2 = new Terrestre("Textiles Peñalta", "10002", 500.0, 600.0);
        Envio e3 = new Aereo("Flores Colombi...", "10003", 1500.0, 2000.0);
        
        servicioLogistica.agregarEnvio(e1);
        servicioLogistica.agregarEnvio(e2);
        servicioLogistica.agregarEnvio(e3);
        
        modeloTabla.addRow(new Object[]{
            e1.getTipoEnvio(), e1.getCodigoEnvio(), e1.getCliente(),
            String.format("%.1f", e1.getPesoKg()),
            String.format("%.1f", e1.getDistanciaKm()),
            String.format("%.1f", e1.getCosto())
        });
        
        modeloTabla.addRow(new Object[]{
            e2.getTipoEnvio(), e2.getCodigoEnvio(), e2.getCliente(),
            String.format("%.1f", e2.getPesoKg()),
            String.format("%.1f", e2.getDistanciaKm()),
            String.format("%.1f", e2.getCosto())
        });
        
        modeloTabla.addRow(new Object[]{
            e3.getTipoEnvio(), e3.getCodigoEnvio(), e3.getCliente(),
            String.format("%.1f", e3.getPesoKg()),
            String.format("%.1f", e3.getDistanciaKm()),
            String.format("%.1f", e3.getCosto())
        });
    }
    
    /**
     * Método principal
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfazLogistica ventana = new InterfazLogistica();
                ventana.setVisible(true);
            }
        });
    }
}

