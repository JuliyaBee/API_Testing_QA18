package restAssured;
import com.jayway.restassured.RestAssured;

import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestLoginTests {
    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY4NTU2MTAxNywiaWF0IjoxNjg0OTYxMDE3fQ.eWNTcSfLfjaK_BO3NLvqHNmDHBDjM1bRMLNAdE2NCOs";
    @Test
    public void loginPositiveTest() {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abc@def.com")
                .password("$Abcdef12345")
                .build();
        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType("application/json")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());
    }

    @Test
    public void loginNegativeEmailTest() {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abcdef.com")
                .password("$Abcdef12345")
                .build();
        ErrorDTO responseDTO = given()
                .body(requestDTO)
                .contentType("application/json")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);
        Object message = responseDTO.getMessage();
        System.out.println(message);
        Assert.assertEquals(message, "Login or Password incorrect");
    }

    @Test
    public void loginNegativeWrongPasswordTest() {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abc@def.com")
                .password("Abcdef12345")
                .build();
        given()
                .body(requestDTO)
                .contentType("application/json")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message", containsString("Login or Password incorrect"));

            }
}