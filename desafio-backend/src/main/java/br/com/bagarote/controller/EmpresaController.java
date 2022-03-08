package br.com.bagarote.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bagarote.dtos.EmpresaDTO;
import br.com.bagarote.dtos.ListEmpresaDTO;
import br.com.bagarote.servicies.EmpresaService;

@RestController
public class EmpresaController {
	
	@Autowired
	private EmpresaService service;
	
	@GetMapping("empresa")
	public ResponseEntity<List<ListEmpresaDTO>> getaAll() {
	    return ResponseEntity.ok().body(service.getAll());
    }
	@GetMapping("empresa/{idEmpresa}")
	public ResponseEntity<EmpresaDTO> getByIdEmpresa(@PathVariable Long idEmpresa) {
	    return ResponseEntity.ok().body(service.findById(idEmpresa));
    }
	@PostMapping("empresa")
	public ResponseEntity<EmpresaDTO> create(@RequestBody EmpresaDTO objDto) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(objDto));
    }
	@PutMapping("empresa/{idEmpresa}")
	public ResponseEntity<EmpresaDTO> update(@PathVariable Long idEmpresa, @RequestBody EmpresaDTO objDto) {
		service.findById(idEmpresa);
		BeanUtils.copyProperties(idEmpresa, objDto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.update(idEmpresa, objDto));
    }
	@DeleteMapping("empresa/{idEmpresa}")
	public ResponseEntity<EmpresaDTO> delete(@PathVariable Long idEmpresa){
		service.delete(idEmpresa);
		return ResponseEntity.noContent().build();
	}
	
}
