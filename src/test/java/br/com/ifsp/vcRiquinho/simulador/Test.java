package br.com.ifsp.vcRiquinho.simulador;

import java.math.BigDecimal;

import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;

public class Test {
	private static final BigDecimal CDI_ANUAL = new BigDecimal("12"); // CDI anual de 12%
    private static final int DIAS_UTEIS = 252;
    
	public static void main(String[] args) {
		int dias = 60; // Período de 30 dias
System.out.println(2000.0 + 2000.0 * ( 30 * (0.12/30)));


		ContaCDI conta = new ContaCDI(1, "", 2000.0, 0.12);
		System.out.println(conta.rendimentoPorDias(dias));
		System.out.println("-------------");

		System.out.println("-------------");
		double cdiDiatio = Math.pow(2000.0 *(0.12/30.0), dias/365);
		System.out.println(cdiDiatio);
		System.out.println(2000.0 * cdiDiatio);

//		2000.0
//		1.004
//		0.23809523809523808
//		1.0006564380356495
//		2001.901866210213

	}
}
