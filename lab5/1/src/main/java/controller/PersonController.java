package controller;

import com.google.gson.Gson;
import model.Person;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by claudiu on 19.05.2017.
 */
@WebServlet("/person")
public class PersonController extends HttpServlet {
    private PersonService service;

    @Override
    public void init() throws ServletException {
        try {
            service = new PersonService();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws IOException {
        int id = 1;
        if (req.getParameter("id") != null) {
            id = (Integer.parseInt(req.getParameter("id")));
        }
        Person person = null;
        try {
            person = service.find(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (person != null) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String json = new Gson().toJson(person);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        try {
            service.update(new Person(id, firstname, lastname, email, phone));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
