package be.vdab.web.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;
import be.vdab.terrarium.Board;
import be.vdab.terrarium.BoardException;
import be.vdab.terrarium.util.SpecifiedAmountsTerrariumGenerator;
import be.vdab.terrarium.util.TerrariumRenderer;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board.htm")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doGetNewBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpecifiedAmountsTerrariumGenerator generator = new SpecifiedAmountsTerrariumGenerator();
		HttpSession session = request.getSession(false);
				//if (session != null) {
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

		TerrariumRenderer renderer = new TerrariumRenderer(board.getOrganisms());
		
		Organism[][] organisms = board.getOrganisms();
		
		Map <Integer, Organism> organismsMap = new LinkedHashMap <>();
		
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getRow(); j++) {
				organismsMap.put( (i * 10) + j, organisms[i][j]);
			}
			
		}
		request.setAttribute("organisms", organismsMap);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
