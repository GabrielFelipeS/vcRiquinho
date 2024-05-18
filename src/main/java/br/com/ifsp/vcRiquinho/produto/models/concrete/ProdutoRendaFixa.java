package br.com.ifsp.vcRiquinho.produto.models.concrete;

import java.time.LocalDate;
import java.util.Objects;

import br.com.ifsp.vcRiquinho.produto.exceptions.DaysCannotByNegativeException;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class ProdutoRendaFixa extends Produto {
	private Double rendimentoMensal;
	private Integer carencia;
	private LocalDate localDate;

	public ProdutoRendaFixa(Integer id, Double rendimentoMensalEsperado, Integer carencia) {
		this(id, "vcInveste", 
				"Permite clientes investir ações de empresas globais, fundos de índice, "
				+ "commodities e moedas digitais, os investidores podem diversificar suas carteiras de forma automática e eficaz"
			,rendimentoMensalEsperado, carencia);
	}
	
	public ProdutoRendaFixa(Integer id, String nome, String descricao, Double rendimentoMensal, Integer carencia) {
		this(id, nome, descricao, rendimentoMensal, carencia, LocalDate.now());
	}
	
	public ProdutoRendaFixa(Integer id, String nome, String descricao, Double rendimentoMensal, Integer carencia, LocalDate localDate) {
		super(id, nome, descricao);
		this.rendimentoMensal = Objects.requireNonNull(rendimentoMensal, "O rendimento mensal esperado do produto de renda fixa não pode ser nulo");
		this.carencia = Objects.requireNonNull(carencia, "A carencia do produto de renda fixa não pode ser nulo");
		this.localDate = Objects.requireNonNull(localDate, "O localDate do produto de renda fixa não pode ser nulo");
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
