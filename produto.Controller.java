package com.generation.lojadegames.lojadegames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojadegames.lojadegames.model.Produto;
import com.generation.lojadegames.lojadegames.repository.categoriasRepository;
import com.generation.lojadegames.lojadegames.repository.produtosRepository;


@RestController
@RequestMapping ("/produtos")
@CrossOrigin ("*")
public class produtoController {
	@Autowired
	private produtosRepository repository; 
	
	@Autowired
	private categoriasRepository categoriarepositorio; 
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(repository.findAll()); 
		
	}
		
	@GetMapping ("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
		
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	
	@PostMapping
	public ResponseEntity<Produto> postPostagem (@Valid @RequestBody Produto produto){
		if (categoriarepositorio.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
	public ResponseEntity<Produto> putPostagem (@Valid @RequestBody Produto produto){
			if (repository.existsById(produto.getId())){
				if (categoriarepositorio.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		java.util.Optional<Produto> produto = repository.findById(id);
		
		if (produto.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		repository.deleteById(id);
		
	}
}
