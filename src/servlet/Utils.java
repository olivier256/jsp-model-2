package servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {
	private static final Logger log = Logger.getLogger("Utils");

	private Utils() {
		super();
	}

	public static void redirect(final HttpServletRequest request, final HttpServletResponse response, final String url) {
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException | IOException e) {
			log.warning(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
