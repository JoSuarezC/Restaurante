package Model;

public class User {
    private String userID;
    private String userType;
    private String job;
    private String sucursalName;
    private String sucursalID;
    private static User currentUser;

    public User(String userID, String userType, String job, String sucursalName, String sucursalID) {
        this.userID = userID;
        this.userType = userType;
        this.job = job;
        this.sucursalName = sucursalName;
        this.sucursalID = sucursalID;
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
}
