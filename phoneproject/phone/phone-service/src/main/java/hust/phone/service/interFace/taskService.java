package hust.phone.service.interFace;

import java.util.List;

import hust.phone.mapper.pojo.Task;

public interface taskService {
	
	List<Task> selectAllByTask(Task task);

	Task getTaskByTask(Task task);

	boolean rollbackTaskByTask(Task task,String status);

	boolean setStatusTaskByTask(Task task, String string);

	Task selectOneExeTask(Task task);
	
	String getTaskStatus(Task task);

}
