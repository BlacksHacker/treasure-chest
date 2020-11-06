package com.shelton.treasure.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @ClassName HttpUtil
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class HttpUtil {
    protected static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            return;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            return;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    } };

    public static HttpURLConnection getHttpConnection(String url){
        HttpURLConnection connection = null;
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            URL getUrl = new URL(url);
            connection = (HttpURLConnection) getUrl.openConnection();
            String authorization = "";
            String currentTime = Long.toString(System.currentTimeMillis());
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("x-access-date", currentTime);
            connection.setRequestProperty("Authorization", authorization);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Accept", "text/plain, application/json, */*");
            connection.connect();
            return connection;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }
    public static class NullHostNameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String paramString, SSLSession paramSSLSession) {
            return true;
        }
    }

}
