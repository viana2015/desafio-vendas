package br.com.bagarote.dtos;

import br.com.bagarote.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ListEmpresaDTO {

	private Long idEmpresa;
	private String nomeFantasia;
	
	
	public ListEmpresaDTO(Empresa entity) {
		idEmpresa = entity.getIdEmpresa();
		nomeFantasia = entity.getNomeFantasia();
	}
	
	
}
