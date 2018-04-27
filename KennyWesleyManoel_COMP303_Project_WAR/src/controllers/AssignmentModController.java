package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.Course;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Assignment> assignments = (List<Assignment>) session.getAttribute("assignments");
		for (Assignment assgn : assignments) {
			if (request.getParameter("btnUpdate_" + assgn.getId()) != null) {
				System.out.println("I clicked update on the assignment where title = " + assgn.getTitle());
				Assignment assgnUpdate = em.find(Assignment.class, assgn.getId());
				em.getTransaction().begin();
				assgnUpdate.setTitle(request.getParameter("title_" + assgn.getId()));
				assgnUpdate.setDescription(request.getParameter("description_" + assgn.getId()));
				String dd_str = request.getParameter("dueDate_" + assgn.getId());
				try {
					assgnUpdate.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse(dd_str));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				assgnUpdate.setWeight(request.getParameter("weight_" + assgn.getId()));
				assgnUpdate.setType(request.getParameter("type_" + assgn.getId()));
				em.getTransaction().commit();
				break;
			}
			if (request.getParameter("btnDelete_" + assgn.getId()) != null) {
				System.out.println("I clicked delete on the assignment where title = " + assgn.getTitle());
				Assignment assgnDelete = em.find(Assignment.class, assgn.getId());
				em.getTransaction().begin();
				em.remove(assgnDelete);
				em.getTransaction().commit();
				break;
			}
		}
		doGet(request, response);
	}

}
