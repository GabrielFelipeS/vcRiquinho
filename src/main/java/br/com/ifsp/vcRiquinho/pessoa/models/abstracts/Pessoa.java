package br.com.ifsp.vcRiquinho.pessoa.models.abstracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.exceptions.ZeroContasException;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa", length = 8, discriminatorType = DiscriminatorType.STRING)
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "documento_titular")
    private String documentoTitular;
    private String nome;
    private String email;

    @OneToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private Set<Conta> contas;

    public Pessoa() {
    }

    public Pessoa(Integer id, String documentoTitular, String nome, String email, Set<Conta> contas) {
        this.id = Objects.requireNonNull(id, "Id não pode ser nulo");
        this.documentoTitular = Objects.requireNonNull(documentoTitular, "Documento titular não pode ser nulo");
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.email = Objects.requireNonNull(email, "Email não pode ser nulo");
        setContas(contas);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentoTitular() {
        return documentoTitular;
    }

    public void setDocumentoTitular(String documentoTitular) {
        this.documentoTitular = documentoTitular;
    }


    public abstract String tipo();

    /**
     * Verifica se a quantidade de elementos é igual a 0 caso verdadeiro lançando
     * uma ZeroContaException caso falso seta a lista de contas no atributo contas
     *
     * @param contas a serem adicionadas
     */
    private void setContas(Set<Conta> contas) {
        if (contas.size() == 0) {
            throw new ZeroContasException("É necessario possui ao menos uma conta na vcRiquinho");
        }

        this.contas = contas;
    }

    /**
     * Adiciona a conta a lista de contas caso ela não estiver cadastrada
     *
     * @param conta a ser adicionada
     * @return true se a conta for adiionada com sucesso, false se a conta já
     *         estiver cadastrada
     */
    public boolean addConta(Conta conta) {
        boolean jaContemOTipoDeConta = contas.stream().anyMatch(p -> p.equals(conta));

        if (jaContemOTipoDeConta) {
            return false;
        }

        contas.add(conta);
        return true;
    }

    /**
     * @return List<Contas> É uma copia da lista, para não permitir acesso direto as contas
     */
    public List<Conta> getContasListCopy() {
        List<Conta> listCopy = new ArrayList<>();
        contas.forEach(c -> listCopy.add(c));

        return listCopy;
    }

}
