package View;

import Controller.Control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

import Model.Checklist;
import jxl.read.biff.BiffException;


@SuppressWarnings("serial")
public class View extends JPanel {
    JFrame frame;
    public Control control = null;
    JLabel emptyLabel;
    public static JFileChooser fileChooser = null;
    int dialogButton = JOptionPane.YES_NO_OPTION;

    public View(Control control) {
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

    public void drawMainWindow() {
        JButton loadButton = new JButton("LOAD EXCEL FILE...");
        JButton closeButton = new JButton("CLOSE");

        frame.add(loadButton);
        frame.add(closeButton);
        loadButton.setBounds(50, 100, 170, 20);
        closeButton.setBounds(350, 100, 100, 20);
        //Display the window.
        frame.setVisible(true);

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("LOAD Button clicked.");
                int fileChooserResult = fileChooser.showOpenDialog(getParent());
                File selectedFile = fileChooser.getSelectedFile();
                if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
                    if (Control.getAndCheckFileName(selectedFile)) {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to load " +
                                "\"" + selectedFile.getName() + "\"", "Warning", dialogButton);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            try {
                                Control.parseExcel(selectedFile);
                                drawTestRunner();
                                frame.setVisible(false);
                                // Close excel loader.
                            } catch (BiffException | IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                                JOptionPane.showMessageDialog(null, "File not found, try again!");
                            }
                        } else {
                            System.out.println("User has chosen not to parse excel.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath() +
                                " and its extension is: " + FilenameUtils.getExtension(selectedFile.getName()) + "\nPlease, choose an excel file with extension \".xls\".");
                    }
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("CLOSE Button clicked.");
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }
    
    public void drawTestRunner(){
    	
		 ImageIcon icon = new ImageIcon("pic1.jpg");  
         JLabel label = new JLabel();  
         
         ImageIcon icon2 = new ImageIcon("pic2.jpg");  
         JLabel label2 = new JLabel(); 
    	
		 ImageIcon icon3 = new ImageIcon("na.jpg");  
         JLabel label3 = new JLabel();  
         
        JButton btnAddFlight = new JButton("CLOSE");
        btnAddFlight.setBounds(220, 400, 220, 30);
        
        JButton resultbutton = new JButton("GET RESULT");
        resultbutton.setBounds(450, 400, 220, 30);
        
        JFrame window = new JFrame("CAT Result Table"); 
        
        JRadioButton t[] = new JRadioButton[Control.count_f_total];
        
 //       ButtonGroup buttonGroup = new ButtonGroup();
        
        int x;
        int offset =180;
        for (x = 0 ; x < Control.count_f_total ; x++ )  {
        	t[x] = new JRadioButton("");
            t[x].setBounds(570, offset, 18, 18);
//            buttonGroup.add(t[x]);
            window.add(t[x]);
            offset += 16;
        }
        
        
        window.add(btnAddFlight);
        window.add(resultbutton);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setPreferredSize(new Dimension(940, 480));
        window.add(new JLabel(Control.result.toString()), BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);

        
        btnAddFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("CLOSE Button clicked.");
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            }
        });

        
        resultbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Initiate the icons first.
                label3.setEnabled(false); 
                label2.setEnabled(false);
                label.setEnabled(false);
                label.setIcon(null);
                label2.setIcon(null);
                label3.setIcon(null);
            	int k = 0;
            	int check = 0;
            	for (k = 0 ; k < Control.count_f_total ; k++) {
            		if (t[k].isSelected() ) check++;
            	}
            	if (check == 0){
                    label3.setIcon(icon3);
                    label3.setBounds(300, 400, 5, 5);
                    label3.setVisible(true);
                    label3.setEnabled(true);
                    window.remove(label);
                    window.remove(label2);
                    window.add(label3, BorderLayout.EAST);
                    window.revalidate();
                    Control.test_status = 2;
	            } else {
                    window.remove(label3);
	            	if (check == Control.count_f_total) {
	            	//	 window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	                    label.setIcon(icon);
	                    label.setBounds(300, 400, 5, 5);
	                    label.setVisible(true);
	                    label.setEnabled(true);
	                    window.remove(label2);
	                    window.add(label, BorderLayout.EAST);
	                    window.revalidate();
	                    Control.test_status = 0;
	            	
	            	} else {
	            //		 window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	                    label2.setIcon(icon2);
	                    label2.setBounds(300, 400, 5, 5);
	                    label2.setVisible(true);
	                    label2.setEnabled(true);
	                    window.remove(label);
	                    window.add(label2, BorderLayout.EAST);
	                    window.revalidate();
	                    Control.test_status = 1;
	            	}
	                label.updateUI();
	                label2.updateUI();
	               
	            }
                switch (Control.test_status){
	                case 0: System.out.println("test_status is PASS"); break;
	                case 1: System.out.println("test_status is FAIL"); break;
	                case 2: System.out.println("test_status is NA"); break;
	                default: System.out.println("A weird test_status"); break;
                }
            }
        });
    }


}