
package co.edu.uniquindio.quindioexpress.Controller;

import co.edu.uniquindio.quindioexpress.Model.Package;

import java.util.ArrayList;
import java.util.HashMap;


public class PackageController {

    private ArrayList<Package> packages;
    private HashMap<String, Package> packagesByCode;

    public PackageController() {
        packages = new ArrayList<>();
        packagesByCode = new HashMap<>();
    }

    public void addPackage(Package newPackage) {


    }

    public Package searchPackage(String code) {

        if (code == null || code.isEmpty()) {
            return null;
        }

        return packagesByCode.get(code);
    }

    public boolean removePackage(String code) {

        Package packageFound = searchPackage(code);

        if (packageFound == null) {
            return false;
        }

        packages.remove(packageFound);
        packagesByCode.remove(code);

        return true;
    }



    public ArrayList<Package> getPackages() {

        ArrayList<Package> copy = new ArrayList<>();

        for (int i = 0; i < packages.size(); i++) {
            copy.add(packages.get(i));
        }

        return copy;
    }

    public void clearPackages() {

        packages.clear();
        packagesByCode.clear();
    }

    public double calcularPesoTotalPorMunicipio(ArrayList<Package> packages, String municipio) {
        return calcularPesoRecursivo(packages, municipio, 0);
    }

    private double calcularPesoRecursivo(ArrayList<Package> packages, String municipio, int indice) {
        if (indice == packages.size()) {
            return 0.0;
        }

        Package actual = packages.get(indice);
        double pesoActual = 0.0;

        if (actual.getMunicipio() != null && actual.getMunicipio().equalsIgnoreCase(municipio)) {
            pesoActual = actual.getWeight(); // Como ya es double, se usa directamente
        }

        return pesoActual + calcularPesoRecursivo(packages, municipio, indice + 1);
    }

    public int contarPaquetesPorPrioridadMinima(ArrayList<Package> packages, int minPriority) {
        return contarPaquetesRecursivo(packages, minPriority, 0);
    }

    private int contarPaquetesRecursivo(ArrayList<Package> packages, int minPriority, int indice) {
        if (indice == packages.size()) {
            return 0;
        }

        Package actual = packages.get(indice);
        int coincide = 0;


        if (actual.getPriority() >= minPriority) {
            coincide = 1;
        }


        return coincide + contarPaquetesRecursivo(packages, minPriority, indice + 1);
    }
}
