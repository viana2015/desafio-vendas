package br.com.bagarote.servicies;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bagarote.dtos.EmpresaDTO;
import br.com.bagarote.dtos.ListEmpresaDTO;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.repository.EmpresaRepository;
import br.com.bagarote.servicies.exceptions.DatabaseException;
import br.com.bagarote.servicies.exceptions.ResourceNotFoundException;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	@Transactional(readOnly = true)
	public List<ListEmpresaDTO>getAll() {
		List<Empresa>empresa = repository.findAll();
		return empresa.stream().map(x -> new ListEmpresaDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public EmpresaDTO findById(Long idEmpresa) {
		Optional<Empresa>entity = repository.findById(idEmpresa);
		Empresa obj =  entity.orElseThrow(() -> new ResourceNotFoundException("Código de ID informado não consta na base de dados!"));
		return new EmpresaDTO(obj);
	}
	
	@Transactional
	public EmpresaDTO save(EmpresaDTO objDTO) {
		Empresa entity = new Empresa();
		entity.setNomeFantasia(objDTO.getNomeFantasia());
		entity.setRazaoSocial(objDTO.getRazaoSocial());
		entity.setCnpj(objDTO.getCnpj());
		entity.setTelefone(objDTO.getTelefone());
		entity.setResponsavelLegal(objDTO.getResponsavelLegal());
		entity = repository.save(entity);
		return new EmpresaDTO(entity);
		
	}
	
	@Transactional
	public EmpresaDTO update(Long idEmpresa, EmpresaDTO objDTO) {
		try {
			Empresa entity = repository.getById(idEmpresa);
			entity.setNomeFantasia(objDTO.getNomeFantasia());
			entity.setRazaoSocial(objDTO.getRazaoSocial());
			entity.setCnpj(objDTO.getCnpj());
			entity.setTelefone(objDTO.getTelefone());
			entity.setResponsavelLegal(objDTO.getResponsavelLegal());
			entity = repository.save(entity);
			return new EmpresaDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Código de ID informado não consta na base de dados!");
		}				
	}
	
	@Transactional
	public void delete(Long idEmpresa) {
		try {
			repository.deleteById(idEmpresa);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Código de ID informado não consta na base de dados!" + idEmpresa);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não foi possivél deletear pois já existe venda associados a esta empresa!\"");
		}
	}
}
