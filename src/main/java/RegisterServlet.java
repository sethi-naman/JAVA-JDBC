import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet({ "/register", "/read", "/update", "/updaterecord", "/delete" })
@MultipartConfig(fileSizeThreshold = 500000000, maxFileSize = 500000001, maxRequestSize = 5000000l)
public class RegisterServlet extends HttpServlet {

	Operartion opr = new Operartion();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getServletPath().equals("/register")) {

			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String mobile = req.getParameter("mobile");
			String fnmae = req.getPart("file").getSubmittedFileName();
			for (Part part : req.getParts()) {
				part.write("C:\\Users\\DELL\\Documents\\workspace\\Basic_Servlet\\src\\main\\webapp\\upload\\" + fnmae);
			}

			// PrintWriter out = resp.getWriter();
			// out.println(name);
			// out.println(email);
			// out.println(password);
			// out.println(mobile);
			// out.println(address);
			// out.println(pincode);

			try {
				int status = opr.Insert(name, email, password, mobile, fnmae);
				System.out.println(status != 0 ? "Inserted" : "Check Connection");
			} catch (Exception err) {
				System.out.println(err.getMessage());
			}
		}

		if (req.getServletPath().equals("/updaterecord"))

		{
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String mobile = req.getParameter("mobile");
			String fname = req.getPart("file").getSubmittedFileName();

			if (fname != null) {
				for (Part part : req.getParts()) {
					part.write("C:\\Users\\DELL\\Documents\\workspace\\Basic_Servlet\\src\\main\\webapp\\upload\\"
							+ fname);
				}

				try {
					int status = opr.Update(name, email, password, mobile, fname);
					System.out.println(status == 0 ? "Updated" : "Check Connection");
				} catch (Exception err) {
					System.out.println(err.getMessage());
				}

			} else {
				try {
					int status = opr.Update(name, email, password, mobile);
					System.out.println(status == 0 ? "Updated" : "Check Connection");
				} catch (Exception err) {
					System.out.println(err.getMessage());
				}
			}

		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getServletPath().equals("/delete")) {
			String email = req.getParameter("email");
			
			ArrayList<HashMap> data = opr.readbyemail(email);
			System.out.println(data.get(0).get("image"));

			File f = new File("C:\\Users\\DELL\\Documents\\workspace\\Basic_Servlet\\src\\main\\webapp\\upload\\"
					+ data.get(0).get("image"));
			f.delete();
			opr.deletebyemail(email);

		}
		if (req.getServletPath().equals("/read")) {
			ArrayList<HashMap> records = opr.read();
			req.setAttribute("records", records);

			RequestDispatcher rd = req.getRequestDispatcher("show.jsp");
			rd.forward(req, resp);

		}

		if (req.getServletPath().equals("/update")) {
			String email = req.getParameter("email");
			System.out.println(email);
			ArrayList<HashMap> records = opr.readbyemail(email);

			System.out.println(records);
			req.setAttribute("records", records);
			RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
			rd.forward(req, resp);

		}

	}
}
