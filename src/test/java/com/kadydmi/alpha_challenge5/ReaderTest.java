package com.kadydmi.alpha_challenge5;

import org.junit.jupiter.api.Test;
import java.io.IOException;

public class ReaderTest {
    CsvReader reader = new CsvReader();

    @Test
    public void readerTest() throws IOException {
        reader.readGroups("src/test/resources/groupTest.csv");
        reader.readItems("src/test/resources/itemsTest.csv");
    }
}
