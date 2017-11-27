package doaction;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Filewrite {
	
            public  void Writefile(StringBuilder sb,String path) throws IOException{
            	 FileOutputStream writerStream = new FileOutputStream(path, true);  
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
                 
                 bw.write(sb.toString());
                 bw.close();
                 writerStream.close();

            }
}
