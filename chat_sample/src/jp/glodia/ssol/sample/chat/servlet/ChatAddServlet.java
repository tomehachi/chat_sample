package jp.glodia.ssol.sample.chat.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.glodia.ssol.sample.chat.dao.ChatDAO;
import jp.glodia.ssol.sample.chat.util.StringUtil;

public class ChatAddServlet extends HttpServlet {

    /*
     * (非 Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        StringBuffer message = new StringBuffer();

        String name = StringUtil.sanitize((String)req.getParameter("name"));
        String chat = StringUtil.sanitize((String)req.getParameter("chat"));

        if(name.length() > 10) {
            message.append("<p>名前は10文字以内におさめて下さい.</p>");
            name = "";

        } else if(name.length() < 1) {
            message.append("<p>名前が入力されていません.</p>");
        }

        if(chat.length() > 130) {
            message.append("<p>チャットメッセージは130文字に収めてください.</p>");

        } else if(chat.length() < 1) {
            message.append("<p>チャットメッセージが入力されていません.</p>");
        }

        if(message.length() == 0) {
            try {
                (new ChatDAO()).addChatData(name, chat, req.getRemoteAddr());

            } catch (SQLException e) {
                throw new ServletException("チャットログの追加に失敗しました.", e);
            }
        }

        // セッションに名前を登録.
        req.getSession().setAttribute("name", name);

        // セッションにメッセージを登録.
        req.getSession().setAttribute("message", message.toString());

        res.sendRedirect("/chat/view");
    }

}
