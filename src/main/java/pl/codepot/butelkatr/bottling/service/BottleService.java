package pl.codepot.butelkatr.bottling.service;

import com.google.common.util.concurrent.ListenableFuture;
import com.nurkiewicz.asyncretry.RetryExecutor;
import com.ofg.infrastructure.discovery.ServiceAlias;
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.netflix.hystrix.HystrixCommand.Setter.withGroupKey;
import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 * @author kubukoz
 *         created on 28/08/15.
 */
@Service
public class BottleService {
    private ServiceRestClient serviceRestClient;
    private RetryExecutor retryExecutor;

    @Autowired
    public BottleService(ServiceRestClient serviceRestClient, RetryExecutor retryExecutor) {
        this.serviceRestClient = serviceRestClient;
        this.retryExecutor = retryExecutor;
    }

    public ListenableFuture<Void> sendBottlingUpdate() {
        return serviceRestClient.forService(new ServiceAlias("prezentatr"))
                .retryUsing(retryExecutor)
                .put()
                .withCircuitBreaker(withGroupKey(asKey("hystrix_group")))
                .onUrl("/feed/butelkatr")
                .withoutBody()
                .withHeaders().contentType("application/vnd.pl.codepot.prezentatr.v1+json")
                .andExecuteFor().ignoringResponseAsync();
    }

    public ListenableFuture<Void> putBottleCount(Long count) {
        return serviceRestClient.forService(new ServiceAlias("prezentatr"))
                .retryUsing(retryExecutor)
                .put()
                .withCircuitBreaker(withGroupKey(asKey("hystrix_group")))
                .onUrl("/feed/bottles/"+count)
                .withoutBody()
                .withHeaders().contentType("application/vnd.pl.codepot.prezentatr.v1+json")
                .andExecuteFor().ignoringResponseAsync();
    }

}
