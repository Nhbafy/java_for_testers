package ru.stqa.mantis.manager;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class JamesApiHelper extends HelperBase {
    public static final MediaType JSON = MediaType.get("application/jason");

    OkHttpClient client;

    public JamesApiHelper(ApplicationManager manager) {

        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();

    }

    public void addUser(String email, String password) {
        RequestBody body = RequestBody.create(String.format("{\"password\":\"%s\"}",password),JSON);
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s",manager.property("jamesApiBaseUrl"),email))
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public Response getUsers(){;
//        Request request = new Request.Builder()
//                .url(String.format("%s/users",manager.property("jamesApiBaseUrl")))
//                .get()
//                .build();
//   //     Response response ;
//        try (Response response = client.newCall(request).execute() ) {
//            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
