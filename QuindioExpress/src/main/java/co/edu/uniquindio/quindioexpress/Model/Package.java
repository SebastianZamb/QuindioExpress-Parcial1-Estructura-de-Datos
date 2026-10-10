package co.edu.uniquindio.quindioexpress.Model;


public class Package {

    private String code;
    private String municipio;
    private String weight;
    private String priority;
    private String time;

    public Package(String code, String municipio, String weight, String priority, String time) {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
