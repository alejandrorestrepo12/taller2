/**
 * Clase abstracta base para representar un envío
 * Contiene los atributos comunes a todos los tipos de envío
 * Principio S (Single Responsibility): Solo maneja datos de envío
 * Principio L (Liskov Substitution): Puede ser sustituida por cualquier subtipo
 */
public abstract class Envio implements ICalculadoraTarifa {
    protected String cliente;
    protected String codigoEnvio;
    protected double pesoKg;
    protected double distanciaKm;
    
    /**
     * Constructor de Envio
     * @param cliente Nombre del remitente
     * @param codigoEnvio Código único del envío
     * @param pesoKg Peso en kilogramos
     * @param distanciaKm Distancia en kilómetros
     */
    public Envio(String cliente, String codigoEnvio, double pesoKg, double distanciaKm) {
        this.cliente = cliente;
        this.codigoEnvio = codigoEnvio;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
    }
    
    /**
     * Método abstracto para calcular la tarifa del envío
     * Cada tipo de envío implementará su propia lógica
     * @return Tarifa total del envío
     */
    public abstract double calcularTarifa();
    
    /**
     * Obtiene el costo del envío (alias de calcularTarifa)
     * @return Costo total del envío
     */
    public double getCosto() {
        return calcularTarifa();
    }
    
    /**
     * Método abstracto para obtener el tipo de envío
     * @return String con el tipo de envío
     */
    public abstract String getTipoEnvio();
    
    // Getters
    public String getCliente() {
        return cliente;
    }
    
    public String getCodigoEnvio() {
        return codigoEnvio;
    }
    
    public double getPesoKg() {
        return pesoKg;
    }
    
    public double getDistanciaKm() {
        return distanciaKm;
    }
    
    // Setters
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }
    
    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }
    
    @Override
    public String toString() {
        return String.format("Tipo: %s | Código: %s | Cliente: %s | Peso: %.1f kg | Distancia: %.1f km | Costo: $%.1f",
                getTipoEnvio(), codigoEnvio, cliente, pesoKg, distanciaKm, getCosto());
    }
}

