package com.barcode.example;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class QRCode {
	// Tutorial: http://zxing.github.io/zxing/apidocs/index.html
	 
		public static void main(String[] args) {
			String myCodeText = "1234567890";//"http://crunchify.com/";
			String filePath = "CrunchifyQR.png";
			int size = 200;
			String fileType = "png";
			File myFile = new File(filePath);
			try {
				
				Map<EncodeHintType, Object> hintMap = 
						new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
				
				hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
				
				// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
				hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	 
				//Code39Writer code39Writer = new Code39Writer();
			    MultiFormatWriter code39Writer = new MultiFormatWriter();
			    String finaldata = Uri.encode(myCodeText, "utf-8");
				BitMatrix byteMatrix = code39Writer.encode(finaldata, BarcodeFormat.CODE_39, size, size, hintMap);	
				BitMatrix bm = code39Writer.encode(myCodeText, BarcodeFormat.CODE_39, 150, 150);
				Bitmap imageBitmap = Bitmap.createBitmap(size, size,Config.ARGB_8888);

				for (int i = 0; i < size; i++) {//width
				    for (int j = 0; j < size; j++) {//height
				         imageBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK.getRGB(): Color.WHITE.getRGB());
				    }
				}

		
				
				//BitMatrix byteMatrix = code39Writer.encode(myCodeText, BarcodeFormat.CODE_39, size, size, hintMap);
				// will only work with BarcodeFormat.QR_CODE
				//QRCodeWriter qrCodeWriter = new QRCodeWriter();
				//BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
				int CrunchifyWidth = byteMatrix.getWidth();
				BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
						BufferedImage.TYPE_INT_RGB);
				image.createGraphics();
	 
				Graphics2D graphics = (Graphics2D) image.getGraphics();
				graphics.setColor(Color.WHITE);
				graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
				graphics.setColor(Color.BLACK);
	 
				for (int i = 0; i < CrunchifyWidth; i++) {
					for (int j = 0; j < CrunchifyWidth; j++) {
						if (byteMatrix.get(i, j)) {
							graphics.fillRect(i, j, 1, 1);
						}
					}
				}
				
				Bitmap fileBitMap = BitmapFactory.decodeFile(myFile.getName());
								
				ImageIO.write(image, fileType, myFile);
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("\n\nYou have successfully created QR Code.");
		}
}
