package com.kadydmi.alpha_challenge5;

import com.kadydmi.alpha_challenge5.cvso.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Getter
@Component
@Slf4j
public class CsvReader {
    @Autowired
    ResourceLoader resourceLoader;

    private Map<String, String> groups = new HashMap<>();
    private Map<String, Item> items = new HashMap<>();

    @PostConstruct
    public void postConstruct() throws IOException {
        readGroups("classpath:groups.csv");
        log.info("finished reading groups");
        readItems("classpath:items.csv");
        log.info("finished reading items");
    }

    public void readGroups(String filename) throws IOException {
        Resource resource = resourceLoader.getResource(filename);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                groups.put(values[0], values[1]);
            }
        }
    }

    public void readItems(String filename) throws IOException {
        Resource resource = resourceLoader.getResource(filename);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF8"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Item item = new Item(values[1], values[values.length-2], new BigDecimal(values[values.length-1]).setScale(2, RoundingMode.HALF_EVEN));
                items.put(values[0], item);
            }
        }
    }


}
