package controladores;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.util.List;
import logica.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet(name = "DevolucionServlet", urlPatterns = {"/devolucion"})
public class DevolucionServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "realizarDevolucion": {
                Session session = sessionFactory.openSession();

                List<ClasePrestamo> listaPrestamo = session.createQuery("FROM ClasePrestamo ", ClasePrestamo.class).getResultList();

                session.close();

                HttpSession sesion = req.getSession();
                sesion.setAttribute("listaPrestamos", listaPrestamo);

                response.sendRedirect("realizarDevolucion.jsp");
                break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "":{

            }
        }
    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}