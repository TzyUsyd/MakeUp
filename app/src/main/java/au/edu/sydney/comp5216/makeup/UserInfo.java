package au.edu.sydney.comp5216.makeup;

public class UserInfo {
    public static final int CUSTOMER = 1;
    public static final int  ARTIST = 2;
    public String uid;
    public int role;
    public String  email;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
