/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Norath
 */
public class HiloRecepcion extends Thread {

    ServerSocket escuchador;
    Socket conexion;
    Scanner entrada;
    PrintWriter salida;
    JLabel lblColor;
    int puerto;
    boolean stop;

    public HiloRecepcion(int puerto, JLabel label) {
        this.puerto = puerto;
        this.lblColor = label;
        stop = false;
    }

    public void run() {
        try {
            escuchador = new ServerSocket(puerto);
            System.out.println("Escuchando");
            do {
                conexion = escuchador.accept();
                System.out.println("Conexión establecida");

                entrada = new Scanner(conexion.getInputStream());

                String recibido = entrada.nextLine();
                System.out.println(recibido);
                String colorStrings[] = recibido.split("#");
                Color colorRecibido = new Color(Integer.valueOf(colorStrings[0]), Integer.valueOf(colorStrings[1]), Integer.valueOf(colorStrings[2]));

                lblColor.setBackground(colorRecibido);
                conexion.close();
            } while (!stop);
            
        } catch (IOException ex) {
            System.err.println("Error al iniciar la conexión (puerto ocupado)");
        }
    }

}
