package be.vdab.web.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns="/index.htm")
public class IndexServlet extends javax.servlet.http.HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String INDEX_VIEW = "/WEB-INF/jsp/index.jsp";


  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, IOException {
    request.getRequestDispatcher(INDEX_VIEW).forward(request, response);
  }
}
