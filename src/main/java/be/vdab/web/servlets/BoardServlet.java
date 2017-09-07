package be.vdab.web.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import be.vdab.entities.Carnivore;
import be.vdab.entities.Dirt;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;
import be.vdab.terrarium.Board;
import be.vdab.terrarium.BoardException;
import be.vdab.terrarium.util.SpecifiedAmountsTerrariumGenerator;
import be.vdab.terrarium.util.TerrariumRenderer;

@WebServlet("/board.htm")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/jsp/board.jsp";

    public BoardServlet() {

    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpecifiedAmountsTerrariumGenerator generator = new SpecifiedAmountsTerrariumGenerator();
		HttpSession session= request.getSession(false);
    	if (session == null) {
    		
    		session= request.getSession();
			generator.setSize(6, 6);
			generator.setAmountForType(Plant.class, 2);
			generator.setAmountForType(Herbivore.class, 3);
			generator.setAmountForType(Carnivore.class, 2);
			generator.setLifeForceRangeForType(Plant.class, 1, 1);
			generator.setLifeForceRangeForType(Herbivore.class, 0, 10);
			generator.setLifeForceRangeForType(Carnivore.class, 0, 10);
			
			Board board = new Board();
			
			board.setOrganisms(generator.generateTerrarium());
			board.setAantalPlantenPerBeurt(1);
	
			@SuppressWarnings("unused")
			TerrariumRenderer renderer = new TerrariumRenderer(board.getOrganisms());
			
			Organism[][] organisms = board.getOrganisms();
			int aantalPlanten = 0, aantalHerbivore = 0, aantalCarnivore = 0, aantalDirt = 0;
			
			Map <String, Organism> organismsMap = new LinkedHashMap <>();
			
			for (int i = 0; i < board.getRow(); i++) {
				for (int j = 0; j < board.getRow(); j++) {			
					if (organisms[i][j] == null)	{
						organismsMap.put("organism row-" + i + " col-" + j, new Dirt (0, true));
						aantalDirt++;
					} 
					else 	{
						organismsMap.put("organism row-" + i + " col-" + j, organisms[i][j]);
						switch (organisms[i][j].toString()) {
							case "Herb": 	aantalHerbivore++;
											break;
							case "Plant": 	aantalPlanten++;
											break;	
							case "Carn": 	aantalCarnivore++;
											break;
						}
					}
				}
			}
			
		request.setAttribute("numberDays", "Day: " + board.getAantalDagen());
		request.setAttribute("numberHerb", aantalHerbivore);
		request.setAttribute("numberPlant", aantalPlanten);
		request.setAttribute("numberCarn", aantalCarnivore);
		request.setAttribute("numberDirt", aantalDirt);
		request.setAttribute("organisms", organismsMap);
		
		session.setAttribute("board", board);
		request.getRequestDispatcher(VIEW).forward(request, response);	
		
    	} else {
    		
    		Board board = (Board) session.getAttribute("board");
			
			Organism[][] organisms = (Organism[][]) request.getAttribute("organisms");
			int aantalPlanten = 0, aantalHerbivore = 0, aantalCarnivore = 0, aantalDirt = 0;
			
			Map <String, Organism> organismsMap = new LinkedHashMap <>();
			
			for (int i = 0; i < board.getRow(); i++) {
				for (int j = 0; j < board.getRow(); j++) {			
					if (organisms[i][j] == null)	{
						organismsMap.put("organism row-" + board.getRow() + " col-" + board.getColumn(), new Dirt (0, true));
						aantalDirt++;
					} 
					else 	{
						organismsMap.put("organism row-" + board.getRow() + " col-" + board.getColumn(), organisms[i][j]);
						switch (organisms[i][j].toString()) {
							case "Herb": 	aantalHerbivore++;
											break;
							case "Plant": 	aantalPlanten++;
											break;	
							case "Carn": 	aantalCarnivore++;
											break;
						}
					}
				}
			}
			
			request.setAttribute("numberDays", "Day: " + board.getAantalDagen());
			request.setAttribute("numberHerb", aantalHerbivore);
			request.setAttribute("numberPlant", aantalPlanten);
			request.setAttribute("numberCarn", aantalCarnivore);
			request.setAttribute("numberDirt", aantalDirt);
			request.setAttribute("organisms", organismsMap);
			
			session.setAttribute("board", board);
			request.getRequestDispatcher(VIEW).forward(request, response);	
    	}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session= request.getSession(false);
    	if (session != null) {
    		Board board = (Board) session.getAttribute("board");
    		try {
    			board.nextDay();
    		} catch (BoardException ex)
    		{
    			request.setAttribute("exception", ex);
    		}
    		response.sendRedirect(request.getRequestURI());
    	}
    	
	}

}
