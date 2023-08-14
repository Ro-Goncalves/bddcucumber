package br.com.rogon.leilao.acceptance.steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import br.com.rogon.leilao.model.Lance;
import br.com.rogon.leilao.model.Leilao;
import br.com.rogon.leilao.model.Usuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PropondoLanceSteps {

    private Lance  lance;
    private Leilao leilao;
    private Lance  lance10;
    private Lance  lance15;

    @Given("Um lance válido")
    public void um_lance_válido() {
        var usuario = new Usuario("Teste", "email_teste@teste.com", "pass");
        var valorLance = new BigDecimal("200.00");
        lance = new Lance(usuario, valorLance);

        leilao = new Leilao("Leilão Teste");
    }

    @Given("Vários lances válidos")
    public void vários_lances_válidos() {
        var usuario1 = new Usuario("Teste", "email_teste@teste.com", "pass");        
        var usuario2 = new Usuario("Teste2", "email2_teste@teste.com", "pass");

        lance10 = new Lance(usuario1, new BigDecimal("10.00"));
        lance15 = new Lance(usuario2, new BigDecimal("15.00"));

        leilao = new Leilao("Leilão Teste");
    }

    @When("Propoe o lance")
    public void propoe_o_lance() {        
        leilao.propoe(lance);
    }

    @When("Propoe vários lances")
    public void propoe_vários_lances() {
        leilao.propoe(lance10);
        leilao.propoe(lance15);

    }

    @Then("O lance é aceito")
    public void o_lance_é_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(new BigDecimal("200.00"), leilao.getLances().get(0).getValor());       
       
    }

    @Then("Os lances são aceitos")
    public void os_lances_são_aceitos() {
        assertEquals(2, leilao.getLances().size());
        assertEquals(new BigDecimal("10.00"), leilao.getLances().get(0).getValor());
        assertEquals(new BigDecimal("15.00"), leilao.getLances().get(1).getValor());

    }
    
}
