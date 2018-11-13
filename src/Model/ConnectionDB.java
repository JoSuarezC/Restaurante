package Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionDB {

    private static ConnectionDB instance;

    // Server IP
    private static final String URL_HOST = "http://restaurante-ak7.esy.es/";

    /* PHP login */
    public static final String login_PHP = URL_HOST + "RestaurantePHP/login.php";  // http://restaurante-ak7.esy.es/RestaurantePHP/login.php


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
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);

                } in .close();
                try{
                    JSONObject myResponse = new JSONObject(response.toString());
                    if (myResponse.getString("status").equals("true")){

                        System.out.println(myResponse.getJSONObject("value").getString("IdUsuario"));
                        System.out.println(myResponse.getJSONObject("value").getString("Contraseña"));
                        System.out.println(myResponse.getJSONObject("value").getString("Correo"));
                        System.out.println(myResponse.getJSONObject("value").getString("Estado"));
                        System.out.println(myResponse.getJSONObject("value").getString("TipoUsuario"));
                        System.out.println(myResponse.getJSONObject("value").getString("IdTipoUsuario"));
                        System.out.println(myResponse.getJSONObject("value").getString("Cedula"));
                        System.out.println(myResponse.getJSONObject("value").getString("Apellidos"));
                        System.out.println(myResponse.getJSONObject("value").getString("Nombre"));

                        if (myResponse.getJSONObject("value").getString("TipoUsuario").equals("Empleado")){
                            System.out.println(myResponse.getJSONObject("value").getString("IdEm"));
                            System.out.println(myResponse.getJSONObject("value").getString("Puesto"));
                            System.out.println(myResponse.getJSONObject("value").getString("CuentaBancaria"));

                        }else if(myResponse.getJSONObject("value").getString("TipoUsuario").equals("Cliente")){
                            System.out.println(myResponse.getJSONObject("value").getString("IdCliente"));
                            System.out.println(myResponse.getJSONObject("value").getString("Telefono1"));
                            System.out.println(myResponse.getJSONObject("value").getString("Telefono2"));
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


    public void xd (){

    }

}
