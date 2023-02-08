package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    void assercaoAgrupada() {
        Pessoa pessoa = new Pessoa("Cristiano", "Filho");

        assertAll("Asserções de pessoa",
                () -> assertEquals("Cristiano", pessoa.getNome()),
                () -> assertEquals("Filho", pessoa.getSobrenome()));
    }

}