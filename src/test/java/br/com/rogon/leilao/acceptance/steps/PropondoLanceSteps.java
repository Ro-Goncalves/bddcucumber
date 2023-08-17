package br.com.rogon.leilao.acceptance.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.rogon.leilao.model.Lance;
import br.com.rogon.leilao.model.Leilao;
import br.com.rogon.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PropondoLanceSteps {

    private Lance       lance;
    private Leilao      leilao;
    private List<Lance> lances;

    @Before
    public void setUp(){
        lances = new ArrayList<>();
        leilao = new Leilao("Leilão Teste");
    }

    @Given("Um lance válido")
    public void um_lance_valido() {
        var usuario = new Usuario("Teste", "email_teste@teste.com", "pass");
        var valorLance = new BigDecimal("200.00");
        lance = new Lance(usuario, valorLance);
    }

    @Given("Um lance de {double} reais do usuário {string}")
    public void um_lance_de_reais_do_usuario_usuario(Double valorLance, String nomeUsuario) {
        var usuario = new Usuario(nomeUsuario);
        var lance = new Lance(usuario, new BigDecimal(valorLance));
        lances.add(lance);
    }

    @Given("Um lance inválido de {double} reais")
    public void um_lance_invalido_de_valor_lance_reais(Double valorLance) {      
       lance = new Lance(BigDecimal.valueOf(valorLance));
    }

    @Given("dois lances")
    public void dois_lances(DataTable dataTable) {
        var itensTabela = dataTable.asMaps();

        itensTabela.forEach(E -> {
            var lance = new Lance(
                new Usuario(E.get("nomeUsuario")), 
                new BigDecimal(E.get("valorLance")));

            lances.add(lance);
        });
    }

    @When("Propoe o lance")
    public void propoe_o_lance() {        
        leilao.propoe(lance);
    }

    @When("Propoe vários lances")
    public void propoe_varios_lances() {
        lances.forEach(leilao::propoe);
    }

    @Then("O lance é aceito")
    public void o_lance_e_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(new BigDecimal("200.00"), leilao.getLances().get(0).getValor()); 
    }

    @Then("Os lances são aceitos")
    public void os_lances_sao_aceitos() {
        assertEquals(2, leilao.getLances().size());
        assertEquals(new BigDecimal("10"), leilao.getLances().get(0).getValor());
        assertEquals(new BigDecimal("15"), leilao.getLances().get(1).getValor());
    }

    @Then("O lance não é aceito")
    public void o_lance_nao_e_aceito() {
        assertEquals(0, leilao.getLances().size());
        assertFalse(leilao.propoe(lance));
    }    

    @Then("O segundo lance não é aceito")
    public void o_segundo_lance_não_é_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(new BigDecimal("10"), leilao.getLances().get(0).getValor());       
    }

    
}
