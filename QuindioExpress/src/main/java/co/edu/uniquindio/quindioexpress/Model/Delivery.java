package co.edu.uniquindio.quindioexpress.Model;

import java.time.LocalDate;

// Clase que representa una entrega de un paquete
public class Delivery {

    // Paquete que se va a entregar
    private Package package_;

    // Repartidor encargado de la entrega
    private Dealer dealer;

    // Fecha en la que se realiza la entrega
    private LocalDate date;

    // Constructor para inicializar los datos de la entrega
    public Delivery(Package package_, Dealer dealer, LocalDate date) {
        this.package_ = package_;
        this.dealer = dealer;
        this.date = date;
    }

    // Obtiene el paquete de la entrega
    public Package getPackage_() {
        return package_;
    }

    // Modifica el paquete de la entrega
    public void setPackage_(Package package_) {
        this.package_ = package_;
    }

    // Obtiene el repartidor encargado
    public Dealer getDealer() {
        return dealer;
    }

    // Modifica el repartidor encargado
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    // Obtiene la fecha de la entrega
    public LocalDate getDate() {
        return date;
    }

    // Modifica la fecha de la entrega
    public void setDate(LocalDate date) {
        this.date = date;
    }
}