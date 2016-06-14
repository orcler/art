package com.art.pdf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.art.schema.TrafficSchema;
import com.art.util.DataSource;

public class InConfPirnt {
	
	public static final String FILEPATH = "";
	public static final String TEMPLETE = "";

	public boolean printPdf(TrafficSchema aTrafficSchema) throws Exception {
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
		tSerialNoSchema.setCoordinate(10, 700);
		tList.add(tSerialNoSchema);
		
		PDFSchema tEngineNoSchema = new PDFSchema();
		tEngineNoSchema.setText(tEngineNo);
		tEngineNoSchema.setCoordinate(200, 700);
		tList.add(tEngineNoSchema);
		
		PDFSchema tVINSchema = new PDFSchema();
		tVINSchema.setText(tVIN);
		tVINSchema.setCoordinate(10, 690);
		tList.add(tVINSchema);
		
		PDFSchema tCostSchema = new PDFSchema();
		tCostSchema.setText(String.valueOf(tCost));
		tCostSchema.setCoordinate(200, 690);
		tList.add(tCostSchema);
		
		PDFSchema tModelSchema = new PDFSchema();
		tModelSchema.setText(tModel);
		tModelSchema.setCoordinate(10, 680);
		tList.add(tModelSchema);
		
		PDFSchema tColorSchema = new PDFSchema();
		tColorSchema.setText(tColor);
		tColorSchema.setCoordinate(200, 680);
		tList.add(tColorSchema);
		
		PDFSchema tComSchema = new PDFSchema();
		tComSchema.setText(tCom);
		tComSchema.setCoordinate(10, 670);
		tList.add(tComSchema);
		PDFEditor editor = new PDFEditor();
		String tFileName = FILEPATH + "in_" + tSerialNo + ".pdf";
		editor.create(tList ,tFileName , TEMPLETE);
		return false;
	}
	
}
