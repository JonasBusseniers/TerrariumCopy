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
		HttpSession session = request.getSession();
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
			
			Map <Integer, Organism> organismsMap = new LinkedHashMap <>();
			
//			Organism organism = new Plant (1, true);
//			
//			organismsMap.put(1, organism);
			
			for (int i = 0; i < board.getRow(); i++) {
				for (int j = 0; j < board.getRow(); j++) {
//					organismsMap.put(i, new Dirt (1, true));
					organismsMap.put(i * 10 + j, organisms[i][j]);
				}
			}
//			request.setAttribute("organism", organismsMap);
//			request.getRequestDispatcher(VIEW).forward(request, response);
			
		request.setAttribute("organisms", organismsMap);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

//    @Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}

}
