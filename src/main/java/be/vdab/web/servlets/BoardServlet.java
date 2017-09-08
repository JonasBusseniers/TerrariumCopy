package be.vdab.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Omnivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;
import be.vdab.terrarium.Board;
import be.vdab.terrarium.BoardException;
import be.vdab.terrarium.util.SpecifiedAmountsTerrariumGenerator;

@WebServlet("/board.htm")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/jsp/board.jsp";

	public BoardServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SpecifiedAmountsTerrariumGenerator generator = new SpecifiedAmountsTerrariumGenerator();
		HttpSession session = request.getSession();
		String param = request.getParameter("new");
		Board board;
		if (session.getAttribute("board") == null || param != null) {
			// if (!param.isEmpty()) {
			// request.setAttribute("new", "");
			session = request.getSession();

			generator.setSize(6, 6);
			generator.setAmountForType(Plant.class, 2);
			generator.setAmountForType(Herbivore.class, 3);
			generator.setAmountForType(Carnivore.class, 2);
			generator.setLifeForceRangeForType(Plant.class, 1, 1);
			generator.setLifeForceRangeForType(Herbivore.class, 0, 10);
			generator.setLifeForceRangeForType(Carnivore.class, 0, 10);
			generator.setAmountForType(Omnivore.class, 2);
			generator.setLifeForceRangeForType(Omnivore.class, 0, 10);
			
			board = new Board();

			board.setOrganisms(generator.generateTerrarium());
			board.setAantalPlantenPerBeurt(1);

			// } else {
			// request.setAttribute("new", "");
			// board = (Board)session.getAttribute("board");
			// }

			// Organism[][] organisms = board.getOrganisms().clone();
			Organism[][] organismsTemp = board.getOrganisms().clone();

			int aantalPlanten = 0, aantalHerbivore = 0, aantalCarnivore = 0;
			int aantalOmnivore = 0;

			for (int i = 0; i < organismsTemp.length; i++) {
				for (int j = 0; j < organismsTemp[0].length; j++) {
					if (organismsTemp[i][j] != null) {
						switch (organismsTemp[i][j].toString()) {
						case "Herb":
							aantalHerbivore++;
							break;
						case "Plant":
							aantalPlanten++;
							break;
						case "Carn":
							aantalCarnivore++;
							break;
						case "Omni":
							aantalOmnivore++;
							break;
						}
					}
				}
			}

			
			
			
			request.setAttribute("numberDays", "Day: " + board.getAantalDagen());
			request.setAttribute("numberHerb", aantalHerbivore);
			request.setAttribute("numberPlant", aantalPlanten);
			request.setAttribute("numberCarn", aantalCarnivore);
			request.setAttribute("numberOmni", aantalOmnivore);
			request.setAttribute("organisms", organismsTemp);

			session.setAttribute("board", board);
			request.getRequestDispatcher(VIEW).forward(request, response);

		} else {

			board = (Board) session.getAttribute("board");

			Organism[][] organisms = board.getOrganisms().clone();
			int aantalPlanten = 0, aantalHerbivore = 0, aantalCarnivore = 0;
			int aantalOmnivore = 0;
			
			for (int i = 0; i < organisms.length; i++) {
				for (int j = 0; j < organisms[0].length; j++) {
					if (organisms[i][j] != null) {
						switch (organisms[i][j].toString()) {
						case "Herb":
							aantalHerbivore++;
							break;
						case "Plant":
							aantalPlanten++;
							break;
						case "Carn":
							aantalCarnivore++;
							break;
						case "Omni":
							aantalOmnivore++;
							break;
						}
					}
				}
			}
			
			
			// TerrariumRenderer renderer = new
			// TerrariumRenderer(board.getOrganisms());
			// renderer.render();
			request.setAttribute("numberDays", "Day: " + board.getAantalDagen());
			request.setAttribute("numberHerb", aantalHerbivore);
			request.setAttribute("numberPlant", aantalPlanten);
			request.setAttribute("numberCarn", aantalCarnivore);
			request.setAttribute("organisms", organisms);
			request.setAttribute("numberOmni", aantalOmnivore);
			session.setAttribute("board", board);
			request.setAttribute("new", "");
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Board board = (Board) session.getAttribute("board");
		try {
			board.nextDay();
		} catch (BoardException ex) {
			board.setException("Het terrarium is vol");
		}
		session.setAttribute("board", board);
		response.sendRedirect(request.getRequestURI());

	}

}
