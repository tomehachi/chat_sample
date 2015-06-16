package jp.glodia.ssol.sample.chat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.glodia.ssol.sample.chat.dto.ChatLogDto;

public class ChatDAO extends DataAccessObject {

    /**
     * チャットログを全件取得する.<br>
     *
     * @param param
     * @return
     * @throws SQLException SQL例外
     */
    public List<ChatLogDto> getAllChatData() throws SQLException {
        Statement statement = connection.createStatement();

        // 検索実行
        ResultSet rs = statement.executeQuery("SELECT * FROM chat_log ORDER BY id DESC");

        // 結果取得
        List<ChatLogDto> result = new ArrayList<ChatLogDto>();
        while(rs.next()) {
            ChatLogDto dto = new ChatLogDto();
            dto.id = rs.getInt("id");
            dto.name = rs.getString("name");
            dto.chat = rs.getString("chat");
            dto.likeCount = rs.getInt("like_count");
            dto.ip = rs.getString("ip");
            dto.regDate = new Date(rs.getTimestamp("reg_date").getTime());
            result.add(dto);
        }

        // 切断
        if(connection!=null) {
            statement.close();
            connection.close();
        }
        return result;
    }

    /**
     * チャットログを書き込む.<br>
     *
     * @param name 名前
     * @param chat チャット
     * @return 追加件数
     * @throws SQLException SQL例外
     */
    public int addChatData(String name, String chat, String ip) throws SQLException {
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate(
                "INSERT INTO chat_log "
                        +"( name"
                        +", chat"
                        +", ip"
                        +", reg_date ) VALUES ("
                        +"'"+ name +"',"
                        +"'"+ chat +"',"
                        +"'"+ ip + "'"
                        +", CURRENT_TIME)");

        if(result != 1) {
            throw new IllegalArgumentException("INSERT に失敗したようです.");
        }

        if(connection!=null) {
            statement.close();
            connection.close();
        }
        return result;
    }

    /**
     * 動作確認用.<br>
     *
     * @param args 実行パラメータ
     * @throws SQLException SQL例外
     */
    public static void main(String...args) throws SQLException {
        // 書き込み
        (new ChatDAO()).addChatData("なまえ", "チャットメッセージ", "000.000.000.000");

        // 全件検索＋表示
        List<ChatLogDto> records = (new ChatDAO()).getAllChatData();
        for(ChatLogDto dto : records) {
            System.out.println(dto.id + "\t" + dto.name + "\t" + dto.chat + "\t" + dto.regDate);
        }
    }
}
