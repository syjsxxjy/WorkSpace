package doaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ImageCopy {
	public void copy(String pathin , String pathout) {
		File s=new File(pathin);
		File t =new File(pathout);
		
		FileInputStream fi = null;

		FileOutputStream fo = null;

		FileChannel in = null;

		FileChannel out = null;

		try {

			fi = new FileInputStream(s);

			fo = new FileOutputStream(t);

			in = fi.getChannel();// �õ���Ӧ���ļ�ͨ��

			out = fo.getChannel();// �õ���Ӧ���ļ�ͨ��

			in.transferTo(0, in.size(), out);  // ��������ͨ�������Ҵ�inͨ����ȡ��Ȼ��д��outͨ��
			out.transferFrom(in, 0, in.size());

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				fi.close();

				in.close();

				fo.close();

				out.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
	}
}
