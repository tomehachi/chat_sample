package jp.glodia.ssol.sample.chat.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.glodia.ssol.sample.chat.dao.ChatDAO;

public class ChatAddServlet extends HttpServlet {

    /*
     * (非 Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String name = (String)req.getParameter("name");
        String chat = (String)req.getParameter("chat");

        try {
            (new ChatDAO()).addChatData(name, chat);

        } catch (SQLException e) {
            throw new ServletException("チャットログの追加に失敗しました.", e);
        }

        // セッションに名前を登録.
        req.getSession().setAttribute("name", name);

        res.sendRedirect("/chat/view");
    }

}
