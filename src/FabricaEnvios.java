/**
 * Fábrica de envíos
 * Principio O (Open/Closed): Facilita agregar nuevos tipos sin modificar código cliente
 * Principio S (Single Responsibility): Solo crea instancias de envíos
 */
public class FabricaEnvios {
    
    /**
     * Tipos de envío disponibles
     */
    public enum TipoEnvio {
        TERRESTRE,
        AEREO,
        MARITIMO
    }
    
    /**
     * Crea un envío según el tipo especificado
     * @param tipo Tipo de envío
     * @param cliente Nombre del cliente
     * @param codigo Código del envío
     * @param peso Peso en kilogramos
     * @param distancia Distancia en kilómetros
     * @return Instancia del envío creado
     * @throws IllegalArgumentException si el tipo no es válido
     */
    public static Envio crearEnvio(TipoEnvio tipo, String cliente, String codigo, 
                                   double peso, double distancia) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de envío no puede ser nulo");
        }
        
        switch (tipo) {
            case TERRESTRE:
                return new Terrestre(cliente, codigo, peso, distancia);
            case AEREO:
                return new Aereo(cliente, codigo, peso, distancia);
            case MARITIMO:
                return new Maritimo(cliente, codigo, peso, distancia);
            default:
                throw new IllegalArgumentException("Tipo de envío no soportado: " + tipo);
        }
    }
    
    /**
     * Crea un envío desde un string del tipo
     * @param tipoString String representando el tipo (case-insensitive)
     * @param cliente Nombre del cliente
     * @param codigo Código del envío
     * @param peso Peso en kilogramos
     * @param distancia Distancia en kilómetros
     * @return Instancia del envío creado
     * @throws IllegalArgumentException si el tipo no es válido
     */
    public static Envio crearEnvioDesdeString(String tipoString, String cliente, 
                                               String codigo, double peso, double distancia) {
        if (tipoString == null || tipoString.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de envío no puede ser nulo o vacío");
        }
        
        TipoEnvio tipo;
        switch (tipoString.toUpperCase()) {
            case "TERRESTRE":
                tipo = TipoEnvio.TERRESTRE;
                break;
            case "AEREO":
            case "AÉREO":
                tipo = TipoEnvio.AEREO;
                break;
            case "MARITIMO":
            case "MARÍTIMO":
                tipo = TipoEnvio.MARITIMO;
                break;
            default:
                throw new IllegalArgumentException("Tipo de envío no reconocido: " + tipoString);
        }
        
        return crearEnvio(tipo, cliente, codigo, peso, distancia);
    }
}

