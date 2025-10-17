# 🚚 Sistema de Gestión de Envíos - Logística

## 📋 Descripción

Sistema de gestión de envíos que permite administrar tres tipos de medios de transporte: **Terrestre**, **Aéreo** y **Marítimo**. El sistema ofrece dos modos de operación:

- **Interfaz Gráfica (GUI)** - Interfaz visual moderna con Swing
- **Interfaz por Consola** - Interfaz de texto interactiva

## 🚀 Inicio Rápido

### Compilar el Proyecto

```bash
javac -d bin src/*.java
```

### Ejecutar la Aplicación

```bash
java -cp bin Main
```

Al ejecutar, se mostrará un menú para seleccionar entre:
1. **Interfaz Gráfica (GUI)** - Recomendado para uso visual
2. **Interfaz por Consola** - Para uso en terminal
3. **Salir**

## 📁 Estructura del Proyecto

```
Exm 2 - Tecnicas/
├── src/                              # Código fuente
│   ├── Envio.java                   # Clase abstracta base
│   ├── Terrestre.java               # Envío terrestre
│   ├── Aereo.java                   # Envío aéreo
│   ├── Maritimo.java                # Envío marítimo
│   ├── ICalculadoraTarifa.java      # Interfaz para cálculo de tarifas
│   ├── IRepositorioEnvios.java      # Interfaz del repositorio
│   ├── IServicioLogistica.java      # Interfaz del servicio
│   ├── RepositorioEnvios.java       # Implementación del repositorio
│   ├── ServicioLogistica.java       # Servicio de lógica de negocio
│   ├── FabricaEnvios.java           # Fábrica de envíos
│   ├── Main.java                    # Punto de entrada principal
│   └── InterfazLogistica.java       # Interfaz gráfica (GUI)
│
├── bin/                              # Archivos compilados (.class)
│
└── README.md                         # Documentación completa
```

## 💡 Características

### Tipos de Envío

Cada envío contiene la siguiente información:
- **Cliente**: Nombre del remitente
- **Código del envío**: Identificador único
- **Peso**: En kilogramos
- **Distancia**: En kilómetros

### 💰 Tarifas por Tipo de Transporte

| Medio      | Tarifa Base por Km | Recargo por Kg |
|------------|-------------------|----------------|
| Terrestre  | $1,500            | $2,000         |
| Aéreo      | $5,000            | $4,000         |
| Marítimo   | $800              | $1,000         |

**Fórmula de cálculo:**
```
Costo Total = (Tarifa Base × Distancia) + (Recargo × Peso)
```

### ⚙️ Funcionalidades

✅ **Agregar envío**
- Ingreso de datos: código, cliente, peso, distancia
- Selección de tipo de transporte
- Validación de código único
- Cálculo automático de tarifa

✅ **Retirar envío**
- Búsqueda por código
- Confirmación antes de eliminar

✅ **Listar envíos**
- Visualización de todos los envíos registrados
- Información completa: tipo, código, cliente, peso, distancia, costo

## 💻 Uso del Sistema

### Interfaz Gráfica (GUI)

1. Ejecuta el programa y selecciona la opción 1
2. Completa el formulario en la parte superior:
   - **Número**: Código único del envío
   - **Cliente**: Nombre del remitente
   - **Peso**: Peso en kilogramos
   - **Distancia**: Distancia en kilómetros
   - **Tipo**: Selecciona Terrestre, Aéreo o Marítimo
3. Haz clic en **"Guardar"** para agregar el envío
4. Usa **"Cancelar"** para limpiar el formulario
5. Los envíos se muestran en la tabla inferior
6. **Doble clic** en un envío de la tabla para eliminarlo

### Interfaz por Consola

1. Ejecuta el programa y selecciona la opción 2
2. Usa el menú interactivo:
   - **1. Agregar envío**: Ingresa los datos solicitados
   - **2. Retirar envío**: Ingresa el código del envío a eliminar
   - **3. Listar envíos**: Muestra todos los envíos con sus detalles
   - **4. Salir**: Finaliza el programa

## 📊 Diagrama de Clases

```
┌─────────────────────────────────────────┐
│            <<abstract>>                 │
│              Envio                      │
├─────────────────────────────────────────┤
│ - codigo: String                        │
│ - cliente: String                       │
│ - peso: double                          │
│ - distancia: double                     │
├─────────────────────────────────────────┤
│ + getCodigo(): String                   │
│ + getCliente(): String                  │
│ + getPeso(): double                     │
│ + getDistancia(): double                │
│ + calcularTarifa(): double              │ <<abstract>>
│ + getCosto(): double                    │
└─────────────────────────────────────────┘
              △
              │
      ┌───────┼───────┐
      │       │       │
┌─────┴────┐  │  ┌────┴────────┐
│Terrestre │  │  │  Maritimo   │
├──────────┤  │  ├─────────────┤
│+calcular │  │  │+calcularTarifa
│ Tarifa() │  │  │ (): double  │
└──────────┘  │  └─────────────┘
      ┌───────┴────────┐
      │     Aereo      │
      ├────────────────┤
      │+calcularTarifa │
      │ (): double     │
      └────────────────┘

┌─────────────────────────────────────────┐
│           Logistica                     │
├─────────────────────────────────────────┤
│ - envios: ArrayList<Envio>              │
├─────────────────────────────────────────┤
│ + agregarEnvio(Envio): boolean          │
│ + retirarEnvio(String): boolean         │
│ + buscarEnvioPorCodigo(String): Envio   │
│ + listarEnvios(): void                  │
│ + getCantidadEnvios(): int              │
│ + calcularTotalTarifas(): double        │
└─────────────────────────────────────────┘
```

## 🎯 Diseño Orientado a Objetos

### Principios SOLID Aplicados

El proyecto implementa **completamente** los 5 principios SOLID:

#### ✅ **S - Single Responsibility Principle (Responsabilidad Única)**

Cada clase tiene una única razón para cambiar:

- **`Envio`**: Solo maneja datos de envío
- **`Terrestre`, `Aereo`, `Maritimo`**: Solo implementan lógica específica de su tipo
- **`RepositorioEnvios`**: Solo gestiona el almacenamiento de envíos
- **`ServicioLogistica`**: Solo maneja la lógica de negocio
- **`FabricaEnvios`**: Solo crea instancias de envíos
- **`InterfazLogistica`**: Solo maneja la presentación visual
- **`Main`**: Solo maneja el inicio de la aplicación

#### ✅ **O - Open/Closed Principle (Abierto/Cerrado)**

El sistema está abierto a extensión pero cerrado a modificación:

- **Extensibilidad**: Se pueden agregar nuevos tipos de envío (ej: `Ferroviario`) sin modificar código existente
- **`FabricaEnvios`**: Centraliza la creación, facilitando agregar nuevos tipos
- **Interfaces**: Permiten implementaciones alternativas sin cambiar el código cliente

**Ejemplo**: Para agregar un nuevo tipo de envío:
1. Crear nueva clase que extienda `Envio`
2. Agregar el tipo al enum de `FabricaEnvios`
3. ¡Sin modificar clases existentes!

#### ✅ **L - Liskov Substitution Principle (Sustitución de Liskov)**

Los subtipos pueden sustituir a sus tipos base:

- **`Terrestre`, `Aereo`, `Maritimo`** pueden usarse donde se espera un `Envio`
- **`RepositorioEnvios`** puede usarse donde se espera `IRepositorioEnvios`
- **`ServicioLogistica`** puede usarse donde se espera `IServicioLogistica`
- El comportamiento esperado se mantiene en todas las sustituciones

#### ✅ **I - Interface Segregation Principle (Segregación de Interfaces)**

Interfaces específicas y cohesivas:

- **`ICalculadoraTarifa`**: Solo para cálculo de tarifas
- **`IRepositorioEnvios`**: Solo para operaciones de repositorio
- **`IServicioLogistica`**: Solo para lógica de negocio
- Ninguna clase se ve forzada a implementar métodos que no usa

#### ✅ **D - Dependency Inversion Principle (Inversión de Dependencias)**

Las clases de alto nivel no dependen de implementaciones concretas:

- **`ServicioLogistica`** depende de `IRepositorioEnvios` (no de `RepositorioEnvios`)
- **`InterfazLogistica`** depende de `IServicioLogistica` (no de `ServicioLogistica`)
- **`Main`** usa interfaces para desacoplar componentes
- **Inyección de dependencias**: Constructores reciben interfaces

```java
// Ejemplo de Dependency Inversion
public class ServicioLogistica implements IServicioLogistica {
    private final IRepositorioEnvios repositorio; // ← Interfaz, no implementación
    
    public ServicioLogistica(IRepositorioEnvios repositorio) {
        this.repositorio = repositorio;
    }
}
```

### Principios POO Tradicionales

✅ **Herencia**
- Las clases `Terrestre`, `Aereo` y `Maritimo` heredan de `Envio`

✅ **Polimorfismo**
- Cada tipo de envío implementa `calcularTarifa()` de forma diferente
- Se usan interfaces para polimorfismo de comportamiento

✅ **Encapsulamiento**
- Atributos `private` y `protected` con getters/setters
- Uso de `final` para inmutabilidad donde corresponde
- Uso de `List<Envio>` (colecciones genéricas)

✅ **Abstracción**
- Clase `Envio` define la estructura base abstracta
- Interfaces definen contratos sin implementación

✅ **Composición**
- `ServicioLogistica` contiene una instancia de `IRepositorioEnvios`
- `RepositorioEnvios` contiene una colección `List<Envio>`

### Buenas Prácticas Aplicadas

✅ **DRY (Don't Repeat Yourself)**
- `FabricaEnvios` elimina código duplicado de creación
- Métodos de cálculo de tarifa no se repiten

✅ **Nombres Descriptivos**
- `ServicioLogistica`, `RepositorioEnvios`, `FabricaEnvios`
- Prefijo `I` para interfaces (convención Java)

✅ **Separación de Responsabilidades**
- Lógica de negocio separada de la interfaz de usuario
- Capa de datos separada de la lógica de negocio

✅ **Colecciones Genéricas**
- `List<Envio>` en lugar de `ArrayList` directo
- Uso de interfaces de colecciones

✅ **Validaciones**
- Validación de parámetros nulos
- Validación de valores numéricos
- Manejo de excepciones

### Arquitectura en Capas

```
┌─────────────────────────────────────────┐
│        Capa de Presentación             │
│    (Main, InterfazLogistica)            │
└─────────────┬───────────────────────────┘
              │ usa
┌─────────────▼───────────────────────────┐
│      Capa de Lógica de Negocio          │
│    (ServicioLogistica, FabricaEnvios)   │
└─────────────┬───────────────────────────┘
              │ usa
┌─────────────▼───────────────────────────┐
│       Capa de Datos                     │
│    (RepositorioEnvios)                  │
└─────────────┬───────────────────────────┘
              │ gestiona
┌─────────────▼───────────────────────────┐
│       Modelo de Dominio                 │
│  (Envio, Terrestre, Aereo, Maritimo)    │
└─────────────────────────────────────────┘
```

### Jerarquía de Clases

- **Envio** (clase abstracta): Define la estructura base e implementa `ICalculadoraTarifa`
- **Terrestre, Aereo, Maritimo**: Implementan el cálculo específico de tarifas según su tipo
- **IRepositorioEnvios / RepositorioEnvios**: Gestiona el almacenamiento de envíos
- **IServicioLogistica / ServicioLogistica**: Coordina la lógica de negocio
- **FabricaEnvios**: Crea instancias de envíos (patrón Factory)
- **Main**: Punto de entrada que permite seleccionar el modo de operación
- **InterfazLogistica**: Interfaz gráfica de usuario

## 📖 Ejemplo de Uso

### Modo Consola

```
╔════════════════════════════════════════════════════╗
║   SISTEMA DE GESTIÓN DE ENVÍOS - LOGÍSTICA        ║
╚════════════════════════════════════════════════════╝

Seleccione el modo de operación:
1. Interfaz Gráfica (GUI)
2. Interfaz por Consola
3. Salir

Opción: 2

========================================
   SISTEMA DE GESTIÓN DE ENVÍOS
========================================
1. Agregar envío
2. Retirar envío
3. Listar envíos
4. Salir
========================================
Seleccione una opción: 1

========================================
         AGREGAR NUEVO ENVÍO
========================================
Nombre del cliente: Juan Pérez
Código del envío: ENV001
Peso en kilogramos: 10
Distancia en kilómetros: 5

Seleccione el tipo de envío:
1. Terrestre ($1500/km + $2000/kg)
2. Aéreo ($5000/km + $4000/kg)
3. Marítimo ($800/km + $1000/kg)
Opción: 1

*** Envío agregado exitosamente ***
Tipo: Terrestre | Código: ENV001 | Cliente: Juan Pérez | Peso: 10.0 kg | Distancia: 5.0 km | Costo: $27500.0
```

## 🔧 Requisitos

- **Java JDK 8 o superior**
- Sistema operativo: Windows, Linux o macOS

## 📚 Documentación del Código

### Clase Envio (abstracta)

```java
public abstract class Envio {
    protected String cliente;
    protected String codigoEnvio;
    protected double pesoKg;
    protected double distanciaKm;
    
    public abstract double calcularTarifa();
    public double getCosto() { return calcularTarifa(); }
}
```

### Clases Concretas

Cada clase implementa el método `calcularTarifa()` según su fórmula específica:

**Terrestre:**
```java
return (1500 * distanciaKm) + (2000 * pesoKg);
```

**Aéreo:**
```java
return (5000 * distanciaKm) + (4000 * pesoKg);
```

**Marítimo:**
```java
return (800 * distanciaKm) + (1000 * pesoKg);
```

## ✨ Características Destacadas

- ✅ Código limpio y bien documentado
- ✅ Validación de datos de entrada
- ✅ Interfaz gráfica intuitiva
- ✅ Manejo de errores
- ✅ Diseño orientado a objetos
- ✅ Implementación de buenas prácticas de programación
- ✅ Dos modos de operación (GUI y Consola)
- ✅ Menú principal para seleccionar modo

## 👨‍💻 Autor

Proyecto desarrollado para la materia de **Técnicas de Programación**

---

## 📝 Notas Adicionales

### Compilación

Para compilar el proyecto, asegúrate de que el directorio `bin` existe:

```bash
# Si el directorio bin no existe, créalo
mkdir bin

# Compilar todos los archivos Java
javac -d bin src/*.java
```

### Ejecución

El punto de entrada principal es la clase `Main`:

```bash
java -cp bin Main
```

Esto mostrará un menú donde podrás seleccionar:
- **Opción 1**: Abre la interfaz gráfica (GUI)
- **Opción 2**: Inicia el modo consola interactivo
- **Opción 3**: Cierra el programa

### Estructura de Datos

El sistema utiliza `ArrayList<Envio>` para almacenar los envíos, permitiendo:
- Agregar envíos dinámicamente
- Buscar envíos por código
- Eliminar envíos específicos
- Listar todos los envíos

### Validaciones Implementadas

- Código de envío único (no se permiten duplicados)
- Valores numéricos válidos para peso y distancia
- Peso y distancia mayores a cero
- Confirmación antes de eliminar envíos

---

**¡Gracias por usar el Sistema de Gestión de Envíos!** 🚚📦

