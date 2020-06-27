package com.kadydmi.alpha_challenge5;

import com.kadydmi.alpha_challenge5.runtime.PromoEndpoint;
import com.kadydmi.alpha_challenge5.runtime.ReceiptEndpoint;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Services {

    @Bean
    public PromoEndpoint promoEndpoint() {
        return new PromoEndpoint();
    }

    @Bean
    public CsvReader reader() {
        return new CsvReader();
    }

    @Bean
    public ReceiptEndpoint receiptEndpoint(CsvReader csvReader) {
        return new ReceiptEndpoint(csvReader);
    }

}
