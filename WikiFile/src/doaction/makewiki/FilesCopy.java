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
		String sbpath ="tiles";   //------------------�趨ԭ��Ϸ�ļ�·������Ϊ�ļ��з���ʹ��
		List<File> filelist=new ArrayList<File>();
		List<File> filelistcn=new ArrayList<File>();
		File path=new File("FilesIN/sb/"+sbpath);   //�趨�ļ�·��
		File pathcn=new File("FilesIN/cnmod/"+sbpath);   //�趨�ļ�·��
		
		FileTraverse ft=new FileTraverse();
		ft.Filetraverse(path, filelist);
		ft.Filetraverse(pathcn, filelistcn);
		
		 Fileread fr=new Fileread();
			for(int i=0;i<filelistcn.size();i++){//����mod�ļ�
		         for(int j=0;j<filelist.size();j++){//Ӣ��ԭ���ļ�
		     if((filelist.get(j).getName()      +".patch").equals(filelistcn.get(i).getName())){	  
		   		//	  System.out.println(filelist2.get(j).getName()+"==="+filelist.get(i).getName());  
		    	 
		                    fr.Readfile(filelist.get(j).getPath());//��ȡӢ��ԭ�ļ�
		                    fr.Readfilecn(filelistcn.get(i).getPath());//��ȡ����ԭ���ļ�����list
		                    System.out.println(filelist.get(j).getPath());
		                    TilesPage bp =new TilesPage();    //����wikiҳ�����
		                    bp.getPage(fr.map, fr.mapcn);
		                    ImageCopy ic =new ImageCopy();
		                    if(!bp.inventoryIconpath.equals(" ")){
		                    	System.out.println(filelist.get(j).getParentFile()+"\\"+bp.inventoryIconpath.replace("/", "\\"));
		                    	File f = new File("FilesOUT/"+sbpath+"\\"+bp.itemid);   //�ж��ļ�·���Ƿ���ڣ������ھʹ���
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
