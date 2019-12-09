package br.jus.trt3.boleto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BoletoResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/reajustar_boleto")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}