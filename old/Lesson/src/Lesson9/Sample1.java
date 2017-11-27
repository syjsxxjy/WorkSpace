package Lesson9;

import java.io.File;

public class Sample1 {
 public static void main(String[] args) {
	if(args.length!=1){
		System.out.println("•—•È•·©`•ø§Œ ˝§¨ﬂ`§§§ﬁ§π°£");
		System.exit(1);
	}
	try {
		File fl=new File(args[0]);
		System.out.println("•’•°•§•Î√˚§œ"+fl.getName()+"§«§π");
		System.out.println("Ω~åù•—•π§œ"+fl.getAbsolutePath()+"§«§π");
		System.out.println("•µ•§•∫§œ"+fl.length()+"•–•§•»§«§π");
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}
