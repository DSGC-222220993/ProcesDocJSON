import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class TablaJson {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });

    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Tabla car_sales.json");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        try {

            File file = new File("car_sales.json");
            JsonArray jsonArray = new Gson().fromJson(new FileReader(file), JsonArray.class);

            String[] columnNames = {"ID", "First Name", "Last Name", "Car", "Price", "State"};
            DefaultTableModel modelo = new DefaultTableModel(columnNames, 0);

            for (int i = 0; i < jsonArray.size(); i++) {

                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                Object[] datos = {

                        jsonObject.get("id").getAsString(),
                        jsonObject.get("first_name").getAsString(),
                        jsonObject.get("last_name").getAsString(),
                        jsonObject.get("car").getAsString(),
                        jsonObject.get("price").getAsString(),
                        jsonObject.get("state").getAsString()

                };
                modelo.addRow(datos);

            }

            JTable tabla = new JTable(modelo);
            JScrollPane scrollPane = new JScrollPane(tabla);
            frame.add(scrollPane, BorderLayout.CENTER);

            JButton btnSalir = new JButton("Salir");

            btnSalir.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    System.exit(0);

                }

            });

            JPanel panel = new JPanel();
            panel.add(btnSalir);

            frame.add(panel, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
