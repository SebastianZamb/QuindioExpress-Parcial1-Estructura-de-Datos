package co.edu.uniquindio.quindioexpress.Controller;

import co.edu.uniquindio.quindioexpress.Model.Dealer;

import java.util.Map;

// Controlador para manejar los repartidores
public class dealerController {

    // Mapa de repartidores identificados por su ID
    Map<Integer, Dealer> mapDealers;

    // Registra un repartidor si su ID no existe
    public boolean existsDealerById(Dealer newDealer) {
        if (mapDealers.containsKey(newDealer.getId())) {
            return false;
        }
        mapDealers.put(newDealer.getId(), newDealer);
        return true;
    }
}