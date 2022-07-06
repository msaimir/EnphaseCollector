package com.hz.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.hz.models.envoy.xml.EnvoyInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.List;

@TestConfiguration
@Profile("testing")
@Log4j2
public class TestEnphaseSystemInfoConfig {

	@Bean
	public String mockEnvoyInfo() {
		return "<?xml version='1.0' encoding='UTF-8'?><envoy_info><device><sn>Unknown</sn><software>Unknown</software></device></envoy_info>";
	}

	@Bean
	public EnvoyInfo envoyInfo(String mockEnvoyInfo) {
		log.info("Creating Mocked EnvoyInfo");
		try {
			ObjectMapper xmlMapper = new XmlMapper();
			xmlMapper.registerModule(new JaxbAnnotationModule());
			xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);   // We want to fail on unknown properties, so we can test new releases

			return xmlMapper.readValue(mockEnvoyInfo, EnvoyInfo.class);
		} catch (IOException e) {
			return new EnvoyInfo(e.getMessage(),e.getMessage());
		}
	}

	@Bean
	@Primary
	public HttpMessageConverters messageConverters() {
		return new HttpMessageConverters(false, List.of(new MappingJackson2HttpMessageConverter()));
	}

}
