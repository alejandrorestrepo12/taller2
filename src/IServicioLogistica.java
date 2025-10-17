import java.util.List;

/**
 * Interfaz para el servicio de logística
 * Principio D (Dependency Inversion): Abstraer la lógica de negocio
 * Principio I (Interface Segregation): Interfaz específica para operaciones de logística
 */
public interface IServicioLogistica {
    /**
     * Agrega un nuevo envío
     * @param envio Envío a agregar
     * @return true si se agregó exitosamente
     */
    boolean agregarEnvio(Envio envio);
    
    /**
     * Retira un envío
     * @param codigoEnvio Código del envío a retirar
     * @return true si se retiró exitosamente
     */
    boolean retirarEnvio(String codigoEnvio);
    
    /**
     * Busca un envío por código
     * @param codigoEnvio Código a buscar
     * @return El envío encontrado o null
     */
    Envio buscarEnvio(String codigoEnvio);
    
    /**
     * Lista todos los envíos
     * @return Lista de envíos
     */
    List<Envio> listarEnvios();
    
    /**
     * Calcula el total de tarifas
     * @return Suma de todas las tarifas
     */
    double calcularTotalTarifas();
}

