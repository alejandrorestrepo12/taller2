import java.util.List;

/**
 * Interfaz para el repositorio de envíos
 * Principio D (Dependency Inversion): Abstraer el acceso a datos
 * Principio I (Interface Segregation): Interfaz específica para gestión de envíos
 */
public interface IRepositorioEnvios {
    /**
     * Agrega un envío al repositorio
     * @param envio Envío a agregar
     * @return true si se agregó exitosamente, false si ya existe
     */
    boolean agregar(Envio envio);
    
    /**
     * Retira un envío del repositorio
     * @param codigoEnvio Código del envío a retirar
     * @return true si se retiró exitosamente, false si no se encontró
     */
    boolean retirar(String codigoEnvio);
    
    /**
     * Busca un envío por su código
     * @param codigoEnvio Código del envío a buscar
     * @return El envío encontrado o null si no existe
     */
    Envio buscarPorCodigo(String codigoEnvio);
    
    /**
     * Obtiene todos los envíos
     * @return Lista de envíos
     */
    List<Envio> obtenerTodos();
    
    /**
     * Obtiene la cantidad de envíos
     * @return Cantidad de envíos registrados
     */
    int obtenerCantidad();
}

