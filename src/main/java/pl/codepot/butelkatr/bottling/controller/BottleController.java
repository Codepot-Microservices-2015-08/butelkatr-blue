package pl.codepot.butelkatr.bottling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.codepot.butelkatr.bottling.model.Bottle;
import pl.codepot.butelkatr.bottling.model.Version;
import pl.codepot.butelkatr.bottling.service.BottleService;

import javax.servlet.http.HttpServletResponse;

/**
 * @author kubukoz
 *         created on 28/08/15.
 */

@RestController
public class BottleController {
    BottleService bottleService;
    public static final Logger log = LoggerFactory.getLogger(BottleController.class);

    @Autowired
    public BottleController(BottleService bottleService) {
        this.bottleService = bottleService;
    }

    @RequestMapping(value = "/bottle",
            method = RequestMethod.POST,
            consumes = Version.V1)
    void checkBottle(@RequestBody Bottle bottle, HttpServletResponse response) {
        boolean bottleValid = bottleService.validateBottle(bottle);
        log.info("Found a " + (bottleValid?"valid":"invalid") + " bottle");
        response.setStatus(bottleValid ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_ACCEPTABLE);
    }
}


