package com.sinosoft.pdf;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.art.pdf.PDFSchema;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author wei jian
 *
 */
public class PDFEditor {

	private Logger logger = Logger.getLogger(PDFEditor.class);
	private List<PDFSchema> mPdfList = new ArrayList<PDFSchema>();
	private PdfReader reader;
	private PdfStamper stamp;
	private String mOutFile;
	
	public boolean create(List<PDFSchema> aPdfList, String aOutFile, String aTemplete) throws Exception {
		mPdfList = aPdfList;
		mOutFile = aOutFile;
		reader = new PdfReader(aTemplete);
		stamp = new PdfStamper(reader, new FileOutputStream(mOutFile));
		int n = reader.getNumberOfPages();
		for (int i = 1; i <= n; i++) { //ѭ��ÿһҳ
			PdfContentByte Content =  stamp.getOverContent(i);
			for (PDFSchema schema : mPdfList) {
				if (i == schema.getPage()) { 
					Content.beginText();
					Content.setColorFill(new BaseColor(0, 0, 0));
					Content.setFontAndSize(schema.getFont(), schema.getFontSize());
					int tCoor[] = schema.getCoordinate();
					Content.setTextMatrix(tCoor[0], tCoor[1]); 
					Content.showText(schema.getText());
					Content.endText();
				}
			}
		}
		stamp.close();
		reader.close();
		return true;
	}
	
	 public  boolean mergePdfFiles(String[] files, String newfile) {   
       boolean retValue = false;   
       Document document = null;   
       try {   
    	   PdfReader reader1 = new PdfReader(files[0]);
           document = new Document(reader1.getPageSize(1));   
           reader1.close();
           PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));   
           document.open();   
           for (int i = 0; i < files.length; i++) {   
               PdfReader reader = new PdfReader(files[i]);   
               int n = reader.getNumberOfPages();   
               for (int j = 1; j <= n; j++) {   
                   document.newPage();   
                   PdfImportedPage page = copy.getImportedPage(reader, j);   
                	copy.addPage(page);   
               }   
               reader.close();
           }  
           retValue = true;   
       } catch (Exception e) {   
           e.printStackTrace();   
       } finally {   
           document.close();   
       }   
       return retValue;   
   }   

}
