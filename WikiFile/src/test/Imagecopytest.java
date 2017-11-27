
package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Imagecopytest {
	public static void main(String[] args) {  
        try {  
            FileChannel in = new FileInputStream("FilesIN/grapple.activeitem").getChannel(), out = new FileOutputStream("FilesOUT/1.txt").getChannel();  
            in.transferTo(0, in.size(), out);//简便写法  
            out.transferFrom(in, 0, in.size());//效果同上  
            
            in.close();  out.close();
        } catch (FileNotFoundException e) {  
            System.out.println("找不到文件");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
