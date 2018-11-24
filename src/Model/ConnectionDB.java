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
    private static final String login_PHP = URL_HOST + "RestaurantePHP/Usuario/login.php";
    private static final String select_products_by_productType_PHP = URL_HOST + "RestaurantePHP/Producto/select_products_by_productType.php";
    private static final String makeOrder = URL_HOST + "RestaurantePHP/Pedido/makeOrder.php";
    private static final String buyProduct = URL_HOST + "RestaurantePHP/Pedido/buyProduct.php";
    private static final String search_product_PHP = URL_HOST +"RestaurantePHP/Producto/search_product.php";
    private static final String add_product_PHP = URL_HOST +"RestaurantePHP/Producto/insert_product.php";
    private static final String makeCombo= URL_HOST + "RestaurantePHP/Pedido/makeCombo.php";
    private static final String addProductCombo = URL_HOST + "RestaurantePHP/Pedido/addProductCombo.php";
    private static final String insert_Client_PHP = URL_HOST+"RestaurantePHP/Usuario/insert_client.php";
    private static final String create_user_PHP = URL_HOST + "RestaurantePHP/Usuario/create_user.php";
    private static final String select_sucursales = URL_HOST + "RestaurantePHP/Sucursal/select_sucursales.php";
    private static final String select_pedidos_de_usuario_PHP = URL_HOST + "RestaurantePHP/Pedido/select_pedido_usuario.php";
    private static final String select_productos_por_pedido_PHP= URL_HOST + "RestaurantePHP/Producto/select_productos_por_pedido.php";
    private static final String select_pedidos_pendientes_PHP = URL_HOST + "RestaurantePHP/Pedido/select_pedidos_pendientes.php";
    private static final String set_pedido_entregado_PHP = URL_HOST + "RestaurantePHP/Pedido/set_pedido_entregado.php";
    private static final String set_pedido_enviado_PHP = URL_HOST + "RestaurantePHP/Pedido/set_pedido_enviado.php";
    private static final String generateBill = URL_HOST+"RestaurantePHP/Pedido/generateBill.php";
    private static final String is_assigned_PHP = URL_HOST+"RestaurantePHP/Producto/is_assigned_product.php";
    private static final String update_product_PHP = URL_HOST+"RestaurantePHP/Producto/update_product.php";
    private static final String insertPedidoSucursal = URL_HOST+"RestaurantePHP/Pedido/insertPedidoSucursal.php";
    private static final String insertEvaluacion = URL_HOST+"RestaurantePHP/Pedido/insertEvaluacion.php";
    private static final String select_combos = URL_HOST+"RestaurantePHP/Producto/select_combos.php";
    private static final String select_productos_combo = URL_HOST+"RestaurantePHP/Producto/select_productos_combo.php";
    private static final String select_puestos= URL_HOST + "RestaurantePHP/Usuario/select_puestos.php";
    private static final String insert_empleado = URL_HOST + "RestaurantePHP/Usuario/insert_empleado.php";


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
            String readLine;
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
        }catch(IOException e){e.printStackTrace();}
        return response.toString();
    }


    private String GETRequest(String url){
        StringBuilder response = new StringBuilder();
        try{
            URL urlForGetRequest = new URL(url);
            String readLine;
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
        }catch(IOException e){e.printStackTrace();}
        return response.toString();
    }

    public String login(String user, String password){
        String userType = "";
        try{
            JSONObject myResponse = new JSONObject(GETRequest(login_PHP+"?UserId="+user+"&Password="+password));
            if (myResponse.getString("status").equals("true")){
                userType = myResponse.getJSONObject("value").getString("NombreTipoUsuario");
                String email = myResponse.getJSONObject("value").getString("Correo");
                if (userType.equals("Empleado")){
                    String EmID = myResponse.getJSONObject("value").getString("IdEm");
                    String job = myResponse.getJSONObject("value").getString("NombrePuesto");
                    String sucursalID = myResponse.getJSONObject("value").getString("IdSuc");
                    String sucursalName = myResponse.getJSONObject("value").getString("NombreSucursal");
                    User u = new User(EmID,userType,job,sucursalName,sucursalID,email);
                    User.setCurrentUser(u);


                }else if(userType.equals("Cliente")){
                    String ClientID = myResponse.getJSONObject("value").getString("IdCliente");
                    User u = new User(ClientID,userType,"","","",email);
                    User.setCurrentUser(u);
                }
            }else{
                System.out.print("No existe el usuario");
            }
        }catch (JSONException e){ e.printStackTrace();}
        return userType;
    }


    public ArrayList<Product> selectProductInventory_byType (String productType){
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
                    int state = results.getJSONObject(i).getInt("Estado");
                    Product p = new Product(name, type, productID, prize, detail, state);
                    arraylistProducto.add(p);
                }
            }else{System.out.print("No existe el producto");}
        }catch (JSONException e){ e.printStackTrace();}
        return arraylistProducto;
    }

    public int is_assigned (int productTd){
        try{
            JSONObject myResponse = new JSONObject(GETRequest(is_assigned_PHP+"?Id="+productTd));
            if (myResponse.getString("status").equals("true")){
                return myResponse.getJSONObject("value").getInt("total");
            }else{System.out.println("No existe el producto2");}
        }catch (JSONException e){ System.out.println(e);}
        return 0;
    }

    public String makeOrder(String ClientID, String DateTime, String OrderType, String Price, String Sucursal, String SucursalID, String DireccionEntrega, String Estado){

        String URLparameters = "ClientID=" + ClientID + "&Datetime=" + DateTime + "&OrderType=" + OrderType + "&TotalAPagar=" + Price +"&Estado="+Estado+ "&IdSucursal" + Sucursal + "&DireccionEntrega="+DireccionEntrega;
        try{
            System.out.print("------------------------------------------------------------------------\n");
            JSONObject myResponse = new JSONObject(POSTrequest(makeOrder, URLparameters));
            if (myResponse.getString("status").equals("true")) {
                String orderID = myResponse.getJSONObject("value").getString("orderID");
                System.out.print(orderID);
                JSONObject myResponse2 = new JSONObject(POSTrequest(insertPedidoSucursal, "IdSucursal="+SucursalID+"&IdPedido="+orderID));
                if (myResponse2.getString("status").equals("true")) {
                    return orderID;
                }
            }
        }catch (JSONException e){ e.printStackTrace();}
        return null;
    }

    public String makeCombo(String ComboDesc, String DateTime, String Discount){
        String URLparameters = "ComboDesc=" + ComboDesc + "&Datetime=" + DateTime + "&Discount=" + Discount;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(makeCombo, URLparameters));
            if (myResponse.getString("status").equals("true")) {
                return myResponse.getJSONObject("value").getString("comboID");
            }
        }catch (JSONException e){ e.printStackTrace();}
        return null;
    }

    public void addProductCombo(String IdCombo, String IdProducto, String Cantidad){
        String URLparameters = "IdCombo=" + IdCombo + "&IdProducto=" + IdProducto + "&Cantidad=" + Cantidad;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(addProductCombo, URLparameters));
            if (myResponse.getString("status").equals("true")) {
                System.out.print("Producto añadido al combo \n");
            }else{System.out.print("Producto NO añadido al combo \n");}
        }catch (JSONException e){ e.printStackTrace();}
    }

    public boolean searchProduct(String ProductName){
        try {
            JSONObject myResponse = new JSONObject(POSTrequest(search_product_PHP, "Nombre="+ProductName));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ e.printStackTrace();}
        return false;
    }

    public boolean insertProduct(String pName, String pType, String pDescription, String pPrice){
        String URLparameters = "Nombre="+pName+"&Tipo="+pType+"&Descripcion="+pDescription+"&Precio="+pPrice;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(add_product_PHP, URLparameters));
            System.out.print(myResponse.getString("status"));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ e.printStackTrace();}
        return false;
    }

    public boolean updateProduct(String pName, String pType, String pDescription, int pPrice, int pState, int pId){
        String URLparameters = "Nombre="+pName+"&Tipo="+pType+"&Descripcion="+pDescription+"&Precio="+pPrice+"&Estado="
                +pState+"&Id="+pId;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(update_product_PHP, URLparameters));
            System.out.print(myResponse.getString("status"));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ e.printStackTrace();}
        return false;
    }

    public void buyProduct(String productID, String productQuantity, String orderID, String prize){
        String URLparameters = "IdProducto=" + productID + "&IdPedido=" + orderID + "&Cantidad=" + productQuantity + "&Precio=" + prize;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(buyProduct, URLparameters));
            if (myResponse.getString("status").equals("true")) {
                System.out.print("Producto comprado \n");
            }else{System.out.print("Error, producto no comprado \n");}
        }catch (JSONException e){ e.printStackTrace();}
    }

    public boolean createUser(String pUsername,String pPassword,String pEmail, String pIdClient){
        String URLparameters = "NombreUsuario=" + pUsername + "&Contrasenia=" + pPassword + "&Correo=" + pEmail + "&IdCliente=" + pIdClient;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(create_user_PHP, URLparameters));
            System.out.print(myResponse.getString("status"));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ e.printStackTrace();}
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
        }catch (JSONException e){ e.printStackTrace();}
        return -1;
    }

    public ArrayList<Sucursal> selectSucursales (){
        ArrayList<Sucursal> arraylistSucursal= new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_sucursales));
            if (myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    String IDBO = results.getJSONObject(i).getString("IdSuc");
                    String brachOffice = results.getJSONObject(i).getString("NombreSucursal");
                    String Direccion = results.getJSONObject(i).getString("Direccion");
                    String telfPedidos = results.getJSONObject(i).getString("Telf-Pedidos");
                    String telfSuc = results.getJSONObject(i).getString("Telf-Sucursal");
                    Sucursal s = new Sucursal(IDBO, brachOffice, Direccion, telfSuc, telfPedidos);
                    arraylistSucursal.add(s);
                }
            }else{System.out.print("No hay sucursales en la base de datos");}
        }catch (JSONException e){ e.printStackTrace();}
        return arraylistSucursal;
    }

    public ArrayList<Pedido> selectPedidos_porCliente(){
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
                    ArrayList<ShoppingList_Product> listaProductosPedido= selectProductos_porPedido(Integer.toString(idPedido));
                    Pedido p = new Pedido(idPedido, fecha, precio, estado, listaProductosPedido);
                    arraylistPedidos.add(p);
                }
            }else{System.out.print("No existe el pedido");}
        }catch (JSONException e){ e.printStackTrace();}
        return arraylistPedidos;
    }

    private ArrayList<ShoppingList_Product> selectProductos_porPedido(String pIdPedido){
        ArrayList<ShoppingList_Product> listaRetorno = new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_productos_por_pedido_PHP+"?IdPedido="+pIdPedido));
            if(myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    int Cantidad = results.getJSONObject(i).getInt("Cantidad");
                    String Nombre = results.getJSONObject(i).getString("Nombre");
                    int PrecioUnitario = results.getJSONObject(i).getInt("PrecioUnitario");
                    ShoppingList_Product shoppingList_product = new ShoppingList_Product(Nombre,null,null, PrecioUnitario,null,Cantidad, 1);
                    listaRetorno.add(shoppingList_product);
                }
            }else{System.out.print("No sirvo");}
        }catch (JSONException e){ e.printStackTrace();}
        return listaRetorno;
    }

    public ArrayList<Pedido>  selectPedidos_pendientes(){
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
        }catch (JSONException e){ e.printStackTrace();}
        return arraylistPedidos;
    }

    public boolean SetDelivered(String IdPedido){
        try {
            JSONObject myResponse = new JSONObject(GETRequest(set_pedido_entregado_PHP+"?IdPedido="+IdPedido));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ e.printStackTrace();}
        return false;
    }

    public boolean SetSend(String IdPedido){
        try {
            JSONObject myResponse = new JSONObject(GETRequest(set_pedido_enviado_PHP+"?IdPedido="+IdPedido));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ e.printStackTrace();}
        return false;
    }

    public void generateBill(String formapago, String bankCard, String orderID, String total){
        String URLparameters = "IdPedido=" + orderID + "&TarjetaBancaria=" + bankCard + "&FormaPago=" + formapago + "&Total=" + total;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(generateBill, URLparameters));
            System.out.print(myResponse.getString("status"));
        }catch (JSONException e){ e.printStackTrace();}
    }

    public Boolean sendEvaluation(String fecha, String cliente, String pedido, String comentarios, String facilidad, String calidadServicio, String calidadComida){
        String URLparameters = "fecha=" + fecha + "&cliente=" + cliente + "&pedido=" + pedido + "&comentarios=" + comentarios + "&facilidad=" + facilidad + "&calidadServicio=" + calidadServicio + "&calidadComida=" + calidadComida;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(insertEvaluacion, URLparameters));
            return myResponse.getString("status").equals("true");
        }catch (JSONException e){ e.printStackTrace();}
        return false;
    }

    public ArrayList<Puesto>  select_puestos(){
        ArrayList<Puesto> arraylistPuestos = new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_puestos));
            if (myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    String IdPuesto = results.getJSONObject(i).getString("IdPuesto");
                    String NombrePuesto = results.getJSONObject(i).getString("NombrePuesto");
                    Puesto p = new Puesto(IdPuesto, NombrePuesto);
                    arraylistPuestos.add(p);
                }
            }else{System.out.print("No existe el pedido");}
        }catch (JSONException e){ e.printStackTrace();}
        return arraylistPuestos;
    }

    public String insertEmpleado(String pName, String pLastName, String pIdNumber, String pJob, String pBankAcc, String pSalary){
        String URLparameters = "Nombre=" + pName + "&Apellido=" + pLastName + "&Cedula=" + pIdNumber + "&Puesto=" + pJob + "&CuentaBancaria=" + pBankAcc + "&Salario=" + pSalary;
        try{
            JSONObject myResponse = new JSONObject(POSTrequest(insert_empleado, URLparameters));
            System.out.print(myResponse.getString("status"));
            if (myResponse.getString("status").equals("true")){
                return myResponse.getJSONObject("value").getString("orderID");
            }
        }catch (JSONException e){ e.printStackTrace();}
        return null;
    }

    public ArrayList<Combo> selectCombos(){
        ArrayList<Combo> arrayCombos = new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_combos));
            if (myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    String idCombo = results.getJSONObject(i).getString("IdCombo");
                    String descripcion = results.getJSONObject(i).getString("Descripcion");
                    String fecha = results.getJSONObject(i).getString("Fecha");
                    String descuento = results.getJSONObject(i).getString("Descuento");

                    ArrayList<ShoppingList_Product> listaProdcutosCombo=selectProductosCombo(idCombo);
                    Combo c = new Combo(idCombo,descripcion,fecha,descuento,listaProdcutosCombo);
                    arrayCombos.add(c);
                }
            }else{System.out.print("No existe el pedido");}
        }catch (JSONException e){ e.printStackTrace();}
        return arrayCombos;
    }

    public ArrayList<ShoppingList_Product> selectProductosCombo(String idCombo){
        ArrayList<ShoppingList_Product> listaProductosCombo = new ArrayList<>();
        try{
            JSONObject myResponse = new JSONObject(GETRequest(select_productos_combo+"?IdCombo="+idCombo));
            if (myResponse.getString("status").equals("true")){
                JSONArray results = myResponse.getJSONArray("value");
                for (int i = 0; i < results.length(); i++) {
                    String nombre = results.getJSONObject(i).getString("Nombre");
                    String tipoProducto = results.getJSONObject(i).getString("TipoProducto");
                    String idPoducto = results.getJSONObject(i).getString("IdProd");
                    int precio = results.getJSONObject(i).getInt("PrecioUnitario");
                    String detalle = results.getJSONObject(i).getString("Detalle");
                    int estado = results.getJSONObject(i).getInt("Estado");
                    int cantidad = results.getJSONObject(i).getInt("Cantidad");
                    ShoppingList_Product nuevoProducto = new ShoppingList_Product(nombre,tipoProducto,idPoducto,precio,detalle,estado,cantidad);
                    listaProductosCombo.add(nuevoProducto);
                }
            }else{System.out.print("Error, no existen productos para el combo.");}
        }catch (JSONException e){ e.printStackTrace();}
        return listaProductosCombo;
    }


}
