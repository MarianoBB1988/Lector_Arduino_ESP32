# Monitor de LEDs desde ESP32

Este proyecto es una aplicación Java con interfaz gráfica (Swing) que recibe datos enviados por un **ESP32** a través del puerto serie y muestra el estado de tres LEDs virtuales en pantalla.  

## 🎯 Objetivo
Simular visualmente en una PC el encendido y apagado de LEDs en función de datos enviados por un microcontrolador **ESP32**.  
Cada LED en la interfaz puede encenderse en **verde** o apagarse en **rojo** dependiendo del mensaje recibido.

## 🖥️ Funcionamiento
1. El **ESP32** envía por el puerto serie valores simples:
   - `"1"` → Enciende LED 1.
   - `"2"` → Enciende LED 2.
   - `"3"` → Enciende LED 3.
   - `"x"` → Apaga todos los LEDs.
2. La aplicación Java escucha en tiempo real el puerto serie.
3. Según el valor recibido, actualiza el color de cada LED en la ventana.

## 📋 Requisitos
- **Java 8** o superior.
- Librería [jSerialComm](https://fazecast.github.io/jSerialComm/) para la comunicación por puerto serie.
- Un microcontrolador **ESP32** conectado a la PC por USB.
- Configuración correcta del **puerto COM** en el código (ejemplo: `COM7`).

## ⚙️ Instalación y ejecución
1. Conectar el **ESP32** al PC.
2. Descargar e importar la librería `jSerialComm` en el proyecto Java.
3. Modificar en el código la línea:
   ```java
   SerialPort port = SerialPort.getCommPort("COM7");
