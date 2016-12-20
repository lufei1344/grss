package com.grss.jlsx.admin.controller.sys.message;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.upload.UploadFile;
import com.grss.jlsx.admin.process.vo.MessageVO;
import com.grss.jlsx.core.bean.GrssSysMessage;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssSysMessageService;
import com.grss.jlsx.core.utils.DateUtil;
import com.grss.jlsx.core.utils.StringUtil;
@Controller
@RequestMapping("/admin/sys/message")
public class SysMessageController extends BaseController{
	
	@Resource
	private GrssSysMessageService GrssSysMessageService;
	
	@RequestMapping("/listSysMessage")
	public String listSysMessage(HttpServletRequest request,MessageVO messageVO){
		Pager<GrssSysMessage> pager = GrssSysMessageService.findGrssSysMessageList(messageVO.getParamsMap());
		return goTo(request, "/sys/message/listSysMessage", pager);
	}
	
	@RequestMapping("/addSysMessage")
	public String addSysMessage(){
		return goTo("/sys/message/addSysMessage");
	}
	
	@RequestMapping("/sendSysMessage")
	public ModelAndView sendSysMessage(GrssSysMessage grssSysMessage,MultipartFile imgFile,MultipartFile htmlFile){
		try {
			
			if(imgFile != null && !imgFile.isEmpty()){
				String imgUrl = UploadFile.doUploadImg(imgFile, null, DateUtil.date2Str(new Date()));
				grssSysMessage.setImgUrl(imgUrl);
			}
			if(htmlFile != null && !htmlFile.isEmpty()){
				String linkUrl = UploadFile.doUploadFile(htmlFile, null, DateUtil.date2Str(new Date()));
				grssSysMessage.setLinkUrl(linkUrl);
			}
			grssSysMessage.setId(StringUtil.getUUID());
			grssSysMessage.setSendTime(new Date());
			GrssSysMessageService.addGrssSysMessage(grssSysMessage);
			return ajaxDoneSuccess("发送成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("发送失败！");
		}
		
		
	}
	
	@RequestMapping("/deleteSysMessage/{messageId}")
	public ModelAndView deleteSysMessage(@PathVariable("messageId") String messageId){
		try {
			GrssSysMessageService.deleteByGrssSysMessage(messageId);
			return ajaxDoneSuccess("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("删除失败！");
		}
	}
}
