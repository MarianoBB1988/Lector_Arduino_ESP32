/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PruebaESP32;

/**
 *
 * @author maria
 */
import com.fazecast.jSerialComm.SerialPort;
import java.util.Scanner;

public class LectorESP32 {
    public static void main(String[] args) {
        // Abrir el puerto serie (ajusta el nombre según tu PC)
        SerialPort comPort = SerialPort.getCommPort("COM7"); // <-- cambia COM4 por el puerto del Arduino
        comPort.setBaudRate(115200);

        if (!comPort.openPort()) {
            System.out.println("No se pudo abrir el puerto.");
            return;
        }

        System.out.println("Conectado. Esperando datos...");

        Scanner scanner = new Scanner(comPort.getInputStream());

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine().trim();
            System.out.println("Recibido: " + linea);
        }

        comPort.closePort();
    }
}
