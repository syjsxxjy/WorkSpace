package doaction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTraverse {//遍历置顶文件路径，将文件保存在list数列中
	 

	
	public void Filetraverse(File path, List<File> filelist){

	        if (!path.exists()){
	            System.out.println("文件名称不存在!");
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
