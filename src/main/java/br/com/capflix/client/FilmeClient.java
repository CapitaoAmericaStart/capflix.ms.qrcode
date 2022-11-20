package br.com.capflix.client;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.capflix.model.dto.FilmeClientSaidaDto;
import br.com.capflix.util.JsonUtil;

@Component
public class FilmeClient {

	private static final String URL = "http://localhost:8080/filme/id/";
	@Autowired
	private JsonUtil jsonUtil = new JsonUtil();

	public FilmeClientSaidaDto pegarUm(Long id) throws IOException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + id);
		Builder builder = target.request();
		
		Response response = builder.get();
		
		int status = response.getStatus();
		if(status == 200) {
			String jsonVindo = response.readEntity(String.class);
			
			FilmeClientSaidaDto dto = jsonUtil.objeto(jsonVindo, FilmeClientSaidaDto.class);
			
			return dto;
		}
		throw new RuntimeException("status: " + status);
	}
}
