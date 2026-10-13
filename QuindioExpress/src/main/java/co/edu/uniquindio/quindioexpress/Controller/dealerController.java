package co.edu.uniquindio.quindioexpress.Controller;

import co.edu.uniquindio.quindioexpress.Model.Dealer;

import java.util.Map;

public class dealerController {
    Map<Integer, Dealer> mapDealers;


    public boolean existsDealerById(Dealer newDealer){
        if (mapDealers.containsKey(newDealer.getId())){
            return false;
        }
        mapDealers.put(newDealer.getId(), newDealer);
        return true;
    }
}
