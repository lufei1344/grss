package com.grss.jlsx.admin.controller.user;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.util.ParsePropertiesUtil;
import com.grss.jlsx.admin.process.vo.DrawMoneyVO;
import com.grss.jlsx.core.bean.GrssDrawMoneyVO;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssDrawMoneyService;
import com.grss.jlsx.core.utils.DateUtil;
import com.grss.jlsx.core.utils.StringUtil;

@Controller
@RequestMapping("/admin/drawMoney/")
public class DrawMoneyController extends BaseController {
	private static final String FK_ACCOUNT = "11014904869003";
	@Resource
	private GrssDrawMoneyService grssDrawMoneyService;

	@RequestMapping("listDrawMoney")
	public String listDrawMoney(HttpServletRequest request, DrawMoneyVO paramsVO) {
		Map<String, Object> params = paramsVO.getParamsMap();
		Pager<GrssDrawMoneyVO> pager = grssDrawMoneyService.findDrawMoneyList(params);
		return goTo(request, "drawMoney/listDrawMoney", pager);
	}

	@RequestMapping("exportDrawMoneyExcel")
	@ResponseBody
	public String exportDrawMoneyExcel(DrawMoneyVO paramsVO) {
		try {
			Map<String, Object> params = paramsVO.getParamsMap();
			List<GrssDrawMoneyVO> list = grssDrawMoneyService.findDrawMoneyData(params);
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("sheet1");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("流水号");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("付款账号");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("收款人卡号");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("收款人户名");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("收款卡开户行省");
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue("收款卡开户行市");
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("收款卡开户行名称");
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue("金额");
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellValue("摘要");
			cell.setCellStyle(style);
			cell = row.createCell(9);
			cell.setCellValue("收款卡开户行联行号");
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue("收款人手机号");
			cell.setCellStyle(style);
			DecimalFormat myformat=new DecimalFormat("0.00");
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(i + 1);
				GrssDrawMoneyVO grssDrawMoneyVO = list.get(i);
				String nowTime = System.currentTimeMillis() +""+getFileNumber()+(i + 1);
				// 第四步，创建单元格，并设置值
				row.createCell(0).setCellValue(nowTime);
				row.createCell(1).setCellValue(FK_ACCOUNT);
				row.createCell(2).setCellValue(grssDrawMoneyVO.getCardNo());
				row.createCell(3).setCellValue(grssDrawMoneyVO.getOpenAccountName());
				row.createCell(4).setCellValue(grssDrawMoneyVO.getOpenAccountCity());
				row.createCell(5).setCellValue(grssDrawMoneyVO.getOpenAccountCity());
				row.createCell(6).setCellValue(grssDrawMoneyVO.getOpenAccountBank());
				row.createCell(7).setCellValue(myformat.format(grssDrawMoneyVO.getAmount()));
				row.createCell(8).setCellValue("教练随行");
				row.createCell(9).setCellValue("");
				String userPhone = grssDrawMoneyVO.getUserPhone();
				row.createCell(10).setCellValue(userPhone == null ? "" : userPhone);
			}
			String datePath=DateUtil.date2Str(new Date(),"yyyyMMdd");
			String fileName="reg_"+datePath+"_"+getFileNumber()+".xls";
			String url=ParsePropertiesUtil.getServiceAddress()+ParsePropertiesUtil.getFileAddress()+datePath+"/"+fileName;
			String path=ParsePropertiesUtil.getUploadFileAddress()+datePath+"/";
			File file =new File(path);    
			//如果文件夹不存在则创建    
			if  (!file .exists()  && !file .isDirectory()){       
			    file.mkdirs();    
			}
			path+=fileName;
			FileOutputStream fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	private String getFileNumber(){
		String ranDomStr=(System.currentTimeMillis()*StringUtil.getRandomNumber(1,100))+"";
		ranDomStr=ranDomStr.substring(ranDomStr.length()-4, ranDomStr.length());
		return ranDomStr;
	}
}
