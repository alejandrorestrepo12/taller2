/**
 * Interfaz para el cálculo de tarifas de envíos
 * Principio I (Interface Segregation): Interfaz específica para cálculo de tarifas
 */
public interface ICalculadoraTarifa {
    /**
     * Calcula la tarifa del envío
     * @return Tarifa total calculada
     */
    double calcularTarifa();
}

