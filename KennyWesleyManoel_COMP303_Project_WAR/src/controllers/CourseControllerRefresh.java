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

import model.Course;

/**
 * Servlet implementation class CourseControllerRefresh
 */
@WebServlet("/CourseControllerRefresh")
public class CourseControllerRefresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static EntityManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseControllerRefresh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		System.out.println("In the controllerRefresh");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectDataStore");
		em = emf.createEntityManager();
		HttpSession session = request.getSession();

		Query q2 = em.createNamedQuery("Course.findAll");
		
		List<Course> courses = q2.getResultList();
		request.setAttribute("courses", courses);
		request.getRequestDispatcher("addAssignment.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
