package com.atlassian.acc.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class Util {
    public static boolean isStringSet(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static String getExternalApiResponse(String api) throws URISyntaxException, IOException {
        return getExternalApiResponse(api, new HashMap<>());
    }

    public static String getExternalApiResponse(String api, Map<String, String> params) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(api);
        params.forEach(builder::setParameter);

        HttpGet getRequest = new HttpGet(builder.build());
        HttpResponse response = HttpClientBuilder.create().build().execute(getRequest);
        return new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
    }
}
