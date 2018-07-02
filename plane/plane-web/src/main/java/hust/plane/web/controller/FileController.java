package hust.plane.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	@RequestMapping("/oneFileImport")
	public String importOneFile(@RequestParam("courseExcel") MultipartFile file)
	{
		String filename=file.getOriginalFilename();
		//得到数据插入
		return "";
	}

}
