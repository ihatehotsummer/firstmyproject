import java.util.Scanner;

public class ScoreMPTestC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 스캐너 사용 인풋 받음

        // 루프 멈출지 말지 결정하는 인스턴스 변수
        String start = "0";
        String stop = "1";
        int idx = 1; //루프 내 증가하는 인덱스 표현

        // 배열이 반복되며 생기는 정보를 저장 해야할 껍데기 변수
        String[][] studentInfoResult = new String[][] {
                {"이름", "국어", "영어" , "수학", "평균"}
        };
        // 초기값 입력
//        studentInfoResult[0][0] = "이름";
//        studentInfoResult[0][1] = "국어";
//        studentInfoResult[0][2] = "영어";
//        studentInfoResult[0][3] = "수학";
//        studentInfoResult[0][4] = "평균";
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

                // 클래스 Student 에 원하는 각각의 전달값을 받는 생성자
                student = new Student(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3]);
                studentInfoResult[idx][0] = student.name;
                studentInfoResult[idx][1] = student.korean;
                studentInfoResult[idx][2] = student.english;
                studentInfoResult[idx][3] = student.math;

                // 평균값 구하기 씨발; 존나 대가리 터질뻔
                Double average = ((Double.parseDouble(student.korean) + Double.parseDouble(student.english) + Double.parseDouble(student.math)) / 3);
                studentInfoResult[idx][4] = String.format("%.2f", average);
                idx++; // 인덱스 증가시킴
            }
        }

        // 결과값 출력
        System.out.println("학생성적관리프로그램(v0.0.2)");
        System.out.println("----------------------------");
        for (int i = 0; i < idx; i++) {
            for (int j = 0; j < studentInfoResult[i].length; j++) {
                System.out.print(studentInfoResult[i][j] + "\t");
            }
            System.out.println();
        }
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
        // 걍 에러 다뺏다
//    class WrittenWrongValue extends Exception {
//        public WrittenWrongValue(String message) {
//            super(message);
//        }
//    }
//
}
