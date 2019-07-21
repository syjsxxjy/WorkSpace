package doaction.others;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import doaction.FileTraverse;
import doaction.Fileread;
import doaction.Filewrite;
import samplewikipage.ObjectsPage;

public class GetAllProperty {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String ex="matmod";    //根绝不同类型的文件获取属性key，需要修改ex的值
		List<File> filelist=new ArrayList<File>();
		List<File> filelistcn=new ArrayList<File>();
		File path=new File("FilesIN/sb/");
		File pathcn=new File("FilesIN/cnmod/");
		StringBuilder sb=new StringBuilder();
		FileTraverse ft=new FileTraverse();
		ft.Filetraverse(path, filelist);
		ft.Filetraverse(pathcn, filelistcn);
		Map<String,String>map=new HashMap<String,String>();
		
		 Fileread fr=new Fileread();
			
		         for(int j=0;j<filelist.size();j++){//英文原文文件
		     if(filelist.get(j).getName().substring(filelist.get(j).getName().lastIndexOf(".")+1).equals(ex)){	    //fileName.substring(fileName.lastIndexOf(".")+1)
		   		//	  System.out.println(filelist2.get(j).getName()+"==="+filelist.get(i).getName());  
		    	 
		                    fr.Readfile(filelist.get(j).getPath());//读取英文原文件
		                    System.out.println(filelist.get(j).getPath());
		                    Set<Map.Entry<String, String>> entryseSet=fr.map.entrySet();  
		                    for (Map.Entry<String, String> entry:entryseSet) {  
		                     System.out.println(entry.getKey());  
		                     map.put(entry.getKey()," ");
		                    }        
		                   
		     }
           	
		         
	}
			
			Set<Map.Entry<String, String>> entryseSet=map.entrySet();  
			  for (Map.Entry<String, String> entry:entryseSet) {  
			   System.out.println("map------"+entry.getKey());  
			   sb.append(entry.getKey()+"      "+entry.getValue()+"\r\n");
			  }  
			
         	Filewrite fw =new Filewrite();   
          fw.Writefile(sb, "FilesOUT/Allproperties/"+ex+".txt");
			
}}



