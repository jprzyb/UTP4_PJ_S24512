package zad1;


import javax.swing.*;
import java.awt.*;
import java.util.List;

class Window{
    TravelData travelData;
    public Window(TravelData travelData){
        this.travelData = travelData;
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        String[] headers = {"Code", "Country", "Start", "End", "Location","Price","Currency"};
        JFrame frame = new JFrame("Language Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton polishButton = new JButton("Polski");
        JButton englishButton = new JButton("English");

        buttonPanel.add(polishButton);
        buttonPanel.add(englishButton);

        frame.add(buttonPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        JTable table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.setVisible(false);

        frame.add(tablePanel, BorderLayout.CENTER);

        polishButton.addActionListener(e -> {
            table.setModel(new CustomTableModel(convertListTo2DArray(travelData.offersAsStringGUI()), headers));
            tablePanel.setVisible(true);
//            frame.pack();
        });

        englishButton.addActionListener(e -> {
            table.setModel(new CustomTableModel(convertListTo2DArray(travelData.translatedAsStringGUI()), headers));
            tablePanel.setVisible(true);
//            frame.pack();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static Object[][] convertListTo2DArray(List<String> list) {
        int numRows = list.size();
        String[][] result = new String[numRows][];

        for (int i = 0; i < numRows; i++) {
            result[i] = list.get(i).split("\t");
        }

        return result;
    }
}
class CustomTableModel extends javax.swing.table.AbstractTableModel {

    private Object[][] data;
    private String[] columnNames;

    public CustomTableModel(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}