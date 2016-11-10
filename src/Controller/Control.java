package Controller;

import Model.Checklist;
import View.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public Control() {
        view = new View(this);
    }

    /**
     * Show GUI
     */
    public void start() {
        view.drawWindow();
    }

    public static boolean getAndCheckFileName(File selectedFile) {
        if (selectedFile == null) {
            System.out.println("null input to getAndCheckFileName, don't try to parse.");
            return false;
        }
        String fileName = selectedFile.getName();
        System.out.println("Selected file: " + selectedFile.getAbsolutePath() +
                " and its extension is: " + FilenameUtils.getExtension(fileName));
        if ((FilenameUtils.isExtension(fileName, "xls")))
            return true;
        else
            return false;
    }
    /*
     * This function will not be used.
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
	*/

    public static ArrayList<Checklist> parseExcel(File file) throws FileNotFoundException, BiffException, IOException {

        if (!getAndCheckFileName(file)) {
            System.out.println("wrong input to parse excel function, return false.");
            return null;
        }

        System.out.println("File to parse: " + file.getAbsolutePath() +
                " and its extension is: " + FilenameUtils.getExtension(file.getName()));

        int count_function;
        int count_info;
        int count_status;
        int count_method;
        String[] function;
        String[] test_method;
        String criteria = "";
        Map<String, String> test_info;
        String[] status;
        String checklist_item = "";
        DateFormat dateFormat;
        Date date;
        Checklist checklist;
        Workbook w;
        ArrayList<Checklist> checklists = new ArrayList<Checklist>();

        w = Workbook.getWorkbook(file);

        for (int n = 0; n < w.getNumberOfSheets(); n++) {
            status = new String[3];
            test_method = new String[30];
            function = new String[30];
            count_function = 0;
            count_info = 0;
            count_status = 0;
            count_method = 0;
            test_info = new HashMap<String, String>();
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = new Date();

            Sheet sheet = w.getSheet(n);

            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);

                    if (cell.getContents().startsWith("Checklist")) {
                        checklist_item = sheet.getCell(j + 1, i).getContents();
                        System.out.println("Checklist item is: " + checklist_item);
                    } else if (cell.getContents().startsWith("Pass and Fail")) {
                        criteria = sheet.getCell(j + 1, i + 1).getContents();
                        System.out.println("Criteria is: " + criteria);
                    } else if (cell.getContents().equals("Function")) {
                        while (!sheet.getCell(j + 1, i + 1 + count_function).getContents().equals("")) {
                            function[count_function] = sheet.getCell(j + 1, i + 1 + count_function).getContents();
                            System.out.println("Function number " + (count_function + 1) + " is: " + function[count_function]);
                            count_function++;
                        }
                    } else if (cell.getContents().equals("Test Information")) {
                        while (count_info < 4) {
                            test_info.put(sheet.getCell(j + 1, i + 1 + count_info).getContents(), sheet.getCell(j + 2, i + 1 + count_info).getContents());
                            System.out.println(sheet.getCell(j + 1, i + 1 + count_info).getContents() + ": " + test_info.get(sheet.getCell(j + 1, i + 1 + count_info).getContents()));
                            count_info++;
                        }
                    } else if (cell.getContents().equals("Status")) {
                        while (count_status < 3) {
                            status[count_status] = sheet.getCell(j + 1, i + 1 + count_status).getContents();
                            System.out.println("Status option is: " + status[count_status]);
                            count_status++;
                        }
                    } else if (cell.getContents().equals("Test Method")) {
                        while (!sheet.getCell(j + 1, i + 1 + count_method).getContents().equals("")) {
                            test_method[count_method] = sheet.getCell(j + 1, i + 1 + count_method).getContents();
                            System.out.println("Test method is: " + test_method[count_method]);
                            count_method++;
                        }
                    }
                }
            }

            //create object to print sheet elements on window
            checklist = new Checklist(checklist_item, function, test_method, criteria, test_info, status);

            //add sheet object to arraylist
            checklists.add(checklist);
        }
        //close excel file
        w.close();

        return checklists;
    }
}