package internet;

public class Customer {
    private String username; 
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String birthday;
    private String address;
    private int orderId;


    public Customer( String firstName, String lastName, String password, String email, String birthday, String address) {
        this.username = firstName+" "+lastName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
    }
    public void setAdress(String address) {
    	this.address=address;
    }
    public String getAddress() {
    	return this.address;
    }
    
    public int getOrderId() {
    	return this.orderId;
    }

    public void setOrderId(int orderId) {
    	this.orderId=orderId;
    }
    
    public String getUsername() {
        return username;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getBirthday() {
        return birthday;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
}
