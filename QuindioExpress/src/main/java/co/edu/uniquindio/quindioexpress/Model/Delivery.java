package co.edu.uniquindio.quindioexpress.Model;

import java.time.LocalDate;

public class Delivery {

    private Package package_;
    private Dealer dealer;
    private LocalDate date;

    public Delivery(Package package_, Dealer dealer, LocalDate date) {
        this.package_ = package_;
        this.dealer = dealer;
        this.date = date;
    }

    public Package getPackage_() {
        return package_;
    }

    public void setPackage_(Package package_) {
        this.package_ = package_;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
