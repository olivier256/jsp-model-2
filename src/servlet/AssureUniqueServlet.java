package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.AssureUnique;
import proxy.AssureUniquePager;
import dao.AssureUniqueDao;
import dao.PostgresDataSource;

@WebServlet("/AssureUniqueServlet")
public class AssureUniqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("AssureUniqueServlet");

	private static final String PAGER = "pager";

	private final DataSource dataSource;

	public AssureUniqueServlet() {
		super();
		dataSource = new PostgresDataSource();
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			action = "findAll";
		}
		if (action.equals("findAll")) {
			List<AssureUnique> assuresUniques = null;
			try (Connection connection = dataSource.getConnection()) {
				AssureUniqueDao assureUniqueDao = new AssureUniqueDao(connection);
				assuresUniques = assureUniqueDao.findAll();
			} catch (SQLException e) {
				log.warning(e.getMessage());
			}
			request.getSession().setAttribute(PAGER, new AssureUniquePager(assuresUniques));
		} else if (action.equals("previous")) {
			AssureUniquePager assureUniqueProxy = (AssureUniquePager) request.getSession().getAttribute(PAGER);
			assureUniqueProxy.previous();
		} else if (action.equals("next")) {
			AssureUniquePager assureUniqueProxy = (AssureUniquePager) request.getSession().getAttribute(PAGER);
			assureUniqueProxy.next();
		} else {
			String column = request.getParameter("column");
			AssureUniquePager assureUniqueProxy = (AssureUniquePager) request.getSession().getAttribute(PAGER);
			assureUniqueProxy.orderBy(column);
		}
		Utils.redirect(request, response, "assure_unique.jsp");
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
