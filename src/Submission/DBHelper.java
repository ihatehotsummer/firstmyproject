package Submission;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper implements DBHelperIF {

    // 사용할 인스턴트 객체들
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "AssignmentA";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hwang8107";

    @Override
    public void createSchemaA() throws SQLException, ClassNotFoundException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTableA() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS StudentScore (" +
                "이름 VARCHAR(10) PRIMARY KEY NOT NULL," +
                "국어 INT NOT NULL," +
                "영어 INT NOT NULL," +
                "수학 INT NOT NULL," +
                "평균 DOUBLE NOT NULL)";

        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 데이터 입력 메소드(입력 받을 데이터들 매개변수로 받아옴 -> main에선 student 인스턴스변수들로 받을거임)
    @Override
    public void insertData(String name, String korean, String english, String math) throws ClassNotFoundException, SQLException {
        String setName = name;
        int setKorean = Integer.parseInt(korean);
        int setEnglish = Integer.parseInt(english);
        int setMath = Integer.parseInt(math);
        Double average = Double.valueOf(((setKorean+setEnglish+setMath) / 3.0));
        String averageScore = String.format("%.2f", average);

        // 데이터 저장을 위한 SQL문
        String sql = "INSERT INTO StudentScore("
                + " 이름,"
                + " 국어,"
                + " 영어,"
                + " 수학,"
                + " 평균"
                + ") VALUES(?,?,?,?,?)";

        // JDBC 드라이버 로드
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PASSWORD); // 커넥션 연결
        PreparedStatement preparedStatement = con.prepareStatement(sql); //preparedStatement로 쿼리 실행

        // 각 컬럼에 담을 새끼들 박아넣기
        preparedStatement.setString(1,setName);
        preparedStatement.setInt(2, setKorean);
        preparedStatement.setInt(3, setEnglish);
        preparedStatement.setInt(4, setMath);
        preparedStatement.setString(5,averageScore);

        // 쿼리 실행
        preparedStatement.executeUpdate();

        // 연결 종료함
        preparedStatement.close();
        con.close();
    }

    @Override
    public void readData() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM StudentScore";

        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PASSWORD);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<DBModel> list = new ArrayList<>(); //각자 테이블에서 추출한 값을 담아줄 리스트
        // 테이블 돌면서 각 칼럼들 가져와서 그릇에 담는 루프
        while (rs.next()) { //테이블의 마지막까지 탐색
            String columnName = rs.getString("이름");
            int columnKorean = rs.getInt("국어");
            int columnEnglish = rs.getInt("영어");
            int columnMath = rs.getInt("수학");
            Double columnAverage = rs.getDouble("평균");
            // 추출 완료

            DBModel db = new DBModel(columnName,columnKorean,columnEnglish,columnMath,columnAverage); // 추출한 값을 생성자 만들어놨던 Db 그릇에 담는다
            list.add(db); // 담은 값을 리스트에 담기
        }
        rs.close();
        stmt.close();
        con.close();
//        return list; // 테이블에서 추출한 값이 담긴 리스트
        // 결과값 출력
        System.out.println("학생성적관리프로그램(v0.0.2)");
        System.out.println("----------------------------");
        list.forEach(m-> System.out.printf("%-6s %4d %4d %4d %6.1f%n", m.columnName,m.columnKorean,m.columnEnglish,m.columnMath,m.columnAverage));
        //list에 담긴 member형태의 데이터를 첫번째부터 하나하나 꺼내오기
        System.out.println("----------------------------");
    }
 }

