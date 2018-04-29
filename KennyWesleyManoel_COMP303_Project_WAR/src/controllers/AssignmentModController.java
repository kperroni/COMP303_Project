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
		/*
		 * Query q2 = em.createNamedQuery("Course.findAll");
		 * 
		 * List<Course> courses = q2.getResultList(); request.setAttribute("courses",
		 * courses); request.getRequestDispatcher("AddAssignment.jsp").forward(request,
		 * response);
		 */

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

		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("addAssignment")) {

				try {
					addAssignment(request, response);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {

			HttpSession session = request.getSession();
			List<Assignment> assignments = (List<Assignment>) session.getAttribute("assignments");
			for (Assignment assgn : assignments) {
				if (request.getParameter("btnUpdate_" + assgn.getId()) != null) {
					System.out.println("I clicked update on the assignment where title = " + assgn.getTitle());
					Assignment assgnUpdate = em.find(Assignment.class, assgn.getId());
					em.getTransaction().begin();
					String oldCourseCode = assgn.getCourse().getCourseCode();
					String courseCode = request.getParameter("assignment-courseCode_" + assgn.getId());
					Course course = em.find(Course.class, courseCode);
					if (course != null) {
						if (!courseCode.equals(oldCourseCode)) {
							assgnUpdate.setCourse(course);
							request.setAttribute("message", "Course with code " + courseCode + " found.");
						}
					} else {
						request.setAttribute("messageErr", "Course with code " + courseCode + " not found.");
					}
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
		}

		doGet(request, response);
	}

	private void addAssignment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectDataStore");
		em = emf.createEntityManager();
		boolean err = false;
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
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
		} catch (ParseException e) {
			request.setAttribute("messageAssignment", "Date incorrect");
			err = true;
		} catch (PersistenceException s) {
			request.setAttribute("messageAssignment", "Course Code Not Found");
			err = true;
		}
		if (err == false) {
			request.setAttribute("messageAssignmentSucess", "Assignment Created");
		}
		// session.setAttribute("assignments", assignments);
		request.getRequestDispatcher("addAssignment.jsp").forward(request, response);

	}

}
