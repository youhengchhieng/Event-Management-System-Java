package eventmanagement.users;

public class Customer extends User {
    private String address;
    private String preferredEventType;
    
    public Customer(String userId, String name, String email, String phone, String address, String preferredEventType) {
        super(userId, name, email, phone);
        this.address = address;
        this.preferredEventType = preferredEventType;
    }
    
    @Override
    public String getUserType() {
        return "Customer";
    }
    
    // Additional methods specific to Customer
    public void requestQuote(String eventType) {
        System.out.println("Customer " + getName() + " is requesting a quote for " + eventType + " event.");
    }
    
    public void provideFeedback(String eventId, int rating) {
        System.out.println("Customer " + getName() + " provided a rating of " + rating + " for event " + eventId);
    }
    
    // Getters and setters
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPreferredEventType() {
        return preferredEventType;
    }
    
    public void setPreferredEventType(String preferredEventType) {
        this.preferredEventType = preferredEventType;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nUser Type: " + getUserType() +
               "\nAddress: " + address +
               "\nPreferred Event Type: " + preferredEventType;
    }
}
