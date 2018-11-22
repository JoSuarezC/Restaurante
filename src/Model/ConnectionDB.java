package Model;

import Controller.Main;
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

    private static final String URL_HOST = "http://restaurante-ak7.esy.es/";
    private static final String login_PHP = URL_HOST + "RestaurantePHP/login.php";
    private static final String select_products_by_productType_PHP = URL_HOST + "RestaurantePHP/select_products_by_productType.php";
    private static final String makeOrder = URL_HOST + "RestaurantePHP/makeOrder.php";
    private static final String buyProduct = URL_HOST + "RestaurantePHP/buyProduct.php";
    private static final String search_product_PHP = URL_HOST +"RestaurantePHP/search_product.php";
    private static final String add_product_PHP = URL_HOST +"RestaurantePHP/insert_product.php";
    private static final String insert_Client_PHP = URL_HOST+"RestaurantePHP/insert_client.php";
    private static final String create_user_PHP = URL_HOST + "RestaurantePHP/create_user.php";
    private static final String select_sucursales = URL_HOST + "RestaurantePHP/select_sucursales.php";
    private static final String select_pedidos_de_usuario_PHP = URL_HOST + "RestaurantePHP/select_pedido_usuario.php";
    private static final String select_productos_por_pedido_PHP= URL_HOST + "RestaurantePHP/select_productos_por_pedido.php";
    private static final String select_pedidos_pendientes_PHP = URL_HOST + "RestaurantePHP/select_pedidos_pendientes.php";
    private static final String set_pedido_entregado_PHP = URL_HOST + "RestaurantePHP/set_pedido_entregado.php";

    public static ConnectionDB getInstance(){
        if (instance == null){
            instance = new ConnectionDB();
        }
        return instance;
    }

    private String POSTrequest(String url, String parameters){
        StringBuilder response = new StringBuilder();
        try {
            URL urlForPOSTRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForPOSTRequest.openConnection();
            conection.setRequestMethod("POST");
            conection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conection.getOutputStream());
            wr.writeBytes(parameters);
            wr.flush();
            wr.close();
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
            } else {
                Main.MessageBox("Error de conexión", "No se ha podido conectar con el servidor.");}
        }catch(IOException e){System.out.print(e);}
        return response.toString();
    }


    private String GETRequest(String url){
        StringBuilder response = new StringBuilder();
        try{
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
            } else {
                Main.MessageBox("Error de conexión", "No se ha podido conectar con el servidor.");}
        }catch(IOException e){System.out.print(e);}
        return response.toString();
    }

    public String login(String user, String password){
        String userType = "";
        try{
            JSONObject myResponse = new JSONObject(GETRequest(login_PHP+"?UserId="+user+"&Password="+password));
            if (myResponse.getString("status").equals("true")){
                userType = myResponse.getJSONObject("value").getString("NombreTipoUsuario");
                if (userType.equals("Empleado")){
                    String EmID = myResponse.getJSONObject("value").getString("IdEm");
                    String job = myResponse.getJSONObject("value").getString("NombrePuesto");
                    User u = new User(EmID,userType,job);
                    User.setCurrentUser(u);

                }else if(userType.equals("Cliente")){
                    String ClientID = myResponse.getJSONObject("value").getString("IdCliente");
                    User u = new User(ClientID,userType,"");
                    User.setCurrentUser(u);
                }
            }else{
                System.out.print("No existe el usuario");
            }
        }catch (JSONException e){ System.out.println(e);}
        return userType;
    }


    public ArrayList selectProductInventory_byType (String productType){
        ArrayList<Product> arraylistProducto = new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_products_by_productType_PHP+"?TipoProducto="+productType));
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
        return arraylistProducto;
    }


    public String makeOrder(String ClientID, String DateTime, String OrderType, String Price, String Sucursal, String DireccionEntrega){
        String URLparameters = "ClientID=" + ClientID + "&Datetime=" + DateTime + "&OrderType=" + OrderType + "&TotalAPagar=" + Price + "&IdSucursal";
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(makeOrder, URLparameters));
            if (myResponse.getString("status").equals("true")) {
                return myResponse.getJSONObject("value").getString("orderID");
            }
        }catch (JSONException e){ System.out.println(e);}
        return null;
    }


    public boolean searchProduct(String ProductName){
        try {
            JSONObject myResponse = new JSONObject(GETRequest(search_product_PHP+"?Nombre="+ProductName));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ System.out.println(e);}
        return false;
    }

    public boolean insertProduct(String pName, String pType, String pDescription, String pPrice){
        String URLparameters = "Nombre="+pName+"&Tipo="+pType+"&Descripcion="+pDescription+"&Precio="+pPrice;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(add_product_PHP, URLparameters));
            System.out.print(myResponse.getString("status"));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ System.out.println(e);}
        return false;
    }

    public void buyProduct(String productID, String productQuantity, String orderID, String prize){
        String URLparameters = "IdProducto=" + productID + "&IdPedido=" + orderID + "&Cantidad=" + productQuantity + "&Precio=" + prize;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(buyProduct, URLparameters));
            if (myResponse.getString("status").equals("true")) {
                System.out.print("Producto comprado \n");
            }else{System.out.print("Error, producto no comprado \n");}
        }catch (JSONException e){ System.out.println(e);}
    }

    public boolean createUser(String pUsername,String pPassword,String pEmail, String pIdClient){
        String URLparameters = "NombreUsuario=" + pUsername + "&Contrasenia=" + pPassword + "&Correo=" + pEmail + "&IdCliente=" + pIdClient;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(create_user_PHP, URLparameters));
            System.out.print(myResponse.getString("status"));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ System.out.println(e);}
        return false;
    }

    public int insertClient(String pName, String pLastName, String pIdNumber, String pPhoneNumber){
        String URLparameters = "Nombre=" + pName + "&Apellido=" + pLastName + "&Cedula=" + pIdNumber + "&Telefono=" + pPhoneNumber;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(insert_Client_PHP, URLparameters));
            System.out.print(myResponse.getString("status"));
            if (myResponse.getString("status").equals("true")){
                return Integer.parseInt(myResponse.getJSONObject("value").getString("orderID"));
            }
            else{
                return -1;
            }
        }catch (JSONException e){ System.err.print(e);}
        return -1;
    }

    public ArrayList selectSucursales (){
        ArrayList<Sucursal> arraylistSucursal= new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_sucursales));
            if (myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    String IDBO = results.getJSONObject(i).getString("IdSuc");
                    String brachOffice = results.getJSONObject(i).getString("Nombre");
                    String Direccion = results.getJSONObject(i).getString("Direccion");
                    String telfPedidos = results.getJSONObject(i).getString("Telf-Pedidos");
                    String telfSuc = results.getJSONObject(i).getString("Telf-Sucursal");
                    Sucursal s = new Sucursal(IDBO, brachOffice, Direccion, telfSuc, telfPedidos);
                    arraylistSucursal.add(s);
                }
            }else{System.out.print("No hay sucursales en la base de datos");}
        }catch (JSONException e){ System.out.println(e);}
        return arraylistSucursal;
    }

    public ArrayList selectPedidos_porCliente(){
        ArrayList<Pedido> arraylistPedidos = new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_pedidos_de_usuario_PHP+"?IdCliente="+User.getCurrentUser().getUserID()));
            if (myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    int idPedido = results.getJSONObject(i).getInt("IdPedido");
                    String fecha = results.getJSONObject(i).getString("FechaHora");
                    String precio = results.getJSONObject(i).getString("TotalAPagar");
                    String estado = results.getJSONObject(i).getString("Estado");
                    ArrayList<ShoppingList_Product> listaProductosPedido=selectProductos_porPedido(Integer.toString(idPedido));
                    Pedido p = new Pedido(idPedido, fecha, precio, estado, listaProductosPedido);
                    arraylistPedidos.add(p);
                }
            }else{System.out.print("No existe el pedido");}
        }catch (JSONException e){ System.out.println(e);}
        return arraylistPedidos;
    }


    public ArrayList selectProductos_porPedido(String pIdPedido){
        ArrayList<ShoppingList_Product> listaRetorno = new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_productos_por_pedido_PHP+"?IdPedido="+pIdPedido));
            if(myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    int Cantidad = results.getJSONObject(i).getInt("Cantidad");
                    String Nombre = results.getJSONObject(i).getString("Nombre");
                    int PrecioUnitario = results.getJSONObject(i).getInt("PrecioUnitario");
                    ShoppingList_Product shoppingList_product = new ShoppingList_Product(Nombre,null,null, PrecioUnitario,null,Cantidad);
                    listaRetorno.add(shoppingList_product);
                }
            }else{System.out.print("No sirvo");}
        }catch (JSONException e){ System.out.println(e);}
        return listaRetorno;
    }

    public ArrayList selectPedidos_pendientes(){
        ArrayList<Pedido> arraylistPedidos = new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_pedidos_pendientes_PHP));
            if (myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    int idPedido = results.getJSONObject(i).getInt("IdPedido");
                    String fecha = results.getJSONObject(i).getString("FechaHora");
                    String precio = results.getJSONObject(i).getString("TotalAPagar");
                    String estado = results.getJSONObject(i).getString("Estado");
                    ArrayList<ShoppingList_Product> listaProductosPedido=selectProductos_porPedido(Integer.toString(idPedido));
                    Pedido p = new Pedido(idPedido, fecha, precio, estado, listaProductosPedido);
                    arraylistPedidos.add(p);
                }
            }else{System.out.print("No existe el pedido");}
        }catch (JSONException e){ System.out.println(e);}
        return arraylistPedidos;
    }

    public boolean SetDelivered(String IdPedido){
        try {
            JSONObject myResponse = new JSONObject(GETRequest(set_pedido_entregado_PHP+"?IdPedido="+IdPedido));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ System.out.println(e);}
        return false;
    }
}
