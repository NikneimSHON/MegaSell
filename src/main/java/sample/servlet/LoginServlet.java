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
import sample.util.LoginResult;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


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

        LoginResult result = employService.login(number, password);

        if (result.getEmploy().isPresent()) {
            onLoginSuccess(result.getEmploy().get(), req, resp);
        } else {
            onLoginFail(req, resp, result.getErrorMessage());
        }
    }

    @SneakyThrows
    private void onLoginSuccess(EmployBaseInfoDto employ, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("employ", employ);
        resp.sendRedirect("/mainpage");

    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp, String message) {
        resp.sendRedirect("/login?error=" + URLEncoder.encode(message, StandardCharsets.UTF_8.toString()));
    }


}
