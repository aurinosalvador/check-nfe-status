package io.github.aurinosalvador.checknfestatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "io.github.aurinosalvador")
@EntityScan(basePackages = "io.github.aurinosalvador.entities")
public class CheckNfeStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckNfeStatusApplication.class, args);
	}

}
