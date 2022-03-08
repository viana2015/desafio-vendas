package br.com.bagarote.dtos;

import br.com.bagarote.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListProdutoDTO {

	private Long idProduto;
	private String produto;
	
	public ListProdutoDTO(Produto entity) {
		idProduto = entity.getIdProduto();
		produto = entity.getProduto();
	}
	
	
}
