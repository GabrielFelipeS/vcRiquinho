package br.com.ifsp.vcRiquinho.produto.models.concrete;

import java.util.Objects;

import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("renda_variavel")
public class ProdutoRendaVariavel extends Produto {
	private Double rendimentoMensalEsperado;


	public ProdutoRendaVariavel() {

	}

	public ProdutoRendaVariavel(Integer id, Double rendimentoMensalEsperado) {
		this(id, "vcInveste", 
				"Permite clientes investir ações de empresas globais, fundos de índice, "
				+ "commodities e moedas digitais, os investidores podem diversificar suas carteiras de forma automática e eficaz"
			,rendimentoMensalEsperado);
	}
	
	public ProdutoRendaVariavel(Integer id, String nome, String descricao, Double rendimentoMensalEsperado) {
		super(id, nome, descricao);
		this.rendimentoMensalEsperado = Objects.requireNonNull(rendimentoMensalEsperado, "O rendimento mensal esperado do produto de renda variavel não pode ser nulo");
	}

	@Override
	public Double getRendimentoMensal() {
		return rendimentoMensalEsperado;
	}

	@Override
	public Integer getCarencia() {
		return 0;
	}

	@Override
	public Boolean isInGracePeriodIn(int days) {
		return false;
	}

	@Override
	public String tipoProduto() {
		return "renda_variavel";
	}

}
