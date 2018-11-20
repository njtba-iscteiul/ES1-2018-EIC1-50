package table;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean select, boolean focused, int row,
			int col) {

		setText((obj==null) ? "":obj.toString()); 
		return this;
	}
}
