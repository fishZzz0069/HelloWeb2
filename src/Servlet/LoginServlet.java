package Servlet;

import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         // 接收数据:
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 从ServletContext域中获得保存用户信息集合:
        List<User> list = (List<User>) this.getServletContext().getAttribute("list");

        for(User user : list){
            // 判断用户名是否正确:
            if(username.equals(user.getUsername())){
                // 用户名是正确的
                if(password.equals(user.getPassword())){
                    // 密码也正确：
                    // 登录成功:
                    // 判断复选框是否勾选:

                    // 将用户的信息保存session中：
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("/success.jsp");
                    return;
                }
            }
        }
        request.setAttribute("msg", "用户名或密码错误！");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
