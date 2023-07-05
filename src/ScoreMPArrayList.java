import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreMPArrayList {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "submission";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hwang8107";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 스캐너 사용 인풋 받음

        // schema 만드는 메소드
        createSchema();
        // 테이블 만드는 메소드 테이블 명 이름 국어 영어 수학 평균
        createTable();

        // 성적 넣을 어레이리스트import java.util.ArrayList;
        //import java.util.Scanner;
        //import java.sql.Connection;
        //import java.sql.DriverManager;
        //import java.sql.SQLException;
        //import java.sql.Statement;
        //
        //public class ScoreMPArrayList {
        //    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
        //    private static final String DB_NAME = "submission";
        //    private static final String DB_USER = "root";
        //    private static final String DB_PASSWORD = "hwang8107";
        //
        //    public static void main(String[] args) {
        //        Scanner sc = new Scanner(System.in); // 스캐너 사용 인풋 받음
        //
        //        // schema 만드는 메소드
        //        createSchema();
        //        // 테이블 만드는 메소드 테이블 명 이름 국어 영어 수학 평균
        //        createTable();
        //
        //        // 성적 넣을 어레이리스트
        //        ArrayList<String> scoreList = new ArrayList<>();
        //
        //        // 루프 멈출지 말지 결정하는 인스턴스 변수
        //        String start = "0";
        //        String stop = "1";
        //        int idx = 1; //루프 내 증가하는 인덱스 표현
        //
        //        Student student;
        //
        //        System.out.println("학생성적관리 프로그램에 접속하셨습니다.");
        //        System.out.println("성적을 입력하기를 원하면 0을 입력하고 다 입력했으면 1을 눌러라.");
        //        System.out.println("이름 국어 영어 수학 순으로 입력하고 ㅈ대로 입력하면 나도 어찌될지 모른다");
        //        // 루프 시작할지 말지를 알리는 인풋
        //        String firstStart = sc.nextLine();
        //        if (firstStart.equals("0")) {
        //            while (true) {
        //                String contents = sc.nextLine(); // 학생 성적 입력
        //                if (contents.equals("1")) { // 1을 입력하면 프로그램 종료
        //                    break;
        //                }
        //                String[] studentInfo = contents.split(" "); // 학생 정보 입력받은걸 " " 스페이스바 기준으로 나눔 -> 배열생성됨
        //
        //                // 문자열 잘못 입력했을 시
        //                if (Integer.parseInt(studentInfo[1]) >99 || Integer.parseInt(studentInfo[2]) >99 || Integer.parseInt(studentInfo[3]) >99) {
        //                    System.out.println("잘못 입력하셨습니다. 다시 입력하세요");
        //                    continue;
        //                }
        //
        //                // 클래스 Student 에 원하는 각각의 전달값을 받는 생성자
        //                student = new Student(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3]);
        //                scoreList.add(student.name);
        //                scoreList.add(student.korean);
        //                scoreList.add(student.english);
        //                scoreList.add(student.math);
        //
        //                // 평균값 구하기 씨발; 존나 대가리 터질뻔
        //                Double average = ((Double.parseDouble(student.korean) + Double.parseDouble(student.english) + Double.parseDouble(student.math)) / 3);
        //                String averageScore = String.format("%.2f", average);
        //                scoreList.add(averageScore);
        //
        //                for (String value : scoreList) {
        //                    // db에 넣을 메소드 뿅
        //                }
        //
        //            }
        //        }
        //
        //        // 결과값 출력
        //        System.out.println("학생성적관리프로그램(v0.0.2)");
        //        System.out.println("----------------------------");
        //        // db에서 불러오기~
        //        System.out.println("----------------------------");
        //    }
        //
        //    static class Student {
        //        String name;
        //        String korean;
        //        String english;
        //        String math;
        //
        //        public Student(String name, String korean, String english, String math) {
        //            this.name = name;
        //            this.korean = korean;
        //            this.english = english;
        //            this.math = math;
        //        }
        //    }
        //
        //    // 스키마 생성 메소드
        //    public static void createSchema() {
        //        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        //             Statement stmt = conn.createStatement()) {
        //            String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
        //            stmt.executeUpdate(sql);
        //        } catch (SQLException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //
        //    // 테이블 생성 메소드
        //    public static void createTable() {
        //        String createTableSQL = "CREATE TABLE IF NOT EXISTS StudentScore (" +
        //                "이름 VARCHAR(10) PRIMARY KEY NOT NULL," +
        //                "국어 INT NOT NULL," +
        //                "영어 INT NOT NULL," +
        //                "수학 INT NOT NULL," +
        //                "평균 INT NOT NULL)";
        //
        //        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
        //             Statement stmt = conn.createStatement()) {
        //            stmt.executeUpdate(createTableSQL);
        //        } catch (SQLException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //
        //}
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

                // 문자열 잘못 입력했을 시
                if (Integer.parseInt(studentInfo[1]) >99 || Integer.parseInt(studentInfo[2]) >99 || Integer.parseInt(studentInfo[3]) >99) {
                    System.out.println("잘못 입력하셨습니다. 다시 입력하세요");
                    continue;
                }

                // 클래스 Student 에 원하는 각각의 전달값을 받는 생성자
                student = new Student(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3]);
                scoreList.add(student.name);
                scoreList.add(student.korean);
                scoreList.add(student.english);
                scoreList.add(student.math);

                // 평균값 구하기 씨발; 존나 대가리 터질뻔
                Double average = ((Double.parseDouble(student.korean) + Double.parseDouble(student.english) + Double.parseDouble(student.math)) / 3);
                String averageScore = String.format("%.2f", average);
                scoreList.add(averageScore);

                for (String value : scoreList) {
                    // db에 넣을 메소드 뿅
                }

            }
        }

        // 결과값 출력
        System.out.println("학생성적관리프로그램(v0.0.2)");
        System.out.println("----------------------------");
        // db에서 불러오기~
        System.out.println("----------------------------");
    }

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
                "평균 INT NOT NULL)";

        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
