package gpacalc;

import javax.swing.table.AbstractTableModel;

public class GradeTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	Object[][] data;
	
	void addRow() {
		Object[][] newData = new Object[data.length + 1][2];
		for (int i = 0; i < data.length; i ++) {
			newData[i][0] = data[i][0];
			newData[i][1] = data[i][1];
		}
		newData[data.length][0] = ClassType.REGULAR;
		newData[data.length][1] = Grade.A;
		
		// Switcheroo
		Object[][] oldData = data;
		data = newData;
		
		fireTableRowsInserted(oldData.length, oldData.length);
	}
	
	float calculateGPA() {
		float gpa = 0;
		for (int i = 0; i < data.length; i ++) {
			ClassType type = (ClassType) data[i][0];
			Grade grade = (Grade) data[i][1];
			gpa += grade.getWeight();
			gpa += type.getOffset();
		}
		gpa /= data.length;
		return gpa;
	}
	
	public GradeTableModel() {
		data = new Object[0][2];
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return data[row][column];
	}
	
	@Override
	public void setValueAt(Object value, int row, int column) {
		data[row][column] = value;
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return ClassType.class;
		case 1:
			return Grade.class;
		default:
			return Object.class;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Class Type";
		case 1:
			return "Grade";
		default:
			return null;
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

	public void removeRow(int selectedRow) {
		if (selectedRow < 0) {
			return;
		}
		
		Object[][] newData = new Object[data.length -1][2];
		for (int i = 0; i < selectedRow; i ++) {
			newData[i][0] = data[i][0];
			newData[i][1] = data[i][1];
		}
		for (int i = selectedRow + 1; i < data.length; i ++) {
			newData[i - 1][0] = data[i][0];
			newData[i - 1][1] = data[i][0];
		}
		fireTableRowsDeleted(selectedRow, selectedRow);
	} 

}
