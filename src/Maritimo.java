/**
 * Clase que representa un envío marítimo.
 * Implementa el principio de sustitución de Liskov (LSP) ya que
 * puede ser usada como un Envio sin alterar el comportamiento esperado.
 */
public class Maritimo extends Envio {
    
    // Constantes para el cálculo de tarifa (principio DRY)
    private static final double TARIFA_BASE_POR_KM = 800.0;
    private static final double RECARGO_POR_KG = 1000.0;
    
    /**
     * Constructor de la clase Maritimo
     * @param codigo Código único del envío
     * @param cliente Nombre del cliente
     * @param peso Peso en kilogramos
     * @param distancia Distancia en kilómetros
     */
    public Maritimo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }
    
    /**
     * Implementa el cálculo de tarifa específico para envíos marítimos.
     * Aplica el principio de polimorfismo al sobrescribir el método abstracto.
     * @return Costo total del envío marítimo
     */
    @Override
    public double calcularTarifa() {
        return (getDistancia() * TARIFA_BASE_POR_KM) + (getPeso() * RECARGO_POR_KG);
    }
}
