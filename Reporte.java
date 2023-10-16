import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Reporte {
    public static void main(String[] args) {

        String ruta = "car_sales.json";

        try {

            String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
            JsonArray jsonArray = new Gson().fromJson(contenido, JsonArray.class);
            Map<String, Double> totalPrecioMap = new HashMap<>();
            Map<String, Integer> countMap = new HashMap<>();

            for (int i = 0; i < jsonArray.size(); i++) {

                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String marca = jsonObject.get("car").getAsString();
                double precio = Double.parseDouble(jsonObject.get("price").getAsString().replaceAll("[^0-9.]", ""));
                totalPrecioMap.put(marca, totalPrecioMap.getOrDefault(marca, 0.0) + precio);
                countMap.put(marca, countMap.getOrDefault(marca, 0) + 1);

            }

            Map<String, Double> promedioPrecioMap = new HashMap<>();

            for (Map.Entry<String, Double> entry : totalPrecioMap.entrySet()) {

                String marca = entry.getKey();
                double total = entry.getValue();
                int conteo = countMap.get(marca);
                double promedio = total / conteo;
                promedioPrecioMap.put(marca, promedio);

            }

            System.out.println("Reporte de precio promedio por marca: ");

            for (Map.Entry<String, Double> entry : promedioPrecioMap.entrySet()) {

                String marca = entry.getKey();
                double precioPromedio = entry.getValue();
                System.out.println(marca + ": $" + String.format("%.2f", precioPromedio));

            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
