package restAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RestUpdateContact {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY4NTU2MTAxNywiaWF0IjoxNjg0OTYxMDE3fQ.eWNTcSfLfjaK_BO3NLvqHNmDHBDjM1bRMLNAdE2NCOs";
    String id;
    ContactDTO contactDTO;
    int i = (int)(System.currentTimeMillis() / 1000)%3600;
    @BeforeMethod
    public  void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
        contactDTO= ContactDTO.builder()
                .name("John")
                .lastName("Silver")
                .email("dycjh"+i+"@example.com")
                .phone("1234567"+i)
                .address("Haifa")
                .description("LastOfUs")
                .build();
        String message = given()
                .header("Authorization", token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract()
                .path("message");
        id = message.substring(message.lastIndexOf(' ') + 1);
        System.out.println(message);

    }
@Test
    public void updateContactTest(){
        contactDTO.setName("John");
        contactDTO.setId(id);
        given()
                .body(contactDTO)
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .put("contacts")
                .then()
                .assertThat().statusCode(200);


}
}
