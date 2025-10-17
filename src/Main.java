import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal - Punto de entrada del sistema
 * Principio S (Single Responsibility): Solo maneja el inicio de la aplicación
 * Principio D (Dependency Inversion): Usa interfaces y abstracciones
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static IServicioLogistica servicioLogistica;
    
    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }
    
    /**
     * Muestra el menú principal para seleccionar el modo de operación
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE ENVÍOS - LOGÍSTICA        ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println("\nSeleccione el modo de operación:");
        System.out.println("1. Interfaz Gráfica (GUI)");
        System.out.println("2. Interfaz por Consola");
        System.out.println("3. Salir");
        System.out.print("\nOpción: ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1:
                ejecutarInterfazGrafica();
                break;
            case 2:
                ejecutarInterfazConsola();
                break;
            case 3:
                System.out.println("\n*** Gracias por usar el sistema de logística ***");
                scanner.close();
                break;
            default:
                System.out.println("\n*** Opción inválida ***");
                mostrarMenuPrincipal();
        }
    }
    
    /**
     * Ejecuta la interfaz gráfica
     */
    private static void ejecutarInterfazGrafica() {
        scanner.close();
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
    
    /**
     * Ejecuta la interfaz por consola
     */
    private static void ejecutarInterfazConsola() {
        // Inicializar servicios con inyección de dependencias
        IRepositorioEnvios repositorio = new RepositorioEnvios();
        servicioLogistica = new ServicioLogistica(repositorio);
        
        int opcion;
        
        do {
            mostrarMenu();
            opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    agregarEnvio();
                    break;
                case 2:
                    retirarEnvio();
                    break;
                case 3:
                    listarEnvios();
                    break;
                case 4:
                    System.out.println("\n*** Gracias por usar el sistema de logística ***");
                    break;
                default:
                    System.out.println("\n*** Opción inválida. Intente nuevamente ***");
            }
            
        } while (opcion != 4);
        
        scanner.close();
        System.out.println("\n*** Presione Enter para continuar ***");
    }
    
    /**
     * Muestra el menú principal
     */
    private static void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE ENVÍOS");
        System.out.println("========================================");
        System.out.println("1. Agregar envío");
        System.out.println("2. Retirar envío");
        System.out.println("3. Listar envíos");
        System.out.println("4. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Lee una opción del menú
     * @return Opción seleccionada
     */
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Agrega un nuevo envío al sistema
     */
    private static void agregarEnvio() {
        System.out.println("\n========================================");
        System.out.println("         AGREGAR NUEVO ENVÍO");
        System.out.println("========================================");
        
        // Leer datos comunes
        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();
        
        System.out.print("Código del envío: ");
        String codigo = scanner.nextLine();
        
        System.out.print("Peso en kilogramos: ");
        double peso = leerDouble();
        
        System.out.print("Distancia en kilómetros: ");
        double distancia = leerDouble();
        
        // Seleccionar tipo de envío
        System.out.println("\nSeleccione el tipo de envío:");
        System.out.println("1. Terrestre ($1500/km + $2000/kg)");
        System.out.println("2. Aéreo ($5000/km + $4000/kg)");
        System.out.println("3. Marítimo ($800/km + $1000/kg)");
        System.out.print("Opción: ");
        
        int tipoEnvio = leerOpcion();
        Envio nuevoEnvio;
        
        try {
            FabricaEnvios.TipoEnvio tipo;
            switch (tipoEnvio) {
                case 1:
                    tipo = FabricaEnvios.TipoEnvio.TERRESTRE;
                    break;
                case 2:
                    tipo = FabricaEnvios.TipoEnvio.AEREO;
                    break;
                case 3:
                    tipo = FabricaEnvios.TipoEnvio.MARITIMO;
                    break;
                default:
                    System.out.println("\n*** Tipo de envío inválido ***");
                    return;
            }
            
            nuevoEnvio = FabricaEnvios.crearEnvio(tipo, cliente, codigo, peso, distancia);
        } catch (IllegalArgumentException e) {
            System.out.println("\n*** Error al crear envío: " + e.getMessage() + " ***");
            return;
        }
        
        if (servicioLogistica.agregarEnvio(nuevoEnvio)) {
            System.out.println("\n*** Envío agregado exitosamente ***");
            System.out.println(nuevoEnvio);
        } else {
            System.out.println("\n*** Error: Ya existe un envío con el código " + codigo + " ***");
        }
    }
    
    /**
     * Retira un envío del sistema
     */
    private static void retirarEnvio() {
        System.out.println("\n========================================");
        System.out.println("           RETIRAR ENVÍO");
        System.out.println("========================================");
        
        if (servicioLogistica.listarEnvios().isEmpty()) {
            System.out.println("\n*** No hay envíos registrados ***");
            return;
        }
        
        System.out.print("Ingrese el código del envío a retirar: ");
        String codigo = scanner.nextLine();
        
        Envio envio = servicioLogistica.buscarEnvio(codigo);
        
        if (envio != null) {
            System.out.println("\nEnvío encontrado:");
            System.out.println(envio);
            System.out.print("\n¿Confirma que desea retirar este envío? (S/N): ");
            String confirmacion = scanner.nextLine();
            
            if (confirmacion.equalsIgnoreCase("S")) {
                servicioLogistica.retirarEnvio(codigo);
                System.out.println("\n*** Envío retirado exitosamente ***");
            } else {
                System.out.println("\n*** Operación cancelada ***");
            }
        } else {
            System.out.println("\n*** No se encontró un envío con el código " + codigo + " ***");
        }
    }
    
    /**
     * Lista todos los envíos registrados
     */
    private static void listarEnvios() {
        if (servicioLogistica.listarEnvios().isEmpty()) {
            System.out.println("\n*** No hay envíos registrados ***");
            return;
        }
        
        System.out.println("\n========================================");
        System.out.println("         LISTADO DE ENVÍOS");
        System.out.println("========================================");
        int i = 1;
        for (Envio envio : servicioLogistica.listarEnvios()) {
            System.out.println(i + ". " + envio);
            i++;
        }
        System.out.println("========================================");
        System.out.println("Total de envíos: " + servicioLogistica.listarEnvios().size());
        System.out.printf("Total en tarifas: $%.2f\n", servicioLogistica.calcularTotalTarifas());
    }
    
    /**
     * Lee un número double con validación
     * @return Valor double ingresado
     */
    private static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Intente nuevamente: ");
            }
        }
    }
}

