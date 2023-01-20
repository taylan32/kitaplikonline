package com.kitaplik.libraryservice.client;

import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    private final ObjectFactory<HttpMessageConverters> messageConvertersObjectFactory;

    public FeignClientConfiguration(ObjectFactory<HttpMessageConverters> messageConvertersObjectFactory) {
        this.messageConvertersObjectFactory = messageConvertersObjectFactory;
    }
/*
    @Bean
    public ErrorDecoder getErrorDecoder() {
        return new RetrieveErrorMessageDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConvertersObjectFactory)));
    }

*/

}
