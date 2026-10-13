
package co.edu.uniquindio.quindioexpress;

import co.edu.uniquindio.quindioexpress.Controller.PackageController;
import co.edu.uniquindio.quindioexpress.Model.Dealer;
import co.edu.uniquindio.quindioexpress.Model.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final PackageController packControl =
            new PackageController();

    public static void main(String[] args) {

        loadInitialData();

        int option;

        do {
            System.out.println("\n========================================");
            System.out.println("       BIENVENIDO A QUINDIO EXPRESS");
            System.out.println("========================================");
            System.out.println("1. Gestion de Paquetes");
            System.out.println("2. Gestion de Repartidores");
            System.out.println("3. Gestion de Envios");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opcion: ");

            option = readInt();

            switch (option) {
                case 1:
                    packageMenu();
                    break;
                case 2:
                    dealerMenu();
                    break;
                case 3:
                    deliveryMenu();
                    break;
                case 0:
                    System.out.println("Gracias por utilizar Quindio Express.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (option != 0);

        sc.close();
    }

    private static void packageMenu() {

        int option;

        do {
            System.out.println("\n========================================");
            System.out.println("          GESTION DE PAQUETES");
            System.out.println("========================================");
            System.out.println("1. Registrar paquete");
            System.out.println("2. Mostrar todos los paquetes");
            System.out.println("3. Consultar paquete por codigo (HashMap)");
            System.out.println("4. Buscar paquete por codigo (Busqueda binaria)");
            System.out.println("5. Eliminar paquete");
            System.out.println("6. Calcular peso total por municipio");
            System.out.println("7. Contar paquetes por prioridad minima");
            System.out.println("8. Encontrar paquete de mayor peso");
            System.out.println("9. Mostrar municipios sin repetir");
            System.out.println("10. Mostrar municipios ordenados");
            System.out.println("11. Agrupar paquetes por municipio");
            System.out.println("12. Ordenar paquetes por codigo");
            System.out.println("13. Ordenar paquetes por peso");
            System.out.println("14. Ordenar paquetes por prioridad");
            System.out.println("15. Ordenar paquetes por tiempo");
            System.out.println("0. Volver al menu principal");
            System.out.print("Ingrese su opcion: ");

            option = readInt();

            switch (option) {
                case 1:
                    registerPackage();
                    break;
                case 2:
                    showPackages(packControl.getPackages());
                    break;
                case 3:
                    searchPackage();
                    break;
                case 4:
                    binarySearchPackage();
                    break;
                case 5:
                    removePackage();
                    break;
                case 6:
                    calculateWeightByMunicipality();
                    break;
                case 7:
                    countByPriority();
                    break;
                case 8:
                    showHeaviestPackage();
                    break;
                case 9:
                    showMunicipalities();
                    break;
                case 10:
                    showSortedMunicipalities();
                    break;
                case 11:
                    showPackagesByMunicipality();
                    break;
                case 12:
                    showSortedPackages(1);
                    break;
                case 13:
                    showSortedPackages(2);
                    break;
                case 14:
                    showSortedPackages(3);
                    break;
                case 15:
                    showSortedPackages(4);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (option != 0);
    }

    private static void registerPackage() {

        System.out.println("\nREGISTRO DE PAQUETE");
        System.out.print("Codigo: ");
        String code = sc.nextLine().trim();

        System.out.print("Municipio de destino: ");
        String municipality = sc.nextLine().trim();

        System.out.print("Peso en kilogramos: ");
        double weight = readDouble();

        System.out.print("Prioridad (1 a 5): ");
        int priority = readInt();

        System.out.print("Tiempo estimado en minutos: ");
        int time = readInt();

        Package newPackage = new Package(
                code, municipality, weight, priority, time);

        if (isValidPackage(newPackage)) {

            if (packControl.searchPackage(code) != null) {
                System.out.println("Ya existe un paquete con ese codigo.");
                return;
            }

            packControl.addPackage(newPackage);
            packControl.registerPackageForProcessing(newPackage);
            packControl.addPriorityPackage(newPackage);

            if (packControl.searchPackage(code) != null) {
                System.out.println("Paquete registrado correctamente.");
            } else {
                System.out.println("No fue posible registrar el paquete.");
            }

        } else {
            System.out.println("Datos invalidos. Revise los valores ingresados.");
        }
    }

    private static boolean isValidPackage(Package pkg) {

        return pkg != null
                && pkg.getCode() != null
                && !pkg.getCode().trim().isEmpty()
                && pkg.getMunicipio() != null
                && !pkg.getMunicipio().trim().isEmpty()
                && pkg.getWeight() > 0
                && pkg.getPriority() >= 1
                && pkg.getPriority() <= 5
                && pkg.getTime() > 0;
    }

    private static void searchPackage() {

        System.out.print("\nIngrese el codigo del paquete: ");
        String code = sc.nextLine().trim();

        Package found = packControl.searchPackage(code);

        if (found == null) {
            System.out.println("No se encontro el paquete.");
        } else {
            printPackage(found);
        }
    }

    private static void binarySearchPackage() {

        System.out.print("\nCodigo que desea buscar: ");
        String code = sc.nextLine().trim();

        Package found = packControl.binarySearch(code);

        if (found == null) {
            System.out.println("No se encontro el paquete.");
        } else {
            System.out.println("Resultado de la busqueda binaria:");
            printPackage(found);
        }
    }

    private static void removePackage() {

        System.out.print("\nCodigo del paquete que desea eliminar: ");
        String code = sc.nextLine().trim();

        if (packControl.removePackage(code)) {
            System.out.println("Paquete eliminado correctamente.");
        } else {
            System.out.println("No existe un paquete con ese codigo.");
        }
    }

    private static void calculateWeightByMunicipality() {

        System.out.print("\nMunicipio que desea consultar: ");
        String municipality = sc.nextLine().trim();

        double total = packControl.calculateTotalWeightByMunicipality(
                packControl.getPackages(), municipality);

        System.out.printf(
                "Peso total para %s: %.2f kg%n", municipality, total);
    }

    private static void countByPriority() {

        System.out.print("\nIngrese la prioridad minima (1 a 5): ");
        int minimum = readInt();

        if (minimum < 1 || minimum > 5) {
            System.out.println("La prioridad debe estar entre 1 y 5.");
            return;
        }

        int count = packControl.countPackagesByMinimumPriority(
                packControl.getPackages(), minimum);

        System.out.println(
                "Cantidad de paquetes con prioridad igual o superior a "
                        + minimum + ": " + count);
    }

    private static void showHeaviestPackage() {

        Package heaviest = packControl.findHeaviestPackage();

        if (heaviest == null) {
            System.out.println("No hay paquetes registrados.");
        } else {
            System.out.println("Paquete de mayor peso:");
            printPackage(heaviest);
        }
    }

    private static void showMunicipalities() {

        Set<String> municipalities =
                packControl.getUniqueMunicipalities();

        System.out.println("\nMUNICIPIOS REGISTRADOS");

        if (municipalities.isEmpty()) {
            System.out.println("No hay municipios registrados.");
            return;
        }

        for (String municipality : municipalities) {
            System.out.println("- " + municipality);
        }
    }

    private static void showSortedMunicipalities() {

        Set<String> municipalities =
                packControl.getSortedMunicipalities();

        System.out.println("\nMUNICIPIOS ORDENADOS ALFABETICAMENTE");

        if (municipalities.isEmpty()) {
            System.out.println("No hay municipios registrados.");
            return;
        }

        for (String municipality : municipalities) {
            System.out.println("- " + municipality);
        }
    }

    private static void showPackagesByMunicipality() {

        Map<String, Set<Package>> groups =
                packControl.groupPackagesByMunicipality();

        System.out.println("\nPAQUETES AGRUPADOS POR MUNICIPIO");

        if (groups.isEmpty()) {
            System.out.println("No hay paquetes registrados.");
            return;
        }

        for (Map.Entry<String, Set<Package>> entry : groups.entrySet()) {
            System.out.println("\nMunicipio: " + entry.getKey());

            for (Package pkg : entry.getValue()) {
                System.out.println(
                        "  " + pkg.getCode()
                                + " | Peso: " + pkg.getWeight() + " kg"
                                + " | Prioridad: " + pkg.getPriority());
            }
        }
    }

    private static void showSortedPackages(int type) {

        List<Package> ordered = packControl.getPackages();

        switch (type) {
            case 1:
                ordered.sort(null);
                System.out.println("\nPAQUETES ORDENADOS POR CODIGO");
                break;
            case 2:
                packControl.sortPackagesByWeight(ordered);
                System.out.println("\nPAQUETES ORDENADOS POR PESO");
                break;
            case 3:
                packControl.sortPackagesByPriority(ordered);
                System.out.println("\nPAQUETES ORDENADOS POR PRIORIDAD");
                break;
            case 4:
                packControl.sortPackagesByTime(ordered);
                System.out.println("\nPAQUETES ORDENADOS POR TIEMPO");
                break;
            default:
                return;
        }

        showPackages(ordered);
    }

    private static void showPackages(List<Package> packages) {

        if (packages.isEmpty()) {
            System.out.println("No hay paquetes registrados.");
            return;
        }

        System.out.println("\nLISTADO DE PAQUETES");
        System.out.printf(
                "%-12s %-18s %-12s %-12s %-12s%n",
                "CODIGO", "MUNICIPIO", "PESO (KG)",
                "PRIORIDAD", "TIEMPO (MIN)");

        for (Package pkg : packages) {
            System.out.printf(
                    "%-12s %-18s %-12.2f %-12d %-12d%n",
                    pkg.getCode(),
                    pkg.getMunicipio(),
                    pkg.getWeight(),
                    pkg.getPriority(),
                    pkg.getTime());
        }
    }

    private static void printPackage(Package pkg) {

        System.out.println("\nINFORMACION DEL PAQUETE");
        System.out.println("Codigo: " + pkg.getCode());
        System.out.println("Municipio: " + pkg.getMunicipio());
        System.out.printf("Peso: %.2f kg%n", pkg.getWeight());
        System.out.println("Prioridad: " + pkg.getPriority());
        System.out.println("Tiempo estimado: " + pkg.getTime() + " minutos");
    }

    private static void dealerMenu() {

        int option;

        do {
            System.out.println("\n========================================");
            System.out.println("        GESTION DE REPARTIDORES");
            System.out.println("========================================");
            System.out.println("1. Registrar repartidor");
            System.out.println("2. Mostrar repartidores");
            System.out.println("0. Volver al menu principal");
            System.out.print("Ingrese su opcion: ");

            option = readInt();

            switch (option) {
                case 1:
                    registerDealer();
                    break;
                case 2:
                    showDealers();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (option != 0);
    }

    private static void registerDealer() {

        System.out.println("\nREGISTRO DE REPARTIDOR");

        System.out.print("Identificacion: ");
        int id = readInt();

        System.out.print("Nombre: ");
        String name = sc.nextLine().trim();

        System.out.print("Municipio o zona: ");
        String municipality = sc.nextLine().trim();

        System.out.print("Esta disponible? (1 = Si, 0 = No): ");
        int availableOption = readInt();

        if (id <= 0 || name.isEmpty() || municipality.isEmpty()
                || (availableOption != 0 && availableOption != 1)) {
            System.out.println("Datos invalidos.");
            return;
        }

        Dealer dealer = new Dealer(
                id, name, municipality, availableOption == 1);

        if (packControl.addDealer(dealer)) {
            System.out.println("Repartidor registrado correctamente.");
        } else {
            System.out.println("Ya existe un repartidor con esa identificacion.");
        }
    }

    private static void showDealers() {

        List<Dealer> dealers = packControl.getDealers();

        System.out.println("\nLISTADO DE REPARTIDORES");

        if (dealers.isEmpty()) {
            System.out.println("No hay repartidores registrados.");
            return;
        }

        System.out.printf(
                "%-12s %-24s %-18s %-14s%n",
                "ID", "NOMBRE", "MUNICIPIO", "DISPONIBLE");

        for (Dealer dealer : dealers) {
            System.out.printf(
                    "%-12d %-24s %-18s %-14s%n",
                    dealer.getId(),
                    dealer.getName(),
                    dealer.getMunicipio(),
                    dealer.isAvailable() ? "Si" : "No");
        }
    }

    private static void deliveryMenu() {

        int option;

        do {
            System.out.println("\n========================================");
            System.out.println("            GESTION DE ENVIOS");
            System.out.println("========================================");
            System.out.println("1. Consultar siguiente paquete por llegada");
            System.out.println("2. Atender siguiente paquete por llegada");
            System.out.println("3. Consultar siguiente paquete prioritario");
            System.out.println("4. Atender siguiente paquete prioritario");
            System.out.println("5. Asignar paquete a un repartidor");
            System.out.println("6. Registrar entrega de paquete");
            System.out.println("7. Mostrar historial de entregas");
            System.out.println("8. Mostrar paquetes pendientes por llegada");
            System.out.println("0. Volver al menu principal");
            System.out.print("Ingrese su opcion: ");

            option = readInt();

            switch (option) {
                case 1:
                    showNextArrivalPackage();
                    break;
                case 2:
                    attendArrivalPackage();
                    break;
                case 3:
                    showNextPriorityPackage();
                    break;
                case 4:
                    attendPriorityPackage();
                    break;
                case 5:
                    assignPackage();
                    break;
                case 6:
                    registerDelivery();
                    break;
                case 7:
                    packControl.displayDeliveryHistory();
                    break;
                case 8:
                    showPendingPackages();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (option != 0);
    }

    private static void showNextArrivalPackage() {

        Package pkg = packControl.peekNextPackage();

        if (pkg == null) {
            System.out.println("No hay paquetes pendientes por llegada.");
        } else {
            System.out.println("Siguiente paquete por llegada:");
            printPackage(pkg);
        }
    }

    private static void attendArrivalPackage() {

        Package pkg = packControl.attendNextPackage();

        if (pkg == null) {
            System.out.println("No hay paquetes pendientes por llegada.");
        } else {
            System.out.println("Paquete retirado de la cola de llegada:");
            printPackage(pkg);
        }
    }

    private static void showNextPriorityPackage() {

        Package pkg = packControl.peekNextPriorityPackage();

        if (pkg == null) {
            System.out.println("No hay paquetes en la cola de prioridad.");
        } else {
            System.out.println("Siguiente paquete prioritario:");
            printPackage(pkg);
        }
    }

    private static void attendPriorityPackage() {

        Package pkg = packControl.attendNextPriorityPackage();

        if (pkg == null) {
            System.out.println("No hay paquetes en la cola de prioridad.");
        } else {
            System.out.println("Paquete retirado de la cola de prioridad:");
            printPackage(pkg);
        }
    }

    private static void assignPackage() {

        System.out.print("\nCodigo del paquete que desea asignar: ");
        String code = sc.nextLine().trim();

        Package pkg = packControl.searchPackage(code);

        if (pkg == null) {
            System.out.println("No existe un paquete con ese codigo.");
            return;
        }

        if (packControl.assignPackageToDealer(pkg)) {
            System.out.println("Paquete asignado a un repartidor disponible de la zona.");
        } else {
            System.out.println(
                    "No fue posible asignar el paquete. "
                            + "Verifique la disponibilidad y el municipio de los repartidores.");
        }
    }

    private static void registerDelivery() {

        System.out.print("\nCodigo del paquete entregado: ");
        String code = sc.nextLine().trim();

        Package pkg = packControl.searchPackage(code);

        if (pkg == null) {
            System.out.println("No existe un paquete con ese codigo.");
            return;
        }

        packControl.registerPackageDelivery(pkg);
        packControl.removePackage(code);

        System.out.println("Entrega registrada en el historial.");
    }

    private static void showPendingPackages() {

        System.out.println("\nPAQUETES PENDIENTES POR ORDEN DE LLEGADA");

        while (packControl.hasPendingPackages()) {
            Package pkg = packControl.attendNextPackage();

            if (pkg != null) {
                System.out.println(
                        pkg.getCode() + " - " + pkg.getMunicipio());
            }
        }

        System.out.println("Fin del listado de la cola.");
    }

    private static void loadInitialData() {

        Package[] initialPackages = {
                new Package("PQ731", "Armenia", 4.5, 3, 30),
                new Package("PQ105", "Salento", 3.0, 5, 25),
                new Package("PQ942", "Armenia", 7.2, 4, 20),
                new Package("PQ318", "Calarca", 18.7, 5, 15),
                new Package("PQ567", "Salento", 7.4, 2, 40),
                new Package("PQ829", "Armenia", 5.1, 3, 22),
                new Package("PQ214", "Montenegro", 6.3, 1, 35),
                new Package("PQ842", "Quimbaya", 9.8, 4, 18),
                new Package("PQ420", "Calarca", 2.8, 3, 30),
                new Package("PQ654", "Filandia", 12.4, 5, 12),
                new Package("PQ276", "Armenia", 8.1, 2, 28),
                new Package("PQ913", "Salento", 3.7, 1, 45),
                new Package("PQ487", "Montenegro", 15.2, 4, 19),
                new Package("PQ362", "Quimbaya", 4.9, 2, 26),
                new Package("PQ758", "Filandia", 10.5, 5, 14),
                new Package("PQ193", "Armenia", 6.8, 3, 32),
                new Package("PQ625", "Calarca", 11.3, 4, 21),
                new Package("PQ849", "Salento", 2.6, 2, 38),
                new Package("PQ531", "Montenegro", 13.7, 5, 17),
                new Package("PQ407", "Quimbaya", 5.6, 1, 33),
                new Package("PQ968", "Filandia", 7.9, 3, 24),
                new Package("PQ152", "Armenia", 16.4, 5, 11),
                new Package("PQ583", "Calarca", 3.5, 2, 42),
                new Package("PQ746", "Salento", 9.2, 4, 16),
                new Package("PQ329", "Montenegro", 6.1, 3, 27),
                new Package("PQ875", "Quimbaya", 14.6, 5, 13),
                new Package("PQ246", "Filandia", 4.2, 1, 36),
                new Package("PQ691", "Armenia", 20.1, 4, 10),
                new Package("PQ534", "Calarca", 8.7, 3, 23),
                new Package("PQ987", "Salento", 5.4, 2, 29)
        };

        for (Package pkg : initialPackages) {
            packControl.addPackage(pkg);
            packControl.registerPackageForProcessing(pkg);
            packControl.addPriorityPackage(pkg);
        }

        Dealer[] initialDealers = {
                new Dealer(1001, "Carlos Ramirez", "Armenia", true),
                new Dealer(1002, "Laura Gomez", "Salento", true),
                new Dealer(1003, "Andres Torres", "Calarca", true),
                new Dealer(1004, "Diana Castro", "Montenegro", true),
                new Dealer(1005, "Felipe Rojas", "Quimbaya", true),
                new Dealer(1006, "Maria Lopez", "Filandia", true)
        };

        for (Dealer dealer : initialDealers) {
            packControl.addDealer(dealer);
        }

        System.out.println("Datos iniciales cargados correctamente.");
        System.out.println("Paquetes registrados: "
                + packControl.getPackages().size());
        System.out.println("Repartidores registrados: "
                + packControl.getDealers().size());
    }

    private static int readInt() {

        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero valido: ");
            }
        }
    }

    private static double readDouble() {

        while (true) {
            try {
                double value = Double.parseDouble(
                        sc.nextLine().trim().replace(',', '.'));

                return value;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}
