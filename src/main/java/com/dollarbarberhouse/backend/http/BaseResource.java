package com.dollarbarberhouse.backend.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;

public class BaseResource {
    protected static final Logger LOG = LoggerFactory.getLogger(AccountsResource.class);
    protected final HttpClient client = HttpClient.newHttpClient();
}
