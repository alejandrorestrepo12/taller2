import java.util.*;

/**
 * Clase que gestiona la colección de envíos.
 * Implementa el principio de responsabilidad única (SRP) al encargarse
 * únicamente de la gestión de envíos.
 * Aplica el principio de inversión de dependencias (DIP) al trabajar
 * con la abstracción Envio en lugar de implementaciones concretas.
 */
public class Logistica {
    
    // Colección genérica para almacenar envíos (buena práctica)
    private List<Envio> envios;
    private int contadorCodigo;
    
    /**
     * Constructor de la clase Logistica
     */
    public Logistica() {
        this.envios = new ArrayList<>();
        this.contadorCodigo = 10000; // Comienza desde 10001
    }
    
    /**
     * Agrega un nuevo envío a la colección.
     * Aplica el principio abierto/cerrado (OCP) al permitir agregar
     * nuevos tipos de envío sin modificar esta clase.
     * @param envio El envío a agregar
     * @return true si se agregó exitosamente
     */
    public boolean agregarEnvio(Envio envio) {
        if (envio != null && !existeCodigo(envio.getCodigo())) {
            envios.add(envio);
            return true;
        }
        return false;
    }
    
    /**
     * Crea y agrega un nuevo envío según el tipo especificado.
     * @param tipo Tipo de envío (Terrestre, Aereo, Maritimo)
     * @param cliente Nombre del cliente
     * @param peso Peso en kilogramos
     * @param distancia Distancia en kilómetros
     * @return true si se agregó exitosamente
     */
    public boolean agregarEnvio(String tipo, String cliente, double peso, double distancia) {
        contadorCodigo++;
        String codigo = String.valueOf(contadorCodigo);
        
        Envio envio = null;
        switch (tipo.toLowerCase()) {
            case "terrestre":
                envio = new Terrestre(codigo, cliente, peso, distancia);
                break;
            case "aereo":
                envio = new Aereo(codigo, cliente, peso, distancia);
                break;
            case "maritimo":
                envio = new Maritimo(codigo, cliente, peso, distancia);
                break;
            default:
                return false;
        }
        
        return agregarEnvio(envio);
    }
    
    /**
     * Retira un envío de la colección por código.
     * @param codigo Código del envío a retirar
     * @return true si se retiró exitosamente
     */
    public boolean retirarEnvio(String codigo) {
        return envios.removeIf(envio -> envio.getCodigo().equals(codigo));
    }
    
    /**
     * Lista todos los envíos en la colección.
     * @return Lista de todos los envíos
     */
    public List<Envio> listarEnvios() {
        return new ArrayList<>(envios); // Retorna una copia para mantener encapsulamiento
    }
    
    /**
     * Obtiene un envío por su código.
     * @param codigo Código del envío
     * @return El envío encontrado o null si no existe
     */
    public Envio buscarEnvio(String codigo) {
        return envios.stream()
                .filter(envio -> envio.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Verifica si existe un envío con el código especificado.
     * @param codigo Código a verificar
     * @return true si existe un envío con ese código
     */
    private boolean existeCodigo(String codigo) {
        return envios.stream()
                .anyMatch(envio -> envio.getCodigo().equals(codigo));
    }
    
    /**
     * Obtiene el número total de envíos.
     * @return Cantidad de envíos en la colección
     */
    public int getCantidadEnvios() {
        return envios.size();
    }
    
    /**
     * Calcula el costo total de todos los envíos.
     * Utiliza polimorfismo para calcular la tarifa de cada envío.
     * @return Suma de todas las tarifas
     */
    public double calcularCostoTotal() {
        return envios.stream()
                .mapToDouble(Envio::calcularTarifa)
                .sum();
    }
    
    /**
     * Genera un reporte de todos los envíos.
     * @return String con el reporte formateado
     */
    public String generarReporte() {
        if (envios.isEmpty()) {
            return "No hay envíos registrados.";
        }
        
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE ENVÍOS ===\n");
        reporte.append("Tipo | Código | Cliente | Peso | Distancia | Costo\n");
        reporte.append("--------------------------------------------------------\n");
        
        for (Envio envio : envios) {
            reporte.append(envio.toString()).append("\n");
        }
        
        reporte.append("--------------------------------------------------------\n");
        reporte.append(String.format("Total de envíos: %d\n", getCantidadEnvios()));
        reporte.append(String.format("Costo total: $%.2f", calcularCostoTotal()));
        
        return reporte.toString();
    }
}
