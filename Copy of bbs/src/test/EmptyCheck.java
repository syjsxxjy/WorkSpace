package test;
import model.*;

public class EmptyCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\"\"  空字符测试结果="+IsEmpty.Checkisempty(""));
		System.out.println("\"    \"  空格字符测试结果="+IsEmpty.Checkisempty("    "));
		System.out.println("\"    Hello\"  普通字符测试结果="+IsEmpty.Checkisempty("    Hello"));
		System.out.println("\"Hello    \"  普通字符测试结果="+IsEmpty.Checkisempty("Hello    "));
		System.out.println("\",    \"  普通字符测试结果="+IsEmpty.Checkisempty(",    "));

	}

}
