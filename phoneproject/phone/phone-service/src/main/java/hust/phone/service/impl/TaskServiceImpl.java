package hust.phone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.phone.mapper.mapper.TaskMapper;
import hust.phone.mapper.pojo.Task;
import hust.phone.mapper.pojo.TaskExample;
import hust.phone.mapper.pojo.TaskExample.Criteria;
import hust.phone.service.interFace.taskService;

@Service
public class TaskServiceImpl implements taskService {

	@Autowired
	private TaskMapper taskMapper;
	
	
	@Override
	public List<Task> selectAllByTask(Task task) {
		
		List<Task> taskList = taskMapper.selectByTask(task);
		return taskList;
	}
	
	@Override
	public Task getTaskByTask(Task task) {
		
		return taskMapper.selectByPrimaryKey(task.getTaskid());	
		
	}

	//根据task设置数据库中task的任务状态为已完成，退回状态,同时终止任务
	@Override
	public boolean rollbackTaskByTask(Task task,String status) {
		
		Task task2 = getTaskByTask(task);
		task2.setStatus(status);
		task2.setFinishstatus("1");
		
		TaskExample example=new TaskExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTaskidEqualTo(task2.getTaskid());
		
		taskMapper.updateByExample(task2, example);
		
		return true;
	}

	//根据task更改任务状态，状态标识为status,可复用
	@Override
	public boolean setStatusTaskByTask(Task task, String status) {
		
		Task task2 = getTaskByTask(task);
		
		task2.setStatus(status);       //改变状态
		
		TaskExample example=new TaskExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTaskidEqualTo(task2.getTaskid());
		
		taskMapper.updateByExample(task2, example);
			
		return true;
	}

	@Override
	public Task selectOneExeTask(Task task) {
		
		List<Task> taskList = taskMapper.selectByTaskOptions(task);
		if(taskList.size()>0)
			return taskList.get(0);
		else
			return null;
	}

	@Override
	public String getTaskStatus(Task task) {
		// TODO Auto-generated method stub
		
		return taskMapper.getTaskStatus(task);
	}

	
	
	
	/*TaskExample example=new TaskExample();
	Criteria createCriteria = example.createCriteria();
	if(task.getUseraid()!=null)
	{
		createCriteria.andUserbidEqualTo(task.getUseraid());
	}
	if(task.getUsercid()!=null)
	{
		createCriteria.andUsercidEqualTo(task.getUsercid());		
	}
	if(task.getStatus()!=null)
	{
		createCriteria.andStatusEqualTo(task.getStatus());
	}
	if(task.getFinishstatus()!=null)
	{
		createCriteria.andFinishstatusEqualTo(task.getFinishstatus());
	}
	List<Task> taskList = taskMapper.selectByExample(example);
	return taskList;*/

}
