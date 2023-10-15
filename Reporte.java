import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Reporte {
    public static void main(String[] args) {
        String ruta="car_sales.json";

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(contenido);
            Map<String, Double> totalPrecioMap = new HashMap<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject  jsonObject = (JSONObject) jsonArray.get(i);
                String marca = (String) jsonObject.get("car");
                double precio = Double.parseDouble(((String) jsonObject.get("price")).replaceAll("[^0-9.]", ""));
                totalPrecioMap.put(marca, totalPrecioMap.getOrDefault(marca, 0.0) + precio);
            }
            Map<String, Double> promedioPrecioMap = new HashMap<>();
            for (Map.Entry<String, Double> entry : totalPrecioMap.entrySet()) {
                String marca = entry.getKey();
                double total = entry.getValue();
                long conteo = jsonArray.stream().filter(jsonObj -> jsonObj instanceof JSONObject &&
                        ((JSONObject) jsonObj).get("car").equals(marca)).count();
                double promedio = total / conteo;
                promedioPrecioMap.put(marca, promedio);
            }
            System.out.println("Reporte de precio promedio por marca: ");
            for (Map.Entry<String,Double> entry : promedioPrecioMap.entrySet()) {
                String marca = entry.getKey();
                double precioPromedio = entry.getValue();
                System.out.println(marca + ": $" + String.format("%.2f", precioPromedio));
            }
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
