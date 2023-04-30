package jsf;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;


import internet.DbConnection;
import internet.Book;
import internet.Customer;


@Named("LoginHandler")
@SessionScoped
public class LoginHandler implements Serializable { 
   


    private static final long serialVersionUID = 1L; 
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String birthday;
    private String address;
    private boolean loggedIn = false;

    private Customer client;
    
    public Customer getClient() {
    	return this.client;
    }
    
    public void setClient(Customer client) {
    	this.client = client;
    }

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }   
    
    public String login() throws ClassNotFoundException {
		DbConnection db = new DbConnection();   
		System.out.println("login");
        if(email ==""||password=="") {
        	 FacesContext.getCurrentInstance().addMessage("Form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "F�llen sie E-mail und Passwort aus."));
        	return null;
        }else {
        	 if (db.verifyLogin(email, password)) {
        		client = db.getKunde(email);
        		int orderId = db.getOrderId(client.getEmail());
        		client.setOrderId(orderId);
             	FacesContext.getCurrentInstance().addMessage("FormLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Willkommen "+client.getUsername()));
                setLoggedIn(true);
                setClient(client);
                System.out.println(orderId);
                System.out.println(client.getOrderId());
                System.out.println(client);
                return "Warenkorb.xhtml?faces-redirect=true";
             }else {
            	 FacesContext.getCurrentInstance().addMessage("Form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "E-mail oder Passwort falsch"));
            	 return null;	
             }
        }
    }
    
    public String logout() {
    	System.out.println(loggedIn);
        if(loggedIn) {
        	System.out.println("logout");
        	loggedIn = false;
        	FacesContext.getCurrentInstance().addMessage("Form", new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Auf Wiedersehen " +client.getFirstName()+"!"));
        	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        	setClient(null);
        	return "Login.xhtml?faces-redirect=true";
        }
        return null;
    }

    public String register() throws ClassNotFoundException  {
        System.out.println("RegisterHandler.register()");
        System.out.println("username: " + firstName);
    	 DbConnection db = new DbConnection();
     	 if(firstName==""||lastName==""||password==""||email=="" || birthday==null || address=="") {
     	 	FacesContext.getCurrentInstance().addMessage("Form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "F�llen sie alle Felder aus."));
     	return null;
     	 }
         else {
     	 	if(db.registerUser(firstName, lastName, password, email, birthday,address)) {
     	 		FacesContext.getCurrentInstance().addMessage("Form", new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registrierung erfolgreich"));
     	 	}else {
     	 		FacesContext.getCurrentInstance().addMessage("Form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Registrierung fehlgeschlagen"));
     	 	}
     	 }
        return "success";
    }

}