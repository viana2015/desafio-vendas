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

import br.com.bagarote.dtos.ListProdutoDTO;
import br.com.bagarote.dtos.ProdutoDTO;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.model.Produto;
import br.com.bagarote.repository.EmpresaRepository;
import br.com.bagarote.repository.ProdutoRepository;
import br.com.bagarote.servicies.exceptions.DatabaseException;
import br.com.bagarote.servicies.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@Transactional(readOnly = true)
	public List<ListProdutoDTO>findAll() {
		List<Produto>empresa = repository.findAll();
		return empresa.stream().map(x -> new ListProdutoDTO(x)).collect(Collectors.toList());
	}
	
	
	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long idProduto) {
		Optional<Produto>entity = repository.findById(idProduto);
		Produto obj =  entity.orElseThrow(() -> new ResourceNotFoundException("Código de ID informado não consta na base de dados!"));
		return new ProdutoDTO(obj);
	}
	
	@Transactional
	public ProdutoDTO save(ProdutoDTO objDTO) {
		Long idEmpresa = objDTO.getIdEmpresa();
		Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(()-> new 
				ResourceNotFoundException("Empresa informada não consta no banco de dados. ID: " + idEmpresa));
		
		Produto entity = new Produto();
		entity.setProduto(objDTO.getProduto());
		entity.setDescricao(objDTO.getDescricao());
		entity.setValorBase(objDTO.getValorBase());
		//entity.setImagemProduto(objDTO.getImagemProduto());
		entity.setEmpresa(empresa);
		entity = repository.save(entity);
		return new ProdutoDTO(entity);
		
	}
	
	@Transactional
	public ProdutoDTO update(Long idProduto, ProdutoDTO objDTO) {
		try {
			Long idEmpresa = objDTO.getIdEmpresa();
			Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(()-> new
					ResourceNotFoundException("Empresa informada não consta no banco de dados. ID: " + idEmpresa));
			Produto entity = repository.getById(idProduto);
			entity.setProduto(objDTO.getProduto());
			entity.setDescricao(objDTO.getDescricao());
			entity.setValorBase(objDTO.getValorBase());
			//entity.setImagemProduto(objDTO.getImagemProduto());
			entity.setEmpresa(empresa);
			entity = repository.save(entity);
			return new ProdutoDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Código de ID informado não consta na base de dados!");
		}
	}
	
	@Transactional
	public void delete(Long idProduto) {
		try {
			repository.deleteById(idProduto);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Código de ID informado não consta na base de dados!" + idProduto);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não foi possivél deletear pois já existe empresa associados a este produto!\"");
		}
	}
}
