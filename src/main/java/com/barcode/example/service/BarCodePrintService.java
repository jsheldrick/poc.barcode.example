package com.barcode.example.service;

import java.util.Map;

public interface BarCodePrintService {
	
	String generateBarCode(Map<String, String> barCodeValues);
	
	void generateBarCodePDF(Map<String, String> barCodeValues);
 

}
