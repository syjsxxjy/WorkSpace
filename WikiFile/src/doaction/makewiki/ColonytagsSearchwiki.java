package doaction.makewiki;

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

import PropertyClasses.ColonyTags;
import doaction.FileTraverse;
import doaction.Fileread;
import doaction.Filewrite;
import doaction.GetColonytags;
import samplewikipage.ObjectsPage;

public class ColonytagsSearchwiki {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ColonyTags Ctags=new ColonyTags();
		GetColonytags GetC=new GetColonytags();
		Ctags= GetC.Getcolonytags();   //��ȡ��tag��ǩset
		
		List<File> filelist=new ArrayList<File>();
		List<File> filelistcn=new ArrayList<File>();
		File path=new File("FilesIN/sb/objects");
		File pathcn=new File("FilesIN/cnmod/objects");
		
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
		                    ObjectsPage bp =new ObjectsPage();    //����wikiҳ�����
		                    bp.getPage(fr.map, fr.mapcn);
		     }
}
	}}}
	




