package com.bolsaideas.springboot.webflux.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsaideas.springboot.webflux.app.models.dao.ProductoDao;
import com.bolsaideas.springboot.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private ProductoDao dao;

	@GetMapping
	public Flux<Producto> index() {
		return dao.findAll().map(producto -> {
			producto.setNombre(producto.getNombre().toUpperCase());
			return producto;
		}).doOnNext(prod -> LOGGER.info(prod.getNombre()));
	}
	
	@GetMapping("/{id}")
	public Mono<Producto> show(@PathVariable String id) {
//		Mono<Producto> producto = dao.findById(id);
		Flux<Producto> productos = dao.findAll();
		
		return productos.filter( p -> p.getId().equals(id))
				.next()
				.doOnNext(prod -> LOGGER.info(prod.getId()));
	}
}
