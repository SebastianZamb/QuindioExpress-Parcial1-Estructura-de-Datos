package co.edu.uniquindio.quindioexpress.Model;

// Clase que representa un paquete y permite compararlos por codigo
public class Package implements Comparable<Package> {

    // Datos principales del paquete
    private String code;
    private String municipio;
    private double weight;
    private int priority;
    private int time;

    // Constructor para inicializar los datos del paquete
    public Package(String code, String municipio, double weight, int priority, int time) {
        this.code = code;
        this.municipio = municipio;
        this.weight = weight;
        this.priority = priority;
        this.time = time;
    }

    // Obtiene el codigo del paquete
    public String getCode() {
        return code;
    }

    // Modifica el codigo del paquete
    public void setCode(String code) {
        this.code = code;
    }

    // Obtiene el municipio de destino
    public String getMunicipio() {
        return municipio;
    }

    // Modifica el municipio de destino
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    // Obtiene el peso del paquete
    public double getWeight() {
        return weight;
    }

    // Modifica el peso del paquete
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Obtiene la prioridad del paquete
    public int getPriority() {
        return priority;
    }

    // Modifica la prioridad del paquete
    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Obtiene el tiempo asociado al paquete
    public int getTime() {
        return time;
    }

    // Modifica el tiempo asociado al paquete
    public void setTime(int time) {
        this.time = time;
    }

    // Compara dos paquetes por su codigo para establecer un orden
    @Override
    public int compareTo(Package otroPaquete) {
        if (otroPaquete == null) {
            throw new NullPointerException("El paquete no puede ser null.");
        }

        if (this.code == null && otroPaquete.getCode() == null) {
            return 0;
        }

        if (this.code == null) {
            return -1;
        }

        if (otroPaquete.getCode() == null) {
            return 1;
        }

        return this.code.compareTo(otroPaquete.getCode());
    }
}