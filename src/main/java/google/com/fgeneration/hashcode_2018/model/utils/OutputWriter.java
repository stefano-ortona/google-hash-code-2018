package google.com.fgeneration.hashcode_2018.model.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class OutputWriter {

	public static void writeToFile(String outputFileName) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");

//		writer.println("x");
		
		writer.close();
	}

}
