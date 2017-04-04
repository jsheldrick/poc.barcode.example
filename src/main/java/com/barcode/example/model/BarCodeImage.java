package com.barcode.example.model;

import java.io.File;

import android.graphics.Bitmap;

public class BarCodeImage {
	
	private File file;
	private String fileType;
	private Bitmap fileBitMap;

	public BarCodeImage(File file, String fileType) {
		super();
		this.file = file;
		this.fileType = fileType;
	}

	public File getFile() {
		return file;
	}

	public String getFileType() {
		return fileType;
	}

	public Bitmap getFileBitMap() {
		return fileBitMap;
	}

	public void setFileBitMap(Bitmap fileBitMap) {
		this.fileBitMap = fileBitMap;
	}

}
