package sample.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import sample.dto.impl.EmployBaseInfoDto;
import sample.service.EmployServiceImpl;
import sample.util.JspHelper;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final EmployServiceImpl employService = EmployServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("/login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var number = req.getParameter("number");
        var password = req.getParameter("password");

        employService.login(number, password).ifPresentOrElse(
                employ -> onLoginSuccess(employ,req, resp),
                () -> onLoginFail(req, resp)
        );
    }

    @SneakyThrows
    private void onLoginSuccess(EmployBaseInfoDto employ, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("employ", employ);
        resp.sendRedirect("/mainpage");
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error");
    }

}
