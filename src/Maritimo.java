/**
 * Clase que representa un envío marítimo
 * Tarifa base: $800 por km
 * Recargo: $1000 por kg
 * Principio S (Single Responsibility): Solo maneja lógica de envío marítimo
 * Principio O (Open/Closed): Extiende sin modificar la clase base
 * Principio L (Liskov Substitution): Sustituible por Envio
 */
public class Maritimo extends Envio {
    private static final double TARIFA_BASE_POR_KM = 800.0;
    private static final double RECARGO_POR_KG = 1000.0;
    
    /**
     * Constructor de Envio Marítimo
     * @param cliente Nombre del remitente
     * @param codigoEnvio Código único del envío
     * @param pesoKg Peso en kilogramos
     * @param distanciaKm Distancia en kilómetros
     */
    public Maritimo(String cliente, String codigoEnvio, double pesoKg, double distanciaKm) {
        super(cliente, codigoEnvio, pesoKg, distanciaKm);
    }
    
    @Override
    public double calcularTarifa() {
        return (TARIFA_BASE_POR_KM * distanciaKm) + (RECARGO_POR_KG * pesoKg);
    }
    
    @Override
    public String getTipoEnvio() {
        return "Marítimo";
    }
}

