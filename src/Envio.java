/**
 * Clase abstracta que representa un envío genérico.
 * Implementa el principio de responsabilidad única (SRP) al definir
 * la estructura común para todos los tipos de envío.
 */
public abstract class Envio {
    // Atributos privados para implementar encapsulamiento
    private String codigo;
    private String cliente;
    private double peso;
    private double distancia;
    
    /**
     * Constructor de la clase Envio
     * @param codigo Código único del envío
     * @param cliente Nombre del cliente
     * @param peso Peso en kilogramos
     * @param distancia Distancia en kilómetros
     */
    public Envio(String codigo, String cliente, double peso, double distancia) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.peso = peso;
        this.distancia = distancia;
    }
    
    // Getters para acceder a los atributos privados
    public String getCodigo() {
        return codigo;
    }
    
    public String getCliente() {
        return cliente;
    }
    
    public double getPeso() {
        return peso;
    }
    
    public double getDistancia() {
        return distancia;
    }
    
    /**
     * Método abstracto para calcular la tarifa.
     * Cada tipo de envío implementará su propia lógica de cálculo.
     * Aplica el principio de polimorfismo.
     * @return Costo total del envío
     */
    public abstract double calcularTarifa();
    
    /**
     * Método para obtener el tipo de envío
     * @return Nombre de la clase (tipo de envío)
     */
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public String toString() {
        return String.format("%s | %s | %s | %.1f | %.1f | %.1f",
                getTipo(), codigo, cliente, peso, distancia, calcularTarifa());
    }
}
