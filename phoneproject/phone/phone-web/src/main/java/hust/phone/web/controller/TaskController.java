package hust.phone.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

		String userid = user.getUserid();		
		String reString = "";
		if(task.getUserbid().equals(userid)) {   //如果是用户在该任务是放飞者
			
			taskServiceImpl.ensureTaskByTask(task,"2");
			reString = "放飞者确认任务成功";
		}
		if(task.getUsercid().equals(userid)) {    //如果是用户在该任务是接收者
			
			taskServiceImpl.ensureTaskByTask(task,"3");
			//如果是接收者确认的话，则在exetask表中加入一条执行任务数据
			reString = "接收者确认任务成功";				
		}
		return JsonView.render(1, reString);
	}
	
	
	//退回任务，如果任一角色退回任务的话那该任务直接完成，并且完成结果为失败
	@RequestMapping(value="/rollbackTask",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String rollbackTask(Task task,HttpServletRequest request) {
		
		User user = PhoneUtils.getLoginUser(request);
		
		String userid = user.getUserid();
		//下面开始退回任务	
		String reString = "";
		
		taskServiceImpl.rollbackTaskByTask(task,"0");
		
		if(task.getUserbid().equals(userid)) {   //如果是用户在该任务是放飞者							
				reString = "放飞者退回任务成功";
		}
		if(task.getUsercid().equals(userid)) {    //如果是用户在该任务是接收者
			    reString = "接收者退回任务成功";				
		}
		
		return JsonView.render(1, reString);
	}
	
	// 根据完成状态查询所有的工作单
	@RequestMapping("/taskList")
	public String taskList(String flag, Model model,HttpServletRequest request) {
		
		Task task = new Task();	
		User user = PhoneUtils.getLoginUser(request);
		
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
		//设置bid，cid都为对应的
		task.setUserbid(user.getUserid());
		task.setUsercid(user.getUserid());
		
		taskList = taskServiceImpl.selectAllByTask(task);
		
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

	//跳转到无人机操纵界面，同时选取第一个正在执行的任务显示在界面上
	@RequestMapping("/toPlane")
	public String toPlane(HttpServletRequest request)
	{
		User user = PhoneUtils.getLoginUser(request);	
		List<Task> taskList = new ArrayList<Task>();	
		//taskList = taskServiceImpl.findExeTask();
		//Exetask exetask = exetaskServiceImpl.findExetaskByTask(taskList.get(0));  //把第一个放入到界面
		
		return "fight";
	}
}
