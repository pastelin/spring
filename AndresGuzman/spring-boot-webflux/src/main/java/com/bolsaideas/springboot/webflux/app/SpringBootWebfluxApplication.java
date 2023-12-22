package com.bolsaideas.springboot.webflux.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.bolsaideas.springboot.webflux.app.models.dao.ProductoDao;
import com.bolsaideas.springboot.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

	@Autowired
	private ProductoDao dao;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Elimina los registros de la BD
		mongoTemplate.dropCollection("productos").subscribe();
		
		// Crea resgistros en la BD
		Flux.just(new Producto("TV Panasonic Pantalla LCD", 456.89),
				new Producto("Sony Camara HD Digital", 456.89),
				new Producto("Apple iPod", 456.89),
				new Producto("Sony Notebook", 456.89)
				)
		.flatMap(producto -> {
			producto.setCreateAt(new Date());
			return dao.save(producto);
		})
		.subscribe(producto -> LOGGER.info("Insert: {} {}", producto.getId(), producto.getNombre()));
	}

}
