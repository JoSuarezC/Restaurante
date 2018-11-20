package Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
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
    private static final String search_product_PHP = URL_HOST +"RestaurantePHP/search_product.php";
    private static final String add_product_PHP = URL_HOST +"RestaurantePHP/insert_product.php";


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
                        String userType = myResponse.getJSONObject("value").getString("NombreTipoUsuario");
                        String idCard = myResponse.getJSONObject("value").getString("Cedula");
                        String lastname = myResponse.getJSONObject("value").getString("Apellidos");
                        String firstname = myResponse.getJSONObject("value").getString("Nombre");

                        if (userType.equals("Empleado")){
                            String EmID = myResponse.getJSONObject("value").getString("IdEm");
                            String job = myResponse.getJSONObject("value").getString("Puesto");
                            String bankAcc = myResponse.getJSONObject("value").getString("CuentaBancaria");
                            Employee e = new Employee(user, password, email, idCard, lastname, firstname, EmID, job, bankAcc);
                            User.setCurrentUser(e);

                        }else if(userType.equals("Cliente")){
                            String ClientID = myResponse.getJSONObject("value").getString("IdCliente");
                            String telf1 = myResponse.getJSONObject("value").getString("Telefono1");
                            String telf2 = myResponse.getJSONObject("value").getString("Telefono2");
                            Client e = new Client(user, password, email, idCard, lastname, firstname, ClientID, telf1, telf2);
                            User.setCurrentUser(e);
                        }
                        return userType;
                    }else{
                        System.out.print("No existe el usuario");
                    }
                }catch (JSONException e){ System.out.println(e);}
            } else {
                System.out.println("GET NOT WORKED");
            }
        }catch (IOException e){System.out.println(e);}
        return "";
    }


    public ArrayList selectProductInventory_byType (String productType){
        ArrayList<Product> arraylistProducto = new ArrayList<>();
        try{
            URL urlForGetRequest = new URL(select_products_by_productType_PHP+"?TipoProducto="+productType);
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
                        JSONArray results = myResponse.getJSONArray("value");
                        for (int i = 0; i < results.length(); i++) {
                            String productID = results.getJSONObject(i).getString("IdProd");
                            String name = results.getJSONObject(i).getString("Nombre");
                            String detail = results.getJSONObject(i).getString("Detalle");
                            int prize = results.getJSONObject(i).getInt("PrecioUnitario");
                            String type = results.getJSONObject(i).getString("TipoProducto");
                            Product p = new Product(name, type, productID, prize, detail);
                            arraylistProducto.add(p);
                        }
                    }else{System.out.print("No existe el producto");}
                }catch (JSONException e){ System.out.println(e);}
            } else {System.out.println("GET NOT WORKED");}
        }catch (IOException e){}
        return arraylistProducto;
    }

    public String makeOrder(String date){
        return "";
    }


    public boolean searchProduct(String ProductName){
        try{
            URL urlForGetRequest = new URL(search_product_PHP+"?Nombre=");
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("POST");
            String URLparameters = "Nombre="+ProductName;
            conection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conection.getOutputStream());
            wr.writeBytes(URLparameters);
            wr.flush();
            wr.close();
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                try {
                    JSONObject myResponse = new JSONObject(response.toString());
                    if (myResponse.getString("status").equals("true")){
                        return true;
                    }
                    else{
                        return false;
                    }
                }catch (JSONException e){ System.out.println(e);}
            }
        }catch(IOException e){
            System.out.println(e);
        }
       return false;
    }

    public boolean insertProduct(String pName, String pType, String pDescription, String pPrice){
        try {
            URL urlForGetRequest = new URL(add_product_PHP);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("POST");
            String URLparameters = "Nombre="+pName+"&Tipo="+pType+"&Descripcion="+pDescription+"&Precio="+pPrice;
            conection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conection.getOutputStream());
            wr.writeBytes(URLparameters);
            wr.flush();
            wr.close();
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                try{
                    JSONObject myResponse = new JSONObject(response.toString()); // Get JSON response
                    System.out.print(myResponse.getString("status"));
                    if (myResponse.getString("status").equals("true")){
                        return true;
                    }
                    else{
                        return false;
                    }
                }catch (JSONException e){ System.out.println(e);}
            } else {System.out.println("GET NOT WORKED");}
        }catch(IOException e){}
        return false;
    }
}
