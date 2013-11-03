import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;


public class GPACalculator extends JFrame {
	

	private JLabel jlblGPA1 = new JLabel("Course 1");
	private JLabel jlblGPA2 = new JLabel("Course 2");
	private JLabel jlblGPA3 = new JLabel("Course 3");
	private JLabel jlblGPA4 = new JLabel("Course 4");
	private JLabel jlblGPA5 = new JLabel("Course 5");
	private JLabel jlblGPA6 = new JLabel("Course 6");
	private JTextField jtfGPAFinal = new JTextField();
	
	ComboString gList = new ComboString();
	

	
	JComboBox gL1 = new JComboBox(gList.gListItems);
	JComboBox gL2 = new JComboBox(gList.gListItems);
	JComboBox gL3 = new JComboBox(gList.gListItems);
	JComboBox gL4 = new JComboBox(gList.gListItems);
	JComboBox gL5 = new JComboBox(gList.gListItems);
	JComboBox gL6 = new JComboBox(gList.gListItems);
	
	ImageIcon gpaIcon = new ImageIcon("src/gpa.png");
	

	public class ComboString{
		private String[] gListItems = new String[52]; 
		ComboString(){
			for(int i = 1 ; i < 52 ; i++){
				gListItems[i] = "" + (101 - i);
			}
			gListItems[0] = "N/A";
		}
	}
		

	public GPACalculator() {
		
	GridLayout gd1 = new GridLayout(6,2);
	gd1.setVgap(50);
	gd1.setHgap(-70);
	JPanel p1 = new JPanel(gd1);
	
	p1.add(jlblGPA1);
	p1.add(gL1);
	p1.add(jlblGPA2);
	p1.add(gL2);
	p1.add(jlblGPA3);
	p1.add(gL3);
	p1.add(jlblGPA4);
	p1.add(gL4);
	p1.add(jlblGPA5);
	p1.add(gL5);
	p1.add(jlblGPA6);
	p1.add(gL6);
		
	p1.setBorder(new TitledBorder("Enter your Grades (%)"));
	
	JPanel p2 = new JPanel(new GridLayout(2,1));
	JButton jbtCompute = new JButton("Compute");
	jbtCompute.setToolTipText("Click to compute GPA");
	p2.add(jbtCompute);
	
	p2.add(jtfGPAFinal);

	JPanel p3 = new JPanel();
	JLabel jlblIcon = new JLabel(gpaIcon);
	//jlblIcon.setSize(200, 500);
	p3.add(jlblIcon);
	
	
	JPanel p4 = new JPanel(new BorderLayout());
	p4.add(p3, BorderLayout.CENTER);
	p4.add(p2, BorderLayout.SOUTH);
	
	

	jbtCompute.addActionListener(new ButtonListener());
	
	add(p1, BorderLayout.CENTER);
	add(p4, BorderLayout.EAST);
	//add(gList, BorderLayout.SOUTH);
			
	}
	
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String[] gradeList = new String[6];
	
			
			try{
				gradeList[0] = (String)gL1.getSelectedItem();
				gradeList[1] = (String)gL2.getSelectedItem();
				gradeList[2] = (String)gL3.getSelectedItem();
				gradeList[3] = (String)gL4.getSelectedItem();
				gradeList[4] = (String)gL5.getSelectedItem();
				gradeList[5] = (String)gL6.getSelectedItem();
				double GPAFinal = getGPA(gradeList);
				double AVGFinal = getAVG(gradeList);
				jtfGPAFinal.setText(String.format("Your GPA is: %.2f. Your Avg is: %.2f", GPAFinal, AVGFinal) + "%.");
			}
			catch(NumberFormatException e1){
				jtfGPAFinal.setText("Please enter your marks %");	
			}
			

	
			
			
		}
	}

	public static void main(String[] args) {
		GPACalculator f = new GPACalculator();
		f.setSize(500,500);
		f.setVisible(true);
		f.setTitle("GPA Calculator");
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

	double getGPA(String[] List){
		int numCourses = 0;
		double gradeSum = 0;
		
		for(int i = 0; i<6; i++){
			if (List[i] == "N/A") {gradeSum += 0; numCourses--;}
		    else if(Double.parseDouble(List[i])>=90)                gradeSum += 4;
			else if(Double.parseDouble(List[i])>=85)           gradeSum += 4.0;
			else if(Double.parseDouble(List[i])>=80)           gradeSum += 3.7;
			else if(Double.parseDouble(List[i])>=77)           gradeSum += 3.3;
			else if(Double.parseDouble(List[i])>=73)           gradeSum += 3.0;
			else if(Double.parseDouble(List[i])>=70)           gradeSum += 2.7;
			else if(Double.parseDouble(List[i])>=67)           gradeSum += 2.3;
			else if(Double.parseDouble(List[i])>=63)           gradeSum += 2.0;
			else if(Double.parseDouble(List[i])>=60)           gradeSum += 1.7;
			else if(Double.parseDouble(List[i])>=57)           gradeSum += 1.3;
			else if(Double.parseDouble(List[i])>=53)           gradeSum += 1.0;
			else if(Double.parseDouble(List[i])>=50)           gradeSum += 0.7;
			numCourses++;
		}
		if(numCourses == 0) return 0;
		
		double GPA = gradeSum/numCourses;
		return GPA;
		
	}
	
	double getAVG(String[] List){
		int numCourses = 0;
		double percentSum = 0;
		double AVG = 0;
		
		for(int i = 0; i<6; i++){
			if (List[i] == "N/A") {percentSum += 0; numCourses--;}
			else{
				percentSum += Double.parseDouble(List[i]);	
			}
			numCourses++;
		}
		
		if(numCourses ==0) return 0;
		
		AVG = percentSum/numCourses;
		return AVG;
		
		
	}
}
