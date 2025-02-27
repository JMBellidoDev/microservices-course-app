package formacion.microservicios.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = { "formacion.microservicios.app.models" })
public class SpringbootServicioProductosApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootServicioProductosApplication.class, args);
  }

}
