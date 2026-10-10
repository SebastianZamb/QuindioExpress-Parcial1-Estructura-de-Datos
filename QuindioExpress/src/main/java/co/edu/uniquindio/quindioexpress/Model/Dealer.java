package co.edu.uniquindio.quindioexpress.Model;

public class Dealer {

    private int id;
    private String name;
    private String municipio;
    private boolean available;

    public Dealer(int id, String name, String municipio, boolean available) {
        this.id = id;
        this.name = name;
        this.municipio = municipio;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
