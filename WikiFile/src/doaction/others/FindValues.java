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
import java.util.Map.Entry;
import java.util.Set;

import doaction.FileTraverse;
import doaction.Fileread;
import doaction.Filewrite;
import samplewikipage.ObjectsPage;

public class FindValues {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<File> filelist=new ArrayList<File>();
		List<File> filelistcn=new ArrayList<File>();
		File path=new File("FilesIN/sb/objects");
		File pathcn=new File("FilesIN/cnmod/objects");
		HashMap<String,String>map=new HashMap<String,String>();
		HashSet<String>set=new HashSet<String>();
		FileTraverse ft=new FileTraverse();
		ft.Filetraverse(path, filelist);
		ft.Filetraverse(pathcn, filelistcn);
		
		 Fileread fr=new Fileread();
		//	for(int i=0;i<filelistcn.size();i++){//中文mod文件
		       for(int j=0;j<filelist.size();j++){//英文原文文件
		    if((filelist.get(j).getName().substring(filelist.get(j).getName().lastIndexOf(".")+1).equals("object"))){	  
		   		//	  System.out.println(filelist2.get(j).getName()+"==="+filelist.get(i).getName());  
		    	             StringBuilder sb =new StringBuilder();
		                   fr.Readfile(filelist.get(j).getPath());//读取英文原文件
		            //        fr.Readfilecn(filelistcn.get(i).getPath());//读取中文原文文件存入list
	//	                    System.out.println(filelist.get(j).getPath());
		                   if(fr.map.containsKey("colonyTags")){
		                	   map.put(fr.map.get("colonyTags"), "");
		                   
		                   
		                   Iterator it=map.entrySet() .iterator();
		                   while(it.hasNext()){
		                     Entry entry=(Entry)it.next();
		                    String key=entry.getKey().toString();
		           //          String value=entry.getValue().toString();
		                     sb.append( key+"\r\n");
		                   }}
		                    Filewrite fw =new Filewrite();   
		              
		                    fw.Writefile(sb, "FilesOUT/colonyTags.preview.txt");
		             
		                    		   		                    
		    }
	       }
		   //      }
	}

}
