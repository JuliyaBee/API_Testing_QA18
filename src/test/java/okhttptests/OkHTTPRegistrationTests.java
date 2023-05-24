package okhttptests;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class OkHTTPRegistrationTests {
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    int i = new Random().nextInt(1000) + 1000;

    @Test
    public void registrationPositiveTest() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abc"+i+"@def.com")
                .password("$Abcdef12345" + i)
                .build();
        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
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
            System.out.println(errorDTO.getStatus() + " ==== "
                    + errorDTO.getMessage() + " ==== " + errorDTO.getError());
            Assert.assertFalse(response.isSuccessful());

        }

    }

}

