package br.com.capflix.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import br.com.capflix.client.FilmeClient;
import br.com.capflix.exception.NegocioException;
import br.com.capflix.exception.TabelaDeErros;
import br.com.capflix.model.dto.FilmeClientSaidaDto;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class QrCodeService {
	
	private static final int WIDTH_IMAGE = 200;
	private static final int HEIGHT_IMAGE = 200;
	private static final String IMAGE_TYPE = "png";
	
	@Autowired
	private FilmeClient filmeClient;
	
	public byte[] gerar(Long id) {
		try {
			FilmeClientSaidaDto dto = filmeClient.pegarUm(id);
			
			return gerarQrCode(dto.getUrl());
		} catch (Exception e) {
			log.error("erro genérico: ", e);
			
			throw new NegocioException(TabelaDeErros.SISTEMA);
		} 
	}

	public byte[] gerarQrCode(String text) throws WriterException, IOException {
		Writer writer = new QRCodeWriter();
		
		BitMatrix matrix = writer.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, WIDTH_IMAGE, HEIGHT_IMAGE);

		int width = matrix.getWidth(); 
		int height = matrix.getHeight(); 

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (matrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, IMAGE_TYPE, baos);
		baos.flush();
		baos.close();
		byte[] imageInBytes = baos.toByteArray();

		return imageInBytes;
	}
}
