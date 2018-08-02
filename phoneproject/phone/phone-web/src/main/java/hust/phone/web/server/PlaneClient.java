package hust.phone.web.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Parser;
import com.MAVLink.common.msg_global_position_int;
import com.MAVLink.common.msg_heartbeat;



//模拟无人机，一直发数据
public class PlaneClient {
	InputStream in = null;
	 Socket socket = null;
	 public void socketStart() {
			  try {
				  //socket = new Socket("192.168.110.8", 8888);
				  socket = new Socket("192.168.1.123", 18888);
			     //发送消息
	            //采用打包的方式
	            OutputStream out=socket.getOutputStream();
		        //准备数据，先模拟只发5次
	            msg_global_position_int msg =new msg_global_position_int();
	            msg.lon=1101724;
	            msg.lat=251714;
	            msg.alt=4444;
				
	  			//发送数据包
	  			MAVLinkPacket pack = msg.pack();
	  			pack.sysid =2;
	  			
	  			//更改数据长度
	  			//pack.len=pack.trimPayload();
	  			byte[] encodePacket = pack.encodePacket();
	  			int i=20;
	  			while(i>0)
	  			{
		  			out.write(encodePacket);
		  			System.out.println("打包后: "+Arrays.toString(encodePacket));
		  			i--;
		  			Thread.sleep(1000);
	  			}
			 
		 }catch (Exception e) {
			// TODO: handle exception
		 } 
	 }
	 
   public static void main(String[] args) 
   {
        new PlaneClient().socketStart();
    }
}
