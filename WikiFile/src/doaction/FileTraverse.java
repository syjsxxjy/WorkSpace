package doaction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTraverse {//�����ö��ļ�·�������ļ�������list������
	 

	
	public void Filetraverse(File path, List<File> filelist){

	        if (!path.exists()){
	            System.out.println("�ļ����Ʋ�����!");
	        }
	        else
	        {
	            if (path.isFile()){
	            	filelist.add(path);
	            } else{
	                File[] files = path.listFiles();
	                for (int i = 0; i < files.length; i++  ){
	                    Filetraverse(files[i], filelist);
	                }
	            }
	        }
	    }
}
