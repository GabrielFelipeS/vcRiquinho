package br.com.ifsp.vcRiquinho.produto.model.concrete;

import java.time.LocalDate;

import br.com.ifsp.vcRiquinho.produto.exceptions.DaysCannotByNegativeException;
import br.com.ifsp.vcRiquinho.produto.model.abstracts.Produto;

public class ProdutoRendaFixa extends Produto {
	private Double rendimentoMensal;
	private Integer carencia;
	private LocalDate localDate;

	public ProdutoRendaFixa(Double rendimentoMensalEsperado, Integer carencia) {
		this("vcInveste", 
				"Permite clientes investir ações de empresas globais, fundos de índice, "
				+ "commodities e moedas digitais, os investidores podem diversificar suas carteiras de forma automática e eficaz"
			,rendimentoMensalEsperado, carencia);
	}
	
	public ProdutoRendaFixa(String nome, String descricao, Double rendimentoMensal, Integer carencia) {
		this(nome, descricao, rendimentoMensal, carencia, LocalDate.now());
	}
	
	ProdutoRendaFixa(String nome, String descricao, Double rendimentoMensal, Integer carencia, LocalDate localDate) {
		super(nome, descricao);
		this.rendimentoMensal = rendimentoMensal;
		this.carencia = carencia;
		this.localDate = localDate;
	}

	public Double getRendimentoMensal() {
		return rendimentoMensal;
	}

	public Integer getCarencia() {
		return carencia;
	}

	@Override
	public Boolean isInGracePeriodIn(int days) {
		if(days < 0) throw new DaysCannotByNegativeException("Os dias não podem ser negativos");
		
		var dataComCarencia = localDate.plusDays(carencia);
		
		var dataAtual = LocalDate.now();
		var dataAtualAposOsDias = dataAtual.plusDays(days);
		
		return dataComCarencia.isAfter(dataAtualAposOsDias) || dataComCarencia.equals(dataAtualAposOsDias);
	}



}
