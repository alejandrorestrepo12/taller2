import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Clase que implementa la interfaz gráfica de usuario para el sistema de logística.
 * Aplica el principio de separación de responsabilidades al separar
 * la lógica de negocio de la interfaz de usuario.
 */
public class InterfazLogistica extends JFrame {
    
    // Componentes de la interfaz
    private JTextField txtCodigo;
    private JComboBox<String> cmbTipo;
    private JTextField txtCliente;
    private JTextField txtDistancia;
    private JTextField txtPeso;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnRetirar;
    private JTable tablaEnvios;
    private DefaultTableModel modeloTabla;
    
    // Lógica de negocio
    private Logistica logistica;
    
    /**
     * Constructor de la interfaz
     */
    public InterfazLogistica() {
        this.logistica = new Logistica();
        inicializarComponentes();
        configurarVentana();
        cargarDatosEjemplo();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Configuración de la ventana
        setTitle("Operador Logístico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel superior con campos de entrada
        JPanel panelEntrada = crearPanelEntrada();
        add(panelEntrada, BorderLayout.NORTH);
        
        // Panel central con tabla de envíos
        JPanel panelTabla = crearPanelTabla();
        add(panelTabla, BorderLayout.CENTER);
        
        // Panel inferior con botones adicionales
        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel con los campos de entrada
     */
    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Envío"));
        panel.setBackground(new Color(240, 248, 255));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Número (Código)
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(15);
        txtCodigo.setEditable(false);
        txtCodigo.setBackground(Color.WHITE);
        panel.add(txtCodigo, gbc);
        
        // Tipo
        gbc.gridx = 2;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 3;
        cmbTipo = new JComboBox<>(new String[]{"Terrestre", "Aereo", "Maritimo"});
        cmbTipo.setSelectedIndex(2); // Marítimo por defecto como en la imagen
        panel.add(cmbTipo, gbc);
        
        // Cliente
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Cliente:"), gbc);
        gbc.gridx = 1;
        txtCliente = new JTextField("Cafe de Colombi", 15);
        panel.add(txtCliente, gbc);
        
        // Distancia
        gbc.gridx = 2;
        panel.add(new JLabel("Distancia en Km:"), gbc);
        gbc.gridx = 3;
        txtDistancia = new JTextField("3000", 15);
        panel.add(txtDistancia, gbc);
        
        // Peso
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Peso:"), gbc);
        gbc.gridx = 1;
        txtPeso = new JTextField("3000", 15);
        panel.add(txtPeso, gbc);
        
        // Botones Guardar y Cancelar
        gbc.gridx = 2; gbc.gridy = 2;
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEnvio();
            }
        });
        panel.add(btnGuardar, gbc);
        
        gbc.gridx = 3;
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 182, 193));
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        panel.add(btnCancelar, gbc);
        
        return panel;
    }
    
    /**
     * Crea el panel con la tabla de envíos
     */
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Envíos Registrados"));
        
        // Configuración de la tabla
        String[] columnas = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla de solo lectura
            }
        };
        
        tablaEnvios = new JTable(modeloTabla);
        tablaEnvios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEnvios.getTableHeader().setReorderingAllowed(false);
        
        // Configurar ancho de columnas
        tablaEnvios.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(1).setPreferredWidth(60);
        tablaEnvios.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablaEnvios.getColumnModel().getColumn(3).setPreferredWidth(60);
        tablaEnvios.getColumnModel().getColumn(4).setPreferredWidth(80);
        tablaEnvios.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        JScrollPane scrollPane = new JScrollPane(tablaEnvios);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crea el panel con botones adicionales
     */
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(240, 248, 255));
        
        btnRetirar = new JButton("Retirar Envío Seleccionado");
        btnRetirar.setBackground(new Color(255, 165, 0));
        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirarEnvio();
            }
        });
        panel.add(btnRetirar);
        
        JButton btnReporte = new JButton("Generar Reporte");
        btnReporte.setBackground(new Color(135, 206, 250));
        btnReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarReporte();
            }
        });
        panel.add(btnReporte);
        
        return panel;
    }
    
    /**
     * Configura las propiedades de la ventana
     */
    private void configurarVentana() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Icono de la ventana (simulado con texto)
        setIconImage(new ImageIcon().getImage());
    }
    
    /**
     * Carga datos de ejemplo como se muestra en la imagen
     */
    private void cargarDatosEjemplo() {
        // Datos de ejemplo basados en la imagen
        logistica.agregarEnvio("Terrestre", "Polimeros Col...", 1200.0, 400.0);
        logistica.agregarEnvio("Terrestre", "Textiles Pepalfa", 500.0, 600.0);
        logistica.agregarEnvio("Aereo", "Flores Colombi...", 1500.0, 2000.0);
        
        actualizarTabla();
    }
    
    /**
     * Guarda un nuevo envío
     */
    private void guardarEnvio() {
        try {
            String tipo = (String) cmbTipo.getSelectedItem();
            String cliente = txtCliente.getText().trim();
            double peso = Double.parseDouble(txtPeso.getText().trim());
            double distancia = Double.parseDouble(txtDistancia.getText().trim());
            
            if (cliente.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo Cliente es obligatorio.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (peso <= 0 || distancia <= 0) {
                JOptionPane.showMessageDialog(this, "El peso y la distancia deben ser valores positivos.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean agregado = logistica.agregarEnvio(tipo, cliente, peso, distancia);
            
            if (agregado) {
                JOptionPane.showMessageDialog(this, "Envío agregado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el envío.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos para peso y distancia.", 
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Retira el envío seleccionado
     */
    private void retirarEnvio() {
        int filaSeleccionada = tablaEnvios.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un envío para retirar.", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String codigo = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea retirar el envío con código " + codigo + "?", 
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean retirado = logistica.retirarEnvio(codigo);
            
            if (retirado) {
                JOptionPane.showMessageDialog(this, "Envío retirado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al retirar el envío.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Actualiza la tabla con los datos actuales
     */
    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        List<Envio> envios = logistica.listarEnvios();
        for (Envio envio : envios) {
            Object[] fila = {
                envio.getTipo(),
                envio.getCodigo(),
                envio.getCliente(),
                envio.getPeso(),
                envio.getDistancia(),
                envio.calcularTarifa()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    /**
     * Muestra el reporte de envíos
     */
    private void mostrarReporte() {
        String reporte = logistica.generarReporte();
        JTextArea areaTexto = new JTextArea(reporte);
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Reporte de Envíos", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Limpia los campos de entrada
     */
    private void limpiarCampos() {
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipo.setSelectedIndex(2); // Marítimo por defecto
    }
}
