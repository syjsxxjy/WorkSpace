package test;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import doaction.Fileread;
import doaction.Filewrite;
import samplewikipage.BiomesPage;
import samplewikipage.ItemsPage;

public class Filelisttest {
          //遍历所有文件，存入file list中
	  public static void listDirectory(File path, List<File> myfile){
	        if (!path.exists()){
	            System.out.println("文件名称不存在!");
	        }
	        else
	        {
	            if (path.isFile()){
	                myfile.add(path);
	            } else{
	                File[] files = path.listFiles();
	                for (int i = 0; i < files.length; i++  ){
	                    listDirectory(files[i], myfile);
	                }
	            }
	        }
	    }
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<File> filelist=new ArrayList<File>();
		List<File> filelist2=new ArrayList<File>();
		File path=new File("FilesIN/cnmod/items");
		File path2=new File("FilesIN/sb/items");
		List<String> filelistcn=new ArrayList<>();
		List<String> filelistsb=new ArrayList<>();
		
		
		
		listDirectory(path, filelist);
		listDirectory(path2, filelist2);
		
//		for(int i=0;i<filelist.size();i++){
//			System.out.println( filelist.get(i).getName());
//		}
		
	//	System.out.println( filelist.get(0).getName());
	//	System.out.println( ("dripblood1.object"+".patch").equals(filelist.get(0).getName()));
		     Fileread fr=new Fileread();
			for(int i=0;i<filelist.size();i++){//中文mod文件
		         for(int j=0;j<filelist2.size();j++){//英文原文文件
		     if((filelist2.get(j).getName()      +".patch").equals(filelist.get(i).getName())){	  
		   		//	  System.out.println(filelist2.get(j).getName()+"==="+filelist.get(i).getName());  
		    	 
		                    fr.Readfile(filelist2.get(j).getPath());//读取英文原文件
		                    fr.Readfilecn(filelist.get(i).getPath());//读取中文原文文件存入list
		                    System.out.println(filelist2.get(j).getPath());
		                    ItemsPage bp =new ItemsPage();
		                    bp.getPage(fr.map, fr.mapcn);
		                    Filewrite fw =new Filewrite();   
		                    bp.originaltxt=fr.Readfile(filelist2.get(j).getPath());
		                    String wikifilename=URLEncoder.encode(bp.cnname, "UTF-8") +"_"+bp.itemid+".txt";
		                    fw.Writefile(bp.getPage(fr.map,fr.mapcn), "FilesOUT/items/"+wikifilename);
		                    
		          
		   			
		         }else{
		        	/* Fileread fr=new Fileread();
		        	 fr.Readfile(filelist2.get(j).getPath());
		        	 System.out.println(fr.Readfile(filelist2.get(j).getPath()));*/
		         }
		  }
		         
		}
		       
	}    
  
}
