package br.com.bagarote.dtos;

import br.com.bagarote.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListClienteDTO {

	private Long idCliente;
	private String nome;
	
	public ListClienteDTO(Cliente entity) {
		idCliente = entity.getIdCliente();
		nome = entity.getNome();
	}
	
	
}
