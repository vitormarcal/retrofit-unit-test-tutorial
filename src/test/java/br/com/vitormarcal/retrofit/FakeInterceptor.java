package br.com.vitormarcal.retrofit;


import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class FakeInterceptor implements Interceptor {
    private static final String productDetail = "{\"id\":1,\"name\":\"Book\"}";


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        String responseString = "{}";
        final List<String> paths = chain.request().url().pathSegments();

        Response.Builder builder = new Response.Builder();
        builder.code(404);
        if (paths != null && !paths.isEmpty()) {
            String lastPath = paths.get(paths.size() - 1);
            if (lastPath.equals("1")) {
                builder.code(200);
                responseString = productDetail;
            }
        }

        response = builder
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();

        return response;
    }
}
