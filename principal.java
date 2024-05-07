package challenge2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import challenge2.clase.moneda;

public class principal {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        int opcion = 0;
        Map<Integer, String> monedas = new HashMap<>();
        monedas.put(1, "ARS");
        monedas.put(2, "USD");
        monedas.put(3, "BRL");
        monedas.put(3, "COP");


        Scanner leer = new Scanner(System.in);
        while (opcion != 7) {
            System.out.println("\n**************************************");
            System.out.println("\nSEA BIENBENIDO/A AL CONVERSOR DE MONEDA\n");
            System.out.println("1) Dolar ==> Peso Argentino");
            System.out.println("2) Peso Argentino ==> Dolar");
            System.out.println("3) Dolar ==> Real Brazilero");
            System.out.println("4) Real Brazilero ==> Dolar");
            System.out.println("5) Dolar ==> Peso Colombiano");
            System.out.println("6) Peso Colombiano ==> Dolar");
            System.out.println("7) Salir");
            System.out.println("\nElija una opcion valida");
            opcion = leer.nextInt();

            if(opcion<7){
                System.out.println("Ingrese la cantidad de dinero a cambiar");
                double cantidad = leer.nextDouble();
                
                String convertir = "";
                String convertirA = "";
                
                
                switch (opcion) {
                    case 1:
                        convertir = "USD";
                        convertirA = "ARS";
                        break;
                    case 2:
                        convertir = "ARS";
                        convertirA = "USD";
                        break;
                    case 3:
                        convertir = "USD";
                        convertirA = "BRL";
                        break;
                    case 4:
                        convertir = "BRL";
                        convertirA = "USD";
                        break;

                    case 5:
                        convertir = "USD";
                        convertirA = "COP";

                    default:
                        convertir = "COP";
                        convertirA = "USD";
                        break;
                }

                    String direccion = "https://v6.exchangerate-api.com/v6/0cda7a1afb9dc6f2e4e50047/latest/" + convertir;
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    String json = response.body();
                    //System.out.println(json);

                    Gson gson = new Gson();
                    moneda miMoneda = gson.fromJson(json, moneda.class);
                        
                    String moneda = monedas.getOrDefault(opcion, convertir);
                    Double usdValue = miMoneda.getConversionRates().get(convertirA) * cantidad;
                    System.out.println("\n El valor de " + cantidad + " " + convertir + " en " + convertirA + " es: " + usdValue);

            }else if(opcion == 7){
                System.out.println("***MENU CERRADO***");
            }else{            System.out.println("Elija una opcion valida");
            }
        }
         
    }
}