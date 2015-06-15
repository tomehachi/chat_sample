package jp.glodia.ssol.sample.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataAccessObject {

    /** DBとのコネクション */
    protected Connection connection;

    public DataAccessObject() {
        try {
            // DBドライバをロードする.
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.err.println("DBドライバクラスのロードに失敗しました. "
                    +"mysqlドライバのライブラリはパスに追加しましたか？");
            e.printStackTrace();
        }

        try {
            // コネクションの取得.
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/{database}", "{user name}", "{password}");

        } catch (SQLException e) {
            System.err.println("DBコネクションの取得に失敗しました. "
                    +"接続URL(ホスト名, ポート番号, データベース名, ID, パスワード)は合っていますか？");
            e.printStackTrace();
        }
    }

}
