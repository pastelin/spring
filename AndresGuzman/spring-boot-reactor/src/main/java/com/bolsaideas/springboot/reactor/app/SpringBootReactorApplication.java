package com.bolsaideas.springboot.reactor.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bolsaideas.springboot.reactor.app.models.Usuario;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	/**
	 * doOnNext: se ejecuta cada vez que el observador (nombres) notifica que ha
	 * llegado un nuevo elemento
	 */
	@Override
	public void run(String... args) throws Exception {
		
		List<String> usuariosList = new ArrayList<>();
		usuariosList.add("Juan Pastelin");
		usuariosList.add("Ulises Osnaya");
		usuariosList.add("Sonia Tellez");
		usuariosList.add("Marco Yokunaga");
		usuariosList.add("Julio Martinez");
		usuariosList.add("Buce Lee");
		usuariosList.add("Bruce Willis");
		
		Flux<String> nombres = Flux.fromIterable(usuariosList); //Flux.just("Juan Pastelin", "Ulises Osnaya", "Sonia Tellez", "Marco Yokunaga", "Julio Martinez", "Buce Lee", "Bruce Willis");
		
		Flux<Usuario> usuarios = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
				.filter(usuario -> usuario.getNombre().equalsIgnoreCase("bruce"))
				.doOnNext(usuario -> {
					if (usuario == null) {
						throw new RuntimeException("Nombres no pueden ser vacios");
					}
					System.out.println(usuario.getNombre());
				}).
				map(usuario -> {
					String nombre = usuario.getNombre().toLowerCase();
					usuario.setNombre(nombre);
					return usuario;
				});

		usuarios.subscribe(e -> LOGGER.info(e.getNombre()), error -> LOGGER.error(error.getLocalizedMessage()), new Runnable() {

			@Override
			public void run() {
				LOGGER.info("Ha finalizado la ejecución del observable con exito!!");
			}

		});
	}

}
