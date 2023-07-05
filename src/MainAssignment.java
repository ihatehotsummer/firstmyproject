import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainAssignment {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "Assignment2";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hwang8107";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // schema 만드는 메소드
        createSchema();
        // 테이블 만드는 메소드 테이블 명 이름 국어 영어 수학 평균
        createTable();

        Scanner sc = new Scanner(System.in); // 스캐너 사용 인풋 받음

        // 성적 넣을 어레이리스트
        ArrayList<String> scoreList = new ArrayList<>();

        // 루프 멈출지 말지 결정하는 인스턴스 변수
        String start = "0";
        String stop = "1";
        int idx = 1; //루프 내 증가하는 인덱스 표현

        Student student;

        System.out.println("학생성적관리 프로그램에 접속하셨습니다.");
        System.out.println("성적을 입력하기를 원하면 0을 입력하고 다 입력했으면 1을 눌러라.");
        System.out.println("이름 국어 영어 수학 순으로 입력하고 ㅈ대로 입력하면 나도 어찌될지 모른다");
        // 루프 시작할지 말지를 알리는 인풋
        String firstStart = sc.nextLine();
        if (firstStart.equals("0")) {
            while (true) {
                String contents = sc.nextLine(); // 학생 성적 입력
                if (contents.equals("1")) { // 1을 입력하면 프로그램 종료
                    break;
                }
                String[] studentInfo = contents.split(" "); // 학생 정보 입력받은걸 " " 스페이스바 기준으로 나눔 -> 배열생성됨

                // 문자열 잘못 입력했을시
                if (Integer.parseInt(studentInfo[1]) >99 || Integer.parseInt(studentInfo[2]) >99 || Integer.parseInt(studentInfo[3]) >99) {
                    System.out.println("잘못 입력하셨습니다. 다시 입력하세요");
                    continue;
                }

                // 클래스 Student 에 원하는 각각의 전달값을 받는 생성자
                student = new Student(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3]);

                // DB에 데이터 입력 메소드
                insert(student);
            }
        }

        // 콘솔창에 데이터 출력하기
        DbConsole dc = new DbConsole();
        dc.printDbList(); // 콘솔창에 출력해주는 로직

    }

    ////// 필요한 클래스와 메소드들

    // 니가 만들라던 학생클래스 시발
    static class Student {
        String name;
        String korean;
        String english;
        String math;

        public Student(String name, String korean, String english, String math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }
    }

    // 스키마 생성 메소드
    public static void createSchema() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 테이블 생성 메소드
    public static void createTable() {
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

    // DB 데이터 입력(insert) 메소드
    public static void insert(Student student) throws ClassNotFoundException, SQLException {
        // Split으로 분리되어 student 인스턴스 변수들로 저장되어있는 값들을 받을 객체 생성
        String name = student.name;
        int korean = Integer.parseInt(student.korean);
        int english = Integer.parseInt(student.english);
        int math = Integer.parseInt(student.math);
        Double average = ((Double.parseDouble(student.korean) + Double.parseDouble(student.english) + Double.parseDouble(student.math)) / 3);
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
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2, korean);
        preparedStatement.setInt(3, english);
        preparedStatement.setInt(4, math);
        preparedStatement.setString(5,averageScore);

        // 쿼리 실행
        preparedStatement.executeUpdate();

        // 연결 종료함
        preparedStatement.close();
        con.close();
    }

    // 칼럼들 담을 껍데기만드는 클래스
    public static class Db {
        String columnName;
        int columnKorean;
        int columnEnglish;
        int columnMath;
        Double columnAverage;
        public Db(String columnName, int columnKorean, int columnEnglish, int columnMath, Double columnAverage) {
            this.columnName = columnName;
            this.columnKorean = columnKorean;
            this.columnEnglish = columnEnglish;
            this.columnMath = columnMath;
            this.columnAverage = columnAverage;
        }
    }

    // DB 받아 오기 위한 껍데기(그릇)에 추출한 값을 담는 클래스 만들기
    public static class DbSource{
        public List<Db> getData() throws ClassNotFoundException, SQLException {
            String sql = "SELECT * FROM StudentScore";

            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Db> list = new ArrayList<>(); //각자 테이블에서 추출한 값을 담아줄 리스트
            // 테이블 돌면서 각 칼럼들 가져와서 그릇에 담는 루프
            while (rs.next()) { //테이블의 마지막까지 탐색
                String columnName = rs.getString("이름");
                int columnKorean = rs.getInt("국어");
                int columnEnglish = rs.getInt("영어");
                int columnMath = rs.getInt("수학");
                Double columnAverage = rs.getDouble("평균");
                // 추출 완료

                Db db = new Db(columnName,columnKorean,columnEnglish,columnMath,columnAverage); // 추출한 값을 생성자 만들어놨던 Db 그릇에 담는다
                list.add(db); // 담은 값을 리스트에 담기
            }
            rs.close();
            stmt.close();
            con.close();
            return list; // 테이블에서 추출한 값이 담긴 리스트
        }
    }

    // 추출한 값을 정해진 규칙에 맞게(printf써서 보기좋게 해봄) 콘솔창에 출력하는 클래스
    public static class DbConsole {
        private final DbSource ds;

        public DbConsole() {
            ds = new DbSource();
        }

        public void printDbList() throws SQLException, ClassNotFoundException {
            List<Db> list = ds.getData();
            // DbSource.getData 에서 아웃풋된 리스트

            // 결과값 출력
            System.out.println("학생성적관리프로그램(v0.0.2)");
            System.out.println("----------------------------");
            list.forEach(m-> System.out.printf("%-6s %4d %4d %4d %6.1f%n", m.columnName,m.columnKorean,m.columnEnglish,m.columnMath,m.columnAverage));
            //list에 담긴 member형태의 데이터를 첫번째부터 하나하나 꺼내오기
            System.out.println("----------------------------");
        }

    }

}
