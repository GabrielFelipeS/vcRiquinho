package br.com.ifsp.vcRiquinho.produto.factory.concrate;

import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProdutoCreator;

public class FactoryProdutoCreator implements IFactoryProdutoCreator {
	private static Map<String, IFactoryProduto> map;

	static {
		Map<String, IFactoryProduto> map = new HashMap<>();
		map.put("renda_variavel", new ProdutoRendaVariavelactory());
		map.put("renda_fixa", new ProdutoRendaFixaFactory());
	}

	@Override
	public IFactoryProduto createBy(String str) {
		return map.get(str);
	}

	@Override
	public String convert(IFactoryProduto factory) {
		return factory.toString();
	}

}
