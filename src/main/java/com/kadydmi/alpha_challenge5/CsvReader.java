package com.kadydmi.alpha_challenge5;

import com.kadydmi.alpha_challenge5.cvso.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
    private Map<String, String> groups = new HashMap<>();
    private Map<String, Item> items = new HashMap<>();

    @PostConstruct
    public void postConstruct() throws IOException {
        readGroups("src/main/resources/groups.csv");
        log.info("finish reading groups");
        readItems("src/main/resources/items.csv");
        log.info("finish reading items");
    }

    public void readGroups(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                groups.put(values[0], values[1]);
            }
        }
    }

    public void readItems(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8"))) {
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
