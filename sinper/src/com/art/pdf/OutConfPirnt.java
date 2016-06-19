package com.art.pdf;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.art.schema.TrafficSchema;
import com.art.util.DataSource;

public class OutConfPirnt {
	
	public boolean printOutPdf(TrafficSchema aOutTrafficSchema, TrafficSchema aInTrafficSchema, String aPath) throws Exception {
		String tSerialNo = aOutTrafficSchema.getSerialNo();
		String tEngineNo = aOutTrafficSchema.getEngineNo();
		String tVIN = aOutTrafficSchema.getVIN();
		double tCost = aOutTrafficSchema.getCost();
		String tModel = aOutTrafficSchema.getModel();
		String tColor = aOutTrafficSchema.getColor();
		String tCom = aOutTrafficSchema.getComcode();
		String tSql = " SELECT codename from icode where codetype='comcode' and code='" + tCom + "' ";
		DataSource dataSource = new DataSource();
		Connection tConn =dataSource.openConn();
		Statement tStatement = tConn.createStatement();
		ResultSet tResult = tStatement.executeQuery(tSql);
		while (tResult.next()) {
			tCom = tResult.getString(1);
		}
		dataSource.closeConn(tConn, tStatement, tResult);
		
		String tInSerialNo = aOutTrafficSchema.getSerialNo();
		String tInEngineNo = aOutTrafficSchema.getEngineNo();
		String tInVIN = aOutTrafficSchema.getVIN();
		double tInCost = aOutTrafficSchema.getCost();
		String tInModel = aOutTrafficSchema.getModel();
		String tInColor = aOutTrafficSchema.getColor();
		
		int col1_x = 175;
		int col2_x = 400;
		List<PDFSchema> tList = new ArrayList<PDFSchema>();
		PDFSchema tSerialNoSchema = new PDFSchema();
		tSerialNoSchema.setText(tSerialNo);
		tSerialNoSchema.setCoordinate(col1_x, 602);
		tList.add(tSerialNoSchema);
		
		PDFSchema tEngineNoSchema = new PDFSchema();
		tEngineNoSchema.setText(tEngineNo);
		tEngineNoSchema.setCoordinate(col2_x, 602);
		tList.add(tEngineNoSchema);
		
		PDFSchema tVINSchema = new PDFSchema();
		tVINSchema.setText(tVIN);
		tVINSchema.setCoordinate(col1_x, 586);
		tList.add(tVINSchema);
		
		PDFSchema tCostSchema = new PDFSchema();
		tCostSchema.setText(String.valueOf(tCost));
		tCostSchema.setCoordinate(col2_x, 586);
		tList.add(tCostSchema);
		
		PDFSchema tModelSchema = new PDFSchema();
		tModelSchema.setText(tModel);
		tModelSchema.setCoordinate(col1_x, 570);
		tList.add(tModelSchema);
		
		PDFSchema tColorSchema = new PDFSchema();
		tColorSchema.setText(tColor);
		tColorSchema.setCoordinate(col2_x, 570);
		tList.add(tColorSchema);
		
		PDFSchema tComSchema = new PDFSchema();
		tComSchema.setText(tCom);
		tComSchema.setCoordinate(col1_x, 554);
		tList.add(tComSchema);
		
		PDFSchema tPayModeSchema = new PDFSchema();
		if ("1".equals(aOutTrafficSchema.getPaymode())) {//付款
		    	tPayModeSchema.setText("付款");
		    	tPayModeSchema.setCoordinate(col1_x, 300);
			tList.add(tPayModeSchema);
			
			PDFSchema tPayCostSchema = new PDFSchema();
			tPayCostSchema.setText(String.valueOf(tCost));
			tPayCostSchema.setCoordinate(col2_x, 300);
			tList.add(tPayCostSchema);
		} else {//入车
		    	tPayModeSchema.setText("入车");
			tPayModeSchema.setCoordinate(col1_x, 300);
			tList.add(tPayModeSchema);
		    
			PDFSchema tInSerialNoSchema = new PDFSchema();
			tInSerialNoSchema.setText(tInSerialNo);
			tInSerialNoSchema.setCoordinate(col1_x, 284);
			tList.add(tInSerialNoSchema);
			
			PDFSchema tInEngineNoSchema = new PDFSchema();
			tInEngineNoSchema.setText(tInEngineNo);
			tInEngineNoSchema.setCoordinate(col2_x, 284);
			tList.add(tInEngineNoSchema);
			
			PDFSchema tInVINSchema = new PDFSchema();
			tInVINSchema.setText(tInVIN);
			tInVINSchema.setCoordinate(col1_x, 268);
			tList.add(tInVINSchema);
			
			PDFSchema tInCostSchema = new PDFSchema();
			tInCostSchema.setText(String.valueOf(tInCost));
			tInCostSchema.setCoordinate(col2_x, 268);
			tList.add(tInCostSchema);
			
			PDFSchema tInModelSchema = new PDFSchema();
			tInModelSchema.setText(tInModel);
			tInModelSchema.setCoordinate(col1_x, 252);
			tList.add(tInModelSchema);
			
			PDFSchema tInColorSchema = new PDFSchema();
			tInColorSchema.setText(tInColor);
			tInColorSchema.setCoordinate(col2_x, 252);
			tList.add(tInColorSchema);
			
		}
		
		PDFEditor editor = new PDFEditor();
		String tFileName = aPath + "out" + File.separator + tSerialNo + ".pdf";
		String tTemplete = aPath + "outprint.pdf";
		editor.create(tList ,tFileName , tTemplete);
		return true;
	}
	
}
