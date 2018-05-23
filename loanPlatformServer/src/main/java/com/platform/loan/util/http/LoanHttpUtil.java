/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author caogu.wyp
 * @version $Id: LoanHttpUtil.java, v 0.1 2018-05-20 下午1:08 caogu.wyp Exp $$
 */
public class LoanHttpUtil {

    private static final CloseableHttpClient client = HttpClientBuilder.create().build();

    public static String sendGet(String url, Map<String, String> headers) throws IOException {

        HttpGet request = new HttpGet(url);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
            .getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

    public static String sendPost(String url, String jsonData) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Connection", "close");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(jsonData, "UTF-8"));
        HttpResponse httpResponse = client.execute(httpPost);
        return EntityUtils.toString(httpResponse.getEntity());
    }

}
