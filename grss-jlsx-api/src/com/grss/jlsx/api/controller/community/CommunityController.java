package com.grss.jlsx.api.controller.community;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.grss.jlsx.api.controller.BaseController;
import com.grss.jlsx.api.process.annotation.AuthorityAnnotation;
import com.grss.jlsx.api.process.constant.EndPoints;
import com.grss.jlsx.api.process.upload.UploadFile;
import com.grss.jlsx.api.process.util.DateUtil;
import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.bean.GrssCommunity;
import com.grss.jlsx.core.bean.GrssCommunitySnitch;
import com.grss.jlsx.core.bean.GrssPosts;
import com.grss.jlsx.core.bean.GrssPostsAdmire;
import com.grss.jlsx.core.bean.GrssPostsRemark;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.bean.GrssUserCommunity;
import com.grss.jlsx.core.bean.GrssUserCommunityKey;
import com.grss.jlsx.core.network.im.EasemobIMChannel;
import com.grss.jlsx.core.network.im.IMChannel;
import com.grss.jlsx.core.service.GrssCommunityService;
import com.grss.jlsx.core.service.GrssCommunitySnitchService;
import com.grss.jlsx.core.service.GrssFollowService;
import com.grss.jlsx.core.service.GrssPostsAdmireService;
import com.grss.jlsx.core.service.GrssPostsRemarkService;
import com.grss.jlsx.core.service.GrssPostsService;
import com.grss.jlsx.core.service.GrssUserCommunityService;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.utils.JsonUtil;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;

@Controller
@RequestMapping("/api")
public class CommunityController extends BaseController{
	
	@Resource
	private GrssCommunityService grssCommunityService;
	@Resource
	private GrssPostsService grssPostsService;
	@Resource
	private GrssUserCommunityService grssUserCommunityService;
	@Resource
	private GrssCommunitySnitchService grssCommunitySnitchService;
	@Resource
	private GrssUserService grssUserService;
	@Resource
	private GrssPostsRemarkService grssPostsRemarkService;
	@Resource
	private GrssPostsAdmireService grssPostsAdmireService;
	@Resource
	private GrssFollowService grssFollowService;
	/**
	 * 创建社区
	 * @param token 登录证明
	 * @param name 社区名称
	 * @param comment 社区描述
	 * @param image 社区主图
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "createCommunity",method=RequestMethod.POST)
	public ResultDataTO createCommuniry(@RequestParam("token") String token,@RequestParam(value = "name",required = false) String name,@RequestParam(value = "comment",required = false) String comment,@RequestPart(value = "image",required = false) MultipartFile image){
		try {
			String imgUrl = null;
			if(image != null && !image.isEmpty()){
				imgUrl = UploadFile.doUploadImg(image, null, DateUtil.date2Str(new Date()));
			}
			if(!StringUtil.empty(token)){
				String userId = SecurityUtil.token2UserId(token);
				String communityId = StringUtil.getUUID();
				GrssCommunity community = new GrssCommunity(communityId,name,comment,imgUrl,new Date(),userId);
				GrssUserCommunity grssUserCommunity = new GrssUserCommunity(userId,communityId,1,0);
				int result = grssCommunityService.addGrssCommunity(community,grssUserCommunity);
				if(result > 0){
					return resultSuccess(ParsePropertiesUtil.getMessage("message.community.create.success"), result);
				}
			}
			
			return resultFaild(ParsePropertiesUtil.getMessage("message.community.create.failed"));
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild(ParsePropertiesUtil.getMessage("message.goods.barcode.failed"));
		}
	}
	/**
	 * 获取我的社区
	 * @param token 登录证明
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "getMyCommunity",method=RequestMethod.POST)
	public ResultDataTO getMyCommunity(@RequestParam("token") String token){
		if(StringUtil.empty(token)){
			return resultFaild(ParsePropertiesUtil.getMessage("message.token.invalid"));
		}
		String userId = SecurityUtil.token2UserId(token);
		List<GrssCommunity> communityList = grssCommunityService.findGrssCommuniryByUserId(userId);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), communityList);
	}
	/**
	 * 搜索社区
	 * @param keyword 搜索关键词
	 * @return
	 */ 
	@ResponseBody
	@RequestMapping(value = "searchCommunity",method=RequestMethod.POST)
	public ResultDataTO searchCommuniry(@RequestParam(value = "keyword",required = false) String keyword){
		List<GrssCommunity> communityList = grssCommunityService.findGrssCommuniryByKeyword(keyword);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), communityList);
	}
	/**
	 * 社区详情
	 * @param communityId 社区ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCommunityDetail",method=RequestMethod.POST)
	public ResultDataTO getCommunityDetail(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "communityId",required = false) String communityId){
		List<GrssPosts> postsList = grssPostsService.findGrssPostsByCommunityId(communityId);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList(postsList,token));
	}
	/**
	 * 邀请好友
	 * @param communityId  社区ID
	 * @param userIds 用户ID组 格式以，分隔 例子1,2,3,4
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "invitationFriend",method=RequestMethod.POST)
	public ResultDataTO invitationFriend(@RequestParam(value = "communityId",required = false) String communityId,@RequestParam(value = "userIds",required = false) String userIds){
		if(!StringUtil.empty(userIds)){
			String[] uIds = userIds.split(",");
			grssUserCommunityService.addGrssUserCommunityByUserIds(communityId,uIds);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.invitation.friend.success"), null);
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.params.required.failed"));
		
	}
	/**
	 * 社区帖子
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCommunityPosts",method=RequestMethod.POST)
	public ResultDataTO getCommunityPosts(@RequestParam(value = "token",required = false) String token){
		List<GrssPosts> postsList = grssPostsService.findGrssPostsAll();
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList(postsList,token));
	}
	/**
	 * 关注帖子
	 * @param token 登录证明
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "getFollowPosts",method=RequestMethod.POST)
	public ResultDataTO getFollowPosts(@RequestParam(value = "token",required = false) String token){
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			List<GrssPosts> postsList = grssPostsService.findGrssPostsByFollowMyUserId(userId);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList(postsList,token));
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.params.required.failed"));
	}
	/**
	 * 举报
	 * @param token 登录证明
	 * @param commnuityId 社区ID
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "snitch",method=RequestMethod.POST)
	public ResultDataTO snitch(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "commnuityId",required = false) String commnuityId){
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			GrssCommunitySnitch communitySnitch = new GrssCommunitySnitch(StringUtil.getUUID(),userId,commnuityId,new Date());
			int flag = grssCommunitySnitchService.addGrssCommunitySnitch(communitySnitch);
			if(flag > 0){
				return resultSuccess(ParsePropertiesUtil.getMessage("message.snitch.success"), null);
			}
			return resultSuccess(ParsePropertiesUtil.getMessage("message.snitch.failed"), null); 
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.params.required.failed"));
	}
	
	/**
	 * 帖子动态(完成)
	 * @param token 登录证明（如果登录的时候传）
	 * @param postsId 帖子ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getDynamic",method=RequestMethod.POST)
	public ResultDataTO getDynamic(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "postsId",required = false) String postsId){
		GrssPosts grssPosts = grssPostsService.findGrssPostsByPostsId(postsId);
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			int relationship = grssFollowService.findFollowRelationship(userId,grssPosts.getGrssUser().getUserId());
			GrssUser grssUser = grssPosts.getGrssUser();
			grssUser.setFollowRelationship(relationship);
			grssPosts.setGrssUser(grssUser);
			int isAdmire = grssPostsAdmireService.findPostsAdmireByUserIdAndPostsId(userId,postsId);
			grssPosts.setIsAdmire(isAdmire);
		}
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList2(grssPosts));
	}
	/**
	 * 获取帖子点赞人员默认6前六条
	 * @param postsId 帖子ID
	 * @param userId 用户ID（帖子所属人）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAdmireLimit",method=RequestMethod.POST)
	public ResultDataTO getAdmireLimit(@RequestParam(value="token",required=false)String token,@RequestParam("postsId") String postsId){
		Map<String, Object> params = new HashMap<>();
		params.put("postsId", postsId);
		List<GrssUser> userList = grssUserService.findGrssUserByParams(params);
		if(!StringUtil.empty(token)){
			String loginUserId=SecurityUtil.token2UserId(token);
			List<GrssUser> resultList=new ArrayList<GrssUser>();
			for (int i = 0; i < userList.size(); i++) {
				GrssUser user=userList.get(i);
				String userId=user.getUserId();
				Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, userId);
				user.setFollowRelationship(followRelationship);
				resultList.add(user);
			}
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"),resultList);
		}
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), userList);
	}
	
	/**
	 *  获取帖子点赞人员列表
	 * @param postsId 帖子ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAdmireList",method=RequestMethod.POST)
	public ResultDataTO getAdmireList(@RequestParam(value="token",required=false)String token,@RequestParam("postsId") String postsId){
		List<GrssUser> userList = grssUserService.findGrssUserByPostsIdList(postsId);
		if(!StringUtil.empty(token)){
			String loginUserId=SecurityUtil.token2UserId(token);
			List<GrssUser> resultList=new ArrayList<GrssUser>();
			for (int i = 0; i < userList.size(); i++) {
				GrssUser user=userList.get(i);
				String userId=user.getUserId();
				Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, userId);
				user.setFollowRelationship(followRelationship);
				resultList.add(user);
			}
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"),resultList);
		}
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), userList);
	}
	/**
	 * 获取帖子评论
	 * @param postsId 帖子ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getComment",method=RequestMethod.POST)
	public ResultDataTO getComment(@RequestParam("postsId") String postsId){
		Map<String, Object> params = new HashMap<>();
		params.put("postsId", postsId);
		List<GrssPostsRemark> grssPostsRemarkList = grssPostsRemarkService.findGrssPostsRemarkByParams(params);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), grssPostsRemarkList);
	}
	/**
	 * 发布帖子
	 * @param token 登录证明
	 * @param communityId 社区ID
	 * @param content 帖子内容
	 * @param images 图片组  file[]
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "publishPosts",method=RequestMethod.POST)
	public ResultDataTO publishPosts(@RequestParam(value = "token",required = false) String token,@RequestParam(value="communityId",required = false)String communityId,@RequestParam(value = "content",required = false) String content,@RequestParam(value = "title",required = false) String title,@RequestParam(value = "images",required = false) MultipartFile[] images){
		try {
			if(!StringUtil.empty(token)){
				List<String> imgList = new ArrayList<String>();
				if(images != null){
					for(MultipartFile file : images){
						String imgUrl = UploadFile.doUploadImg(file, null, DateUtil.date2Str(new Date()));
						imgList.add(imgUrl);
					}
				}
				String postsId = StringUtil.getUUID();
				String userId = SecurityUtil.token2UserId(token);
				GrssPosts grssPosts = new GrssPosts(postsId,content,communityId,JSON.toJSONString(imgList),new Date(),title,userId,1);
				grssPostsService.addGrssPosts(grssPosts);
				return resultSuccess(ParsePropertiesUtil.getMessage("message.publish.success"), null);
			}
			return resultFaild(ParsePropertiesUtil.getMessage("message.params.required.failed"));
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild(ParsePropertiesUtil.getMessage("message.params.required.failed"));
		}
	}
	/**
	 * 评论（回复）
	 * @param token 登录证明
	 * @param content 评论内容
	 * @param replyUserId 回复人ID （如果是评论则没有回复人ID）
	 * @param postsId 帖子ID
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "replyPosts",method=RequestMethod.POST)
	public ResultDataTO replyPosts(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "content",required = false) String content,@RequestParam(value = "replyUserId",required = false) String replyUserId,@RequestParam(value = "postsId",required = false) String postsId){
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			GrssPostsRemark grssPostsRemark = new GrssPostsRemark(StringUtil.getUUID(),content,postsId,userId,replyUserId,new Date());
			grssPostsRemarkService.addGrssPostsRemark(grssPostsRemark);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.comment.success"), null);
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.comment.failed"));
	}
	/**
	 * 点赞或取消点赞
	 * @param token 登录证明
	 * @param postsId  帖子ID
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "admireOrCancelPosts",method=RequestMethod.POST)
	public ResultDataTO admireOrCancelPosts(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "postsId",required = false) String postsId){
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			int count = grssPostsAdmireService.findPostsAdmireByUserIdAndPostsId(userId, postsId);
			GrssPostsAdmire admire = new GrssPostsAdmire(postsId,userId);
			if(count == 0){
				grssPostsAdmireService.addGrssPostsAdmire(admire);
				return resultSuccess(ParsePropertiesUtil.getMessage("message.admire.success"), null);
			}else{
				grssPostsAdmireService.deleteGrssPostsAdmireByKey(admire);
				return resultSuccess(ParsePropertiesUtil.getMessage("message.unAdmire.success"), null);
			}
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.admire.failed"));
	}
	/**
	 *  转帖
	 * @param token 登录证明
	 * @param postsId 帖子ID	
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "repaste",method=RequestMethod.POST)
	public ResultDataTO repaste(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "postsId",required = false) String postsId){
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			GrssPosts grssPosts = grssPostsService.findGrssPostsById(postsId);
			if(StringUtil.empty(grssPosts.getSourceUserId())){
				grssPosts.setSourceUserId(grssPosts.getUserId());
			}
			grssPosts.setId(StringUtil.getUUID());
			grssPosts.setIsMaster(0);
			grssPosts.setUserId(userId);
			grssPosts.setSendDate(new Date());
			grssPosts.setCommunityId("");
			grssPostsService.addGrssPosts(grssPosts);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.repaste.success"),null);
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.repaste.failed"));
	}
	/**
	 * 星级
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "starLevel",method=RequestMethod.POST)
	public ResultDataTO starLevel(@RequestParam(value = "token",required = false) String token){
		List<GrssPosts> grssPostsList = grssPostsService.findGrssPostsAllOrderByTotal();
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList(grssPostsList,token));
	}
	private List<GrssPosts> jsonArrayToList(List<GrssPosts> grssPostsList,String token){
		List<GrssPosts> postsList = new ArrayList<>(); 
		for (GrssPosts grssPosts : grssPostsList) {
			grssPosts.setImageUrls(Arrays.asList(JSON.parseArray(grssPosts.getImagesUrl()).toArray(new String[]{})));
			grssPosts.setImagesUrl("");
			if(!StringUtil.empty(token)){
				String userId = SecurityUtil.token2UserId(token);
				int isAdmire = grssPostsAdmireService.findPostsAdmireByUserIdAndPostsId(userId,grssPosts.getId());
				grssPosts.setIsAdmire(isAdmire);
			}
			postsList.add(grssPosts);
		}
		return postsList;
	}
	/**
	 *  获取个人帖子动态
	 * @param token 登录证明
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "getMyPostsList",method=RequestMethod.POST)
	public ResultDataTO getMyPostsList(@RequestParam(value = "token",required = false) String token){
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList(doGetGrssPostsByUserId(userId),token));
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.not.find.token"));
	}
	/**
	 * 获取用户动态
	 * @param token 登陆证明
	 * @param userId 用户ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUserPostsList",method=RequestMethod.POST)
	public ResultDataTO getUserPostsList(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "userId",required = false) String userId){
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList(doGetGrssPostsByUserId(userId),token));
	}
	/**
	 * 获取好友动态
	 * @param token 登陆证明
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "getFriendPostsList",method=RequestMethod.POST)
	public ResultDataTO getFriendPostsList(@RequestParam(value = "token",required = false) String token){
		String userId = SecurityUtil.token2UserId(token);
		IMChannel channel = new EasemobIMChannel();
		String text = channel.getFriends(EndPoints.USERS_URL, credential, userId);
		List<String> uIds = JsonUtil.dissectJson(text, new String[]{"data"});
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList(doGetFriendPostsByUserIds(uIds), token));
	}
	
	private List<GrssPosts> doGetGrssPostsByUserId(String userId){
		List<GrssPosts> postsList = grssPostsService.findGrssPostsByUserId(userId);
		return postsList;
	}
	private List<GrssPosts> doGetFriendPostsByUserIds(List<String> userIds){
		List<GrssPosts> postsList = grssPostsService.findGrssPostsByUserIds(userIds);
		return postsList;
	}
	/**
	 * 推荐社区
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "recommendCommunity",method=RequestMethod.POST)
	public ResultDataTO recommendCommunity(){
		List<GrssCommunity> communitieList = grssCommunityService.findGrssCommunityByRecommentd();
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), communitieList);
	}
	/**
	 * 社区居民
	 * @param communityId 社区ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCommunityUser",method=RequestMethod.POST)
	public ResultDataTO getCommunityUser(@RequestParam(value = "communityId",required = false) String communityId){
		List<GrssUser> grssUserList = grssUserService.findGrssUserByCommunityId(communityId);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), grssUserList);
	}
	
	private GrssPosts jsonArrayToList2(GrssPosts grssPosts){
		grssPosts.setImageUrls(Arrays.asList(JSON.parseArray(grssPosts.getImagesUrl()).toArray(new String[]{})));
		grssPosts.setImagesUrl("");
		return grssPosts;
	}
	/**
	 * 加入社区
	 * @param token 登陆证明
	 * @param communityId 社区ID
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "joinCommunity",method=RequestMethod.POST)
	public ResultDataTO joinCommunity(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "communityId",required = false) String communityId){
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			int count = grssUserCommunityService.findGrssUserCommunityByUserIdAndCommunityId(userId, communityId);
			if(count > 0){
				return resultFaild("您已经是此社区居民");
			}
			GrssUserCommunity grssUserCommunity = new GrssUserCommunity(userId,communityId, 3, 0); 
			grssUserCommunityService.addGrssUserCommunity(grssUserCommunity);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), null);
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.not.find.token"));
		
	}
	/**
	 * 退出社区
	 * @param token 登陆证明
	 * @param communityId 社区ID
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "quitCommunity",method=RequestMethod.POST)
	public ResultDataTO quitCommunity(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "communityId",required = false) String communityId){
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			GrssUserCommunityKey communityKey = new GrssUserCommunityKey(userId,communityId);
			grssUserCommunityService.deleteGrssUserCommunityByUserIdAndCommunityId(communityKey);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), null);
		}
		return resultFaild(ParsePropertiesUtil.getMessage("message.not.find.token"));
	}
	/**
	 * 社区信息
	 * @param communityId 社区ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCommunityInfo",method=RequestMethod.POST)
	public ResultDataTO getCommunityInfo(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "communityId",required = false) String communityId){
		GrssCommunity community = grssCommunityService.findGrssCommunityById(communityId);
		if(!StringUtil.empty(token)){
			String userId = SecurityUtil.token2UserId(token);
			int count = grssUserCommunityService.findGrssUserCommunityByUserIdAndCommunityId(userId,communityId);
			community.setIsJoin(count);
		}
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), community);
	}
	/**
	 * 将社区人员踢出
	 * @param token 登录证明
	 * @param userIds 用户ID组 1,2,3,4,5
	 * @param communityId 社区ID
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "removeCommunityUser",method=RequestMethod.POST)
	public ResultDataTO removeCommunityUser(@RequestParam(value = "token",required = false)String token,@RequestParam(value = "userIds",required = false) String userIds,@RequestParam(value = "communityId",required = false) String communityId){
		if(!StringUtil.empty(token)){
			String uId = SecurityUtil.token2UserId(token);
			GrssUserCommunity userCommunity = grssUserCommunityService.findOneGrssUserCommunityByUserIdAndCommunityId(uId, communityId);
			if(userCommunity!= null ){
				if(userCommunity.getIsOff() > 2){
					return resultFaild("对不起您没有踢人权限！");
				}
				for(String userId : userIds.split(",")){
					GrssUserCommunityKey communityKey = new GrssUserCommunityKey(userId, communityId);
					grssUserCommunityService.deleteGrssUserCommunityByUserIdAndCommunityId(communityKey);
				}
				return resultSuccess("此成员已经踢出该社区！", null);
			}
		}
		return resultFaild("参数不完整！");
	}
	/**
	 * 我参与的帖子
	 * @param token 登录证明
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "involvementPosts",method=RequestMethod.POST)
	public ResultDataTO involvementPosts(@RequestParam(value = "token") String token){
		if(!StringUtil.empty(token)){
			String uId = SecurityUtil.token2UserId(token);
			List<GrssPosts> postsList = grssPostsService.findGrssPostsByAdmireUserId(uId);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), jsonArrayToList(postsList, token));
		}
		return resultFaild("参数不完整！");
	}
	/**
	 * 删除帖子
	 * @param token
	 * @param postsId
	 * @return
	 */
//	@ResponseBody
//	@AuthorityAnnotation(isApprove = false)
//	@RequestMapping(value = "removePosts",method=RequestMethod.POST)
//	public ResultDataTO removePosts(@RequestParam(value = "token") String token,@RequestParam(value = "postsId") String postsId){
//		if(!StringUtil.empty(token)){
//			String userId = SecurityUtil.token2UserId(token);
//			GrssPosts posts = grssPostsService.findGrssPostsById(postsId);
//			if(userId.equals(posts.getUserId())){
//				grssPostsService.deleteGrssPostsById(postsId);
//				return resultSuccess("删除成功！", null);
//			}
//			return resultFaild("对不起您不能删除别人的帖子！");
//		}
//		return resultFaild("参数不完整！");
//	}
	
	/**
	 * @comment 删除帖子
	 * @param token 当前登录人
	 * @param postsId 帖子ID
	 * @return 
	 * 2016年3月27日
	 * 下午4:57:33
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "deletePosts",method=RequestMethod.POST)
	public ResultDataTO deletePosts(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "postsId",required = false) String postsId){
		try {
			if(!StringUtil.empty(token)){
				String userId = SecurityUtil.token2UserId(token);
				GrssPosts grssPosts = grssPostsService.findGrssPostsById(postsId);
				if(grssPosts.getUserId().equals(userId)){
					grssPostsService.deletePosts(postsId);
					return resultSuccess("删除帖子成功！",null);
				}
				else{
					return resultFaild("删帖失败，登录人与持贴人不一致！");
				}
			}
			return resultFaild(ParsePropertiesUtil.getMessage("message.repaste.failed"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return resultFaild("删除帖子异常！");
		}
		
	}
}
