package restAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestAddNewContact {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY4NTU2MTAxNywiaWF0IjoxNjg0OTYxMDE3fQ.eWNTcSfLfjaK_BO3NLvqHNmDHBDjM1bRMLNAdE2NCOs";

    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void addNewContactTest() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        ContactDTO contactDTO = ContactDTO.builder()
                .name("Aaaaa" + i)
                .lastName("Bbbbb")
                .email("zxc" + i + "@gmail.com")
                .phone("025897" + i)
                .address("Haifa")
                .description("zxcvbnm")
                .build();
        given()
                .header("Authorization", token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was added"));

    }
}
