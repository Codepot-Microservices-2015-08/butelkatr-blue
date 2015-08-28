package pl.codepot.butelkatr.bottling.controller;

import com.google.common.util.concurrent.ListenableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.codepot.butelkatr.bottling.model.Version;
import pl.codepot.butelkatr.bottling.model.WortCount;
import pl.codepot.butelkatr.bottling.service.BottleService;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kubukoz
 *         created on 28/08/15.
 */

@RestController
public class BottleController {
    public static final Logger log = LoggerFactory.getLogger(BottleController.class);
    BottleService bottleService;
    private AtomicLong wortCountTotal = new AtomicLong(0);


    @Autowired
    public BottleController(BottleService bottleService) {
        this.bottleService = bottleService;
    }

    @RequestMapping(value = "/bottle",
            method = RequestMethod.POST,
            consumes = Version.V1)
    public synchronized void receiveWort(@RequestBody WortCount wortCount, HttpServletResponse response) throws InterruptedException {
        wortCountTotal.addAndGet(wortCount.getWort());
        response.setStatus(HttpServletResponse.SC_OK);

        if (wortCountTotal.get() >= 3000) {
            bottleService.sendBottlingUpdate();
            Thread.sleep(5000);
            log.info("finished");
            ListenableFuture<Void> promise = bottleService.putBottleCount(wortCountTotal.get() / 100);
            //noinspection StatementWithEmptyBody
            while (!promise.isDone())
            wortCountTotal.set(0);
        }
    }
}

