package com.mycompany.lector.arduino;

import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;
import java.awt.*;

public class ArduinoESP32 extends JFrame {

    private boolean led1 = false, led2 = false, led3 = false;

    public ArduinoESP32() {
        setTitle("Monitor de LEDs desde ESP32");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(led1 ? Color.GREEN : Color.RED);
                g.fillOval(30, 50, 50, 50);
                g.setColor(led2 ? Color.GREEN : Color.RED);
                g.fillOval(120, 50, 50, 50);
                g.setColor(led3 ? Color.GREEN : Color.RED);
                g.fillOval(210, 50, 50, 50);
            }
        };
        add(panel);

        // Conectar al puerto serie
        SerialPort port = SerialPort.getCommPort("COM7"); // Cambia al tuyo
        port.setBaudRate(115200);
        if (!port.openPort()) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el puerto serie");
            return;
        }

        // Hilo para leer datos sin bloquear la interfaz
        new Thread(() -> {
            try {
                while (true) {
                    if (port.bytesAvailable() > 0) {
                        byte[] buffer = new byte[port.bytesAvailable()];
                        int numLeidos = port.readBytes(buffer, buffer.length);
                        if (numLeidos > 0) {
                            String datos = new String(buffer, 0, numLeidos, "UTF-8");
                            for (char c : datos.toCharArray()) {
                                actualizarLEDs(String.valueOf(c));
                                SwingUtilities.invokeLater(panel::repaint);
                            }
                        }
                    }
                    Thread.sleep(50);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void actualizarLEDs(String mensaje) {
        switch (mensaje) {
            case "1":
                led1 = true; led2 = false; led3 = false;
                break;
            case "2":
                led1 = false; led2 = true; led3 = false;
                break;
            case "3":
                led1 = false; led2 = false; led3 = true;
                break;
            case "x":
                led1 = false; led2 = false; led3 = false;
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArduinoESP32 frame = new ArduinoESP32();
            frame.setVisible(true);
        });
    }
}
