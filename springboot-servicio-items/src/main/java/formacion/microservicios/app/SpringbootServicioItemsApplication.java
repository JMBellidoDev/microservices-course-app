package formacion.microservicios.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EntityScan(basePackages = { "formacion.microservicios.app.models" })
public class SpringbootServicioItemsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootServicioItemsApplication.class, args);
  }

}
