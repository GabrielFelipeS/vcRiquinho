package br.com.ifsp.vcRiquinho.pessoa.factory;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.base.exception.TypeNotFoundException;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaJuridica;

import java.util.Set;

public class FactoryPessoa {
    public static Pessoa createBy(DTOPessoa dto, Set<Conta> contas) {
        return switch (dto.tipo_pessoa()) {
            case "fisica" ->  new PessoaFisica(dto.id(), dto.nome(), dto.email(), contas, dto.documento_titular());
            case "juridica" -> new PessoaJuridica(dto.id(), dto.nome(), dto.email(), contas, dto.documento_titular());
            default -> throw new TypeNotFoundException("O tipo pessoa do DTO não pode ser encontrado");
        };
    }



}
