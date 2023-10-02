import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.TableUI;
import javax.swing.plaf.multi.MultiTableUI;

@SuppressWarnings("serial")
public class ArbreGraph extends JFrame {

    public ArbreGraph(String[][] tree) {
        this.setSize(1000, 200);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object[][] data = tree;
        String title[] = new String[tree[0].length];
        for (int i = 0; i < title.length; i++) {
            title[i] = "";
        }
        JTable tableau = new JTable(data, title);
        for (int i = 0; i < tableau.getColumnCount(); i++) {
            System.out.println();
            ArbreGraphCell cell = new ArbreGraphCell();
            cell.setHorizontalAlignment(JLabel.CENTER);
            tableau.getColumnModel().getColumn(i).setCellRenderer(cell);
        }
        tableau.setUI((TableUI) MultiTableUI.createUI(tableau));
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableau.setEnabled(false);
        tableau.setForeground(new Color(34, 93, 164));
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tableau);
        this.getContentPane().add(scroll);
        this.setVisible(true);
    }
}