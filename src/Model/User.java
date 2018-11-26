package Model;

public class User {
    private String userID;
    private String userType;
    private String job;
    private String sucursalName;
    private String sucursalID;
    private String user_Email;
    private String name;
    private String lastnames;
    private String cedula;
    private String celular;
    private String telefono;
    private String username;
    private String password;
    private static User currentUser;

    public User(String userName) {
        this.userID = "";
        this.userType = "";
        this.job = "";
        this.sucursalName = "";
        this.sucursalID = "";
        this.user_Email="";
        this.username = userName;
    }

    public User(String userId, String userName) {
        this.userID = userId;
        this.userType = "";
        this.job = "";
        this.sucursalName = "";
        this.sucursalID = "";
        this.user_Email="";
        this.username = userName;
    }

    public User(String userID, String userType, String job, String sucursalName, String sucursalID, String user_Email) {
        this.userID = userID;
        this.userType = userType;
        this.job = job;
        this.sucursalName = sucursalName;
        this.sucursalID = sucursalID;
        this.user_Email=user_Email;
        this.username = "";
    }

    public User(String userID, String userType, String job, String sucursalName, String sucursalID, String user_Email,
                String name, String lastnames, String cedula, String celular, String telefono, String username, String password) {
        this.userID = userID;
        this.userType = userType;
        this.job = job;
        this.sucursalName = sucursalName;
        this.sucursalID = sucursalID;
        this.user_Email = user_Email;
        this.name = name;
        this.lastnames = lastnames;
        this.cedula = cedula;
        this.celular = celular;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    @Override
    public String toString(){
        if(userID.isEmpty())
            return username;
        else
            return userID + ") " + username;
    }

    public String getSucursalName() {
        return sucursalName;
    }

    public void setSucursalName(String sucursalName) {
        this.sucursalName = sucursalName;
    }

    public String getSucursalID() {
        return sucursalID;
    }

    public void setSucursalID(String sucursalID) {
        this.sucursalID = sucursalID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public static void setCurrentUser(User u){
        User.currentUser = u;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastnames() {
        return lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
