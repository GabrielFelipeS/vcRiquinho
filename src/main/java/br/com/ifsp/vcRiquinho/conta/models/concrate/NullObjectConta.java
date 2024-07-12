package br.com.ifsp.vcRiquinho.conta.models.concrate;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

import java.util.Map;

public class NullObjectConta extends Conta {
    @Override
    public String tipoConta() {
        return "null-object";
    }

    @Override
    public Map<String, String> getDetalhes() {
        return Map.of();
    }

    @Override
    public Double rendimentoPorDias(int dias) {
        return 0.0;
    }
}
