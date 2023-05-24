package okhttptests;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactDTOResponse;
import okhttp3.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHTTPAddNewContactTest {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY4NTU2MDQxNiwiaWF0IjoxNjg0OTYwNDE2fQ._okxEEXBwsT-LKJ_29xk_uRY4JK9LTNCeMNj97gBIGk";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void addNewContactTest() throws IOException {
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
        Assert.assertTrue(response.isSuccessful());
        ContactDTOResponse contactDTOResponse = gson.fromJson(response.body().string(), ContactDTOResponse.class);
        String message = contactDTOResponse.getMessage();
        Assert.assertTrue(message.contains("Contact was added"));
        String id= message.substring(message.lastIndexOf(' ')+1);
        System.out.println(id);


    }
}