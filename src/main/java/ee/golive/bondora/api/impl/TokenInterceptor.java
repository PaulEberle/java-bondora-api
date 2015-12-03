/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ee.golive.bondora.api.impl;

import ee.golive.bondora.api.Bondora;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author Taavi Ilves, Golive O�, http://www.golive.ee/
 */
public class TokenInterceptor implements ClientHttpRequestInterceptor {

    private Bondora api;

    public TokenInterceptor(Bondora api) {
        this.api = api;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest req, byte[] bdy, ClientHttpRequestExecution ex) throws IOException {
        HttpHeaders headers = req.getHeaders();
        if (!req.getURI().getPath().contains("oauth/access_token")) {
            headers.add("Authorization", "Bearer " + api.getAccessToken());
        }
        return ex.execute(req, bdy);
    }

}