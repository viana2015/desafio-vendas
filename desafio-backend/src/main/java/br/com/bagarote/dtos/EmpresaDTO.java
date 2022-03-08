package br.com.bagarote.dtos;

import br.com.bagarote.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpresaDTO {

	private Long idEmpresa;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String telefone;
	private String responsavelLegal;
	
	public EmpresaDTO(Empresa entity) {
		idEmpresa = entity.getIdEmpresa();
		nomeFantasia = entity.getNomeFantasia();
		razaoSocial = entity.getRazaoSocial();
		cnpj = entity.getCnpj();
		telefone = entity.getTelefone();
		responsavelLegal = entity.getResponsavelLegal();
	}
	
	
}
