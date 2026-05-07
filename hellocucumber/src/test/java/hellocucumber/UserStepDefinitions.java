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

public class UserStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    //  Stocke la réponse HTTP pour la vérifier dans le @Then
    private ResultActions result;

    @Given("the user management system is ready")
    public void the_user_management_system_is_ready() {
        // Le système est déjà prêt grâce à Spring Boot
        // Pas besoin de faire quoi que ce soit ici
    }

    @When("I create a guest with username {string} and email {string}")
    public void i_create_a_guest_with_username_and_email(
            String username, String email) throws Exception {

        // ✅ On construit le JSON à envoyer
        String json = String.format(
                "{\"username\": \"%s\", \"email\": \"%s\", \"role\": \"GUEST\"}",
                username, email
        );

        // ✅ MockMVC envoie POST /users avec ce JSON
        result = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );
    }

    @Then("the guest {string} should be successfully recorded")
    public void the_guest_should_be_successfully_recorded(
            String username) throws Exception {

        // ✅ On vérifie que la réponse est 201 Created
        // et que le username est dans la réponse JSON
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(username));
    }
}