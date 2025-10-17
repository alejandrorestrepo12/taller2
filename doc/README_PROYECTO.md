# Sistema de Logística

## Descripción del Proyecto
Sistema de gestión de envíos logísticos que maneja tres tipos de transporte: Terrestre, Aéreo y Marítimo.

## Estructura del Proyecto

### Carpetas Principales
- **src/**: Contiene todo el código fuente Java
- **bin/**: Carpeta para archivos compilados (.class)
- **doc/**: Documentación del proyecto

### Archivos Fuente (src/)
- `Main.java`: Clase principal que inicia la aplicación
- `Envio.java`: Clase abstracta base para todos los tipos de envío
- `Terrestre.java`: Implementación para envíos terrestres
- `Aereo.java`: Implementación para envíos aéreos
- `Maritimo.java`: Implementación para envíos marítimos
- `Logistica.java`: Clase que gestiona la colección de envíos
- `InterfazLogistica.java`: Interfaz gráfica de usuario (Swing)

## Compilación y Ejecución

### Compilar el proyecto
```bash
javac -d bin src/*.java
```

### Ejecutar la aplicación
```bash
java -cp bin Main
```

## Características del Sistema
- Interfaz gráfica intuitiva
- Gestión de diferentes tipos de envío
- Cálculo automático de tarifas
- Generación de reportes
- Validación de datos de entrada

## Principios de Diseño Aplicados
- Principio de Responsabilidad Única (SRP)
- Principio Abierto/Cerrado (OCP)
- Principio de Sustitución de Liskov (LSP)
- Principio de Inversión de Dependencias (DIP)
- Polimorfismo y Herencia
