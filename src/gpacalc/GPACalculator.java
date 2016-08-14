package gpacalc;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

public class GPACalculator {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
    	System.setProperty("apple.awt.application.name", "GPA Calculator");
    	
	    try {
	    	// Standard normalizations
	    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        	e.printStackTrace();
        }
	    
	    SwingUtilities.invokeLater(() -> new GPACalculator().createAndShowGUI());
	}
	
	public static final ClassType[] allClassTypes = new ClassType[] { ClassType.AP, ClassType.REGULAR };
	public static final Grade[] allGrades = new Grade[] { Grade.A, Grade.B, Grade.C, Grade.D, Grade.F };
	
	void createAndShowGUI() {
		JFrame frame = new JFrame("GPA Calculator");
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener((e) -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
		JMenuItem calculate = new JMenuItem("Calculate...");
		fileMenu.add(calculate);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem addClass = new JMenuItem("Add Class");
		addClass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.CTRL_DOWN_MASK));
		editMenu.add(addClass);
		JMenuItem removeClass = new JMenuItem("Remove Class");
		removeClass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK));
		editMenu.add(removeClass);
		menuBar.add(editMenu);
		
		frame.setJMenuBar(menuBar);
		
		JComboBox<ClassType> classBox = new JComboBox<ClassType>(allClassTypes);
		JComboBox<Grade> gradeBox = new JComboBox<Grade>(allGrades);
		
		GradeTableModel model = new GradeTableModel();

		calculate.addActionListener((a) -> showGPA(frame, model));
		
		JTable table = new JTable(model);
		TableColumn classTypeColumn = table.getColumnModel().getColumn(0);
		classTypeColumn.setCellEditor(new DefaultCellEditor(classBox));
		TableColumn gradeColumn = table.getColumnModel().getColumn(1);
		gradeColumn.setCellEditor(new DefaultCellEditor(gradeBox));
		
		for (int i = 0; i < 7; i ++) {
			model.addRow();
		}
		
		addClass.addActionListener((e) -> model.addRow());
		JScrollPane scrollPane = new JScrollPane(table);
		frame.setContentPane(scrollPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	void removeSelectedRow(JTable table, GradeTableModel model) {
		model.removeRow(table.getSelectedRow());
		table.getSelectedRow();
	}
	void showGPA(JFrame frame, GradeTableModel model) {
		JOptionPane.showMessageDialog(frame, "Your GPA is " + model.calculateGPA());
	}

}
