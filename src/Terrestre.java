/**
 * Clase que representa un envío terrestre
 * Tarifa base: $1500 por km
 * Recargo: $2000 por kg
 * Principio S (Single Responsibility): Solo maneja lógica de envío terrestre
 * Principio O (Open/Closed): Extiende sin modificar la clase base
 * Principio L (Liskov Substitution): Sustituible por Envio
 */
public class Terrestre extends Envio {
    private static final double TARIFA_BASE_POR_KM = 1500.0;
    private static final double RECARGO_POR_KG = 2000.0;
    
    /**
     * Constructor de Envio Terrestre
     * @param cliente Nombre del remitente
     * @param codigoEnvio Código único del envío
     * @param pesoKg Peso en kilogramos
     * @param distanciaKm Distancia en kilómetros
     */
    public Terrestre(String cliente, String codigoEnvio, double pesoKg, double distanciaKm) {
        super(cliente, codigoEnvio, pesoKg, distanciaKm);
    }
    
    @Override
    public double calcularTarifa() {
        return (TARIFA_BASE_POR_KM * distanciaKm) + (RECARGO_POR_KG * pesoKg);
    }
    
    @Override
    public String getTipoEnvio() {
        return "Terrestre";
    }
}

