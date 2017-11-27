import java.net.InetAddress;
import java.net.UnknownHostException;

public class Start {
      public static void main(String[] args) throws UnknownHostException {
    	  InetAddress addr = InetAddress.getLocalHost();
    	  String ip = addr.getHostAddress().toString();//获得本机IP
    	  
    	  System.out.println("IP地址："+ip);
    	  StringBuffer s1=new StringBuffer(10);s1.append("1234");
    	  System.out.println("s1.lengh() "+s1.length());
    	  System.out.println("s1.capacity() "+s1.capacity());
    	  System.out.println(s1);
    	  s1.append("23423423423423423");
    	  System.out.println(s1);
	}
}
