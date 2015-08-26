package pl.codepot.butelkatr.base

import com.ofg.infrastructure.base.MvcIntegrationSpec
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import pl.codepot.butelkatr.Application

@ContextConfiguration(classes = [Application], loader = SpringApplicationContextLoader)
class MicroserviceMvcIntegrationSpec extends MvcIntegrationSpec {
}
