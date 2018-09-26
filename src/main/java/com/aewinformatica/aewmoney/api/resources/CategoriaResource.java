package com.aewinformatica.aewmoney.api.resources;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aewinformatica.aewmoney.api.event.RecursoCriadoEvent;
import com.aewinformatica.aewmoney.api.model.Categoria;
import com.aewinformatica.aewmoney.api.repository.Categorias;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	Categorias categorias;
	
	@Autowired
	ApplicationEventPublisher publisher;	
	
	@GetMapping
	public List<Categoria>listar(){
		
		return categorias.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria,HttpServletResponse response  ){
		
		Categoria categoriaSalva = categorias.save(categoria);
		
	  publisher.publishEvent(new RecursoCriadoEvent(this,response,categoriaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo){
		
		 Optional<Categoria> categoria = categorias.findById(codigo);
		 
		 return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}
}
