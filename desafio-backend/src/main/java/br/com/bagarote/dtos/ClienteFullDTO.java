package br.com.bagarote.dtos;

import br.com.bagarote.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteFullDTO {

	private Long idCliente;
	private String nome;
	private String cpf;
	private String telefone;
	private EmpresaDTO empresa;
	
	public ClienteFullDTO(Cliente entity) {
		idCliente = entity.getIdCliente();
		nome = entity.getNome();
		cpf = entity.getCpf();
		telefone = entity.getTelefone();
		empresa = new EmpresaDTO(entity.getEmpresa());
	}
}
