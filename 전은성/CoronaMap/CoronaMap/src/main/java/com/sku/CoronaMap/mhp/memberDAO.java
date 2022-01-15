package com.sku.CoronaMap.mhp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class memberDAO {
    private memberDAO() {}
    public static memberDAO instance = new memberDAO();
    public static memberDAO getInstance() {return instance;}

    Connection conn=null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public Connection getConnection() {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/mhp?serverTimezone=UTC";
        String dbID = "root";
        String dbPassword = "dmstjd758";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public int userCheck(String id, String pw) {
        int check=-1;
        String dbpw = "";
        conn = getConnection();
        try {
            String sql = "select pw from member where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                dbpw = rs.getString(1);
                if(dbpw.equals(pw)) {
                    check=1;

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null)
                try {conn.close();}catch(SQLException sqle) {}
            if(pstmt!=null)
                try {pstmt.close();}catch(SQLException sqle) {}
            if(rs!=null)
                try {rs.close();}catch(SQLException sqle) {}
        }
        return check;
    }

    public String findPw(String name, String id, String tel) {
        String pw="";
        try {
            conn=getConnection();
            String sql = "SELECT * FROM member WHERE name=? and id=? and tel=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.setString(3, tel);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                pw = rs.getString("pw");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(conn!=null)
                try {conn.close();}catch(SQLException sqle) {}
            if(pstmt!=null)
                try {pstmt.close();}catch(SQLException sqle) {}
            if(rs!=null)
                try {rs.close();}catch(SQLException sqle) {}
        }
        return pw;
    }

    public void insertMember(String id, String pw, String name, String nickname, String date, String tel, String address, String email) {
        conn = getConnection();

        try {
            String sql = "insert into member values(?,?,?,?,?,now(),?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, nickname);
            pstmt.setString(5, date);
            pstmt.setString(6, tel);
            pstmt.setString(7, address);
            pstmt.setString(8, email);
            pstmt.executeUpdate();

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(conn!=null)
                try {conn.close();}catch(SQLException sqle) {}
            if(pstmt!=null)
                try {pstmt.close();}catch(SQLException sqle) {}
            if(rs!=null)
                try {rs.close();}catch(SQLException sqle) {}
        }
    }

    public int checkDoubleId(String id) {
        int check=-1;
        try {
            String dbid="";
            conn = getConnection();

            String sql = "select * from member where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                dbid = rs.getString("id");
                if(dbid.equals(id)) {
                    check=1;
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(conn!=null)
                try {conn.close();}catch(SQLException sqle) {}
            if(pstmt!=null)
                try {pstmt.close();}catch(SQLException sqle) {}
            if(rs!=null)
                try {rs.close();}catch(SQLException sqle) {}
        }
        System.out.println("check = " + check);
        return check;
    }

    public int checkDoubleEmail(String email) {
        int check=-1;
        String dbemail="";
        try {
            conn = getConnection();
            String sql = "select email from member where email=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                dbemail = rs.getString(1);
                if(dbemail.equals(email)) {
                    check=2;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(conn!=null)
                try {conn.close();}catch(SQLException sqle) {}
            if(pstmt!=null)
                try {pstmt.close();}catch(SQLException sqle) {}
            if(rs!=null)
                try {rs.close();}catch(SQLException sqle) {}
        }

        return check;
    }
}