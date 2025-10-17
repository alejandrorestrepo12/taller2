import java.util.List;

/**
 * Servicio de logística que gestiona las operaciones de envíos
 * Principio S (Single Responsibility): Solo maneja la lógica de negocio
 * Principio D (Dependency Inversion): Depende de abstracciones (IRepositorioEnvios)
 * Principio O (Open/Closed): Abierto a extensión, cerrado a modificación
 */
public class ServicioLogistica implements IServicioLogistica {
    private final IRepositorioEnvios repositorio;
    
    /**
     * Constructor con inyección de dependencias
     * @param repositorio Repositorio de envíos
     */
    public ServicioLogistica(IRepositorioEnvios repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("El repositorio no puede ser nulo");
        }
        this.repositorio = repositorio;
    }
    
    @Override
    public boolean agregarEnvio(Envio envio) {
        if (envio == null) {
            return false;
        }
        return repositorio.agregar(envio);
    }
    
    @Override
    public boolean retirarEnvio(String codigoEnvio) {
        if (codigoEnvio == null || codigoEnvio.trim().isEmpty()) {
            return false;
        }
        return repositorio.retirar(codigoEnvio);
    }
    
    @Override
    public Envio buscarEnvio(String codigoEnvio) {
        if (codigoEnvio == null || codigoEnvio.trim().isEmpty()) {
            return null;
        }
        return repositorio.buscarPorCodigo(codigoEnvio);
    }
    
    @Override
    public List<Envio> listarEnvios() {
        return repositorio.obtenerTodos();
    }
    
    @Override
    public double calcularTotalTarifas() {
        double total = 0.0;
        for (Envio envio : repositorio.obtenerTodos()) {
            total += envio.calcularTarifa();
        }
        return total;
    }
    
    /**
     * Obtiene la cantidad de envíos
     * @return Cantidad de envíos registrados
     */
    public int obtenerCantidadEnvios() {
        return repositorio.obtenerCantidad();
    }
}

