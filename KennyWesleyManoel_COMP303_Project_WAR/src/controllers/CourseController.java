package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import model.Assignment;

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

		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "addCourse":
				addCourse(request, response);
				break;
			case "addAssignment":
				try {
					addAssignment(request, response);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}

	}

	private void addCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectDataStore");
		em = emf.createEntityManager();
		Course c = new Course();

		String courseCode = request.getParameter("courseCode");
		String courseName = request.getParameter("courseName");
		String courseDescription = request.getParameter("courseDescription");
		String courseSemester = request.getParameter("courseSemester");

		try {

			c.setCourseCode(courseCode);
			c.setCourseName(courseName);
			c.setDescription(courseDescription);
			c.setSemester(courseSemester);
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
			// request.setAttribute("messageCourse", null);
		} catch (PersistenceException s) {
			request.setAttribute("messageCourse", s.getMessage());
		}
		Query q = em.createNamedQuery("Course.findAll");

		List<Course> courses = q.getResultList();

		request.setAttribute("courses", courses);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private void addAssignment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectDataStore");
		em = emf.createEntityManager();
		HttpSession session = request.getSession();
		Assignment a = new Assignment();
		Course c = new Course();
		try {
			String courseCode = request.getParameter("courseCode");
			c.setCourseCode(courseCode); // this link the course created before

			String astTitle = request.getParameter("astTitle");
			String asWeight = request.getParameter("asWeight");
			String asDueDate = request.getParameter("asDueDate");
			String asDesciption = request.getParameter("asDesciption");
			String asType = request.getParameter("asType");

			Date dateDueDate = new SimpleDateFormat("yyyy-MM-dd").parse(asDueDate);

			a.setCourse(c);
			a.setTitle(astTitle);
			a.setWeight(asWeight);
			a.setDescription(asDesciption);
			a.setDueDate(dateDueDate);
			a.setType(asType);
		} catch (ParseException e) {
			request.setAttribute("messageAssignment", e.getMessage());
		}
		try {

			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
			// request.setAttribute("messageAssignment", null);
		} catch (PersistenceException s) {
			request.setAttribute("messageAssignment", s.getMessage());
		}

		Query q = em.createNamedQuery("Assignment.findAll");

		List<Assignment> assignments = q.getResultList();

		session.setAttribute("assignments", assignments);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
