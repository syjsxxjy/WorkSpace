package doaction.makewiki;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import doaction.FileTraverse;
import doaction.Fileread;
import doaction.Filewrite;
import samplewikipage.PlantsPage;
import samplewikipage.TechPage;

public class TechWiki {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<File> filelist=new ArrayList<File>();
		List<File> filelistcn=new ArrayList<File>();
		File path=new File("FilesIN/sb/tech");
		File pathcn=new File("FilesIN/cnmod/tech");
		
		FileTraverse ft=new FileTraverse();
		ft.Filetraverse(path, filelist);
		ft.Filetraverse(pathcn, filelistcn);
		
		 Fileread fr=new Fileread();
			for(int i=0;i<filelistcn.size();i++){//中文mod文件
		         for(int j=0;j<filelist.size();j++){//英文原文文件
		     if((filelist.get(j).getName()      +".patch").equals(filelistcn.get(i).getName())){	  
		   		//	  System.out.println(filelist2.get(j).getName()+"==="+filelist.get(i).getName());  
		    	 
		                    fr.Readfile(filelist.get(j).getPath());//读取英文原文件
		                    fr.Readfilecn(filelistcn.get(i).getPath());//读取中文原文文件存入list
		                    System.out.println(filelist.get(j).getPath());
		                    TechPage bp =new TechPage();    //创建wiki页面对象
		                    bp.getPage(fr.map, fr.mapcn);
		                    Filewrite fw =new Filewrite();   
		                    bp.originaltxt=fr.Readfile(filelist.get(j).getPath());
		                    String wikifilename=URLEncoder.encode(bp.cnname.toLowerCase()+"_"+bp.itemid.toLowerCase()+".txt", "UTF-8")
		                    		.replace("+", "");
		                    fw.Writefile(bp.getPage(fr.map,fr.mapcn), "FilesOUT/tech/"+wikifilename);
		             
		                    		   		                    
		     }
		         }
		         }
	}
}
