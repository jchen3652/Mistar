package stringmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import bot.WebTableAttempt;

public class StringGetter {
	/**
	 * All inclusive method that takes clip board input and writes to file
	 * 
	 * @param cBoardStringInput
	 *            raw input from clip board
	 * @param driver
	 *            WebDriver object name
	 * @param where
	 *            Attempted path of file location
	 */
	public static void finisher(String cBoardStringInput, WebDriver driver, String path) { // this is the main method
		BufferedWriter bw = null;
		FileWriter fw = null;
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String stringWithLabelsRemoved = StringUtils.labelDeleter(cBoardStringInput);

		int iterations = StringUtils.listMaker(stringWithLabelsRemoved).size() / 7;// newl used to be here
		for (int i = 0; i < iterations; i += 1) { // it will run this many times
			String data = StringUtils.whatToWrite(WebTableAttempt.currentId, stringWithLabelsRemoved, i);
			try {
				fw = new FileWriter(file.getAbsoluteFile(), true); // true means append file
				bw = new BufferedWriter(fw);
				bw.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();
					if (fw != null)
						fw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
