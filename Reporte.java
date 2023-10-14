import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Reporte {
    public static void main(String[] args) {
        String ruta="C:\\DSlll\\CarSalesJSON\\car_sales.json";

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
            JSONArray jsonArray = new JSONArray(contenido);
            Map<String, Double> totalPrecioMap = new HashMap<>();

            for (int i = 0; i < jsonArray.length; i++) {
                JSONObject  jsonObject = jsonArray.getJSONObject(i);
                String marca = jsonObject.getString("car");
                double precio = Double.parseDouble(jsonObject.getString("price").replaceAll("[0-9]", ""));
                totalPrecioMap.put(marca, totalPrecioMap.getOrDefault(marca, 0.0) + precio);
            }
            Map<String, Double> promedioPrecioMap = new HashMap<>();
            for (Map.Entry<String, Double> entry : totalPrecioMap.entrySet()) {
                String marca = entry.getKey();
                double total = entry.getValue();
                int conteo = (int) jsonArray.toList().stream().filter(jsonObj -> jsonObj instanceof JSONbject &&
                        ((JSONObject) jsonObj).getString("car").equals(marca)).count();
                double promedio = total / conteo;
                promedioPrecioMap.put(marca, promedio);
            }
            System.out.println("Reporte de precio promedio por marca: ");
            for (Map.Entry<String,Double> entry : promedioPrecioMap.entrySet()) {
                String marca = entry.getKey();
                double precioPromedio = entry.getValue();
                System.out.println(marca + ": $" + String.format("%.2f", precioPromedio));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
