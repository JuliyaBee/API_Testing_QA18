package okhttptests;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHTTPLoginTests {
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");//константа для сопровождения запросов на сервер(получаем из Inspector (страницы в приложении)  Contact-Type

    //eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidHVsaXBAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODQ4NTc3NDksImlhdCI6MTY4NDI1Nzc0OX0.amH2Yd35qU1JuOqn_nR4FZG6pMjlImRpffGiULfiOcs
    @Test
    public void loginTest() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abc@def.com")
                .password("$Abcdef12345")
                .build();
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseJson = response.body().string();
            AuthResponseDTO responseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);

            System.out.println(responseDTO.getToken());
            System.out.println(response.code());
            Assert.assertTrue(response.isSuccessful());

        } else {
            System.out.println("Response code is " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() + "====="

                    + errorDTO.getMessage() + "====" + errorDTO.getError());
            Assert.assertFalse(response.isSuccessful());
        }
    }
}
