/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lector.arduino;
import com.fazecast.jSerialComm.SerialPort; // importación externa. Agregar dependencia en pom.xml
/**
 *
 * @author maria
 */
public class LectorArduino {

    public static void main(String[] args) {
       // Detectar el puerto donde está el Arduino
        SerialPort puerto = SerialPort.getCommPort("COM7"); // Cambiar COM3 por el puerto real
        puerto.setBaudRate(115200);

        if (puerto.openPort()) {
            System.out.println("Puerto abierto correctamente");
        } else {
            System.out.println("No se pudo abrir el puerto");
            return;
        }

        // Leer datos
        try {
            while (true) {
                while (puerto.bytesAvailable() > 0) {
                    byte[] buffer = new byte[puerto.bytesAvailable()];
                    puerto.readBytes(buffer, buffer.length);
                    System.out.print(new String(buffer));
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
