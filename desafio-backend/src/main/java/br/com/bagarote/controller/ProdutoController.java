package br.com.bagarote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bagarote.dtos.ListProdutoDTO;
import br.com.bagarote.dtos.ProdutoDTO;
import br.com.bagarote.servicies.ProdutoService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("produto")
	public ResponseEntity<List<ListProdutoDTO>> getAll() {
	    return ResponseEntity.ok().body(service.findAll());
    }
	@GetMapping("produto/{idProduto}")
	public ResponseEntity<ProdutoDTO> getByIdProduto(@PathVariable Long idProduto) {
	    return ResponseEntity.ok().body(service.findById(idProduto));
    }
	@PostMapping("produto")
	public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO createProduto) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(createProduto));
    }
	@PutMapping("produto/{idProduto}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long idProduto, @RequestBody ProdutoDTO updateProduto) {
		service.findById(idProduto);
	    return ResponseEntity.status(HttpStatus.OK).body(service.update(idProduto, updateProduto));
    }
}
