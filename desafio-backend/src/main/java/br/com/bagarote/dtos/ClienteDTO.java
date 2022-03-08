package br.com.bagarote.dtos;

import br.com.bagarote.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {

	private Long idCliente;
	private String nome;
	private String cpf;
	private String telefone;
	private Long idEmpresa;
	
	public ClienteDTO(Cliente entity) {
		idCliente = entity.getIdCliente();
		nome = entity.getNome();
		cpf = entity.getCpf();
		telefone = entity.getTelefone();
		idEmpresa = entity.getEmpresa().getIdEmpresa();
	}
	
	
}
