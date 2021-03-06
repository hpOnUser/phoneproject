package hust.phone.utils;

import java.util.ArrayList;
import java.util.List;

public class LineUtil {
	//转换为ArrayList
	public static ArrayList<ArrayList<Double>> stringLineToList(String s)
	{
		//s="LineString(1 1,2 2)"
		ArrayList<ArrayList<Double>> list= new ArrayList<ArrayList<Double>>();
		String sub=s.substring(11, s.length()-1);
		String slist[]=sub.split(",");
		for(int i=0;i<slist.length;i++)
		{
			ArrayList<Double> point=new ArrayList<Double>();
			point.add(Double.parseDouble(slist[i].split(" ")[0]));
			point.add(Double.parseDouble(slist[i].split(" ")[1]));
			list.add(point);
		}
		return list;
	}
	
	//将高度列表字符串转化为数组
	public static ArrayList<Double> stringpointToList(String s)
	{
		//s="LineString(1,1,2,2)"
		ArrayList<Double> list= new ArrayList<Double>();
		String sub=s.substring(11, s.length()-1);
		String slist[]=sub.split(",");
		for(int i=0;i<slist.length;i++)
		{
			list.add(Double.parseDouble(slist[i]));
		}
		return list;
	}
	   /**
		* 获取路径的开始点
	    * @author rfYang  
	    * @date 2018/6/29 11:06  
	    * @param [path]  
	    * @return java.lang.String  
	    */  
	    public static String getFirstPoint(String path) {
	        path = path.substring(1, path.length() - 1);
	        String firstPoint = path.substring(0, path.indexOf(']')+1);
	        return firstPoint;
	    }
	   /**
		* 路径转换为数组
	    * @author rfYang  
	    * @date 2018/6/29 11:18
	    * @param [path]
	    * @return java.util.List<java.lang.String>  
	    */  
	    public static List<String> pathToArray(String path){
	        List<String> pathArray = new ArrayList<>();
	        pathArray.add(getFirstPoint(path));
	        String[] result = path.split("\\]");
	        for(int i=1;i<result.length;i++){
	            pathArray.add(result[i].substring(1)+"]");
	        }
	        return pathArray;
	    }

	    public static void main(String[] args) {
	        String s = "LineString(1 1,2 2)";
	        String sub = s.substring(11, s.length() - 1);
	        String slist[] = sub.split(",");
	        System.out.println(slist[0].split(" ")[0]);
	    }

//	public static void main(String[] args) {
//		String s="LineString(1 1,2 2)";
//		String sub=s.substring(11, s.length()-1);
//		String slist[]=sub.split(",");
//		System.out.println(slist[0].split(" ")[0]);
//	}

}
