import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class TablaJson {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TablaJson app = new TablaJson();
            app.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("JSON Table Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("car_sales.json")); // Reemplazar con la ruta real del archivo JSON
            JSONArray jsonArray = (JSONArray) obj;

            String[] columnNames = getColumns(jsonArray);
            Object[][] data = getData(jsonArray, columnNames);

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            table.setModel(model);

        } catch (IOException | ParseException | NullPointerException e) {
            e.printStackTrace();
        }

        JButton btnExit = new JButton("Salir");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(btnExit, BorderLayout.PAGE_END);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String[] getColumns(JSONArray jsonArray) {
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        return (String[]) jsonObject.keySet().toArray(new String[0]);
    }

    private Object[][] getData(JSONArray jsonArray, String[] columnNames) {
        Object[][] data = new Object[jsonArray.size()][columnNames.length];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            for (int j = 0; j < columnNames.length; j++) {
                data[i][j] = jsonObject.get(columnNames[j]);
            }
        }
        return data;
    }
}
