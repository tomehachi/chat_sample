<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="jp.glodia.ssol.sample.chat.dto.ChatLogDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>チャットビュー</title>
    </head>
    <body>
        <%-- タイトル部分 --%>
        サンプルチャット

        <hr>

        <%-- フォーム部分 --%>
        <form action="/chat/add" method="POST">
            名前 : <input type="text" name="name" value="<%= request.getSession().getAttribute("name") == null ? "" : request.getSession().getAttribute("name") %>"><br>
            コメント : <input type="text" name="chat" value=""><br>
            <input type="submit" value="送信">
        </form>

        <hr>

        <%-- チャットログ表示部分 --%>
        <table>
        <%
        @SuppressWarnings("unchecked")
        List<ChatLogDto> logs = (List<ChatLogDto>)request.getAttribute("logs");
        for(ChatLogDto dto : logs) {
            out.print(
                    "<tr>"+
                    "<td>"+ dto.id +"</td>"+
                    "<td>"+ dto.name +"</td>"+
                    "<td>"+ dto.chat +"</td>"+
                    "<td>"+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(dto.regDate) +"</td>"+
                    "</tr>"
            );
        }
        %>
        </table>
    </body>
</html>
