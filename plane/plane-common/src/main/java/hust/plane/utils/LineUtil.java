package hust.plane.utils;

import java.util.ArrayList;
import java.util.List;

import hust.plane.utils.pojo.RouteExcel;

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
	//将list转换为数据库数据
	public static String ListToString(List<RouteExcel> readExcellist)
	{
		StringBuffer s=new StringBuffer();
		s.append("LineString(");
		for(int i=0;i<readExcellist.size();i++)
		{
			Double a=readExcellist.get(i).getLongitude();
			Double b=readExcellist.get(i).getLatitude();
			String s1=a+" "+b+",";
			s.append(s1);
			
		}
		s.deleteCharAt(s.length()-1);
		s.append(")");
		return s.toString();
	}
	public static void main(String[] args) {
		String s="LineString(1 1,2 2)";
		String sub=s.substring(11, s.length()-1);
		String slist[]=sub.split(",");
		System.out.println(slist[0].split(" ")[0]);
	}

}
