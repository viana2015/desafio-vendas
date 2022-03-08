package br.com.bagarote.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bagarote.dtos.ClienteDTO;
import br.com.bagarote.dtos.ClienteFullDTO;
import br.com.bagarote.dtos.ListClienteDTO;
import br.com.bagarote.servicies.ClienteService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ClienteController {
			
	@Autowired
	private ClienteService service;
	
	@GetMapping("cliente")
	public ResponseEntity<List<ListClienteDTO>> getAll() {
	    return ResponseEntity.ok().body(service.getAll());
    }
	
	@GetMapping("cliente/full")
	public ResponseEntity<List<ClienteFullDTO>> getFullAll() {
	    return ResponseEntity.ok().body(service.getFullAll());
    }
	@GetMapping("cliente/{idCliente}")
	public ResponseEntity<ClienteFullDTO> getByIdCliente(@PathVariable Long idCliente) {
	    return ResponseEntity.ok().body(service.findById(idCliente));
    }
	@PostMapping("cliente")
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO createCliente) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(createCliente));
    }
	
	@PutMapping("cliente/{idCliente}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long idCliente, @RequestBody ClienteDTO updateCliente) {
		service.findById(idCliente);
		BeanUtils.copyProperties(idCliente, updateCliente);
		return ResponseEntity.status(HttpStatus.OK).body(service.update(idCliente, updateCliente));
    }
}
