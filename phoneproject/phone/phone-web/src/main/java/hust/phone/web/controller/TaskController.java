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

import hust.phone.mapper.pojo.PlanePath;
import hust.phone.mapper.pojo.Task;
import hust.phone.mapper.pojo.User;
import hust.phone.service.interFace.PlanePathService;
import hust.phone.service.interFace.UserService;
import hust.phone.service.interFace.planeService;
import hust.phone.service.interFace.taskService;
import hust.phone.utils.JsonUtils;
import hust.phone.utils.pojo.JsonView;
import hust.phone.utils.pojo.PhoneUtils;
import hust.phone.web.controller.vo.PlanePathVo;


@Controller
public class TaskController {

	@Autowired
	private taskService taskServiceImpl;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlanePathService planePathService;
	
	@Autowired
	private planeService planeServiceImpl;

	private int Number = 0;    //未完成工单数目
		
	
	// 工作单跳转
	@RequestMapping("/toTask")
	public String toTaskList() {
		
		return "task";
	}

	@RequestMapping("/myindex")
	public String index(HttpServletRequest request,Model model)
	{
		Number = userService.getTaskNumByUser(PhoneUtils.getLoginUser(request));   //进入主页面的时候初始化
		model.addAttribute("tasknum", Number);
		return "home";
	}
	// 确认任务
	// 如果用户角色是放飞者，那么修改该任务的状态为 2
	// 如果用户是接收者，那么修改该任务的状态为 3，
	@RequestMapping(value = "/ensureTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ensureTask(Task task, HttpServletRequest request) {

		User user = PhoneUtils.getLoginUser(request);

		String userid = user.getUserid();
		String reString = "";
		if (task.getUserbid().equals(userid)) { // 如果是用户在该任务是放飞者

			taskServiceImpl.setStatusTaskByTask(task, "2");
			reString = "放飞员确认任务成功";
		}
		if (task.getUsercid().equals(userid)) { // 如果是用户在该任务是接收者

			taskServiceImpl.setStatusTaskByTask(task, "3");
			reString = "接收员确认任务成功";
		}
		return JsonView.render(1, reString);
	}

	// 退回任务，如果任一角色退回任务的话那该任务直接完成，并且完成结果为失败
	@RequestMapping(value = "/rollbackTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String rollbackTask(Task task, HttpServletRequest request) {

		User user = PhoneUtils.getLoginUser(request);

		String userid = user.getUserid();
		// 下面开始退回任务
		String reString = "";

		taskServiceImpl.rollbackTaskByTask(task, "0");

		if (task.getUserbid().equals(userid)) { // 如果是用户在该任务是放飞者
			reString = "放飞者退回任务成功";
		}
		if (task.getUsercid().equals(userid)) { // 如果是用户在该任务是接收者
			reString = "接收者退回任务成功";
		}

		return JsonView.render(1, reString);
	}

	// 根据完成状态查询所有的工作单
	@RequestMapping("/taskList")
	public String taskList(String flag, Model model, HttpServletRequest request) {

		Task task = new Task();
		User user = PhoneUtils.getLoginUser(request);
		String userid = user.getUserid();

		List<Task> taskList = new ArrayList<Task>();
		// 判断是哪类查询条件
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
		// 设置bid，cid都为对应的
		task.setUserbid(userid);
		task.setUsercid(userid);

		taskList = taskServiceImpl.selectAllByTask(task);
		for (int i = 0; i < taskList.size(); i++) {
			// 设置 task角色
			Task taskitem = taskList.get(i);
			String role = userid.equals(taskitem.getUserbid()) ? "1" : "2";
			taskitem.setRole(role);
		}

		model.addAttribute("taskList", taskList);

		return "subtasklist";
	}

	

	// 轮询新的工单数目
	@RequestMapping(value = "getTaskNumber", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getTaskNumber(HttpServletRequest request) {
		// 在这里查询最新的工单数目
		User user = PhoneUtils.getLoginUser(request);
		int newNum = 0;
		while(true){
			 
			    newNum = userService.getTaskNumByUser(user);
		        //数据发生改变 将数据响应客户端
		        if(newNum != Number){
		        	break;	           
		        }else{
		            //没有新的数据 保持住连接
		            try {
		                Thread.sleep(2000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		}
		Number = newNum;
		return newNum+"";
	}

	// 跳转到无人机操纵界面
	@RequestMapping("/toPlane")
	public String toPlane(Task task, HttpServletRequest request, Model model) {

		User user = PhoneUtils.getLoginUser(request);
		Task task2 = taskServiceImpl.getTaskByTask(task);
		String role = user.getUserid().equals(task2.getUserbid()) ? "1" : "2";
		task2.setRole(role);

		
		PlanePath planePath = new PlanePath();
		planePath.setPlanepathid(task2.getPlanepathid());
		PlanePath planePath2 = planePathService.selectByPlanepathId(planePath);

		PlanePathVo planePathVo = new PlanePathVo(planePath2);

		model.addAttribute("planepath", JsonUtils.objectToJson(planePathVo));
		model.addAttribute("task", task2);

		if(role.equals("1")) {
			return "fight";
		}else {
			return "fight1";
		}
		
	}

	@RequestMapping(value = "/exeTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String exeTask(Task task) {

		taskServiceImpl.setStatusTaskByTask(task, "4");
		
		//测试****把任务的状态设为自检中
		taskServiceImpl.setStatusTaskByTask(task, "5");
		
		return JsonView.render(1, "任务开始执行！");

	}

	
	//此处为首页无人机跳转页面的控制方法
	@RequestMapping("/toPlaneControl")
	public String toPlaneControl(HttpServletRequest request, Model model) {

		Task task = new Task();
		User user = PhoneUtils.getLoginUser(request);
		String userid = user.getUserid();

		task.setFinishstatus("0");
		task.setUserbid(userid);
		task.setUsercid(userid);

		Task task2 = taskServiceImpl.selectOneExeTask(task); // 查找一个正在执行的任务
		if (task2 == null) {
			model.addAttribute("tip", "您暂无正在执行的任务！");
			return "fight";
		}
		String role = userid.equals(task2.getUserbid()) ? "1" : "2";
		task2.setRole(role);

		PlanePath planePath = new PlanePath();
		planePath.setPlanepathid(task2.getPlanepathid());
		PlanePath planePath2 = planePathService.selectByPlanepathId(planePath);
		PlanePathVo planePathVo = new PlanePathVo(planePath2);

		model.addAttribute("planepath", JsonUtils.objectToJson(planePathVo));
		model.addAttribute("task", task2);

		if(role.equals("1")) {
			return "fight";
		}else {
			return "fight1";
		}
		

	}

	@RequestMapping(value = "/emergencyload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String emergencyload(Task task) {

		//测试****把任务状态设为故障
		taskServiceImpl.setStatusTaskByTask(task, "-1");
		//降落指令
		planeServiceImpl.planeLand(task.getPlaneid());
		return JsonView.render(1, "执行紧急降落成功！");
	}

	@RequestMapping(value = "/emergencyback", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String emergencyback(Task task) {
		
		//测试****把任务状态设为故障
		taskServiceImpl.setStatusTaskByTask(task, "-1");
		//返航指令
		planeServiceImpl.planeReturn(task.getPlaneid());
		return JsonView.render(1, "执行紧急返航成功！");

	}

	@RequestMapping(value = "/reportNotconnet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String reportNotconnet(Task task) {

		//测试****把任务状态设为故障
		taskServiceImpl.setStatusTaskByTask(task, "-1");
		return JsonView.render(1, "报告失联成功！");

	}

	@RequestMapping(value = "/checkself", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String checkself(Task task) {

		//测试****把任务状态设为自检成功，然后再设为待放飞
		taskServiceImpl.setStatusTaskByTask(task, "7");
		planeServiceImpl.checkConnect(task.getPlaneid());
		return JsonView.render(1, "无人机连接成功，等待放飞确认。");

	}
	
	//无人机起飞
	@RequestMapping(value = "/takeoff", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String takeoff(Task task) {

		//测试****把任务状态设为飞行中
		String oldStatus = taskServiceImpl.getTaskStatus(task);
		if(oldStatus.equals("7")) {
			taskServiceImpl.setStatusTaskByTask(task, "8");
			//放飞指令
			planeServiceImpl.takeoff(task.getPlaneid());
			return JsonView.render(1, "无人机起飞！");
		}else {
			return JsonView.render(0, "任务管理员未确认，不可放飞！");
		}
	}
	
	//无人机实时位置
	@RequestMapping(value = "/showPosition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String showPosition(Task task) {

		//测试****把任务状态设为飞行中
		String oldStatus = taskServiceImpl.getTaskStatus(task);
		if(oldStatus.equals("8")) {
			//实时位置指令
			planeServiceImpl.showData(task.getPlaneid());
			return JsonView.render(1, "飞机已经断开连接");
		}else {
			return JsonView.render(0, "无人机未起飞，不可放飞！");
		}
	}
	
	@RequestMapping(value = "/reportFinish", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String reportFinish(Task task) {

		String oldStatus = taskServiceImpl.getTaskStatus(task);
		if(oldStatus.equals("8")) {
			taskServiceImpl.setStatusTaskByTask(task, "9");
			return JsonView.render(1, "飞行任务完成！");
		}else {
			return JsonView.render(1, "无人机未正常飞行，任务无法完成");
		}
		
	}
	
}
