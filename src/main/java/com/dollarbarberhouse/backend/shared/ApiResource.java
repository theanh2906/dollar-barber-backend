package com.dollarbarberhouse.backend.shared;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class ApiResource {
    private static String HOST_NAME = "http://127.0.0.1";
    /**
     * Accounts APIs
     */
    public static final String GET_ALL_ACCOUNTS = HOST_NAME + "/api/accounts";
    public static final String ADD_ACCOUNT = HOST_NAME + "/api/accounts";
    /**
     * Tools APIs
     */
    public static final String ENCODE_JSON = HOST_NAME + "/api/tools/encodeJson";
    public static final String STRINGIFY = HOST_NAME + "/api/tools/stringify";
    public static final String FROM_EXCEL_TO_JSON = HOST_NAME + "/api/tools/fromExcelToJSON";
}
