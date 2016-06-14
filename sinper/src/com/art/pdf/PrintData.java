package com.art.pdf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wei
 *
 */
public class PrintData {
	
	public List<PDFSchema> datas = new ArrayList<PDFSchema>();
	
	private String contno;
	private String appnt;
	private String receive;
	private String insured;
	private String year;
	private String agentno;
	private String agentName;
	private String agentMoblie;
	private String zipcode;
	private String address;
	private String address1;
	private String customer;
	private String contno1;
	private String idno;
	private String helpno;
	private String sysdate;
	
	public String type;
	
	public void setContno(String contno) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(contno);
		schema.setCoordinate(426, 746);
	}
	public void setAppnt(String appnt) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(appnt);
		schema.setCoordinate(400, 728);
	}
	public void setInsured(String insured) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(insured);
		schema.setCoordinate(410, 710);
	}
	public void setYear(String year) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(year);
		schema.setCoordinate(437, 692);
	}
	public void setAgentno(String agentno) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(agentno);
		schema.setCoordinate(462, 673);
	}
	public void setAgentName(String appntName) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(appntName);
		schema.setCoordinate(56, 764);
	}
	public void setAgentMoblie(String agentMoblie) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(agentMoblie);
		schema.setCoordinate(462, 637);
	}
	public void setZipcode(String zipcode) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(zipcode);
		schema.setCoordinate(56, 746);
	}
	public void setAddress(String address) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(address);
		schema.setCoordinate(56, 728);
	}
	public void setIdno(String idno) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(idno);
		if ("A".equals(type))
			schema.setCoordinate(217, 533);
		else if ("B".equals(type))
			schema.setCoordinate(218, 548);
		else if ("C".equals(type))
			schema.setCoordinate(216, 533);
		else
			schema.setCoordinate(217, 521);
	}
	public void setHelpno(String helpno) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(helpno);
		schema.setCoordinate(391, 70);
	}

	public void setCustomer(String customer) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(customer);
		if ("A".equals(type))
			schema.setCoordinate(130, 533);
		else if ("B".equals(type))
			schema.setCoordinate(130, 548);
		else if ("C".equals(type))
			schema.setCoordinate(130, 533);
		else
			schema.setCoordinate(130, 521);
	}
	public void setContno1(String contno1) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(contno1);
		schema.setCoordinate(124, 70);
	}
	
	
	public void setReceive(String receive, int row) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(receive);
		schema.setCoordinate(56, 710-row*18);
	}
	public void setAddress1(String address1) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(address1);
		schema.setCoordinate(56, 710);
	}
	public void setSysdate(String sysdate) {
		PDFSchema schema = new PDFSchema();
		datas.add(schema);
		schema.setText(sysdate);
		if ("A".equals(type))
			schema.setCoordinate(384, 88);
		else if ("B".equals(type))
			schema.setCoordinate(386, 88);
		else if ("C".equals(type))
			schema.setCoordinate(384, 108);
		else
			schema.setCoordinate(384, 102);
	}
	
}
