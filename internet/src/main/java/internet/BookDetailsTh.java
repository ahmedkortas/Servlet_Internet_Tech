package internet;
import config.TemplateEngineUtil;
import config.ThymeleafConfig;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@WebServlet("/book")
public class BookDetailsTh  extends HttpServlet{
	private static final long serialVersionUID = 100L;
    private DbConnection cn=null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        //query a book with the fun getBookByIsbn passing the request parameter isbn
        Book book = null;
        List<Genre> kat =null;
        List<String> genres = null;
        try {
            cn = new DbConnection();
            book = cn.getBookByIsbn(request.getParameter("isbn"));
            kat = cn.getAllGenres();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        String isbn = book.getIsbn();
         try {
				System.out.println("genres: " + cn.getAllGenresOfBooks(isbn));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				genres =cn.getAllGenresOfBooks(isbn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
        double price = book.getPrice();
        DecimalFormat df = new DecimalFormat("0.00");
            // string arr = df .split(",");
        String[] arr = df.format(price).split("\\,");
        book.setPrice(Double.parseDouble(arr[0] + "." + arr[1]));
        context.setVariable("genres", kat);
        context.setVariable("genreMap", genres);
        context.setVariable("book", book);
        engine.process("bookDetails.html", context, response.getWriter());
    }
}
