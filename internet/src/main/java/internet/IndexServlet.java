package internet;
import config.TemplateEngineUtil;
import config.ThymeleafConfig;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.openjpa.kernel.FillStrategy.Map;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 10L;
    private DbConnection cn=null;
    private HashMap<String, List<String>> map =new HashMap<String,List<String>>();


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        // query all books in the database
        List<Book> books = null;
        List<Genre> genres = null;
        try {
            cn = new DbConnection();
            books = cn.getAllBooks();
            genres = cn.getAllGenres();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        for( int i = 0; i < books.size(); i++ ) {
            double price = books.get(i).getPrice();
            DecimalFormat df = new DecimalFormat("0.00");
            // string arr = df .split(",");
            String[] arr = df.format(price).split("\\,");
            books.get(i).setPrice(Double.parseDouble(arr[0] + "." + arr[1]));
        }
        for( int i = 0; i < books.size(); i++ ) {
            String isbn = books.get(i).getIsbn();
            try {
				System.out.println("genres: " + cn.getAllGenresOfBooks(isbn));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				map.put(isbn,cn.getAllGenresOfBooks(isbn));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        
        }
        context.setVariable("genreMap", map);
        context.setVariable("books", books);
        context.setVariable("genres", genres);
        engine.process("index.html", context, response.getWriter());
    }

}