package Model;

public class ConnectionDB {

    private static ConnectionDB instance;

    // Server IP
    private static final String URL_HOST = "http://restaurante-ak7.esy.es/";

    /* PHP login */
    public static final String login_PHP = URL_HOST + "RestaurantePHP/login.php";


    public ConnectionDB(){

    }

    public static ConnectionDB getInstance(){
        if (instance.equals(null)){
            instance = new ConnectionDB();
        }
        return instance;
    }

    public String login(String email, String password){
        return "";
    }

}
