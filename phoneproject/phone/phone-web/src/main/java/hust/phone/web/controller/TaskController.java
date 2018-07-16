package hust.phone.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hust.phone.mapper.pojo.Task;
import hust.phone.mapper.pojo.User;
import hust.phone.service.interFace.UserService;
import hust.phone.service.interFace.taskService;
import hust.phone.utils.pojo.JsonView;
import hust.phone.utils.pojo.PhoneUtils;

@Controller
public class TaskController {

	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private taskService taskServiceImpl;

	// 工作单跳转
	@RequestMapping("/toTask")
	public String toTaskList() {
		return "task";
	}
	
	//确认任务
	//如果用户角色是放飞者，那么修改该任务的状态为 2
	//如果用户是接收者，那么修改该任务的状态为 3，同时在exe表中插入一条新的数据
	@RequestMapping(value="/ensureTask",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ensureTask(Task task,HttpServletRequest request) {
		
		User user = PhoneUtils.getLoginUser(request);
			
		
		String reString = "已经确认任务";
		return JsonView.render(1, reString);
	}
	
	// 查询所有的工作单
	@RequestMapping("/taskList")
	public String taskList(String flag, HttpSession session, Model model) {
		Task task = new Task();
		// 得到用户id
		// String userid =session.getAttribute("userid").toString();
		String userId = "1"; // 起飞
		// 得到用户
		User user = userServiceImpl.getUserById(userId);
		// 得到用户角色
		String role = user.getRole();
		List<Task> taskList = new ArrayList<Task>();	
		//判断是哪类查询条件
		switch (flag) {
		case "0":
			task.setFinishstatus("0");
			break;
		case "1":
			task.setFinishstatus("1");
			break;
		default:
			task.setFinishstatus(null);
			break;
		}
		
		task.setUserbid(user.getUserid());
		taskList = taskServiceImpl.getAllTask(task);
		model.addAttribute("taskList", taskList);
		
		return "subtasklist";
	}
	
	private int Number = -2;
	//轮询新的工单数目
	@RequestMapping("getTaskNumber")
	@ResponseBody
	public String getTaskNumber(){				
	    //在这里查询最新的工单数目
		Number = Number + 1;
		return Number+"";
	}

}
