package co.edu.uniquindio.quindioexpress;

import co.edu.uniquindio.quindioexpress.Controller.PackageController;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        PackageController packControl = new PackageController();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido a Quindio Express");
        System.out.println("Ingrese su opcion requerida: ");
        System.out.println("\n1.Gestión de Paquetes. ");
        System.out.println("2.Gestión de Repartidores.  ");
        System.out.println("3.Gestión de Envios.  ");
        System.out.print("Ingrese: ");
        int op = sc.nextInt();



    }
}



