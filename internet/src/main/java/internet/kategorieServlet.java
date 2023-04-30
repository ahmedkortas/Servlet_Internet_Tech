package internet;
import config.TemplateEngineUtil;
import config.ThymeleafConfig;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@WebServlet("/kategorie")
public class kategorieServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
		String kat = request.getParameter("genre");
		List<Book> books = null;
        List<Genre> genres = null;
		try {
			DbConnection cn = new DbConnection();
			books = cn.getBooksByGenre(kat);
			genres = cn.getAllGenres();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        context.setVariable("books", books);
        context.setVariable("genres", genres);
        engine.process("kategorie.html", context, response.getWriter());
    }
}


/**
 *  
 * 
 * tabele: genre query id of krimi 
 * 
 * krimi hat genre id = 3  krimi.genre_id = 3
 * 
 * query all buch innerjoing buch_kategorie innerjoiner genre where genre_id = 3
 * 
 * @return a list of all books with the given kategorie
 *  
 * 
 * 
 */