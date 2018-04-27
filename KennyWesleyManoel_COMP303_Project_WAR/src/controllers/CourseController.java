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

import model.Course;

/**
 * Servlet implementation class CourseController
 */
@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static EntityManager em;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseController() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("In the controller");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectDataStore");
		em = emf.createEntityManager();
		Query q = em.createNamedQuery("Course.findAll");
		List<Course> courses = q.getResultList();
		request.setAttribute("courses", courses);
		request.getRequestDispatcher("/listCourses.jsp").forward(request, response);
		;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Query q = em.createNamedQuery("Course.findAll");
		List<Course> courses = q.getResultList();
		for (Course course : courses) {
			if (request.getParameter("btnUpdate_" + course.getCourseCode()) != null) {
				System.out.println("I clicked update on the coursecode = " + course.getCourseCode());
				Course courseUpdate = em.find(Course.class, course.getCourseCode());
				System.out.println("New course name " + request.getParameter("courseName_" + course.getCourseCode()));
				em.getTransaction().begin();
				courseUpdate.setCourseName(request.getParameter("courseName_" + course.getCourseCode()));
				em.getTransaction().commit();
				break;
			}
			if (request.getParameter("btnDelete_" + course.getCourseCode()) != null) {
				System.out.println("I clicked delete on the coursecode = " + course.getCourseCode());
				Course courseDelete = em.find(Course.class, course.getCourseCode());
				em.getTransaction().begin();
				em.remove(courseDelete);
				em.getTransaction().commit();
				break;
			}
		}

	}
}