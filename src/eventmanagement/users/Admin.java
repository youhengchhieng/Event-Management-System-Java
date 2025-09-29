package eventmanagement.users;

public class Admin extends User {
    private String department;
    private String role;
    
    public Admin(String userId, String name, String email, String phone, String department, String role) {
        super(userId, name, email, phone);
        this.department = department;
        this.role = role;
    }
    
    @Override
    public String getUserType() {
        return "Administrator";
    }
    
    // Additional methods specific to Admin
    public void generateReport() {
        System.out.println("Admin " + getName() + " is generating a report.");
    }
    
    public void authorizeAccess(String userId) {
        System.out.println("Admin " + getName() + " authorized access for user " + userId);
    }
    
    // Getters and setters
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nUser Type: " + getUserType() +
               "\nDepartment: " + department +
               "\nRole: " + role;
    }
}
