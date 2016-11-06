package Controller;

import Model.Checklist;
import View.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FilenameUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Control {
	public View view = null;	
	
	public Control()
	{
		view = new View(this);
	}
	
	/**
	 * Show GUI
	 */
	public void start()
	{
		view.drawWindow(); 
	}

	public static boolean getAndCheckFileName(File selectedFile){
	    String fileName = selectedFile.getName();
	    System.out.println("Selected file: " + selectedFile.getAbsolutePath() + 
	    		" and its extension is: " + FilenameUtils.getExtension(fileName));
	    if((FilenameUtils.isExtension(fileName,"xls")) || (FilenameUtils.isExtension(fileName,"xlsx")))
	    	return true;
	    else
	    	return false;
	}
	
	public static void writeToXml(Checklist c) throws IOException{

		try {
			//default file path, rewrite this path to choose where to create xml file
			String file_path = "example.xml";
			FileOutputStream file = new FileOutputStream(new File(file_path));
			JAXBContext jaxbContext = JAXBContext.newInstance(Checklist.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(c, file);
				jaxbMarshaller.marshal(c, System.out);


		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void parseExcel(File file) throws FileNotFoundException,BiffException,IOException {

		System.out.println("File to parse: " + file.getAbsolutePath() +
				" and its extension is: " + FilenameUtils.getExtension(file.getName()));

		int count_function = 0;
		int count_checksteps = 0;
		String[] function = new String[20];
		String[] checkstep = new String[20];
		Map<String, String> checksteps = new HashMap<String, String>();
		String status = "";
		String checklist_item = "";
		Checklist checklist;
		Workbook w;
		w = Workbook.getWorkbook(file);
		// Get the first sheet
		Sheet sheet = w.getSheet(0);

		for (int j = 0; j < sheet.getColumns(); j++) {
			for (int i = 0; i < sheet.getRows(); i++) {
				Cell cell = sheet.getCell(j, i);

				if (cell.getContents().equals("Checklist Item")) {
					System.out.println(sheet.getCell(j+1,i).getContents());
					checklist_item = sheet.getCell(j+1,i).getContents();
				}

				if (cell.getContents().equals("Function")) {
					System.out.println(sheet.getCell(j+1,i).getContents());
					function[count_function] = sheet.getCell(j+1,i).getContents();

					if(sheet.getCell(j+2,i).getContents().equals("Check Steps")) {
						System.out.println(sheet.getCell(j+3,i).getContents());
						checkstep[count_checksteps] = sheet.getCell(j+3,i).getContents();
						checksteps.put(function[count_function],checkstep[count_checksteps]);
						count_checksteps++;

					}
					count_function ++;
				}

				if (cell.getContents().equals("Status")) {
					System.out.println(sheet.getCell(j+1,i).getContents());
					status = sheet.getCell(j+1,i).getContents();
				}

			}

		}

		checklist = new Checklist(checklist_item,function,checksteps,status);

		//close excel file
		w.close();
		//send checklist elements to be written as xml file
		writeToXml(checklist);

	}

	
}
