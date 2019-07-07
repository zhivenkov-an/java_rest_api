package ru.zhivenkov.rest_service.rest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NewsRESTTest {


    @Test
    public void restApiForbided()
            throws ClientProtocolException, IOException {

        // Given

        HttpUriRequest request = new HttpGet( "http://127.0.0.1:8080/newsRest/api/news");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void getAll() throws IOException {

        // Given
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://127.0.0.1:8080/newsRest/api/news" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals( jsonMimeType, mimeType );

    }

    @Test
    public void getById() throws IOException {
        // Given
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://127.0.0.1:8080/newsRest/api/news/1" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals( jsonMimeType, mimeType );

    }

    @Test
    public void deleteById() {
    }

    @Test
    public void addNews() {
    }
}