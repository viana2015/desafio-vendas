package br.com.bagarote.dtos;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bagarote.model.MetodoPagamento;
import br.com.bagarote.model.Venda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VendaDTO {
	
	private Long idVenda;

	private Long idCliente;

	private Long idEmpresa;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String dataVenda;

	private BigDecimal valorTotal;
	private BigDecimal valorDesconto;
	private BigDecimal valorAcrescimo;
	private BigDecimal valorPago;
	
	@Enumerated(EnumType.STRING)
	private MetodoPagamento metodoPagamento;

	private List<VendaProdutoDTO> produtos;
	
	
	
	public VendaDTO(Venda entity) {
		super();
		idVenda = entity.getIdVenda();
		dataVenda = entity.getDataVenda().format(DateTimeFormatter.ofPattern(dataVenda));
		metodoPagamento = entity.getMetodoPagamento();
		valorAcrescimo = entity.getValorAcrescimo();
		valorDesconto = entity.getValorDesconto();
		valorPago = entity.getValorPago();
		//cliente =  new ClienteDTO(entity.getCliente());
		//empresa = new EmpresaDTO(entity.getEmpresa());
		idCliente = entity.getCliente().getIdCliente();
		idEmpresa = entity.getEmpresa().getIdEmpresa();
		
	}
		
	
	
	
}
