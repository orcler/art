package com.art.xls;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.art.pdf.PDFEditor;
import com.art.pdf.PDFSchema;
import com.art.pdf.PrintData;

public class ParseExcel {

	public List<PDFSchema> data = new ArrayList<PDFSchema>();
	
	public boolean parse() throws Exception {
		String file = "data.xls";
		HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = book.getSheetAt(0);
		String[] files = new String[sheet.getLastRowNum()]; 
		String outDir = "data" ;
		DateFormat df = new SimpleDateFormat("yyyy �� MM ��");
		String sysdate = df.format(new Date());
		sysdate = "2016 �� 5 ��";
		File dir = new File(outDir);
		if (!dir.exists())
			dir.mkdirs();
		PDFEditor editor = new PDFEditor();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			PrintData data = new PrintData();	
			int cell2 = (int) row.getCell(2).getNumericCellValue();
			String sex =cell2 == 0 ? "����" : "Ůʿ";
			String outFile = outDir + File.separator + row.getCell(3).getStringCellValue() + ".pdf";
			files[i-1] = outFile;
			String tType = row.getCell(0).getStringCellValue().toUpperCase().trim();
			String tTemplete = "templete/" + tType + cell2 +".pdf";
			data.type= tType;
			String receive = row.getCell(1).getStringCellValue() + " " + sex + "���գ�";
			System.out.println(receive);
			int tValueType = row.getCell(4).getCellType();
			System.out.println(tValueType);
			String tZipCode = "";
			if (tValueType == 0)
				tZipCode = (int)row.getCell(4).getNumericCellValue() + "";
			else
				tZipCode = row.getCell(4).getStringCellValue();
			data.setZipcode(receive);
			data.setAddress(row.getCell(5).getStringCellValue());
			if (row.getLastCellNum()>5 && row.getCell(6) != null) {
				data.setAddress1(row.getCell(6).getStringCellValue());
				data.setReceive("�ʱࣺ" + tZipCode, 1);
			} else {
				data.setReceive("�ʱࣺ" + tZipCode, 0);
			}
			data.setAgentName("�»������������ͻ�����������뺯");
			data.setCustomer(row.getCell(1).getStringCellValue());
			data.setIdno(row.getCell(3).getStringCellValue());
			//data.setSysdate(sysdate);
			//���pdf
			editor.create(data.datas, outFile, tTemplete);
		}
		String newfile = outDir+File.separator+"print.pdf";
		editor.mergePdfFiles(files, newfile);
		
		return true;
	}
	
	
	
}
 