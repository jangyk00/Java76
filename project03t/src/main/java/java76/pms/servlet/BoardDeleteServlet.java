package java76.pms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import java76.pms.dao.BoardDao;

public class BoardDeleteServlet extends HttpServlet {  
  private static final long serialVersionUID = 1L;

  @Override
  public void doPost(
      HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      String password = request.getParameter("password");
      
      ApplicationContext iocContainer = 
          (ApplicationContext)this.getServletContext()
                                  .getAttribute("iocContainer");
      BoardDao boardDao = iocContainer.getBean(BoardDao.class);
      
      if (boardDao.delete(no, password) > 0) {
        response.sendRedirect("list");
        return;
      } 
      
      // 오류 코드를 ServletRequest 보관소에 담는다.
      request.setAttribute("errorCode", "401");

      response.setContentType("text/html;charset=UTF-8");
      RequestDispatcher rd = request.getRequestDispatcher(
          "/board/BoardAuthError.jsp");
      rd.include(request, response);
      
    } catch (Exception e) {
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      request.setAttribute("error", e);
      rd.forward(request, response);
    }
  }
}