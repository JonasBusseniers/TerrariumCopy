package be.vdab.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.jsp.jstl.core.Config;

@WebServlet(name = "SelectLanguageServlet", urlPatterns = "/selectLanguage.html", loadOnStartup = 1)
public class SelectLanguageServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String language = request.getParameter("language");
		String country = request.getParameter("country");
		String redirectURL = request.getParameter("redirectURL");
		Config.set(request.getSession(), Config.FMT_LOCALE, new java.util.Locale(language, country));
		response.sendRedirect(redirectURL);
	}
}