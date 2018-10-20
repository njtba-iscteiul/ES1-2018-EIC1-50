package table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends DefaultCellEditor {

	JButton view;
	
	public ButtonEditor(JTextField txt) {
		super(txt);
		
		view = new JButton("View");
		view.setOpaque(true);
	}
}
