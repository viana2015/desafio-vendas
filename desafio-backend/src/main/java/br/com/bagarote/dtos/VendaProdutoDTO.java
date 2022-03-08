package br.com.bagarote.dtos;

import java.math.BigDecimal;

import br.com.bagarote.model.VendaProduto;
import br.com.bagarote.model.VendaProduto.VendaProdutoId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendaProdutoDTO {

	private VendaProdutoId vendaProdutoId;
	
	private VendaDTO venda;
	private Long idProduto;
	private BigDecimal valorUnitario;
	private Integer qtd;
	private BigDecimal valorTotal;
	
	
	 
	
	public VendaProdutoDTO(VendaProduto entity) {
		valorUnitario = entity.getValorUnitario();
		qtd = entity.getQtd();
		valorTotal = entity.getValorTotal();
		venda =  new VendaDTO(entity.getVendaProdutoId().getVenda());
		//produto = new ProdutoDTO(entity.getVendaProdutoId().getProduto());
		idProduto = entity.getVendaProdutoId().getProduto().getIdProduto();
		
	}
	
	
}
