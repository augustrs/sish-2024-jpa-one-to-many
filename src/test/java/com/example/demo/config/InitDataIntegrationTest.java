package com.example.demo.config;

import com.example.demo.model.Kommune;
import com.example.demo.model.Region;
import com.example.demo.repository.KommuneRepository;
import com.example.demo.repository.RegionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.net.http.HttpClient;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InitDataIntegrationTest {

    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private KommuneRepository kommuneRepository;
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private InitData initData;

    @Test
    void testRunSavesRegionAndKommuner() throws Exception {
        List<Region> regions = regionRepository.findAll();
        Assertions.assertEquals(5, regions.size());

        List<Kommune> kommuner = kommuneRepository.findAll();
        Assertions.assertEquals(99, kommuner.size());

    }


}
