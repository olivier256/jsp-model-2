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
import javax.sql.ConnectionPoolDataSource;

import model.Tache;
import proxy.Pager;
import proxy.TachePager;
import dao.AutoCloseablePooledConnection;
import dao.Dao;
import dao.TacheDao;

@WebServlet("/TacheServlet")
public class TacheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("TacheServlet");

	private static final String PAGER = "pager";

	public TacheServlet() {
		super();
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			action = "findAll";
		}
		if (action.equals("findAll")) {
			ConnectionPoolDataSource connectionPoolDataSource = //
			(ConnectionPoolDataSource) getServletContext().getAttribute(SimpleServletContextListener.CONNECTION_POOL_DATA_SOURCE);
			List<Tache> listeDesTaches = null;
			try (AutoCloseablePooledConnection pooledConnection = (AutoCloseablePooledConnection) connectionPoolDataSource.getPooledConnection();) {
				Connection connection = pooledConnection.getConnection();
				Dao<Long, Tache> tacheDao = new TacheDao(connection);
				listeDesTaches = tacheDao.findAll();
			} catch (SQLException e) {
				log.warning(e.getMessage());
			}

			request.getSession().setAttribute(PAGER, new TachePager(listeDesTaches));
		} else if (action.equals("previous")) {
			Pager<Tache> tacheProxy = (TachePager) request.getSession().getAttribute(PAGER);
			tacheProxy.previous();
		} else if (action.equals("next")) {
			Pager<Tache> tacheProxy = (TachePager) request.getSession().getAttribute(PAGER);
			tacheProxy.next();
		} else {
			String column = request.getParameter("column");
			Pager<Tache> tacheProxy = (TachePager) request.getSession().getAttribute(PAGER);
			tacheProxy.orderBy(column);
		}
		Utils.redirect(request, response, "tache.jsp");
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
