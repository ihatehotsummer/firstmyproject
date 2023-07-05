package JdbcBasic;

import java.sql.*;
public class PracticeJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // JDBC 드라이버 로드
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 커넥션 구현 및 URL,ID,PASSWORD 입력
        String DBURL = "jdbc::mysql://localhost:3306";
        String USERID = "root";
        String PASSWORD = "hwang8107";
        Connection con;
        try {
            con = DriverManager.getConnection(DBURL, USERID, PASSWORD);
        } catch (SQLException e) {
            System.out.print("문제가 발생했습니다. : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Statement 클래스 객체 생성 및 SQL 구현 준비
        Statement stmt = con.createStatement();

        // 쿼리 입력
        String sql = "쿼리문";
//        ResultSet rs1 = stmt.execute(sql); // any SQL
        ResultSet rs2 = stmt.executeQuery(sql); // SELECT문
//        ResultSet rs3 = stmt.executeUpdate(sql); // insert, update, delete 등

        // 데이터 읽어오기
        while (rs2.next()) {
            System.out.println(rs2.getInt("no")); // columnLabel -String || columnIndex - Int
        }

        // 접속 종료 - 열었던 순서와 역순
        rs2.close();
        stmt.close();
        con.close();

    }

}
