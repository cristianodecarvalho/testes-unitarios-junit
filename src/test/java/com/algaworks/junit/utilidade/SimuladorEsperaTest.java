package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorEsperaTest {

    @Test
//    @Disabled("Não é mais aplicável") // Desabilita o teste
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "DEV")
    public void deveEsperarENaoDarTimeout() {
//        Assumptions.assumeTrue("PROD".equals("ENV"),() -> "Abortando teste: Não deve ser executado em PROD"); // Uso de variável ambiente
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));
    }
}