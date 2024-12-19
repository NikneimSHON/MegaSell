package sample.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sample.dto.impl.EmployBaseInfoDto;
import sample.dto.impl.EmployCreateDto;
import sample.dto.impl.EmployPersonalDataDto;
import sample.exception.ValidationException;
import sample.service.EmployServiceImpl;
import sample.util.JspHelper;

import java.io.IOException;

@WebServlet("/personal_cabinet")
public class PersonalCabinetServlet extends HttpServlet {
    EmployServiceImpl employService = EmployServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("/personal_cabinet")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");

        if ("save".equals(action)) {
            handleSave(req, resp, session);
        } else if ("edit".equals(action)) {
            handleEdit(session);
            doGet(req, resp);
        }
    }

    private void handleSave(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException, ServletException {
        EmployBaseInfoDto employ = (EmployBaseInfoDto) session.getAttribute("employ");
        String lastname = req.getParameter("lastName");
        String firstname = req.getParameter("firstName");
        String email = req.getParameter("email");
        EmployPersonalDataDto personalDataDto = EmployPersonalDataDto.builder()
                .id(employ.getId())
                .firstName(!firstname.isEmpty() ? firstname.substring(0, 1).toUpperCase() + firstname.substring(1).toLowerCase() : "")
                .lastName(!lastname.isEmpty() ? lastname.substring(0, 1).toUpperCase() + lastname.substring(1).toLowerCase() : "")
                .email(email)
                .build();

        try {
            employService.update(personalDataDto);
            session.setAttribute("editMode", false);
            session.setAttribute("employ", employService.findEmployById(employ.getId()).get());
            resp.sendRedirect("/personal_cabinet");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            session.setAttribute("editMode", true);
            doGet(req, resp);
        }
    }

    private void handleEdit(HttpSession session) {
        Boolean currentEditMode = (Boolean) session.getAttribute("editMode");
        if (currentEditMode == null) {
            session.setAttribute("editMode", false);
        } else {
            session.setAttribute("editMode", !currentEditMode);
        }
    }

}
