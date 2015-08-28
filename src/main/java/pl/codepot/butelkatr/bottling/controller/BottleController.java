package pl.codepot.butelkatr.bottling.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.codepot.butelkatr.bottling.model.Bottle;

import javax.servlet.http.HttpServletResponse;

/**
 * @author kubukoz
 *         created on 28/08/15.
 */

@RestController
public class BottleController {
    @RequestMapping(value = "/bottle", method = RequestMethod.POST, consumes = "application/vnd.pl.codepot.butelkatr.v1+json")
    void getBottle(@RequestBody Bottle bottle, HttpServletResponse response){
        response.setStatus(bottle.getWort()==1000?HttpServletResponse.SC_OK:HttpServletResponse.SC_NOT_ACCEPTABLE);
    }
}


