package br.com.ifsp.vcRiquinho.pessoa.model.abstracts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.exceptions.ZeroContasException;

public abstract class Pessoa {
	private String nome;
	private String email;
	private Set<Conta> contas;
	
	public Pessoa(String nome, String email, Conta conta) {
		this(nome, email, new HashSet<Conta>(Arrays.asList(conta)));
	}

	public Pessoa(String nome, String email, Set<Conta> contas) {
		//this.nome = Objects.requireNonNull(nome,"Nome não pode ser nulo");
		this.nome = nome;
		this.email = email;
		setContas(contas);
	}
	
	/**
	 * Verifica se a quantidade de elementos é igual a 0
	 * caso verdadeiro lançando uma ZeroContaException
	 * caso falso seta a lista de contas no atributo contas
	 * 
	 * @param contas a serem adicionadas
	 */
	private void setContas(Set<Conta> contas) {
		if(contas.size() == 0) {
			throw new ZeroContasException("É necessario possui ao menos uma conta na vcRiquinho");
		}
		
		this.contas = contas;
	}
	
	/**
	 * Adiciona a conta a lista de contas caso ela não estiver cadastrada
	 * 
	 * @param conta a ser adicionada
	 * @return true se a conta for adiionada com sucesso, false se a conta já estiver cadastrada
	 */
	
	public boolean addConta(Conta conta) {
		boolean jaContemOTipoDeConta = contas.stream().anyMatch(p -> p.equals(conta));
		
		if(jaContemOTipoDeConta) {
			return false;
		}
		
		contas.add(conta);
		return true;
	}
	
	/**
	 * Verifica as contas já cadastradas, devolvendo uma lista de contas ainda não cadastradas
	 * 
	 * @param lista de contas a verificar
	 * @return lista de contas não cadastradas
	 */
	public List<Conta> tiposDeContasNaoCadastradas(List<Conta> contasAVerificar) {
		Predicate<Conta> contasNaoCadastradas = conta -> !contas.contains(conta);
		var contasFaltantes = contasAVerificar.stream().filter(contasNaoCadastradas).toList();
		return contasFaltantes;
	}
	
	// TODO Talvez excluir o método
//	/**
//	 * @return List<Contas> É uma copia da lista, para não permitir acesso direto as contas
//	 */
//	public List<Conta> getContasListCopy() {
//		List<Conta> listCopy = new ArrayList<>(); 
//		contas.forEach(c -> listCopy.add(c));
//		
//		return listCopy;
//	}

}
