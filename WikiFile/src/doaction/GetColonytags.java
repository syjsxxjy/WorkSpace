package doaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import PropertyClasses.ColonyTags;

public class GetColonytags {
      public  ColonyTags Getcolonytags() throws IOException{
    	  String ex="object";    //������ͬ���͵��ļ���ȡ����key����Ҫ�޸�ex��ֵ
  		List<File> filelist=new ArrayList<File>();
  		List<File> filelistcn=new ArrayList<File>();
  		File path=new File("FilesIN/sb/");
  		File pathcn=new File("FilesIN/cnmod/");
  		StringBuilder sb=new StringBuilder();
  		FileTraverse ft=new FileTraverse();
  		ft.Filetraverse(path, filelist);
  		ft.Filetraverse(pathcn, filelistcn);
  		ColonyTags colonytag=new ColonyTags();
  		Set<String>  set=new HashSet<String>();
  		 Fileread fr=new Fileread();
  			
  		         for(int j=0;j<filelist.size();j++){//Ӣ��ԭ���ļ�
  		     if(filelist.get(j).getName().substring(filelist.get(j).getName().lastIndexOf(".")+1).equals(ex)){	    //fileName.substring(fileName.lastIndexOf(".")+1)
  		   		//	  System.out.println(filelist2.get(j).getName()+"==="+filelist.get(i).getName());  
  		    	 
  		                    fr.Readfile(filelist.get(j).getPath());//��ȡӢ��ԭ�ļ�
  		                    System.out.println(filelist.get(j).getPath());
  		                    if (fr.map.containsKey("colonyTags")){ 
  		                    	List<String> list = new ArrayList();
  		                    	String str=fr.map.get("colonyTags").replace("[", "").replace("]", "");
  		                    	if (str.contains(" ")==true) {   //������ڿո���ָ���ַ���
  		                            String[] strArray = str.split(" ");
  		                            for(String strr:strArray){
  		                            	list.add(strr);
  		                            	System.out.println("mapget="+strr);
  		                            }
  		                    	}else{
  		                    		list.add(str);
  		                    		System.out.println("mapget="+str);
  		                    	}
  		                    	set.addAll(list);
  		                    }
  		     }
  		     }
  	
  	 for( Iterator   it = set.iterator();  it.hasNext(); )  
       {               
  		colonytag.tagset.add(it.next().toString());
           System.out.println("value="+it.next().toString());              
       }   
       //    	Filewrite fw =new Filewrite();   
       //     fw.Writefile(sb, "FilesOUT/Allproperties/"+ex+".txt");
	return colonytag;
      }
}
