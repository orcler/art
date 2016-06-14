package art.wei.xls;

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

import art.wei.util.PDFSchema;
import art.wei.util.PrintData;

import com.sinosoft.pdf.PDFEditor;

public class ParseExcel {

	public List<PDFSchema> data = new ArrayList<PDFSchema>();
	
	public boolean parse() throws Exception {
		String file = "data.xls";
		HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = book.getSheetAt(0);
		String[] files = new String[sheet.getLastRowNum()]; //生成pdf文件路径
		String outDir = "data" ;
		DateFormat df = new SimpleDateFormat("yyyy 年 MM 月");
		String sysdate = df.format(new Date());
		sysdate = "2016 年 5 月";
		File dir = new File(outDir);
		if (!dir.exists())
			dir.mkdirs();
		PDFEditor editor = new PDFEditor();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			PrintData data = new PrintData();	
			int cell2 = (int) row.getCell(2).getNumericCellValue();
			String sex =cell2 == 0 ? "先生" : "女士";
			String outFile = outDir + File.separator + row.getCell(3).getStringCellValue() + ".pdf";
			files[i-1] = outFile;
			String tType = row.getCell(0).getStringCellValue().toUpperCase().trim();
			String tTemplete = "templete/" + tType + cell2 +".pdf";
			data.type= tType;
			String receive = row.getCell(1).getStringCellValue() + " " + sex + "（收）";
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
				data.setReceive("邮编：" + tZipCode, 1);
			} else {
				data.setReceive("邮编：" + tZipCode, 0);
			}
			data.setAgentName("德华安顾人寿尊贵客户健康体检邀请函");
			data.setCustomer(row.getCell(1).getStringCellValue());
			data.setIdno(row.getCell(3).getStringCellValue());
			//data.setSysdate(sysdate);
			//生成pdf
			editor.create(data.datas, outFile, tTemplete);
		}
		String newfile = outDir+File.separator+"print.pdf";
		editor.mergePdfFiles(files, newfile);
		
		return true;
	}
	
	
	
}
 