package View;

import Controller.Control;

import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import jxl.read.biff.BiffException;


@SuppressWarnings("serial")
public class View extends JPanel{
	JFrame frame;
	public Control control = null;
	JLabel emptyLabel;
	public static JFileChooser fileChooser = null; 
	int dialogButton = JOptionPane.YES_NO_OPTION;
    
	public View(Control control) 
	{		
		this.control = control;
		frame = new JFrame("CAT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        
		frame.setSize(500, 250);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		//this.setLayout(null);
	}
	
	public void drawWindow() { 
		JButton loadButton = new JButton("LOAD EXCEL FILE...");
		JButton closeButton = new JButton("CLOSE");

		frame.add(loadButton);
		frame.add(closeButton);
		loadButton.setBounds(50, 100, 170, 20);
		closeButton.setBounds(350, 100, 100, 20);
        //Display the window.
        frame.setVisible(true);
        
		loadButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("LOAD Button clicked.");
				int fileChooserResult = fileChooser.showOpenDialog(getParent());
				boolean parseExcelResult = false;
				File selectedFile = fileChooser.getSelectedFile();
				if (fileChooserResult == JFileChooser.APPROVE_OPTION) {					
				    if (Control.getAndCheckFileName(selectedFile)) {
				    	int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure to load " +
				    			"\""+ selectedFile.getName() + "\"" ,"Warning",dialogButton);
				    	if(dialogResult == JOptionPane.YES_OPTION){
					        try {
					        	parseExcelResult = Control.parseExcel(selectedFile);
					        	if (parseExcelResult){
					        		System.out.println("file is parsed successfully!");
					        	} else {
					        		System.out.println("ERROR! file couldn't be parsed!");
					        	}
							} catch (BiffException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "File not found, try again!");
							}
				    	}
				    	else {
				    		System.out.println("User has chosen not to parse excel.");
				    	}
				    }
				    else {
				    	JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath() + 
					    		" and its extension is: " + FilenameUtils.getExtension(selectedFile.getName()) +"\nPlease, choose an excel file with extension \".xls\".");
				    }
				}
			}
		});
		
		closeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("CLOSE Button clicked.");
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	

}
