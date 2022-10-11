public class User {
    private String userID;
    private String userName;
    private String userPassword;
    private String userImage;
    private String userRole;
    private String userPhone;
    private String userEmail;
    private String userAddress;
    private String userCountry;
    private boolean userActive;

    public User() {}

    public User(String userName, String userPassword, String userImage, String userRole, String userPhone, String userEmail, String userAddress, String userCountry, boolean userActive) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.userRole = userRole;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userCountry = userCountry;
        this.userActive = userActive;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public boolean isUserActive() {
        return userActive;
    }

    public void setUserActive(boolean userActive) {
        this.userActive = userActive;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userImage='" + userImage + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userCountry='" + userCountry + '\'' +
                ", userActive=" + userActive +
                '}';
    }
}