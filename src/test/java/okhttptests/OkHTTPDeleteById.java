    package okhttptests;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactDTOResponse;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

    public class OkHTTPDeleteById {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY4NTU2MDQxNiwiaWF0IjoxNjg0OTYwNDE2fQ._okxEEXBwsT-LKJ_29xk_uRY4JK9LTNCeMNj97gBIGk";
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String id ;




@BeforeMethod
public void preCondition() throws IOException {
    int i = (int) (System.currentTimeMillis() / 1000) % 3600;
    ContactDTO contactDTO = ContactDTO.builder()
            .name("Aaaaa" + i)
            .lastName("Bbbbb")
            .email("zxc" + i + "@gmail.com")
            .phone("025897" + i)
            .address("Haifa")
            .description("zxcvbnm")
            .build();
    RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);
    Request request = new Request.Builder()
            .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
            .addHeader("Authorization", token)
            .post(requestBody)
            .build();
    Response response = client.newCall(request).execute();

    ContactDTOResponse contactDTOResponse = gson.fromJson(response.body().string(), ContactDTOResponse.class);
    String message = contactDTOResponse.getMessage();
    id= message.substring(message.lastIndexOf(' ')+1);
    System.out.println("ID = " + id);
}
        @Test
        public void deleteById() throws IOException {


            Request request = new Request.Builder()
                    .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/" + id)
                    .addHeader("Authorization", token)
                    .delete()
                    .build();
            Response response = client.newCall(request).execute();
            Assert.assertTrue(response.isSuccessful());
            ContactDTOResponse contactDTOResponse = gson.fromJson(response.body().string(), ContactDTOResponse.class);
            String message =contactDTOResponse.getMessage();
            System.out.println(message);
            //  Assert.assertTrue(message.contains("Contact was deleted"));
        }

    }
