//create a class book  
package internet;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Book {
    private String title;
    private String author_id;
    private String book_isbn;
    private double price;
    private String description;
    //takes a database query result and creates a book object
//    public Book(ResultSet rs) throws SQLException {
//        System.out.println(rs);
//    }

    public Book (String title, String author, String book_isbn, float price, String description) {
        this.title = title;
        this.author_id = author;
        this.book_isbn = book_isbn;
        this.price =  price;
        this.description = description;
    }

	public String getIsbn() {
		return this.book_isbn;
	}
    public String getTitle() {
        return this.title;
    }
    public String getAuthorName() {
        return this.author_id;
    }
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
   
    public String getDescription() {
        return this.description;
    }

}
