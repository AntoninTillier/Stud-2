import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class ArbreGraphCell extends DefaultTableCellRenderer {

    public ArbreGraphCell() {
        this.setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (table.getValueAt(row, column) != " ") {
            component.setBackground(new Color(254, 246, 219));
        } else {
            component.setBackground(Color.white);
        }
        return component;
    }
}