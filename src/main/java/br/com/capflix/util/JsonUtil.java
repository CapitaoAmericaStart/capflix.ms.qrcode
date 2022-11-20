package br.com.capflix.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {

	private final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public String jsonFormatado(Object objeto) throws IOException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objeto);
	}
	
	public String json(Object objeto) throws IOException {
		return mapper.writeValueAsString(objeto);
	}
	
	public <T> T objeto(String json, Class<T> classe) throws IOException {
		return mapper.readValue(json, classe);
	}
	
	public <T> T objeto(File jsonFile, Class<T> classe) throws IOException {
		return mapper.readValue(jsonFile, classe);
	}
}
