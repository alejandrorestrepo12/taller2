/**
 * Clase que representa un envío aéreo
 * Tarifa base: $5000 por km
 * Recargo: $4000 por kg
 * Principio S (Single Responsibility): Solo maneja lógica de envío aéreo
 * Principio O (Open/Closed): Extiende sin modificar la clase base
 * Principio L (Liskov Substitution): Sustituible por Envio
 */
public class Aereo extends Envio {
    private static final double TARIFA_BASE_POR_KM = 5000.0;
    private static final double RECARGO_POR_KG = 4000.0;
    
    /**
     * Constructor de Envio Aéreo
     * @param cliente Nombre del remitente
     * @param codigoEnvio Código único del envío
     * @param pesoKg Peso en kilogramos
     * @param distanciaKm Distancia en kilómetros
     */
    public Aereo(String cliente, String codigoEnvio, double pesoKg, double distanciaKm) {
        super(cliente, codigoEnvio, pesoKg, distanciaKm);
    }
    
    @Override
    public double calcularTarifa() {
        return (TARIFA_BASE_POR_KM * distanciaKm) + (RECARGO_POR_KG * pesoKg);
    }
    
    @Override
    public String getTipoEnvio() {
        return "Aéreo";
    }
}

