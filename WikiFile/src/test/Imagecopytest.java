
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
            in.transferTo(0, in.size(), out);//���д��  
            out.transferFrom(in, 0, in.size());//Ч��ͬ��  
            
            in.close();  out.close();
        } catch (FileNotFoundException e) {  
            System.out.println("�Ҳ����ļ�");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
