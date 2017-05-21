package controller;

import com.google.gson.Gson;
import service.UploadService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by claudiu on 20.05.2017.
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadController extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPart("file").getSize() != 0) {
            // gets absolute path of the web application
            String applicationPath = req.getServletContext().getRealPath("");
            // constructs path of the directory to save uploaded file
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

            // creates the save directory if it does not exists
            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }

            Part part = req.getPart("file");
            String fileName = getFileName(part);
            String path = uploadFilePath + File.separator + fileName;
            part.write(uploadFilePath + File.separator + fileName);

            long fileSize = req.getPart("file").getSize();
            String contentType = req.getPart("file").getContentType();

            try {
                service.store(fileName, contentType, path, fileSize);
            } catch (SQLException e) {
                resp.setStatus(400);
                PrintWriter out = resp.getWriter();
                out.print("Try again");
            }
        } else {
            resp.setStatus(400);
            PrintWriter out = resp.getWriter();
            out.print("Please choice a file");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<model.File> files = service.all();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String json = new Gson().toJson(files);
            resp.getWriter().write(json);
        } catch (SQLException e) {
            resp.setStatus(400);
            PrintWriter out = resp.getWriter();
            out.print("Try again");
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

}
