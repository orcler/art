package com.art.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.schema.MissionSchema;
import com.art.schema.TrafficSchema;
import com.art.service.TrafficService;
import com.art.util.PubFun;

public class CtrlUpload implements Controller {

    private TrafficService trafficService;
	private String userId;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		userId = (String) tHttpSession.getAttribute("userId");
		String path = request.getSession().getServletContext().getRealPath("upload");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024);
		factory.setRepository(new File(path));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(-1);
		List<FileItem> items = upload.parseRequest(request);
		for (FileItem item : items) {
			if (!item.isFormField()) {
				String fileName = item.getName();
				String tPath = request.getSession().getServletContext()	.getRealPath("/") + "data" + File.separator + fileName;
				File outFile = new File(tPath);
				System.out.println(tPath);
				item.write(outFile);
				String msg = parsexls(tPath);
				if (msg == null) {
				    msg = "保存成功！";
				}
				request.setAttribute("msg", msg);
			}
		}
		return new ModelAndView("msg");
	}

	public String parsexls(String aPath) throws Exception {
		Date tCurDate = PubFun.getCurSqlDate();
		String tCurTime = PubFun.getCurTime();
		HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(aPath));
		HSSFSheet sheet = book.getSheetAt(0);
		List<TrafficSchema> trafficList = new ArrayList<TrafficSchema>();
		List<MissionSchema> missionList = new ArrayList<MissionSchema>();
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			System.out.println(row.getCell(0).getStringCellValue());
			String tEngineNo 		=  getCellText(row.getCell(0));
			String tVIN		 		=  getCellText(row.getCell(1));
			String tModel 	 		=  getCellText(row.getCell(2));
			String tCert	 			=  getCellText(row.getCell(3));
			String tStrCost  			=  getCellText(row.getCell(4));
			String tStrMileage 		=  getCellText(row.getCell(5));
			String tColor 			=  getCellText(row.getCell(6));
			String tAttn 				=  getCellText(row.getCell(7));
			String tPhone  			=  getCellText(row.getCell(8));
			String tComcode 		=  getCellText(row.getCell(9));
			String tRemark   		=  getCellText(row.getCell(10));
			
			double tCost = 0.0;
			if (tStrCost != null && !"".equals(tStrCost)) {
			    tCost = Double.parseDouble(tStrCost);
			}
			double tMileage = 0.0;
			if (tStrMileage != null && !"".equals(tStrMileage)) {
			    tMileage = Double.parseDouble(tStrMileage);
			}
			
			//入库信息
			TrafficSchema tTrafficSchema = new TrafficSchema();
			String tTFSerialNo 		=  PubFun.getSerialNo("TF");
			tTrafficSchema.setSerialNo(tTFSerialNo);
			tTrafficSchema.setEngineNo(tEngineNo);
			tTrafficSchema.setVIN	(tVIN);
			tTrafficSchema.setModel(tModel);
			tTrafficSchema.setCost(tCost);
			tTrafficSchema.setCert(tCert);
			tTrafficSchema.setMileage(tMileage);
			tTrafficSchema.setColor(tColor);
			tTrafficSchema.setAttn(tAttn);
			tTrafficSchema.setPhone(tPhone);
			tTrafficSchema.setComcode(tComcode);
			tTrafficSchema.setIndate(tCurDate);
			tTrafficSchema.setIntime(tCurTime);
			tTrafficSchema.setRemark(tRemark);
			tTrafficSchema.setState("1"); //入库
			
			MissionSchema tMissionSchema = new MissionSchema();
			String tWFSerialNo = PubFun.getSerialNo("WF");
			tMissionSchema.setSubmissionid("1");
			tMissionSchema.setMissionid(tWFSerialNo);
			tMissionSchema.setMissionprop1(tTFSerialNo);//入库流水号
			tMissionSchema.setActivityid("1000000002");
			tMissionSchema.setActivitystatus("0");
			tMissionSchema.setProcessid("0000000001");
			tMissionSchema.setCreateoperator(userId);
			tMissionSchema.setMainmissionid(tWFSerialNo);
			tMissionSchema.setMakedate(tCurDate);
			tMissionSchema.setMaketime(tCurTime);
			tMissionSchema.setModifydate(tCurDate);
			tMissionSchema.setModifytime(tCurTime);
			tMissionSchema.setIndate(tCurDate);
			tMissionSchema.setIntime(tCurTime);
			
			trafficList.add(tTrafficSchema);
			missionList.add(tMissionSchema);
		}
		return trafficService.inbatchRecord(trafficList, missionList);
	}
	
	public String getCellText(Cell cell) {
		if (cell.getCellType() ==0) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return cell.getStringCellValue();
	}
	
    public TrafficService getTrafficService() {
        return trafficService;
    }

    public void setTrafficService(TrafficService trafficService) {
        this.trafficService = trafficService;
    }
	
}
