package org.example;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileReader;
import java.io.FileWriter;

public class XMLaJSON {

    public static void main(String[] args) {
        try {
            //ruta
            String inputXMLFile = "car_sales.xml";

            //leer xml
            FileReader reader = new FileReader(inputXMLFile);
            StringBuilder xmlStringBuilder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                xmlStringBuilder.append((char) character);
            }
            String xmlString = xmlStringBuilder.toString();

            //xml en json
            JSONObject jsonObject = XML.toJSONObject(xmlString);
            String jsonString = jsonObject.toString(4);

            String outputJSONFile = "car_sales.json";
            FileWriter writer = new FileWriter(outputJSONFile);
            writer.write(jsonString);
            writer.close();

            System.out.println("Conversión exitosa. El JSON resultante se ha guardado en " + outputJSONFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
