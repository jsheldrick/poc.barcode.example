package com.barcode.example;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.barcode.example.service.BarCodePrintService;
import com.barcode.example.service.BarCodePrintServiceImpl;

public class BasicBarcodeTest {
	
	private BarCodePrintService barCodePrintService;

	private Map<String, String> barCodeValues;

	@Before
	public void setUpData(){
		barCodeValues = new HashMap<>();
		barCodeValues.put("firstName", "Jose");
		barCodeValues.put("lastName", "Hernandez");
		barCodeValues.put("address", "1 Calle de Primera");
		barCodeValues.put("amountOwe", "1000");
		barCodeValues.put("amountPaid", "500");
		barCodeValues.put("total", "500");
		barCodePrintService = new BarCodePrintServiceImpl();
		
	}
	
	@Test
	public void testGenerateBarcode() {
		String barCode = barCodePrintService.generateBarCode(barCodeValues);
		Assert.assertNotNull(barCode);
		System.out.println("Barcode Value =" + barCode);
		
	}

	@Test
	public void testGenerateBarcodePDF(){
		barCodePrintService.generateBarCodePDF(barCodeValues);
		File file = new File("src/main/resources/barcode.pdf");
		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.length() > 0);
	}
	
}
