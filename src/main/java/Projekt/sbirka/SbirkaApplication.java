package Projekt.sbirka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
@ComponentScan()
@SpringBootApplication
public class SbirkaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SbirkaApplication.class, args);
}}
