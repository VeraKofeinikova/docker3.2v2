package helpers;

import java.sql.*;
import java.util.ArrayList;

public class SqlCommands {

    public static String getId(String login) throws SQLException {
        try (Connection cn = MakeConnection.getConnection();
             PreparedStatement st = cn.prepareStatement("SELECT id FROM users WHERE login = ?;");
        ) {
            st.setString(1, login);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("id");
                }
                return null;
            }
        }
    }

    public static String getVerificationCode(String id) throws SQLException {
        try (Connection cn = MakeConnection.getConnection();
             PreparedStatement st = cn.prepareStatement("SELECT code FROM auth_codes WHERE user_id = ? ORDER BY created;")
        ) {
            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                ArrayList<String> codes = new ArrayList<>();
                while (rs.next()) {
                    codes.add(rs.getString("code"));
                }
                return codes.get(codes.size() - 1);
            }
        }
    }

    public static void clearVerificationCodes() throws SQLException {
        try (Connection cn = MakeConnection.getConnection();
             Statement st = cn.createStatement()
        ) {
            st.execute("TRUNCATE TABLE auth_codes;");
        }
    }

    public static void clearAllTables() throws SQLException {
        try (Connection cn = MakeConnection.getConnection();
             Statement st1 = cn.createStatement();
             Statement st2 = cn.createStatement();
             Statement st3 = cn.createStatement();
             Statement st4 = cn.createStatement()) {
            st1.execute("TRUNCATE TABLE auth_codes;");
            st2.execute("TRUNCATE TABLE cards;");
            st3.execute("TRUNCATE TABLE card_transactions;");
            st4.execute("DELETE FROM users WHERE id IS NOT NULL;");
        }
    }
}