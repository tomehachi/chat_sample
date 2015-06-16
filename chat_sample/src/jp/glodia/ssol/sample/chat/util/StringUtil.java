package jp.glodia.ssol.sample.chat.util;

public class StringUtil {

    private StringUtil() {}

    /**
     * スクリプト化されそうな文字を無効化<br>
     *
     * @param input 入力文字列
     * @return サニタイズ文字列
     */
    public static String sanitize(String input) {
        return input
            .replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;")
            .replaceAll("&", "&amp;")
            .replaceAll("\"", "&quot;")
            .replaceAll("'", "&#39;");
    }
}
