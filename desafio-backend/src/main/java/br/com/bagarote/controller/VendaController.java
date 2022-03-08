package br.com.bagarote.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bagarote.dtos.VendaDTO;
import br.com.bagarote.repository.VendaRepository;
import br.com.bagarote.servicies.VendaService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class VendaController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private VendaService service;
	
	private final VendaRepository vendaRepository;
	
	@GetMapping("venda")
	public ResponseEntity<?> getAll() {
	    return ResponseEntity.ok().body(vendaRepository.findAll());
    }
	@PostMapping("venda")
	public ResponseEntity<VendaDTO> create(@RequestBody VendaDTO createVendaRequest) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(createVendaRequest));
    }
	
	
	@GetMapping("venda/{idVenda}")
	public ResponseEntity<?> getVendaByIdVenda(@PathVariable Long idVenda) {
	    return ResponseEntity.ok().body(vendaRepository.findById(idVenda).orElse(null));
    }
}
