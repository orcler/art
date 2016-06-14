package com.art.pdf;

import com.itextpdf.text.pdf.BaseFont;

public  class PDFSchema {

	private String text;
	private int[] coordinate = new int[2];
	
	private int fontSize = 12;
	private int page = 1;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int[] getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(int x, int y) {
		coordinate[0]=x;
		coordinate[1]=y;
	}
	public BaseFont getFont() {
		BaseFont bfChinese = null;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return bfChinese;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
}
