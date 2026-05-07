package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HotelStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    private ResultActions result;

    @Given("I am logged in as an administrator")
    public void i_am_logged_in_as_an_administrator() {
        // Pour l'instant pas d'authentification — on simule juste
        // On reviendra sur la sécurité dans un task futur
    }

    @When("I create a hotel named {string} in {string}")
    public void i_create_a_hotel_named_in(
            String name, String location) throws Exception {

        String json = String.format(
                "{\"name\": \"%s\", \"location\": \"%s\"}",
                name, location
        );

        result = mockMvc.perform(
                post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );
    }

    @Then("the hotel {string} should be available in the system")
    public void the_hotel_should_be_available_in_the_system(
            String name) throws Exception {

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(name));
    }
}