package br.com.ifsp.vcRiquinho;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;

public class TesteHipotese {
	public static void main(String[] args) {
		Conta conta = new ContaCDI(0, "", 0.0, 0.0);
		ContaCDI c = get(conta);
		
		Conta conta2 = new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0, 0.0, 0));
		ContaInvestimentoAutomatico c2 = get(conta2);
		

		Conta conta3 = new ContaCorrente(0, "", 0.0);
		//ContaCorrente c3 = get(conta2);
		
		//c3.renderPorDias(0);
	
		
		DTOConta dto = new DTOConta(c);
		System.out.println(	dto.toString());
	}
	
    private static void teste(ContaCorrente c) {
		System.out.println(c.getClass());
	}
    
    private static void teste(ContaInvestimentoAutomatico c) {
    	System.out.println(c.getClass());
 	}
    
    private static void teste(ContaCDI c) {
    	System.out.println(c.getClass());
 	}

	public static <T extends Conta> T get(Conta conta) {
    	var result = (T) conta;
    //	System.out.println(result.getClass());
        return  result;
    }
}
