package com.dollarbarberhouse.backend.http;

import com.dollarbarberhouse.backend.shared.ApiResource;
import com.dollarbarberhouse.backend.utils.HelpUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

@Component
public class ToolResource extends BaseResource {
    public String encodeJson(Map<String, String> requestBody) {
        String response = "";
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(ApiResource.ENCODE_JSON)).POST(BodyPublishers.ofString(HelpUtils.stringifyJson(requestBody))).build();
            response = this.client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return response;
    }
}
