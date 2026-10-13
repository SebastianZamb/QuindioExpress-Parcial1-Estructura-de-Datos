package co.edu.uniquindio.quindioexpress.Comparators;
import java.util.Comparator;
import co.edu.uniquindio.quindioexpress.Model.Package;

public class PackageComparators {

    public static final Comparator<Package> POR_PRIORIDAD_DESC =
            Comparator.comparingInt(Package::getPriority).reversed();


    public static final Comparator<Package> POR_PESO_DESC =
            Comparator.comparingDouble(Package::getWeight).reversed();

    public static final Comparator<Package> POR_TIEMPO_ASC =
            Comparator.comparingInt(Package::getTime);
}
