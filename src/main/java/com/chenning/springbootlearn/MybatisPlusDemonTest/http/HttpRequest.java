package com.chenning.springbootlearn.MybatisPlusDemonTest.http;


import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;


public class HttpRequest {
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_LOCATION = "Location";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String PARAM_CHARSET = "charset";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    private HttpURLConnection connection = null;
    private final URL url;
    private final String requestMethod;
    private static ConnectionFactory CONNECTION_FACTORY = ConnectionFactory.DEFAULT;
    private static SSLSocketFactory TRUSTED_FACTORY;
    private static HostnameVerifier TRUSTED_VERIFIER;

    public HttpRequest(final CharSequence url, final String method)
            throws HttpRequestException {
        try {
            this.url = new URL(url.toString());
        } catch (MalformedURLException e) {
            throw new HttpRequestException(e);
        }
        this.requestMethod = method;
    }

    public HttpRequest(final URL url, final String method)
            throws HttpRequestException {
        this.url = url;
        this.requestMethod = method;
    }

    private HttpURLConnection createConnection() {
        try {
            final HttpURLConnection connection = CONNECTION_FACTORY.create(url);
            connection.setRequestMethod(requestMethod);
            return connection;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpURLConnection getConnection() {
        if (connection == null)
            connection = createConnection();
        return connection;
    }

    public HttpRequest disconnect() {
        getConnection().disconnect();
        return this;
    }

    public static HttpRequest get(final CharSequence url)
            throws HttpRequestException {
        return new HttpRequest(url, METHOD_GET);
    }

    public static HttpRequest get(final URL url) throws HttpRequestException {
        return new HttpRequest(url, METHOD_GET);
    }

    public static HttpRequest get(final CharSequence baseUrl, final Map<?, ?> params) {
        String url = append(baseUrl, params);
        return get(url);
    }

    public static HttpRequest post(final CharSequence url)
            throws HttpRequestException {
        return new HttpRequest(url, METHOD_POST);
    }

    public static HttpRequest post(final URL url) throws HttpRequestException {
        return new HttpRequest(url, METHOD_POST);
    }

    public static HttpRequest post(final CharSequence baseUrl, final Map<?, ?> params) {
        String url = append(baseUrl, params);
        return post(url);
    }

    public HttpRequest readTimeout(final int timeout) {
        getConnection().setReadTimeout(timeout);
        return this;
    }

    public HttpRequest connectTimeout(final int timeout) {
        getConnection().setConnectTimeout(timeout);
        return this;
    }

    public HttpRequest header(final String name, final String value) {
        getConnection().setRequestProperty(name, value);
        return this;
    }

    public HttpRequest headers(final Map<String, String> headers) {
        if (!headers.isEmpty())
            for (Map.Entry<String, String> header : headers.entrySet())
                header(header);
        return this;
    }

    public HttpRequest header(final Map.Entry<String, String> header) {
        return header(header.getKey(), header.getValue());
    }

    public String header(final String name) throws HttpRequestException {
        return getConnection().getHeaderField(name);
    }

    public Map<String, List<String>> headers() throws HttpRequestException {
        return getConnection().getHeaderFields();
    }

    public HttpRequest userAgent(final String userAgent) {
        return header(HEADER_USER_AGENT, userAgent);
    }

    public HttpRequest useCaches(final boolean useCaches) {
        getConnection().setUseCaches(useCaches);
        return this;
    }

    public HttpRequest accept(final String accept) {
        return header(HEADER_ACCEPT, accept);
    }

    public HttpRequest contentType(final String contentType, final String charset) {
        if (charset != null && charset.length() > 0) {
            final String separator = "; " + PARAM_CHARSET + '=';
            return header(HEADER_CONTENT_TYPE, contentType + separator + charset);
        } else
            return header(HEADER_CONTENT_TYPE, contentType);
    }

    public HttpRequest acceptEncoding(final String acceptEncoding) {
        return header(HEADER_ACCEPT_ENCODING, acceptEncoding);
    }

    public String location() {
        return header(HEADER_LOCATION);
    }

    public HttpRequest followRedirects(final boolean followRedirects) {
        getConnection().setInstanceFollowRedirects(followRedirects);
        return this;
    }

    public HttpRequest trustAllCerts() throws HttpRequestException {
        final HttpURLConnection connection = getConnection();
        if (connection instanceof HttpsURLConnection)
            ((HttpsURLConnection) connection)
                    .setSSLSocketFactory(getTrustedFactory());
        return this;
    }


    public HttpRequest trustAllHosts() {
        final HttpURLConnection connection = getConnection();
        if (connection instanceof HttpsURLConnection)
            ((HttpsURLConnection) connection)
                    .setHostnameVerifier(getTrustedVerifier());
        return this;
    }

    private static HostnameVerifier getTrustedVerifier() {
        if (TRUSTED_VERIFIER == null)
            TRUSTED_VERIFIER = new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

        return TRUSTED_VERIFIER;
    }

    private static SSLSocketFactory getTrustedFactory()
            throws HttpRequestException {
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
                throw new HttpRequestException(ioException);
            }
        }

        return TRUSTED_FACTORY;
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

    public int code() throws HttpRequestException {
        try {
            return getConnection().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String getResponse() {
        StringBuffer sb = new StringBuffer();
        InputStream is = null;
        BufferedReader br=null;
        try {
            if (code() == 200) {
                is = getConnection().getInputStream();
                br = new BufferedReader(new InputStreamReader(is, CHARSET_UTF8));
                int len;
                final char[] buffer = new char[1024];
                while ((len = br.read(buffer)) != -1) {
                    sb.append(buffer,0,len);
                }
            } else {
                throw new HttpRequestException(getConnection().getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(br != null){
                    br.close();
                }
                if(is != null){
                    is.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 要传入的json参数
     * @param content
     * @return
     */
    public HttpRequest send(String content){
        try {
            getConnection().setDoOutput(true);
            OutputStream os = getConnection().getOutputStream();
            OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");
            osw.write(content);
            osw.flush();
            osw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
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

    public interface ConnectionFactory {
        HttpURLConnection create(URL url) throws IOException;

        ConnectionFactory DEFAULT = new ConnectionFactory() {
            public HttpURLConnection create(URL url) throws IOException {
                return (HttpURLConnection) url.openConnection();
            }
        };
    }

    public static class HttpRequestException extends RuntimeException {
        public HttpRequestException(String message) {
            super(message);
        }

        public HttpRequestException(Throwable cause) {
            super(cause);
        }
    }
}
