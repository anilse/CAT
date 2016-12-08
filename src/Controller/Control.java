package Controller;

import Model.Checklist;
import View.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    public static StringBuilder result = new StringBuilder();
    public static int count_f_total = 0;
    public static final int PASS = 0;
    public static final int FAIL = 1;
    public static final int NA = 2;
    public static int test_status; // Default 2

    public Control() {
        view = new View(this);
    }

    /**
     * Show GUI
     */
    public void start() {
        view.drawMainWindow();
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

    public static boolean parseExcel(File file) throws FileNotFoundException, BiffException, IOException {

        if (!getAndCheckFileName(file)) {
            System.out.println("wrong input to parse excel function, return false.");
            return false;
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
                        result.append("<html>");
                        result.append("Checklist item is: " + checklist_item);
                        result.append("<html>");
                        result.append("<br>");
                    } else if (cell.getContents().startsWith("Pass and Fail")) {
                        criteria = sheet.getCell(j + 1, i + 1).getContents();
                        System.out.println("Criteria is: " + criteria);
                        result.append("<html>");
                        result.append("Criteria is: " + criteria);
                        result.append("<html>");
                        result.append("<br>");
                    } else if (cell.getContents().equals("Function")) {
                        while (!sheet.getCell(j + 1, i + 1 + count_function).getContents().equals("")) {
                            function[count_function] = sheet.getCell(j + 1, i + 1 + count_function).getContents();
                            System.out.println("Function number " + (count_function + 1) + " is: " + function[count_function]);
                            result.append("<html>");
                            result.append("Function number " + (count_function + 1) + " is: " + function[count_function]);
                            result.append("<html>");
                            result.append("<br>");
                            count_function++;
                        }
                    } else if (cell.getContents().equals("Test Information")) {
                        while (count_info < 4) {
                            test_info.put(sheet.getCell(j + 1, i + 1 + count_info).getContents(), sheet.getCell(j + 2, i + 1 + count_info).getContents());
                            System.out.println(sheet.getCell(j + 1, i + 1 + count_info).getContents() + ": " + test_info.get(sheet.getCell(j + 1, i + 1 + count_info).getContents()));
                            result.append("<html>");
                            if (sheet.getCell(j + 1, i + 1 + count_info).getContents().startsWith("Test Date")) {
                                result.append(sheet.getCell(j + 1, i + 1 + count_info).getContents() + ": " + dateFormat.format(date));
                            } else {
                                result.append(sheet.getCell(j + 1, i + 1 + count_info).getContents() + ": " + test_info.get(sheet.getCell(j + 1, i + 1 + count_info).getContents()));
                            }
                            result.append("<html>");
                            result.append("<br>");
                            count_info++;
                        }
                    } else if (cell.getContents().equals("Status")) {
                        while (count_status < 3) {
                            status[count_status] = sheet.getCell(j + 1, i + 1 + count_status).getContents();
                            System.out.println("Status option is: " + status[count_status]);
                            result.append("<html>");
                            result.append("Status option is: " + status[count_status]);
                            result.append("<html>");
                            result.append("<br>");
                            count_status++;
                        }
                    } else if (cell.getContents().equals("Test Method")) {
                        while (!sheet.getCell(j + 1, i + 1 + count_method).getContents().equals("")) {
                            test_method[count_method] = sheet.getCell(j + 1, i + 1 + count_method).getContents();
                            System.out.println("Test method is: " + test_method[count_method]);
                            result.append("<html>");
                            result.append("Test method is: " + test_method[count_method]);
                            result.append("<html>");
                            result.append("<br>");
                            count_method++;
                        }
                    }
                }
            }

            count_f_total = count_function;

            //create object to print sheet elements on window
            checklist = new Checklist(checklist_item, function, test_method, criteria, test_info, status);

            //add sheet object to arraylist
            checklists.add(checklist);
        }

        //close excel file
        w.close();

        return true;

    }

}