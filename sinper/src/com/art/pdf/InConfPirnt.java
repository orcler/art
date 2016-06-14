package com.art.pdf;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.art.schema.TrafficSchema;
import com.art.util.DataSource;

public class InConfPirnt {
	
	public boolean printInPdf(TrafficSchema aTrafficSchema, String aPath) throws Exception {
		String tSerialNo = aTrafficSchema.getSerialNo();
		String tEngineNo = aTrafficSchema.getEngineNo();
		String tVIN = aTrafficSchema.getVIN();
		double tCost = aTrafficSchema.getCost();
		String tModel = aTrafficSchema.getModel();
		String tColor = aTrafficSchema.getColor();
		String tCom = aTrafficSchema.getComcode();
		String tSql = " SELECT codename from icode where codetype='comcode' and code='" + tCom + "' ";
		DataSource dataSource = new DataSource();
		Connection tConn =dataSource.openConn();
		Statement tStatement = tConn.createStatement();
		ResultSet tResult = tStatement.executeQuery(tSql);
		while (tResult.next()) {
			tCom = tResult.getString(1);
		}
		dataSource.closeConn(tConn, tStatement, tResult);
		
		List<PDFSchema> tList = new ArrayList<PDFSchema>();
		PDFSchema tSerialNoSchema = new PDFSchema();
		tSerialNoSchema.setText(tSerialNo);
		tSerialNoSchema.setCoordinate(160, 602);
		tList.add(tSerialNoSchema);
		
		PDFSchema tEngineNoSchema = new PDFSchema();
		tEngineNoSchema.setText(tEngineNo);
		tEngineNoSchema.setCoordinate(380, 602);
		tList.add(tEngineNoSchema);
		
		PDFSchema tVINSchema = new PDFSchema();
		tVINSchema.setText(tVIN);
		tVINSchema.setCoordinate(160, 586);
		tList.add(tVINSchema);
		
		PDFSchema tCostSchema = new PDFSchema();
		tCostSchema.setText(String.valueOf(tCost));
		tCostSchema.setCoordinate(380, 586);
		tList.add(tCostSchema);
		
		PDFSchema tModelSchema = new PDFSchema();
		tModelSchema.setText(tModel);
		tModelSchema.setCoordinate(160, 570);
		tList.add(tModelSchema);
		
		PDFSchema tColorSchema = new PDFSchema();
		tColorSchema.setText(tColor);
		tColorSchema.setCoordinate(380, 570);
		tList.add(tColorSchema);
		
		PDFSchema tComSchema = new PDFSchema();
		tComSchema.setText(tCom);
		tComSchema.setCoordinate(160, 554);
		tList.add(tComSchema);
		PDFEditor editor = new PDFEditor();
		String tFileName = aPath + "in" + File.separator + tSerialNo + ".pdf";
		String tTemplete = aPath + "inprint.pdf";
		editor.create(tList ,tFileName , tTemplete);
		return true;
	}
	
}
