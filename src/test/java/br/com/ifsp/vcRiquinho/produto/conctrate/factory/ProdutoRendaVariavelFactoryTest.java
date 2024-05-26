package br.com.ifsp.vcRiquinho.produto.conctrate.factory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.ProdutoRendaVariavelFactory;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaVariavel;

public class ProdutoRendaVariavelFactoryTest {

	@Test
	void createByProdutoRendaVariavel() {
		IFactoryProduto factory = new ProdutoRendaVariavelFactory();

		Produto produto = factory.createBy(new DTOProduto(0, 100, "renda_variavel", "", "", 0.0));
		assertEquals(ProdutoRendaVariavel.class, produto.getClass());
		assertEquals(0, produto.getCarencia());
	}

	@Test
	void createByProdutoRendaVariavelSemNaCriacaoComCarenciaNula() {
		IFactoryProduto factory = new ProdutoRendaVariavelFactory();

		assertDoesNotThrow(() -> factory.createBy(new DTOProduto(0, null, "renda_variavel", "", "", 0.0)));
	}

	@Test
	void createByProdutoRendaVariavelErroNaCriacao() {
		IFactoryProduto factory = new ProdutoRendaVariavelFactory();

		assertThrows(RuntimeException.class,
				() -> factory.createBy(new DTOProduto(0, 0, "renda_variavel", "", "", null)));
	}

	@Test
	void convertProdutoRendaVariavel() {
		Produto produto = new ProdutoRendaVariavel(0, "", "", 0.0);

		IFactoryProduto factory = new ProdutoRendaVariavelFactory();
		DTOProduto dto = factory.convert(produto);
		assertEquals(produto.getId(), dto.id());
		assertEquals(produto.getCarencia(), dto.carencia());
	}

}
