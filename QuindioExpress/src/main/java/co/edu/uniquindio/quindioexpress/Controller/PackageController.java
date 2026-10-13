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

        if (newPackage == null) {
            return;
        }

        if (newPackage.getCode() == null ||
                newPackage.getCode().isEmpty()) {
            return;
        }

        if (newPackage.getMunicipio() == null ||
                newPackage.getMunicipio().isEmpty()) {
            return;
        }

        if (newPackage.getWeight() <= 0) {
            return;
        }

        if (newPackage.getPriority() < 1 ||
                newPackage.getPriority() > 5) {
            return;
        }

        if (packagesByCode.containsKey(newPackage.getCode())) {
            return;
        }

        packages.add(newPackage);
        packagesByCode.put(newPackage.getCode(), newPackage);
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

    public Package findHeaviestPackage() {

        if (packages.isEmpty()) {
            return null;
        }

        return findHeaviestPackage(
                packages, 0, packages.size() - 1);
    }

    private Package findHeaviestPackage(
            ArrayList<Package> packages, int start, int end) {

        if (start == end) {
            return packages.get(start);
        }

        int middle = (start + end) / 2;

        Package left = findHeaviestPackage(
                packages, start, middle);

        Package right = findHeaviestPackage(
                packages, middle + 1, end);

        if (left.getWeight() > right.getWeight()) {
            return left;
        } else {
            return right;
        }
    }

    public Package binarySearch(String code) {

        if (code == null || code.isEmpty()) {
            return null;
        }

        ArrayList<Package> orderedPackages = getPackages();

        orderedPackages.sort((p1, p2) ->
                p1.getCode().compareTo(p2.getCode()));

        int start = 0;
        int end = orderedPackages.size() - 1;

        while (start <= end) {

            int middle = start + (end - start) / 2;

            Package current = orderedPackages.get(middle);

            int comparison = current.getCode().compareTo(code);

            if (comparison == 0) {
                return current;
            }

            if (comparison < 0) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return null;
    }

    public double calculateTotalWeightByMunicipality(
            ArrayList<Package> packages, String municipality) {

        if (packages == null || municipality == null) {
            return 0.0;
        }

        return calculateWeightRecursively(
                packages, municipality, 0);
    }

    private double calculateWeightRecursively(
            ArrayList<Package> packages,
            String municipality,
            int index) {

        if (index == packages.size()) {
            return 0.0;
        }

        Package current = packages.get(index);
        double currentWeight = 0.0;

        if (current.getMunicipio() != null &&
                current.getMunicipio().equalsIgnoreCase(municipality)) {
            currentWeight = current.getWeight();
        }

        return currentWeight +
                calculateWeightRecursively(
                        packages, municipality, index + 1);
    }

    public int countPackagesByMinimumPriority(
            ArrayList<Package> packages, int minPriority) {

        if (packages == null) {
            return 0;
        }

        return countPackagesRecursively(
                packages, minPriority, 0);
    }

    private int countPackagesRecursively(
            ArrayList<Package> packages,
            int minPriority,
            int index) {

        if (index == packages.size()) {
            return 0;
        }

        Package current = packages.get(index);
        int matches = 0;

        if (current.getPriority() >= minPriority) {
            matches = 1;
        }

        return matches +
                countPackagesRecursively(
                        packages, minPriority, index + 1);
    }
}