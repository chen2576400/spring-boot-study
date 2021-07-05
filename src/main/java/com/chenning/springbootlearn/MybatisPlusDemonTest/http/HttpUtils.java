package com.chenning.springbootlearn.MybatisPlusDemonTest.http;

import org.apache.commons.lang.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpUtils {
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_LOCATION = "Location";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    private static SSLSocketFactory TRUSTED_FACTORY;

    public static SSLSocketFactory getTrustedFactory()
            throws IOException {
        if (TRUSTED_FACTORY == null) {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    // Intentionally left blank
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    // Intentionally left blank
                }
            }};
            try {
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, trustAllCerts, new SecureRandom());
                TRUSTED_FACTORY = context.getSocketFactory();
            } catch (GeneralSecurityException e) {
                IOException ioException = new IOException(
                        "Security exception configuring SSL context");
                ioException.initCause(e);
                throw ioException;
            }
        }

        return TRUSTED_FACTORY;
    }

    public static HttpURLConnection createConnection(URL url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        return connection;
    }

    public static String doGet(CharSequence baseUrl) {
        HttpURLConnection connection = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            connection = createConnection(new URL(baseUrl.toString()), METHOD_GET);
            if (connection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) connection).setSSLSocketFactory(getTrustedFactory());
            }
            connection.setConnectTimeout(30 * 1000);
            connection.setReadTimeout(30 * 1000);
            connection.setRequestProperty("accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream));
                sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                throw new Exception("HTTP 请求发生异常，错误代码：" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String doGet(CharSequence baseUrl, Map<?, ?> params) {
        String url = append(baseUrl, params);
        return doGet(url);
    }

    public static String doPost(CharSequence baseUrl) {
        return doPost(baseUrl, null, null);
    }

    public static String doPost(CharSequence baseUrl, Map<?, ?> params) {
        return doPost(baseUrl, params, null);
    }

    /**
     *
     * @param baseUr  URl 地址
     * @param params  地址挂参
     * @param body    json参数
     * @return
     */
    public static String doPost(CharSequence baseUr, Map<?, ?> params, String body) {
        HttpURLConnection connection = null;
        String url = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        String result = null;
        try {
            if (params != null) {
                url = append(baseUr, params);
            } else {
                url = baseUr.toString();
            }
            connection = createConnection(new URL(url), METHOD_POST);
            if (connection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) connection).setSSLSocketFactory(getTrustedFactory());
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            if (StringUtils.isNotBlank(body)) {
                out.write(body);
            }
            out.flush();
            out.close();
            int responseCode = connection.getResponseCode();
            if (200 == responseCode) {
                InputStream is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                sb = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
                result = sb.toString();

            } else {
                throw new Exception("HTTP发生异常，异常代码：" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != connection) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String getHeader(CharSequence baseUrl, Map<?, ?> params, String fieldName) {
        String message = null;
        HttpURLConnection connection = null;
        try {

            connection = createConnection(new URL(append(baseUrl,params)),METHOD_GET);
            connection.setRequestProperty("accept", "application/json,text/plain,*/*");
            connection.setInstanceFollowRedirects(false);
            connection.connect();
            Map<String, List<String>> maps = connection.getHeaderFields();
            List<String> fieldNames = maps.get(fieldName);
            if(fieldNames != null && fieldNames.size()>0){
                message=fieldNames.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(connection != null){
                    connection.disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return message;
    }

    public static String append(final CharSequence url, final Map<?, ?> params) {
        final String baseUrl = url.toString();
        if (params == null || params.isEmpty())
            return baseUrl;

        final StringBuilder result = new StringBuilder(baseUrl);

        addPathSeparator(baseUrl, result);
        addParamPrefix(baseUrl, result);

        Map.Entry<?, ?> entry;
        Iterator<?> iterator = params.entrySet().iterator();
        entry = (Map.Entry<?, ?>) iterator.next();
        addParam(entry.getKey().toString(), entry.getValue(), result);

        while (iterator.hasNext()) {
            result.append('&');
            entry = (Map.Entry<?, ?>) iterator.next();
            addParam(entry.getKey().toString(), entry.getValue(), result);
        }

        return result.toString();
    }

    private static StringBuilder addPathSeparator(final String baseUrl,
                                           final StringBuilder result) {
        // Add trailing slash if the base URL doesn't have any path segments.
        //
        // The following test is checking for the last slash not being part of
        // the protocol to host separator: '://'.
        if (baseUrl.indexOf(':') + 2 == baseUrl.lastIndexOf('/'))
            result.append('/');
        return result;
    }

    private static StringBuilder addParamPrefix(final String baseUrl,
                                                final StringBuilder result) {
        // Add '?' if missing and add '&' if params already exist in base url
        final int queryStart = baseUrl.indexOf('?');
        final int lastChar = result.length() - 1;
        if (queryStart == -1)
            result.append('?');
        else if (queryStart < lastChar && baseUrl.charAt(lastChar) != '&')
            result.append('&');
        return result;
    }

    private static StringBuilder addParam(final Object key, Object value,
                                   final StringBuilder result) {
        if (value != null && value.getClass().isArray())
            value = arrayToList(value);

        if (value instanceof Iterable<?>) {
            Iterator<?> iterator = ((Iterable<?>) value).iterator();
            while (iterator.hasNext()) {
                result.append(key);
                result.append("[]=");
                Object element = iterator.next();
                if (element != null)
                    result.append(element);
                if (iterator.hasNext())
                    result.append("&");
            }
        } else {
            result.append(key);
            result.append("=");
            if (value != null)
                result.append(value);
        }

        return result;
    }

    private static List<Object> arrayToList(final Object array) {
        if (array instanceof Object[])
            return Arrays.asList((Object[]) array);

        List<Object> result = new ArrayList<Object>();
        // Arrays of the primitive types can't be cast to array of Object, so this:
        if (array instanceof int[])
            for (int value : (int[]) array) result.add(value);
        else if (array instanceof boolean[])
            for (boolean value : (boolean[]) array) result.add(value);
        else if (array instanceof long[])
            for (long value : (long[]) array) result.add(value);
        else if (array instanceof float[])
            for (float value : (float[]) array) result.add(value);
        else if (array instanceof double[])
            for (double value : (double[]) array) result.add(value);
        else if (array instanceof short[])
            for (short value : (short[]) array) result.add(value);
        else if (array instanceof byte[])
            for (byte value : (byte[]) array) result.add(value);
        else if (array instanceof char[])
            for (char value : (char[]) array) result.add(value);
        return result;
    }
}
