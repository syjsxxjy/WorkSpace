package doaction.makewiki;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import doaction.FileTraverse;
import doaction.Fileread;
import doaction.Filewrite;
import doaction.ImageCopy;
import samplewikipage.ItemsPage;
import samplewikipage.ObjectsPage;
import samplewikipage.PlantsPage;
import samplewikipage.TechPage;
import samplewikipage.TilesPage;

public class FilesCopy {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String sbpath ="tiles";   //------------------设定原游戏文件路径，作为文件夹分类使用
		List<File> filelist=new ArrayList<File>();
		List<File> filelistcn=new ArrayList<File>();
		File path=new File("FilesIN/sb/"+sbpath);   //设定文件路径
		File pathcn=new File("FilesIN/cnmod/"+sbpath);   //设定文件路径
		
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
		                    TilesPage bp =new TilesPage();    //创建wiki页面对象
		                    bp.getPage(fr.map, fr.mapcn);
		                    ImageCopy ic =new ImageCopy();
		                    if(!bp.inventoryIconpath.equals(" ")){
		                    	System.out.println(filelist.get(j).getParentFile()+"\\"+bp.inventoryIconpath.replace("/", "\\"));
		                    	File f = new File("FilesOUT/"+sbpath+"\\"+bp.itemid);   //判断文件路径是否存在，不存在就创建
		                    	if(!f.exists()){
		                    		f.mkdirs();
		                    	}
		                    	ic.copy(filelist.get(j).getParentFile()+"\\"+bp.inventoryIconpath.replace(" ", ""), "FilesOUT/"+sbpath+"\\"+
		                    	bp.itemid+"\\"+bp.wikiinventoryIconpath.replace(" ", ""));
		                    	
		                   
		                    }
		                    		   		                    
		     }
		         }
		         }
		
	}
}
