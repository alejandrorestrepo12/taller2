import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Clase principal del sistema de logística.
 * Punto de entrada de la aplicación que inicia la interfaz gráfica.
 * Aplica el principio de responsabilidad única al encargarse únicamente
 * del inicio de la aplicación.
 */
public class Main {
    
    /**
     * Método principal de la aplicación.
     * Configura el look and feel del sistema y lanza la interfaz gráfica.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Configurar el look and feel del sistema para mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Si no se puede configurar el look and feel del sistema,
            // se usa el look and feel por defecto de Java
            System.err.println("No se pudo configurar el look and feel del sistema: " + e.getMessage());
        }
        
        // Ejecutar la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Crear y mostrar la ventana principal
                    InterfazLogistica ventana = new InterfazLogistica();
                    ventana.setVisible(true);
                    
                    // Mensaje de bienvenida
                    System.out.println("=== SISTEMA DE LOGÍSTICA ===");
                    System.out.println("Aplicación iniciada correctamente.");
                    System.out.println("Funcionalidades disponibles:");
                    System.out.println("- Agregar envíos (Terrestre, Aéreo, Marítimo)");
                    System.out.println("- Retirar envíos");
                    System.out.println("- Listar envíos");
                    System.out.println("- Generar reportes");
                    System.out.println("==============================");
                    
                } catch (Exception e) {
                    System.err.println("Error al iniciar la aplicación: " + e.getMessage());
                    e.printStackTrace();
                    
                    // Mostrar mensaje de error al usuario
                    javax.swing.JOptionPane.showMessageDialog(
                        null, 
                        "Error al iniciar la aplicación: " + e.getMessage(),
                        "Error de Inicio", 
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}