package com.example.demo.config;


import com.example.demo.model.Kommune;
import com.example.demo.model.Region;
import com.example.demo.repository.KommuneRepository;
import com.example.demo.repository.RegionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


public class InitDataTest {
    @Mock
    private KommuneRepository kommuneRepository;

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private HttpClient httpClient;


    public InitDataTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchRegions() throws Exception {
        Path regionsPath = Path.of("data/regioner.json");
        String regionsData = Files.readString(regionsPath);
        System.out.println(regionsData);

        HttpResponse<String> mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(regionsData);
        when(mockResponse.statusCode()).thenReturn(200);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);


        InitData initData = new InitData(regionRepository, kommuneRepository, httpClient);

        List<Region> regions = initData.fetchRegions();

        Assertions.assertEquals(5, regions.size());
    }

    @Test
    public void testFetchKommuner() throws Exception {

        // Fetch region
        Path regionsPath = Path.of("data/regioner.json");
        String regionsData = Files.readString(regionsPath);
        System.out.println("Regions Data: " + regionsData);

        HttpResponse<String> mockRegionResponse = mock(HttpResponse.class);
        when(mockRegionResponse.body()).thenReturn(regionsData);
        when(mockRegionResponse.statusCode()).thenReturn(200);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockRegionResponse);

        InitData initData = new InitData(regionRepository, kommuneRepository, httpClient);

        List<Region> regions = initData.fetchRegions();


        // Fetch og test kommune
        Path kommunerPath = Path.of("data/kommuner.json");
        String kommunerData = Files.readString(kommunerPath);
        System.out.println("Kommuner Data: " + kommunerData);

        HttpResponse<String> mockKommunerResponse = mock(HttpResponse.class);
        when(mockKommunerResponse.body()).thenReturn(kommunerData);
        when(mockKommunerResponse.statusCode()).thenReturn(200);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockKommunerResponse);

        List<Kommune> kommuner = initData.fetchKommuner(regions);

        Assertions.assertEquals(99, kommuner.size());
    }


}
