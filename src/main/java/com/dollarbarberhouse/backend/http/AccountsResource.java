package com.dollarbarberhouse.backend.http;

import com.dollarbarberhouse.backend.models.Accounts;
import com.dollarbarberhouse.backend.shared.ApiResource;
import com.dollarbarberhouse.backend.utils.HelpUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountsResource extends BaseResource {
    public List<Accounts> getAllAccounts() {
        List<Accounts> accounts = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(ApiResource.GET_ALL_ACCOUNTS)).GET().build();
            String response = this.client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
            accounts = HelpUtils.getObjectListFromStr(response, Accounts.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
