package co.edu.uniquindio.quindioexpress.Controller;

import co.edu.uniquindio.quindioexpress.Comparators.PackageComparators;
import co.edu.uniquindio.quindioexpress.Model.Dealer;
import co.edu.uniquindio.quindioexpress.Model.Package;
import co.edu.uniquindio.quindioexpress.Structure.SimpleLinkedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

// Controlador encargado de manejar los paquetes, repartidores y entregas
public class PackageController {

    // Lista principal de paquetes registrados
    private ArrayList<Package> packages;

    // Mapa para buscar paquetes por su codigo
    private HashMap<String, Package> packagesByCode;

    // Cola que organiza los paquetes por orden de llegada
    private Queue<Package> arrivalOrderQueue;

    // Cola que organiza los paquetes segun su prioridad
    private PriorityQueue<Package> priorityQueue;

    // Lista de repartidores registrados
    private List<Dealer> dealerList;

    // Mapa que relaciona cada paquete con su repartidor asignado
    private Map<String, Dealer> assignedDealers;

    // Lista enlazada que guarda el historial de entregas
    private SimpleLinkedList<Package> deliveryHistory;

    // Inicializa las estructuras utilizadas por el controlador
    public PackageController() {
        packages = new ArrayList<>();
        packagesByCode = new HashMap<>();
        arrivalOrderQueue = new LinkedList<>();
        priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(Package::getPriority).reversed()
                        .thenComparingInt(Package::getTime)
                        .thenComparing(Package::getCode)
        );
        dealerList = new ArrayList<>();
        assignedDealers = new HashMap<>();
        deliveryHistory = new SimpleLinkedList<>();
    }

    // Registra un paquete si sus datos son validos y su codigo no existe
    public boolean addPackage(Package newPackage) {
        if (!isValidPackage(newPackage)) {
            return false;
        }

        String code = newPackage.getCode().trim();

        if (packagesByCode.containsKey(code)) {
            return false;
        }

        packages.add(newPackage);
        packagesByCode.put(code, newPackage);
        arrivalOrderQueue.offer(newPackage);
        priorityQueue.offer(newPackage);
        return true;
    }

    // Comprueba que los datos del paquete cumplan las condiciones necesarias
    private boolean isValidPackage(Package pkg) {
        return pkg != null
                && pkg.getCode() != null
                && !pkg.getCode().trim().isEmpty()
                && pkg.getMunicipio() != null
                && !pkg.getMunicipio().trim().isEmpty()
                && pkg.getWeight() > 0
                && Double.isFinite(pkg.getWeight())
                && pkg.getPriority() >= 1
                && pkg.getPriority() <= 5
                && pkg.getTime() > 0;
    }

    // Busca un paquete usando su codigo
    public Package searchPackage(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }
        return packagesByCode.get(code.trim());
    }

    // Elimina un paquete si existe y no tiene un repartidor asignado
    public boolean removePackage(String code) {
        Package pkg = searchPackage(code);
        if (pkg == null || assignedDealers.containsKey(pkg.getCode())) {
            return false;
        }

        removeFromPendingStructures(pkg);
        packages.remove(pkg);
        packagesByCode.remove(pkg.getCode());
        return true;
    }

    // Elimina un paquete de las colas donde se encuentre
    private void removeFromPendingStructures(Package pkg) {
        arrivalOrderQueue.remove(pkg);
        priorityQueue.remove(pkg);
    }

    // Devuelve una copia de la lista de paquetes
    public ArrayList<Package> getPackages() {
        return new ArrayList<>(packages);
    }

    // Limpia los paquetes registrados y libera los repartidores asignados
    public void clearPackages() {
        for (Dealer dealer : assignedDealers.values()) {
            dealer.setAvailable(true);
        }
        packages.clear();
        packagesByCode.clear();
        arrivalOrderQueue.clear();
        priorityQueue.clear();
        assignedDealers.clear();
    }

    // Busca el paquete con mayor peso usando divide y venceras
    public Package findHeaviestPackage() {
        if (packages.isEmpty()) {
            return null;
        }
        return findHeaviestPackage(packages, 0, packages.size() - 1);
    }

    // Encuentra el paquete mas pesado dentro de una parte de la lista
    private Package findHeaviestPackage(ArrayList<Package> list, int start, int end) {
        if (start == end) {
            return list.get(start);
        }

        int middle = start + (end - start) / 2;
        Package left = findHeaviestPackage(list, start, middle);
        Package right = findHeaviestPackage(list, middle + 1, end);

        if (left.getWeight() >= right.getWeight()) {
            return left;
        }
        return right;
    }

    // Busca un paquete por codigo mediante busqueda binaria
    public Package binarySearch(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }

        ArrayList<Package> orderedPackages = getPackages();
        orderedPackages.sort(Comparator.naturalOrder());
        return binarySearch(orderedPackages, code.trim());
    }

    // Realiza la busqueda binaria sobre una lista ordenada
    private Package binarySearch(List<Package> orderedPackages, String code) {
        int start = 0;
        int end = orderedPackages.size() - 1;

        while (start <= end) {
            int middle = start + (end - start) / 2;
            Package current = orderedPackages.get(middle);
            int comparison = current.getCode().compareTo(code);

            if (comparison == 0) {
                return current;
            } else if (comparison < 0) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return null;
    }

    // Calcula el peso total de los paquetes de un municipio
    public double calculateTotalWeightByMunicipality(
            ArrayList<Package> list, String municipality) {
        if (list == null || municipality == null) {
            return 0.0;
        }
        return calculateWeightRecursively(list, municipality.trim(), 0);
    }

    // Suma recursivamente los pesos de los paquetes del municipio indicado
    private double calculateWeightRecursively(
            ArrayList<Package> list, String municipality, int index) {
        if (index >= list.size()) {
            return 0.0;
        }

        Package current = list.get(index);
        double currentWeight = 0.0;

        if (current.getMunicipio() != null
                && current.getMunicipio().equalsIgnoreCase(municipality)) {
            currentWeight = current.getWeight();
        }

        return currentWeight
                + calculateWeightRecursively(list, municipality, index + 1);
    }

    // Cuenta los paquetes que tienen una prioridad igual o mayor a la indicada
    public int countPackagesByMinimumPriority(
            ArrayList<Package> list, int minPriority) {
        if (list == null) {
            return 0;
        }
        return countPackagesRecursively(list, minPriority, 0);
    }

    // Cuenta recursivamente los paquetes que cumplen con la prioridad minima
    private int countPackagesRecursively(
            ArrayList<Package> list, int minPriority, int index) {
        if (index >= list.size()) {
            return 0;
        }

        Package current = list.get(index);
        int matches = current.getPriority() >= minPriority ? 1 : 0;

        return matches + countPackagesRecursively(list, minPriority, index + 1);
    }

    // Obtiene los municipios registrados sin repetirlos
    public Set<String> getUniqueMunicipalities() {
        Set<String> municipalities = new HashSet<>();
        for (Package pkg : packages) {
            if (pkg.getMunicipio() != null && !pkg.getMunicipio().trim().isEmpty()) {
                municipalities.add(pkg.getMunicipio());
            }
        }
        return municipalities;
    }

    // Obtiene los municipios sin repetirlos y en orden alfabetico
    public Set<String> getSortedMunicipalities() {
        Set<String> municipalities = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (Package pkg : packages) {
            if (pkg.getMunicipio() != null && !pkg.getMunicipio().trim().isEmpty()) {
                municipalities.add(pkg.getMunicipio());
            }
        }
        return municipalities;
    }

    // Agrupa los paquetes segun el municipio al que van dirigidos
    public Map<String, Set<Package>> groupPackagesByMunicipality() {
        Map<String, Set<Package>> groups = new HashMap<>();
        for (Package pkg : packages) {
            String municipality = pkg.getMunicipio();
            if (municipality != null && !municipality.trim().isEmpty()) {
                groups.putIfAbsent(municipality, new HashSet<>());
                groups.get(municipality).add(pkg);
            }
        }
        return groups;
    }

    // Agrega un paquete a la cola de prioridad si esta registrado
    public void addPriorityPackage(Package pkg) {
        if (pkg != null && packagesByCode.get(pkg.getCode()) == pkg
                && !priorityQueue.contains(pkg)) {
            priorityQueue.offer(pkg);
        }
    }

    // Retira y devuelve el siguiente paquete segun su prioridad
    public Package attendNextPriorityPackage() {
        return priorityQueue.poll();
    }

    // Consulta el siguiente paquete prioritario sin retirarlo
    public Package peekNextPriorityPackage() {
        return priorityQueue.peek();
    }

    // Agrega un paquete registrado a la cola de llegada
    public void registerPackageForProcessing(Package pkg) {
        if (pkg != null && packagesByCode.get(pkg.getCode()) == pkg
                && !arrivalOrderQueue.contains(pkg)) {
            arrivalOrderQueue.offer(pkg);
        }
    }

    // Retira y devuelve el siguiente paquete por orden de llegada
    public Package attendNextPackage() {
        return arrivalOrderQueue.poll();
    }

    // Consulta el primer paquete de la cola sin retirarlo
    public Package peekNextPackage() {
        return arrivalOrderQueue.peek();
    }

    // Indica si hay paquetes pendientes en la cola de llegada
    public boolean hasPendingPackages() {
        return !arrivalOrderQueue.isEmpty();
    }

    // Devuelve una lista con los paquetes pendientes por orden de llegada
    public List<Package> getPendingPackagesByArrival() {
        return new ArrayList<>(arrivalOrderQueue);
    }

    // Ordena una lista de paquetes por peso de mayor a menor
    public void sortPackagesByWeight(List<Package> list) {
        if (list != null) {
            list.sort(PackageComparators.POR_PESO_DESC);
        }
    }

    // Ordena una lista de paquetes por prioridad de mayor a menor
    public void sortPackagesByPriority(List<Package> list) {
        if (list != null) {
            list.sort(PackageComparators.POR_PRIORIDAD_DESC);
        }
    }

    // Ordena una lista de paquetes por tiempo de menor a mayor
    public void sortPackagesByTime(List<Package> list) {
        if (list != null) {
            list.sort(PackageComparators.POR_TIEMPO_ASC);
        }
    }

    // Asigna un repartidor disponible del mismo municipio que el paquete
    public boolean assignPackageToDealer(Package pkg) {
        if (pkg == null || packagesByCode.get(pkg.getCode()) != pkg
                || assignedDealers.containsKey(pkg.getCode())) {
            return false;
        }

        String destination = pkg.getMunicipio();
        if (destination == null || destination.trim().isEmpty()) {
            return false;
        }

        for (Dealer dealer : dealerList) {
            if (dealer != null && dealer.isAvailable()
                    && dealer.getMunicipio() != null
                    && dealer.getMunicipio().equalsIgnoreCase(destination)) {
                assignedDealers.put(pkg.getCode(), dealer);
                dealer.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    // Consulta el repartidor asignado a un paquete por su codigo
    public Dealer getAssignedDealer(String packageCode) {
        if (packageCode == null) {
            return null;
        }
        return assignedDealers.get(packageCode.trim());
    }

    // Registra la entrega y actualiza las estructuras del paquete
    public boolean registerPackageDelivery(Package deliveredPackage) {
        if (deliveredPackage == null
                || packagesByCode.get(deliveredPackage.getCode()) != deliveredPackage
                || !assignedDealers.containsKey(deliveredPackage.getCode())) {
            return false;
        }

        Dealer dealer = assignedDealers.remove(deliveredPackage.getCode());
        deliveryHistory.addLast(deliveredPackage);
        removeFromPendingStructures(deliveredPackage);
        packages.remove(deliveredPackage);
        packagesByCode.remove(deliveredPackage.getCode());

        if (dealer != null) {
            dealer.setAvailable(true);
        }
        return true;
    }

    // Completa la entrega buscando primero el paquete por su codigo
    public boolean completeDelivery(String packageCode) {
        Package pkg = searchPackage(packageCode);
        return registerPackageDelivery(pkg);
    }

    // Muestra los paquetes que ya fueron entregados
    public void displayDeliveryHistory() {
        System.out.println("\nHISTORIAL DE ENTREGAS");
        if (deliveryHistory.isEmpty()) {
            System.out.println("No hay entregas registradas.");
            return;
        }

        for (Package pkg : deliveryHistory) {
            System.out.println("Paquete: " + pkg.getCode()
                    + " | Destino: " + pkg.getMunicipio()
                    + " | Peso: " + pkg.getWeight() + " kg");
        }
    }

    // Registra un repartidor si sus datos son validos y su ID no se repite
    public boolean addDealer(Dealer dealer) {
        if (dealer == null || dealer.getId() <= 0
                || dealer.getName() == null || dealer.getName().trim().isEmpty()
                || dealer.getMunicipio() == null || dealer.getMunicipio().trim().isEmpty()) {
            return false;
        }

        for (Dealer current : dealerList) {
            if (current.getId() == dealer.getId()) {
                return false;
            }
        }
        dealerList.add(dealer);
        return true;
    }

    // Devuelve una copia de la lista de repartidores
    public List<Dealer> getDealers() {
        return new ArrayList<>(dealerList);
    }
}