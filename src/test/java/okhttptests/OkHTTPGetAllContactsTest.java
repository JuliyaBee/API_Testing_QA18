package okhttptests;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHTTPGetAllContactsTest {
   String token =  "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidHVsaXBAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODQ4NTc3NDksImlhdCI6MTY4NDI1Nzc0OX0.amH2Yd35qU1JuOqn_nR4FZG6pMjlImRpffGiULfiOcs";

Gson gson= new Gson();
OkHttpClient client = new OkHttpClient();
@Test
public void getAllContacts() throws IOException {
    Request request = new Request.Builder()
            .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
            .addHeader("Authorization", token)
            .build();
    Response response = client.newCall(request).execute();
    Assert.assertTrue(response.isSuccessful());

GetAllContactsDTO contacts = gson.fromJson(response.body().string(), GetAllContactsDTO.class);
for(ContactDTO contact:contacts.getContacts()){
    System.out.println(contact.getId());
    System.out.println(contact.getEmail());
    System.out.println("=======");
}
}
}
