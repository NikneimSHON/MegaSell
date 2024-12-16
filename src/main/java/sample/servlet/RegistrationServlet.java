package sample.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sample.dto.impl.EmployCreateDto;
import sample.exception.ValidationException;
import sample.service.EmployServiceImpl;
import sample.util.JspHelper;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final EmployServiceImpl employService = EmployServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("/registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var employDto = EmployCreateDto.builder()
                .number(req.getParameter("number"))
                .birthDate(req.getParameter("birth_date"))
                .password(req.getParameter("password"))
                .repeat_password(req.getParameter("repeat_password"))
                .activityId(1)
                .accessPermissionId(1)
                .build();
        try {
            employService.create(employDto);
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
