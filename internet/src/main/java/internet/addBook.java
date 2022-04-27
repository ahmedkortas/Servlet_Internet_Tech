package internet;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Collections;
import java.util.List;
import java.util.Map.*;

// Extend HttpServlet class to create Http Servlet
public class addBook extends HttpServlet {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mymsg;
	private DbConnection cn=null;

   public void init() throws ServletException {
      mymsg = "Hello World!";
		DbConnection conn = null;
		try {
			conn = new DbConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.cn = conn;	
   }
   
   
   protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
               response.setContentType("text/html;charset=UTF-8");
               try (PrintWriter out = response.getWriter()) {
               List<String> genreList = cn.getGenres();
               // Setting the attribute of the request object
               // which will be later fetched by a JSP page
               request.setAttribute("data", genreList);
               // Creating a RequestDispatcher object to dispatch
               // the request the request to another resource
               RequestDispatcher rd = 
               request.getRequestDispatcher("AddBook.jsp");
               // The request will be forwarded to the resource 
               // specified, here the resource is a JSP named,
               // "stdlist.jsp"
               rd.forward(request, response);
         }catch(Exception e){
            e.printStackTrace();
         }
}


public void doGet(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException 
   {
	processRequest(request,response);
   }

   public void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
      //get request parameters
      List<String>	paramNames	=	Collections.list(request.getParameterNames());
      String title = request.getParameter("title");
      String author = request.getParameter("Author");
      float price = Float.parseFloat(request.getParameter("Price"));
      String isbn = request.getParameter("ISBN");
      String description = request.getParameter("Description");
      paramNames.remove("title");
      paramNames.remove("Author");
      paramNames.remove("Price");
      paramNames.remove("ISBN");
      paramNames.remove("Description");
      paramNames.remove("Sign Up");
      String Kategorien = "";
      for(int i=0;i<paramNames.size();i++){
    	 int x =Integer.parseInt(paramNames.get(i));
         cn.addBuchCategory(isbn,x);
      }
      try {
         cn.addBook(new Book(title, author, isbn, price, description));
         response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Add a book</title>");
               out.println("</head>");
               out.println("<body>");
               out.println("<h1>Book added!</h1>");
               out.println("<a href='addBook'>Back to adding a new book</a>");
               out.println("</body>");
               out.println("</html>");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }




  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       //get the request body
	  try {
		processPostRequest(request,response);
	} catch (IOException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
    public void destroy() {
       
   }
}