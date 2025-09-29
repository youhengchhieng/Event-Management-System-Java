package eventmanagement.users;

public abstract class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    
    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // Abstract method to be implemented by subclasses
    public abstract String getUserType();
    
    // Getters and setters
    public String getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return "User ID: " + userId + 
               "\nName: " + name + 
               "\nEmail: " + email + 
               "\nPhone: " + phone;
    }
}
