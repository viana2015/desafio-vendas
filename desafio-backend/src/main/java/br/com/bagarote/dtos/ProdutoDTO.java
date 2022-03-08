package br.com.bagarote.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.bagarote.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idProduto;
	private String produto;
	private String descricao;
	private BigDecimal valorBase;
	//private byte[] imagemProduto;
	private Long idEmpresa;
	
	
	public ProdutoDTO(Produto entity) {
		super();
		idProduto = entity.getIdProduto();
		produto = entity.getProduto();
		descricao = entity.getDescricao();
		valorBase = entity.getValorBase();
		//imagemProduto = entity.getImagemProduto();
		idEmpresa = entity.getEmpresa().getIdEmpresa();
	}
	
	
	
}
