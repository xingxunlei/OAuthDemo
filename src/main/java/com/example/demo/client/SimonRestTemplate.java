package com.example.demo.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 扩展原生RestTemplate, 使其get方式支持设置httpHeader
 *
 * @author SimonX
 */
public class SimonRestTemplate extends RestTemplate {

    /**
     * Create a new instance of the {@link RestTemplate} based on the given {@link ClientHttpRequestFactory}.
     * @param requestFactory the HTTP request factory to use
     * @see org.springframework.http.client.SimpleClientHttpRequestFactory
     * @see org.springframework.http.client.HttpComponentsClientHttpRequestFactory
     */
    public SimonRestTemplate(ClientHttpRequestFactory requestFactory) {
        super();
        setRequestFactory(requestFactory);
    }

    public <T> ResponseEntity<T> getForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
        return nonNull(execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables));
    }

    private static <T> T nonNull(@Nullable T result) {
        Assert.state(result != null, "No result");
        return result;
    }

}
