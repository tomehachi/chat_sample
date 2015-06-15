package jp.glodia.ssol.sample.chat.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.glodia.ssol.sample.chat.dao.ChatDAO;
import jp.glodia.ssol.sample.chat.dto.ChatLogDto;

public class ChatViewServlet extends HttpServlet {

    /*
     * (非 Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        //res.setContentType("text/html; charset=UTF-8");

        List<ChatLogDto> logs = null;
        try {
            // DBに接続.
            logs = (new ChatDAO()).getAllChatData();

        } catch (SQLException e) {
            throw new ServletException("チャットログの取得に失敗しました.", e);
        }

        // JSPに渡す logs を HttpServletRequest の属性に詰め込む
        req.setAttribute("logs", logs);

        // JSPに処理を渡す.
        this.getServletContext()
            .getRequestDispatcher("/view.jsp")
            .forward(req, res);
    }

}
