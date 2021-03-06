package internet;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Collections;
import java.util.List;
import java.util.Map.*;

// Extend HttpServlet class to create Http Servlet
public class addKategorie extends HttpServlet {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mymsg;
	private DbConnection cn=null;

   public void init() throws ServletException {
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
               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Add a Kategorie</title>");
               out.println("<style href='../../webapp/BuchUndKategoieEinfugenFormular.css'>");
               out.println("</head>");
               out.println("<body>");
               List<String> genreList = cn.getGenres();
               // Setting the attribute of the request object
               // which will be later fetched by a JSP page
               request.setAttribute("data", genreList);
               // Creating a RequestDispatcher object to dispatch
               // the request the request to another resource
               RequestDispatcher rd = 
               request.getRequestDispatcher("addNewCategorie.jsp");
               // The request will be forwarded to the resource 
               // specified, here the resource is a JSP named,
               // "stdlist.jsp"
               rd.forward(request, response);
               out.println("</body>");
               out.println("</html>");
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


   protected void processRequestPost(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException 
   {
      String kategorie = request.getParameter("Kategorie");
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Add a Kategorie</title>");
               out.println("</head>");
               out.println("<body>");
      try {
         int result =cn.checkGenre(kategorie);
         if(result==-1) {
        	 out.println("<a href='addKategorie'>kategorie exists</a>");
         }else {
         out.println("<a href='addKategorie'>Kategorie added successfully</a>");
         }
      } catch (SQLException e) {
         mymsg = "Genre already exists";
         request.setAttribute("msg", mymsg);
         processRequest(request,response);
         return;
      }
      out.println("</body>");
      out.println("</html>");
   }

      



  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       processRequestPost(request,response);
  }

   public void destroy() {
      /* leaving empty for now this can be
       * used when we want to do something at the end
       * of Servlet life cycle
       */
   }
}