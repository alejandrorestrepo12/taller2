import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio de envíos
 * Principio S (Single Responsibility): Solo gestiona el almacenamiento de envíos
 * Principio D (Dependency Inversion): Implementa una interfaz
 */
public class RepositorioEnvios implements IRepositorioEnvios {
    private final List<Envio> envios;
    
    /**
     * Constructor del repositorio
     */
    public RepositorioEnvios() {
        this.envios = new ArrayList<>();
    }
    
    @Override
    public boolean agregar(Envio envio) {
        if (envio == null) {
            return false;
        }
        
        // Verificar que no exista un envío con el mismo código
        if (buscarPorCodigo(envio.getCodigoEnvio()) != null) {
            return false;
        }
        
        return envios.add(envio);
    }
    
    @Override
    public boolean retirar(String codigoEnvio) {
        Envio envio = buscarPorCodigo(codigoEnvio);
        if (envio != null) {
            return envios.remove(envio);
        }
        return false;
    }
    
    @Override
    public Envio buscarPorCodigo(String codigoEnvio) {
        if (codigoEnvio == null) {
            return null;
        }
        
        for (Envio envio : envios) {
            if (envio.getCodigoEnvio().equals(codigoEnvio)) {
                return envio;
            }
        }
        return null;
    }
    
    @Override
    public List<Envio> obtenerTodos() {
        // Retorna una copia para mantener encapsulamiento
        return new ArrayList<>(envios);
    }
    
    @Override
    public int obtenerCantidad() {
        return envios.size();
    }
}

