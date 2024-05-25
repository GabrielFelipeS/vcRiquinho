package br.com.ifsp.vcRiquinho.pessoa.dto;

import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;

public record DTOPessoaConta(DTOPessoa dtoPessoa, Set<DTOConta> dtoContas) {

}
