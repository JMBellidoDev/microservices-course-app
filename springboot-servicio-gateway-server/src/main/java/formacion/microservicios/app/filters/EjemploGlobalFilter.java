package formacion.microservicios.app.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class EjemploGlobalFilter implements GlobalFilter, Ordered {

  private static final Logger LOGGER = LoggerFactory.getLogger(EjemploGlobalFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    LOGGER.info("Ejecutando filtro pre");
    exchange.getRequest().mutate().headers(h -> h.add("token", "123456"));

    return chain.filter(exchange).then(Mono.fromRunnable(() -> {

      LOGGER.info("Ejecutando filtro post");

      Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor -> {

        exchange.getResponse().getHeaders().add("token", valor);

      });

      exchange.getResponse().getCookies().add("color", ResponseCookie.from("rojo").build());
      // exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
    }));
  }

  @Override
  public int getOrder() {
    return 1;
  }

}
