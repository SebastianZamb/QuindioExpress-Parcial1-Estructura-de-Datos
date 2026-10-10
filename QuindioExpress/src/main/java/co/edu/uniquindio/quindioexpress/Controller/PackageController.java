
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
}
