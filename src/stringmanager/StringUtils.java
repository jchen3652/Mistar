package stringmanager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;

public class StringUtils {
	public static boolean textIdShowsNotStudent(String str) { //if no student is found, returns true
		String container = str; //This is the string that is found
		String content = "Student information"; //This is the desired message in there
		return container.contains(content);	
	}
	
	public static String frontRemover(String x) { //This method is used as part of the thing to clean the front of the string
		int removed = x.lastIndexOf("Sign Out") + 14; 
		return x.substring((removed), x.length());
	}

	/**
	 * Replaces tab separated list with real list
	 * 
	 * @param str
	 *            Name of String to clean
	 * @return Returns proper list
	 */
	public static List<String> listMaker(String str) {
		List<String> list = Arrays.asList(str.split("	"));
		list = list.stream().map(String::trim).collect(Collectors.toList());
		return list;
	}
	
	/**
	   * Finds what precisely should be written to the text file
	   * @param ID What PIN should be associated with the user
	   * @param tabSeparated What is the raw with the labels deleted
	   * @param iter What is the iteration that it is on
	   * @return Returns what precisely should be written to text file 
	   */	
	public static String whatToWrite(int ID, String tabSeparated, int iter) {
		return (Integer.toString(ID) + "," + Joiner.on(",").join(StringUtils.listMaker(tabSeparated).subList(0+7*iter, 7+7*iter))+ System.lineSeparator());
	}
	
	/**
	   * Deletes the useless labels of a successful login screen
	   * @param str Name of String to clean
	   * @return Returs a cleaned string separated by tabs
	   */
	public static String labelDeleter(String string) {
		string = string.replace("Please Make a Selection" , "");         //cleans the stuff
		string = string.replace("Close","");
		string = string.replace("Print Info", "");
		string = string.replace("Click on a student to view their information.", "");
		string = string.replace("Student Name	Grade	School Name	School Year	Birth Date	Advisor	Counselor", "");
		string = string.replace(", ","-");
		string = string.substring(0, string.length() - 13);
		string = StringUtils.frontRemover(string);
		string = string.replaceAll("(?m)^[ \t]*\r?\n", "");
		return string;
	}
}	
	
	