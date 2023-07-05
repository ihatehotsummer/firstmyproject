package Submission;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class NewAssignment extends DBHelper implements DBHelperIF {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = new DBHelper();
        // schema 만드는 메소드
        dbHelper.createSchemaA();
        // 테이블 만드는 메소드 테이블 명 이름 국어 영어 수학 평균
        dbHelper.createTableA();;

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
                dbHelper.insertData(student.name, student.korean, student.english, student.math);
            }
        }
        // 콘솔창에 데이터 출력하기
        dbHelper.readData();


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
}
