package com.barcode.example.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeInter25;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class BarCodePrintServiceImpl implements BarCodePrintService {
	
	private Document document;
    private static final String BARCODE_FILENAME = "barcode.pdf";
    private static final String DIRECTORY = "src/main/resources";
	
	public String generateBarCode(Map<String, String> barCodeValues) {
		// TODO Auto-generated method stub
		return String.format("TEST");
	}
	
	private BarcodeInter25 getBarcodeLayout(String code){
		BarcodeInter25 code25 = new BarcodeInter25();
	    code25.setGenerateChecksum(true);
	    code25.setCode(code);
	    return code25;
	}

	@Override
	public void generateBarCodePDF(Map<String, String> barCodeValues) {
		// TODO Auto-generated method stub
	 
		// step 1
        this.document = new Document(new Rectangle(340, 842));
        //this.document = new Document(new Rectangle(100, 100));

        // step 2
        PdfWriter pdfWriter = null;
        
        try{
        	pdfWriter = getPdfWriter();
        }catch (FileNotFoundException fne){
        	//TODO: change to logging frameworks
        	System.out.println(fne.getMessage());
        	fne.printStackTrace();
        }catch (DocumentException de){
        	System.out.println(de.getMessage());
        	de.printStackTrace();
        }
       
        // step 3
        this.document.open();
        
        // step 4
        PdfContentByte cb = getPdfContentByte(pdfWriter);
        BarcodeInter25 code25 = getBarcodeLayout("0611012345678");

        try {
			this.document.add(code25.createImageWithBarcode(cb, null, null));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.document.close();
		}
        
	}

	private PdfWriter getPdfWriter() throws FileNotFoundException, DocumentException{
        return PdfWriter.getInstance(document, new FileOutputStream(DIRECTORY + File.separator + BARCODE_FILENAME));
	}
	
	private PdfContentByte getPdfContentByte(PdfWriter pdfWriter){
        return pdfWriter.getDirectContent();
	}
	
}
