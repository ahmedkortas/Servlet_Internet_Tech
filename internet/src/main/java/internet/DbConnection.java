package internet;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bouncycastle.crypto.generators.BCrypt;


public class DbConnection {
    //create a local database connection constructor
	Connection conn;
	
    public DbConnection() throws ClassNotFoundException {
        //connect to mariaDB with username = root and password = ahmed
        //and database name = bushshop
        //and host = localhost
        //and port = 3306
        //and driver = com.mysql.jdbc.Driver
        //and url = jdbc:mysql://localhost:3306/bushshop
        //and user = root
        //and password = ahmed
    	
//    	Class.forName("com.mysql.jdbc.Driver")
        try {
        	 Class.forName("org.mariadb.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db", "root", "ahmed");
             
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //a method that adds a book to the table books 
    public void addBook(String title, int author_id, String book_isbn, float price, String description, int genre_id) throws SQLException {
        Statement stmt = conn.createStatement();
        try{
            stmt.executeUpdate("INSERT INTO books VALUES ('" + title + "','" + author_id + "','" + book_isbn + "','" + price + "','" + description + "','" + genre_id + "')");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //create a method that checks if the author exists in the table author by the name given in the parameter
    //if not create a new author and return the author id
    //if yes return the author id
    public int cheackAuthorAndCreate(String authorName) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM author WHERE author_name = '" + authorName + "'");
        if(rs.next()) {
            return rs.getInt("author_id");
       }else {
           stmt.executeUpdate("INSERT INTO author (author_name) VALUES('" + authorName + "')");
           rs = stmt.executeQuery("SELECT * FROM author WHERE author_name = '" + authorName + "'");
           rs.next();
           return rs.getInt("author_id");
        }
    }

    public int checkGenre(String genreName) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM genre WHERE genre_name = '" + genreName + "'");
        System.out.println("we are here ");
        if(rs.next()) {
            return -1;
        }else {
        	System.out.println("genre added");
            stmt.executeUpdate("INSERT INTO genre (genre_name) VALUES('" + genreName + "')");
            rs = stmt.executeQuery("SELECT * FROM genre WHERE genre_name = '" + genreName + "'");
            rs.next();
            return rs.getInt("genre_id");
        }
    }


    public boolean checkISBN(String isbn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE book_isbn = '" + isbn + "'");
        if(rs.next()) {
            return true;
        }else {
            return false;
        }
    }
    //create a method that will query the database for all genres and return them as a list of genres
    public List<String> getGenres() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM genre");
        List<String> genres = new ArrayList<>();
        while(rs.next()) {
            //get the parameter name and id 
            String name = rs.getString("genre_name");
            //create a new genre object with the name and id
            //add the genre to the list
            genres.add(name);
        }
        return genres;
    }


    public Book getBookByIsbn(String isbn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE book_isbn = '" + isbn + "'");
        rs.next();
        int auth_id=rs.getInt("author_id");
    	ResultSet author_Name =  stmt.executeQuery(" SELECT author_name from author Where author_id ="+auth_id);
        author_Name.next();
        String authorName= author_Name.getNString("author_name");
    	Book book =(new Book(rs.getString("title"), authorName, rs.getString("book_isbn"), rs.getFloat("price"), rs.getString("description")));
       return book;
    }
    
    public List<Genre> getAllGenres() throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM genre");
        List<Genre> genres = new ArrayList<>();
        while(rs.next()) {
            //get the parameter name and id 
            //create a new genre object with the name and id
            //add the genre to the list
            genres.add(new Genre(rs.getString("genre_name"),rs.getInt("genre_id")));
        }
        return genres;
    }


    //create a method that adds to the table buch_kategory the book isbn and the genre_id
    public void addBuchCategory(String isbn, int genre_id) throws SQLException {
        Statement stmt = conn.createStatement();
        System.out.println(isbn);
        System.out.println(genre_id);
        stmt.executeUpdate("INSERT INTO buch_kategorie (book_isbn, genre_id) VALUES('" + isbn + "','" + genre_id + "')");
    }


    //create a method that cheks if a genre name exists in

    public boolean addBook(Book book) throws SQLException {
        Statement stmt = conn.createStatement();
        if(checkISBN(book.getIsbn())) {
            return false;
        }else {
            int author_id = cheackAuthorAndCreate(book.getAuthorName());
            double x= (double)book.getPrice();
            x=Math.round(x* 100.0) / 100.0;
            stmt.executeUpdate("INSERT INTO books (title, book_isbn , author_id , price , description ) VALUES('" + book.getTitle() + "','" + book.getIsbn() + "','" + author_id + "','" + x + "','" + book.getDescription() + "')");
            return true;
        }
    }

//    public ArrayList<Book> getBooksByGenre(String genreName) throws SQLException {
//        Statement stmt = conn.createStatement();
//        String query = "SELECT * FROM books ";
//        ResultSet books = stmt.executeQuery(query);
//        ArrayList<Book> booksList = new ArrayList<Book>();
//        while(books.next()) {
//            String[] bookGenre = books.getString("genre_name").split(",");
//            if(bookGenre.contains(genreName)) {
//                booksList.add(new Book(books.getString("title"), books.getInt("author_id"), books.getString("book_isbn"), books.getFloat("price"), books.getString("description"), books.getString("genre_name")));
//            }
//        }
//        return books;
//    }

    //create a method that queries the table author 
    //and returns the author name and the author id
    public List<Author> getAuthors() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM author");
        List<Author> authorList = new ArrayList<>();
        int id =-1;
        String name="";
        while(rs.next()) {
        	id=rs.getInt("author_id");
        	name=rs.getString("author_name");
        }
        Author author=new Author(id,name);
        authorList.add(author);
        authorList.remove(0);
        return authorList;
    }



    public int getUserId(String email) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM customers where email = '" + email + "'");
        rs.next();
        return rs.getInt("customer_id");
    }

    //create a method that taken a parameter of genre_name
    //returns a list of books that are in that genre
    //innerjoing the table books and buch_kategorie to get the book isbn
    //innerjoining the table genre to get the genre name
    public List<Book> getBooksByGenre(String genreName) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM books INNER JOIN buch_kategorie ON books.book_isbn = buch_kategorie.book_isbn INNER JOIN genre ON buch_kategorie.genre_id = genre.genre_id WHERE genre.genre_name = '" + genreName + "'";
        ResultSet books = stmt.executeQuery(query);
        ArrayList<Book> booksList = new ArrayList<Book>();
        while(books.next()) {
        	int auth_id=books.getInt("author_id");
        	ResultSet author_Name =  stmt.executeQuery(" SELECT author_name from author Where author_id ="+auth_id);
            author_Name.next();
            String authorName= author_Name.getNString("author_name");
            booksList.add(new Book(books.getString("title"), authorName, books.getString("book_isbn"), books.getFloat("price"), books.getString("description")));
        }
        return booksList;
    }

    public List<String> getAllGenresOfBooks(String isbn) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM genre INNER JOIN buch_kategorie ON genre.genre_id = buch_kategorie.genre_id WHERE buch_kategorie.book_isbn = '" + isbn + "'");
        List<String> genres = new ArrayList<>();
        while(rs.next()) {
            //get the parameter name and id 
            //create a new genre object with the name and id
            //add the genre to the list
            genres.add(rs.getString("genre_name"));
            System.out.println(rs.getString("genre_name"));
        }
        return genres;
    }


    public List<Book> getAllBooks() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books");
        List<Book> booksList = new ArrayList<>();
        while(rs.next()) {
        	int auth_id=rs.getInt("author_id");
        	ResultSet author_Name =  stmt.executeQuery(" SELECT author_name from author Where author_id ="+auth_id);
            author_Name.next();
            String authorName= author_Name.getNString("author_name");
        	booksList.add(new Book(rs.getString("title"), authorName, rs.getString("book_isbn"), rs.getFloat("price"), rs.getString("description")));
        }
        return booksList;
    }

	public boolean registerUser(String firstName, String lastName, String password, String email, String birthday, String address) {
        System.out.println("registerUser");
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO customers (customer_first_name, customer_last_name, password_user, email, birdate, address) VALUES('" + firstName + "','" + lastName + "','" + password + "','" + email + "','" + birthday + "','" + address + "')");
            System.out.println("register");
            return true;
        } catch (SQLException e) {
            System.out.println("register failed");
            e.printStackTrace();
            return false;
        }
	}

    public boolean verifyLogin(String email, String password) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE email = '" + email + "' AND password_user = '" + password + "'");
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public Customer getKunde(String email) {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE email = '" + email + "'");
            rs.next();
            Customer customer = new Customer(rs.getString("customer_first_name"), rs.getString("customer_last_name"), rs.getString("password_user"), rs.getString("email"), rs.getString("birdate"), rs.getString("address"));
            return customer;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
	}

    public int createOrder(String email){
        try{
            Statement stmt = conn.createStatement();
            Date date = new Date();
            ResultSet rs = stmt.executeQuery("SELECT customer_id From customers WHERE email = '" + email + "'");
            rs.next();
            int customer_id = rs.getInt("customer_id");
            System.out.println(date);
            stmt.executeUpdate("INSERT INTO orders (customer_id) VALUES('" + customer_id + "')");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM orders INNER JOIN customers ON orders.customer_id = customers.customer_id WHERE customers.email = '" + email + "' AND status =1");
            rs2.next();
            return rs2.getInt("order_id");
        }catch(SQLException e){
            return -1;
        }
    }

    public int getOrderId(String email) {
        try {
            Statement stmt = conn.createStatement();
            //select the order_id from the table Orders with the customer_id
            //innerjoin the table customer to get the customer_email and customer_id
            //where the customer_email is the same as the parameter email in table customers
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders INNER JOIN customers ON orders.customer_id = customers.customer_id WHERE customers.email = '" + email + "' AND status =1");
            if(rs.next()) {
                return rs.getInt("order_id");
            }else{
                return createOrder(email);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void addToCart(String iSBN, int orderId) {
		//add the isbn and order_id to the cart
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO cart (book_isbn, order_id) VALUES('" + iSBN + "','" + orderId + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

    public void delete(String iSBN, int orderId) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM cart WHERE book_isbn = '" + iSBN + "' AND order_id = '" + orderId + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CartItems> getAllBooksForOrder(int orderId) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cart INNER JOIN books ON cart.book_isbn = books.book_isbn WHERE cart.order_id = '" + orderId + "'");
            List<CartItems> booksList = new ArrayList<>();
            while(rs.next()) {
                String book_isbn = rs.getString("book_isbn");
                ResultSet rsName = stmt.executeQuery("SELECT title ,price  FROM books WHERE book_isbn = '" + book_isbn + "'");
                rsName.next();
                String book_title = rsName.getString("title");
                int quantity = rs.getInt("quantity");
                booksList.add(new CartItems(book_title, rs.getFloat("price"), quantity, rs.getInt("cart_id")));
            }
            return booksList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateQuantity(int card_id, int quantity) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE cart SET quantity = '" + quantity + "' WHERE cart_id = '" + card_id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void emptyCart(int orderId) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM cart WHERE order_id = '" + orderId + "'");
            stmt.executeUpdate("DELETE FROM orders WHERE order_id = '" + orderId + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteCartItem(int card_id) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM cart WHERE cart_id = '" + card_id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void changeOrderStatus(int order_id){
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE orders SET status = '0' WHERE order_id = '" + order_id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int payedOrder(int order_id, String email, String paymentMethod ,int paymentStatus){
        try {
        	Statement stmt = conn.createStatement();
            int client_id = (getUserId(email));
            //insert the data to table billing
            stmt.executeUpdate("INSERT INTO billing (order_id, customer_id, payment_method, payment_status) VALUES('" + order_id + "','" + client_id + "','" + paymentMethod + "','" + paymentStatus + "')");
            changeOrderStatus(order_id);
            createOrder(email);
            ResultSet rs=stmt.executeQuery("select * from billing where order_id = '" + order_id + "' and customer_id = '" + client_id + "'");
            rs.next();
            int billing_id = rs.getInt("id");
            return billing_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return -1;
    }


    public void rechnungSaving(RechnungAddress rs, int billing_id){
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO rechnunginfo (billing_id, street, plz, ort, land) VALUES('" + billing_id + "','" + rs.getStreet() + "','" + rs.getPlz() + "','" + rs.getOrt() + "','" + rs.getLand() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveCard(KarteDetails kd , int billing_id){
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO card_info (billing_id, card_number, card_name) VALUES('" + billing_id + "','" + kd.getKarteNummer() + "','" + kd.getKarteName() + "')");
        }catch( SQLException e){
            e.printStackTrace();
        }
    }

}
