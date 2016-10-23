import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FilenameUtils;

public class Model {
	public static void parseExcel(File file) throws FileNotFoundException{
	    System.out.println("File to parse: " + file.getAbsolutePath() + 
	    		" and its extension is: " + FilenameUtils.getExtension(file.getName()));
		
	}

}
