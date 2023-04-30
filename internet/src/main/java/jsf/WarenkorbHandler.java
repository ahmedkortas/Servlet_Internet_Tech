
 

package jsf;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import internet.Book;
import internet.DbConnection;
import internet.Customer;
import internet.Genre;
import internet.CartItems;



@Named("WarenkorbHandler")
@RequestScoped
public class WarenkorbHandler {
	@Inject
	@ManagedProperty("#{LoginHandler.client}")
	private Customer client;
	private int orderId;
	private String ISBN;

	private static DecimalFormat df = new DecimalFormat("0.00");
	private List<CartItems> bucher;

	public String getISBN() {
		return ISBN;
	}

	public void setBucher(List<CartItems> bucher) {
		this.bucher = bucher;
	}

	public List<CartItems> getBucher() throws ClassNotFoundException {
		DbConnection db = new DbConnection();
		List<CartItems> bucher =db.getAllBooksForOrder(client.getOrderId());
		setBucher(bucher);
		return bucher;
	}

	public void setISBN(String isbn) throws ClassNotFoundException {
		DbConnection db = new DbConnection();
		ISBN = isbn;
		if (this.client != null){
			db.addToCart(ISBN, this.client.getOrderId());}
		else{
			return;
		}
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public List<Genre> getGenres() throws ClassNotFoundException {
		DbConnection db = new DbConnection();
		List<Genre> li=null;
		try {
			li = db.getAllGenres();
		}catch(SQLException e) {
			System.out.println(e);
		}
		return li;
	}
	
	public Customer getClient() {
		System.out.println(this.client);
		return this.client;
	}

	public float getGesamtSumme(){
        float sum = (float) 0.0;
		for (CartItems b : bucher) {
			sum += b.getItemTotal();
		}
		DecimalFormat df = new DecimalFormat("#.##");
        String formatHolder = (df.format(sum));
		return sum;
    }

	public void delete() throws ClassNotFoundException {
		DbConnection db = new DbConnection();
		db.emptyCart(this.client.getOrderId());
	}

	public void loeschen(int card_id) throws ClassNotFoundException {
		DbConnection db = new DbConnection();
		db.deleteCartItem(card_id);
	}

}
