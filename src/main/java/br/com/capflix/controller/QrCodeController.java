package br.com.capflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.capflix.service.QrCodeService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("qrcode")
@Log4j2
@Validated
public class QrCodeController {

	@Autowired
	private QrCodeService service;
	
	@GetMapping(value="id/{id}", produces=MediaType.IMAGE_PNG_VALUE)
	public  byte[] gerar(@PathVariable("id") Long id) {
		log.info("pagarUm: {}", id);
		
		return service.gerar(id);
	}
}
