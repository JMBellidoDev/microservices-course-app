package formacion.microservicios.app.gateway_filter_factory;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import lombok.Data;
import reactor.core.publisher.Mono;

@Component
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<Configuracion> {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);

  /** Constructor que redirige al constructor padre la clase de configuración */
  public EjemploGatewayFilterFactory() {
    super(Configuracion.class);
  }

  @Override
  public GatewayFilter apply(Configuracion config) {

    return new OrderedGatewayFilter((exchange, chain) -> {

      // pre
      String msgPreLogger = String.format("Ejecutando pre gateway filter factory: %s", config.getMensaje());
      LOGGER.info(msgPreLogger);

      // enlazando con filter -> post
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {

        Optional.ofNullable(config.getCookieValor()).ifPresent(cookie -> {

          exchange.getResponse()
              .addCookie(ResponseCookie.from(config.getCookieNombre(), config.getCookieValor()).build());

        });

        String msgPostLogger = String.format("Ejecutando post gateway filter factory: %s", config.getMensaje());
        LOGGER.info(msgPreLogger);

      }));
    }, 2);
  }

  @Override
  public String name() {
    return "Ejemplo";
  }

}

@Data
class Configuracion {

  private String mensaje;

  private String cookieNombre;

  private String cookieValor;

}