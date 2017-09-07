package be.vdab.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Plant;
import be.vdab.terrarium.Board;
import be.vdab.terrarium.util.SpecifiedAmountsTerrariumGenerator;

@WebServlet("/custom.htm")
public class CustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/jsp/custom.jsp";
	private static final String REDIRECT_URL = "%s/board.htm";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		int rows = 6;
		int cols = 6;
		int plantsStart = 3;
		int herbivoresStart = 3;
		int carnivoresStart = 3;
		int plantsEveryDay = 1;
		try {
			rows = Integer.parseInt(request.getParameter("rows"));
			cols = Integer.parseInt(request.getParameter("cols"));
			plantsStart = Integer.parseInt(request.getParameter("plantsstart"));
			herbivoresStart = Integer.parseInt(request.getParameter("herbivoresstart"));
			carnivoresStart = Integer.parseInt(request.getParameter("carnivoresstart"));
			plantsEveryDay = Integer.parseInt(request.getParameter("plantseveryday"));
			int organismsStart = plantsStart + herbivoresStart + carnivoresStart;
			int boardSize = rows * cols;
			if (rows < 6 || rows > 10 || cols < 6 || cols > 10 || plantsEveryDay < 0 || plantsEveryDay > 5
					|| plantsStart < 0 || herbivoresStart < 0 || carnivoresStart < 0) {
				fouten.put("number", "You tried to enter a number that is not accepted. Foei!");
			} else if (organismsStart > boardSize) {
				fouten.put("boardIsFull", "You tried to start with a total of " + organismsStart
						+ "organisms, but the board is only " + boardSize + " tiles large.");
			}

		} catch (NumberFormatException ex) {
			fouten.put("number", "You tried to enter something that is not a number. Foei!");
		}
		if (fouten.isEmpty()) {
			SpecifiedAmountsTerrariumGenerator generator = new SpecifiedAmountsTerrariumGenerator();

			generator.setSize(rows, cols);
			generator.setAmountForType(Plant.class, plantsStart);
			generator.setAmountForType(Herbivore.class, herbivoresStart);
			generator.setAmountForType(Carnivore.class, carnivoresStart);
			generator.setLifeForceRangeForType(Plant.class, 1, 1);
			generator.setLifeForceRangeForType(Herbivore.class, 0, 10);
			generator.setLifeForceRangeForType(Carnivore.class, 0, 10);
			Board board = new Board();
			board.setOrganisms(generator.generateTerrarium());
			board.setAantalPlantenPerBeurt(plantsEveryDay);

			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));

		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
