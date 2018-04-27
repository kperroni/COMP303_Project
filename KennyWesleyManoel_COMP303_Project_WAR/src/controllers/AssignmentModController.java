package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Assignment;

/**
 * Servlet implementation class AssignmentModController
 */
@WebServlet("/Assignments")
public class AssignmentModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static EntityManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignmentModController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get List of Assignments (/Assignments)");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectDataStore");
		em = emf.createEntityManager();
		HttpSession session = request.getSession();
		
		Query q = em.createNamedQuery("Assignment.findAll");

		List<Assignment> assignments = q.getResultList();

		session.setAttribute("assignments", assignments);
		request.getRequestDispatcher("assignment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
