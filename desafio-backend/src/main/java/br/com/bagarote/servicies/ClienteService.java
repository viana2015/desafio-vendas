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

import br.com.bagarote.dtos.ClienteDTO;
import br.com.bagarote.dtos.ClienteFullDTO;
import br.com.bagarote.dtos.ListClienteDTO;
import br.com.bagarote.model.Cliente;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.repository.ClienteRepository;
import br.com.bagarote.repository.EmpresaRepository;
import br.com.bagarote.servicies.exceptions.DatabaseException;
import br.com.bagarote.servicies.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@Transactional(readOnly = true)
	public List<ListClienteDTO>getAll() {
		List<Cliente>empresa = repository.findAll();
		return empresa.stream().map(x -> new ListClienteDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<ClienteFullDTO>getFullAll() {
		List<Cliente>empresa = repository.findAll();
		return empresa.stream().map(x -> new ClienteFullDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ClienteFullDTO findById(Long idCliente) {
		Optional<Cliente>entity = repository.findById(idCliente);
		Cliente obj =  entity.orElseThrow(() -> new ResourceNotFoundException("Código de ID informado não consta na base de dados!"));
		return new ClienteFullDTO(obj);
	}
	
	@Transactional
	public ClienteDTO save(ClienteDTO objDTO) {
		Long idEmpresa = objDTO.getIdEmpresa();
		Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(()-> new
				ResourceNotFoundException("Empresa com este ID não encontrada na base de dados" + idEmpresa));
		Cliente entity = new Cliente();
		entity.setNome(objDTO.getNome());
		entity.setCpf(objDTO.getCpf());
		entity.setTelefone(objDTO.getTelefone());
		//empresa.setIdEmpresa(objDTO.getIdEmpresa());
		entity.setEmpresa(empresa);
		entity = repository.save(entity);
		return new ClienteDTO(entity);
		
	}
	
	@Transactional
	public ClienteDTO update(Long idCliente, ClienteDTO objDTO) {
		try {
			Long idEmpresa = objDTO.getIdEmpresa();
			Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(()-> new
					ResourceNotFoundException("Empresa não encontrada na base de dados ID: " + idEmpresa));
			Cliente entity = repository.getById(idCliente);
			entity.setNome(objDTO.getNome());
			entity.setCpf(objDTO.getCpf());
			entity.setTelefone(objDTO.getTelefone());
			entity.setEmpresa(empresa);
			entity = repository.save(entity);
			return new ClienteDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Código de ID informado não consta na base de dados!");
		}		
	}
	
	@Transactional
	public void delete(Long idCliente) {
		try {
			repository.deleteById(idCliente);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Código de ID informado não consta na base de dados!" + idCliente);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não foi possivél deletear pois já existe Empresa associados a esta Cliente!\"");
		}
	}
}
