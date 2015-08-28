package pl.codepot.butelkatr.bottling.service;

import org.springframework.stereotype.Service;
import pl.codepot.butelkatr.bottling.model.Bottle;

/**
 * @author kubukoz
 *         created on 28/08/15.
 */
@Service
class BottleService{
    boolean validateBottle(Bottle bottle){
        return bottle != null && bottle.getWort()==1000;
    }
}
