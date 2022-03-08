package br.com.bagarote.servicies;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bagarote.dtos.VendaDTO;
import br.com.bagarote.dtos.VendaProdutoDTO;
import br.com.bagarote.model.Cliente;
import br.com.bagarote.model.Empresa;
import br.com.bagarote.model.Produto;
import br.com.bagarote.model.Venda;
import br.com.bagarote.model.VendaProduto;
import br.com.bagarote.repository.ClienteRepository;
import br.com.bagarote.repository.EmpresaRepository;
import br.com.bagarote.repository.ProdutoRepository;
import br.com.bagarote.repository.VendaProdutoRepository;
import br.com.bagarote.repository.VendaRepository;
//import br.com.bagarote.servicies.exceptions.DatabaseException;
import br.com.bagarote.servicies.exceptions.ResourceNotFoundException;

@Service
public class VendaService {

	
	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private VendaProdutoRepository vendaProdutoRepository;
	
	
	
	@Transactional
	public VendaDTO save(VendaDTO objDTO) {
		Long idEmpresa = objDTO.getIdEmpresa();
		Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(()-> new 
				ResourceNotFoundException("Empresa informada não consta no banco de dados. ID: " + idEmpresa));
		
		Long idCliente = objDTO.getIdCliente();
		Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new 
				ResourceNotFoundException("Cliente informado não consta no banco de dados. ID: " + idCliente));
		
		Venda entity = new Venda();
		
		entity.setDataVenda(LocalDateTime.now());
		entity.setMetodoPagamento(objDTO.getMetodoPagamento());
		entity.setValorAcrescimo(objDTO.getValorAcrescimo());
		entity.setValorDesconto(objDTO.getValorDesconto());
		entity.setValorPago(objDTO.getValorPago());
		entity.setValorTotal(objDTO.getValorTotal());
		entity.setEmpresa(empresa);
		entity.setCliente(cliente);
		repository.save(entity);
		List<VendaProduto> vendasProduto = converterProdutos(entity, objDTO.getProdutos());
		vendaProdutoRepository.saveAll(vendasProduto);
		entity.setProdutos(vendasProduto);
		return new VendaDTO(entity);
		
	}
		
	private List<VendaProduto> converterProdutos(Venda venda, List<VendaProdutoDTO> produtos) {
		if (produtos.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivél realizar a venda sem produtos.");
		}
		
		return produtos.stream().map(dto -> {
			Long idProduto = dto.getIdProduto();
			Produto produto =  produtoRepository.findById(idProduto).orElseThrow(()-> new
					ResourceNotFoundException("Produto informado não consta no banco de dados. ID: " + idProduto));
			
			VendaProduto entity = new VendaProduto();
			entity.setQtd(dto.getQtd());
			entity.setVenda(venda);
			entity.setProduto(produto);
			return entity;
		}).collect(Collectors.toList());
	}
	
	@Transactional
	public VendaDTO salvaVenda(VendaDTO objDTO) {
		Long idEmpresa = objDTO.getIdEmpresa();
		Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(()-> new 
				ResourceNotFoundException("Empresa informada não consta no banco de dados. ID: " + idEmpresa));
		
		Long idCliente = objDTO.getIdCliente();
		Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new 
				ResourceNotFoundException("Cliente informado não consta no banco de dados. ID: " + idCliente));
		
		Venda entity = new Venda();
		
		entity.setDataVenda(LocalDateTime.now());
		entity.setMetodoPagamento(objDTO.getMetodoPagamento());
		entity.setValorAcrescimo(objDTO.getValorAcrescimo());
		entity.setValorDesconto(objDTO.getValorDesconto());
		entity.setValorPago(objDTO.getValorPago());
		entity.setValorTotal(objDTO.getValorTotal());
		entity.setEmpresa(empresa);
		entity.setCliente(cliente);
		repository.save(entity);
		entity.getProdutos().stream().forEach(vp -> vp.setVenda(entity));
		vendaProdutoRepository.saveAll(entity.getProdutos());
		//List<VendaProduto> vendasProduto = converterProdutos(entity, objDTO.getProdutos());

		//entity.setProdutos(vendasProduto);
		return new VendaDTO(entity);
		
	}	
	
}
