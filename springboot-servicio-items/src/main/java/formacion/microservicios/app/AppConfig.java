package formacion.microservicios.app;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class AppConfig {

  /**
   * Registro de un cliente para trabajar con API REST. <br/>
   * Implementa un balanceo de carga con Ribbon (@LoadBalanced)
   * 
   * @return RestTemplate
   */
  @Bean("clienteRest")
  @LoadBalanced
  RestTemplate registrarBeanTemplate() {
    return new RestTemplate();
  }

  @Bean
  Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {

    return factory -> factory.configureDefault(id ->

    new Resilience4JConfigBuilder(id)

        // Configuración del CircuitBreaker
        .circuitBreakerConfig(CircuitBreakerConfig.custom()

            // Llamadas a tener en cuenta y su % de fallo
            .slidingWindowSize(10).failureRateThreshold(50)

            // Duración en abierto
            .waitDurationInOpenState(Duration.ofSeconds(10L))

            // Número de llamadas permitidas en estado semiabierto
            .permittedNumberOfCallsInHalfOpenState(6)

            // Llamada lenta (Se responde sin llegar a timeout)
            .slowCallRateThreshold(50).slowCallDurationThreshold(Duration.ofSeconds(2L)).build())

        // Configuración del tiempo límite para timeout
        .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6L)).build()).build()

    );
  }

}
