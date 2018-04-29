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

import model.Assignment;
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
		String action = request.getParameter("action");
		if (action != null) {

			if (action.equals("addCourse")) {

				addCourse(request, response);

			}
		} else {

			Query q = em.createNamedQuery("Course.findAll");
			List<Course> courses = q.getResultList();
			for (Course course : courses) {
				if (request.getParameter("btnUpdate_" + course.getCourseCode()) != null) {
					System.out.println("I clicked update on the coursecode = " + course.getCourseCode());
					Course courseUpdate = em.find(Course.class, course.getCourseCode());
					System.out
							.println("New course name " + request.getParameter("courseName_" + course.getCourseCode()));
					em.getTransaction().begin();
					courseUpdate.setCourseName(request.getParameter("courseName_" + course.getCourseCode()));
					courseUpdate.setDescription(request.getParameter("description_" + course.getCourseCode()));
					courseUpdate.setSemester(request.getParameter("semester_" + course.getCourseCode()));
					em.getTransaction().commit();
					this.doGet(request, response);
					break;
				}
				if (request.getParameter("btnDelete_" + course.getCourseCode()) != null) {
					System.out.println("I clicked delete on the coursecode = " + course.getCourseCode());
					Course courseDelete = em.find(Course.class, course.getCourseCode());
					if (!this.checkCourseAssignments(courseDelete)) {
						request.setAttribute("message", "You must delete all the assignments related with this course first!");
						this.doGet(request, response);
					} else {
						em.getTransaction().begin();
						em.remove(courseDelete);
						em.getTransaction().commit();
						this.doGet(request, response);
						break;
					}
				}
			}

		}
	}

	private boolean checkCourseAssignments(Course course) {
		Query q = em.createNamedQuery("Assignment.findAll");
		List<Assignment> assignments = q.getResultList();
		for (Assignment assignment : assignments) {
			System.out.println(course.getCourseCode());
			System.out.println(assignment.getCourse().getCourseCode());
			if (assignment.getCourse().getCourseCode().equals(course.getCourseCode())) {
				return false;
			}
		}
		return true;
	}

	private void addCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectDataStore");
		em = emf.createEntityManager();
		boolean err = false;
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
			request.setAttribute("messageCourse", "Course Code Already exist");
			err = true;
		}
		if (err == false) {
			request.setAttribute("messageCourseSucess", "Course Created");
		}

		request.getRequestDispatcher("addCourse.jsp").forward(request, response);
	}

}
