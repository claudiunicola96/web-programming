package controller;

import com.google.gson.Gson;
import model.File;
import service.UploadService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by claudiu on 21.05.2017.
 */
@WebServlet("/action")
public class ActionController extends HttpServlet {
    private UploadService service = null;

    @Override
    public void init() throws ServletException {
        try {
            service = new UploadService();
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 1;
        if (req.getParameter("id") != null) {
            id = (Integer.parseInt(req.getParameter("id")));
            try {
                File file = service.find(id);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                String json = new Gson().toJson(file);
                resp.getWriter().write(json);
            } catch (SQLException e) {
                resp.setStatus(400);
                PrintWriter out = resp.getWriter();
                out.print("Try again");
            }
        } else {
            resp.setStatus(400);
            PrintWriter out = resp.getWriter();
            out.print("Try again");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            try {
                service.update(id, name);
            } catch (SQLException e) {
                resp.setStatus(400);
                PrintWriter out = resp.getWriter();
                out.print("Try again");
            }
        } else {
            resp.setStatus(400);
            PrintWriter out = resp.getWriter();
            out.print("Try again");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                service.delete(id);
            } catch (SQLException e) {
                resp.setStatus(400);
                PrintWriter out = resp.getWriter();
                out.print("Try again");
            }
        } else {
            resp.setStatus(400);
            PrintWriter out = resp.getWriter();
            out.print("Try again");
        }
    }
}
