package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static com.algaworks.junit.utilidade.SaudacaoUtil.saudar;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SaudacaoUtilTest {

    //Padrão Tripe A
    @Test
    public void Dado_um_horario_matinal_Quando_saudar_Entao_deve_retornar_bom_dia() {
        int horaValida = 9;
        String saudacao = saudar(horaValida);
        assertEquals("Bom dia", saudacao, "Saudação incorreta!");
    }

    @Test
    public void Dado_um_horario_vespertino_Quando_saudar_Entao_deve_retornar_boa_tarde() {
        int horaValida = 15;
        String saudacao = saudar(horaValida);
        assertEquals("Boa tarde", saudacao, "Saudação incorreta!");
    }


    @Test
    public void Dado_um_horario_noturno_Quando_saudar_Entao_deve_retornar_boa_noite() {
        int horaValida = 4;
        String saudacao = saudar(horaValida);
        assertEquals("Boa noite", saudacao, "Saudação incorreta!");
    }

    @Test
    public void Dado_uma_hora_invalida_Quando_saudar_Entao_deve_lancar_exception() {
        //Arrange
        int horaInvalida = -10;

        //Act
        Executable chamadaInvalidaDeMetodo = () -> saudar(horaInvalida);

        //Assert
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, chamadaInvalidaDeMetodo);
        assertEquals("Hora inválida", e.getMessage());
    }

    //Teste não necessário, pois o primeiro teste já seria suficiente
    @Test
    public void Dado_uma_hora_valida_Quando_saudar_Entao_deve_lancar_exception() {
        int horaValida = 0;
        Executable chamadaValidaDeMetodo = () -> saudar(horaValida);
        assertDoesNotThrow(() -> chamadaValidaDeMetodo);
    }

}