package Model;

public class User {
    private String userID;
    private String userType;
    private String job;
    private static User currentUser;

    public User(String userID, String userType, String job) {
        this.userID = userID;
        this.userType = userType;
        this.job = job;
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
