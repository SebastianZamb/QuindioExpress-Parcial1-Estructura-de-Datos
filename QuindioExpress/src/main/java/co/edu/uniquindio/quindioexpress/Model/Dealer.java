package co.edu.uniquindio.quindioexpress.Model;

// Clase que representa un repartidor
public class Dealer {

    // Datos del repartidor
    private int id;
    private String name;
    private String municipio;
    private boolean available;

    // Constructor para inicializar los datos del repartidor
    public Dealer(int id, String name, String municipio, boolean available) {
        this.id = id;
        this.name = name;
        this.municipio = municipio;
        this.available = available;
    }

    // Obtiene el ID del repartidor
    public int getId() {
        return id;
    }

    // Modifica el ID del repartidor
    public void setId(int id) {
        this.id = id;
    }

    // Obtiene el nombre del repartidor
    public String getName() {
        return name;
    }

    // Modifica el nombre del repartidor
    public void setName(String name) {
        this.name = name;
    }

    // Obtiene el municipio del repartidor
    public String getMunicipio() {
        return municipio;
    }

    // Modifica el municipio del repartidor
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    // Indica si el repartidor esta disponible
    public boolean isAvailable() {
        return available;
    }

    // Cambia la disponibilidad del repartidor
    public void setAvailable(boolean available) {
        this.available = available;
    }
}