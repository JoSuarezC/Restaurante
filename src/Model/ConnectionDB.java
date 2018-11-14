package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ConnectionDB {

    private static ConnectionDB instance;

    // Server IP
    private static final String URL_HOST = "http://restaurante-ak7.esy.es/";
    private static final String login_PHP = URL_HOST + "RestaurantePHP/login.php";
    private static final String select_products_by_productType_PHP = URL_HOST + "RestaurantePHP/select_products_by_productType.php";

    public static ConnectionDB getInstance(){
        if (instance == null){
            instance = new ConnectionDB();
        }
        return instance;
    }

    public String login(String user, String password){
        try{
            URL urlForGetRequest = new URL(login_PHP+"?UserId="+user+"&Password="+password);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);

                } in .close();
                try{
                    JSONObject myResponse = new JSONObject(response.toString());
                    if (myResponse.getString("status").equals("true")){

                        String email = myResponse.getJSONObject("value").getString("Correo");
                        String userType = myResponse.getJSONObject("value").getString("TipoUsuario");
                        String idCard = myResponse.getJSONObject("value").getString("Cedula");
                        String lastname = myResponse.getJSONObject("value").getString("Apellidos");
                        String firstname = myResponse.getJSONObject("value").getString("Nombre");

                        if (userType.equals("Employee")){
                            String EmID = myResponse.getJSONObject("value").getString("IdEm");
                            String job = myResponse.getJSONObject("value").getString("Puesto");
                            String bankAcc = myResponse.getJSONObject("value").getString("CuentaBancaria");
                            Employee e = new Employee(user, password, email, idCard, lastname, firstname, EmID, job, bankAcc);
                            User.setCurrentUser(e);
                        }else if(userType.equals("Client")){
                            String ClientID = myResponse.getJSONObject("value").getString("IdCliente");
                            String telf1 = myResponse.getJSONObject("value").getString("Telefono1");
                            String telf2 = myResponse.getJSONObject("value").getString("Telefono2");
                            Client e = new Client(user, password, email, idCard, lastname, firstname, ClientID, telf1, telf2);
                            User.setCurrentUser(e);
                        }

                    }else{
                        System.out.print("no funca");
                    }

                }catch (JSONException e){ System.out.print(e);}

            } else {
                System.out.println("GET NOT WORKED");
            }

        }catch (IOException e){}
        return "";
    }


    public ArrayList selectProductInventory_byType (String productType){
        ArrayList<Product> result = new ArrayList<>();
        try{
            URL urlForGetRequest = new URL(select_products_by_productType_PHP+"?TipoProducto="+productType);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                try{
                    while ((readLine = in .readLine()) != null) {
                        JSONObject myResponse = new JSONObject(readLine.toString());
                        if (myResponse.getString("status").equals("true")){

                            String productID = myResponse.getJSONObject("value").getString("IdProd");
                            String name = myResponse.getJSONObject("value").getString("Nombre");
                            String detail = myResponse.getJSONObject("value").getString("Detalle");
                            int prize = myResponse.getJSONObject("value").getInt("PrecioUnitario");
                            String type = myResponse.getJSONObject("value").getString("TipoProducto");
                            Product p = new Product(new SimpleStringProperty(name), new SimpleStringProperty(type), new SimpleStringProperty(productID), new SimpleIntegerProperty(prize), new SimpleStringProperty(detail));
                            result.add(p);
                        }else{
                            System.out.print("no funca");
                        }

                    } in .close();
                }catch (JSONException e){ System.out.print(e);}
            } else {
                System.out.println("GET NOT WORKED");
            }

        }catch (IOException e){}
        return result;
    }

}
