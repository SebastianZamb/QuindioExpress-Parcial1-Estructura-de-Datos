package co.edu.uniquindio.quindioexpress.Model;


public class Package implements Comparable<Package> {

    private String code;
    private String municipio;
    private double weight;
    private int priority;
    private int time;

    public Package(String code, String municipio, double weight, int priority, int time) {
        this.code = code;
        this.municipio = municipio;
        this.weight = weight;
        this.priority = priority;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Package otroPaquete) {
        return this.code.compareTo(otroPaquete.getCode());
    }
}
