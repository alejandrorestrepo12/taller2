# Sistema de Logística - Operador Logístico

## Descripción
Sistema de gestión de envíos que permite administrar diferentes tipos de transporte (Terrestre, Aéreo y Marítimo) aplicando principios SOLID y buenas prácticas de programación orientada a objetos.

## Características Principales

### Funcionalidades Implementadas
- ✅ **Agregar envío**: Crear nuevos envíos de tipo Terrestre, Aéreo o Marítimo
- ✅ **Retirar envío**: Eliminar envíos existentes por código
- ✅ **Listar envíos**: Visualizar todos los envíos en una tabla
- ✅ **Generar reportes**: Crear reportes detallados de envíos y costos

### Tipos de Envío
| Tipo | Tarifa Base por Km | Recargo por Kg |
|------|-------------------|----------------|
| Terrestre | $1,500 | $2,000 |
| Aéreo | $5,000 | $4,000 |
| Marítimo | $800 | $1,000 |

## Arquitectura del Sistema

### Principios SOLID Aplicados

#### 1. Single Responsibility Principle (SRP)
- **Envio**: Define la estructura común para todos los tipos de envío
- **Logistica**: Gestiona la colección de envíos
- **InterfazLogistica**: Maneja únicamente la interfaz gráfica
- **Main**: Responsable del inicio de la aplicación

#### 2. Open/Closed Principle (OCP)
- El sistema permite agregar nuevos tipos de envío sin modificar código existente
- La clase `Logistica` está abierta para extensión pero cerrada para modificación

#### 3. Liskov Substitution Principle (LSP)
- Las clases `Terrestre`, `Aereo` y `Maritimo` pueden ser usadas como `Envio` sin alterar el comportamiento

#### 4. Interface Segregation Principle (ISP)
- Interfaces específicas y cohesivas (método abstracto `calcularTarifa()`)

#### 5. Dependency Inversion Principle (DIP)
- La clase `Logistica` depende de la abstracción `Envio`, no de implementaciones concretas

### Buenas Prácticas Implementadas

#### Encapsulamiento
- Atributos privados con getters públicos
- Validación de datos en métodos públicos
- Colecciones genéricas (`List<Envio>`)

#### Nombres Descriptivos
- Convenciones Java estándar
- Nombres de clases, métodos y variables claros y descriptivos

#### Evitar Duplicación (DRY)
- Constantes para tarifas y recargos
- Métodos reutilizables en `Logistica`

#### Separación de Responsabilidades
- Lógica de negocio separada de la interfaz gráfica
- Clases especializadas para cada responsabilidad

#### Polimorfismo
- Cálculo de tarifas mediante polimorfismo
- Uso de colecciones genéricas con tipos abstractos

## Estructura del Proyecto

```
Exm 2 - Tecnicas/
├── Envio.java              # Clase abstracta base
├── Terrestre.java          # Implementación envío terrestre
├── Aereo.java             # Implementación envío aéreo
├── Maritimo.java          # Implementación envío marítimo
├── Logistica.java         # Gestión de colección de envíos
├── InterfazLogistica.java # Interfaz gráfica de usuario
├── Main.java              # Punto de entrada de la aplicación
└── README.md              # Documentación del proyecto
```

## Cómo Ejecutar

### Requisitos
- Java 8 o superior
- IDE compatible con Java (Eclipse, IntelliJ IDEA, NetBeans, etc.)

### Pasos de Ejecución

1. **Compilar el proyecto**:
   ```bash
   javac *.java
   ```

2. **Ejecutar la aplicación**:
   ```bash
   java Main
   ```

### Desde IDE
1. Abrir el proyecto en tu IDE favorito
2. Ejecutar la clase `Main`
3. La interfaz gráfica se abrirá automáticamente

## Uso de la Aplicación

### Interfaz Principal
La aplicación presenta una interfaz gráfica intuitiva con:

1. **Panel Superior**: Campos para ingresar datos del envío
   - Número (código automático)
   - Tipo (Terrestre, Aéreo, Marítimo)
   - Cliente
   - Distancia en Km
   - Peso en Kg

2. **Panel Central**: Tabla con todos los envíos registrados
   - Muestra tipo, código, cliente, peso, distancia y costo

3. **Panel Inferior**: Botones para acciones adicionales
   - Retirar envío seleccionado
   - Generar reporte

### Operaciones Disponibles

#### Agregar Envío
1. Seleccionar el tipo de envío
2. Ingresar datos del cliente
3. Especificar peso y distancia
4. Hacer clic en "Guardar"

#### Retirar Envío
1. Seleccionar un envío en la tabla
2. Hacer clic en "Retirar Envío Seleccionado"
3. Confirmar la eliminación

#### Generar Reporte
1. Hacer clic en "Generar Reporte"
2. Se mostrará un reporte detallado con:
   - Lista completa de envíos
   - Total de envíos
   - Costo total

## Características Técnicas

### Validaciones Implementadas
- Campos obligatorios
- Valores numéricos positivos
- Códigos únicos automáticos
- Confirmación para eliminaciones

### Manejo de Errores
- Mensajes informativos para el usuario
- Validación de entrada de datos
- Manejo de excepciones

### Interfaz de Usuario
- Diseño responsivo y intuitivo
- Colores diferenciados para botones
- Tabla de solo lectura
- Campos pre-rellenados con datos de ejemplo

## Datos de Ejemplo
La aplicación incluye datos de ejemplo basados en la especificación:
- 2 envíos terrestres
- 1 envío aéreo
- Cálculos de tarifas según la tabla especificada

## Extensibilidad
El sistema está diseñado para ser fácilmente extensible:
- Agregar nuevos tipos de envío
- Modificar cálculos de tarifa
- Implementar nuevas funcionalidades
- Cambiar la interfaz gráfica

---

**Desarrollado siguiendo principios SOLID y buenas prácticas de programación orientada a objetos.**
