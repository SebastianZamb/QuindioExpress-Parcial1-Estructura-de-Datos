package co.edu.uniquindio.quindioexpress.Model;


public class Package {

    private String code;
    private String municipio;
    private double weight;
    private int priority;
    private String time;

    public Package(String code, String municipio, double weight, int priority, String time) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
