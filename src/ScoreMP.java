import java.util.Scanner;

public class ScoreMP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String start = "0";
        String stop = "1";
        int idx = 1;
        String[][] studentInfoResult = new String[10][5];
        studentInfoResult[0][0] = "이름";
        studentInfoResult[0][1] = "국어";
        studentInfoResult[0][2] = "영어";
        studentInfoResult[0][3] = "수학";
        studentInfoResult[0][4] = "평균";


//        do {
//            System.out.println("입력하시려면 0을 누르십시오");
            while (true) {
                String firstStart = sc.next();
                String contents = sc.nextLine();
                String[] studentInfo = contents.split(" ");
                Student student = new Student(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3]);
                studentInfoResult[idx][0] = student.name;
                studentInfoResult[idx][1] = student.korean;
                studentInfoResult[idx][2] = student.english;
                studentInfoResult[idx][3] = student.math;
                Double average = ((Double.parseDouble(student.korean) + Double.parseDouble(student.english) + Double.parseDouble(student.math)) / 3);
                studentInfoResult[idx][4] = String.valueOf(average);

                String startOrStop = sc.next();
                if (startOrStop.equals(start)) {
                    idx++;
                    continue;
                } else if (startOrStop.equals(stop)) {
                    System.out.println("학생성적관리프로그램(v0.0.2)");
                    System.out.println("----------------------------");
                    for (int i = 0; i < studentInfoResult.length; i++) {
                        for (int j = 0; j < studentInfoResult[i].length; j++) {
                            System.out.print(studentInfoResult[i][j] + "\t");
                        }
                        System.out.println(" ");
                    }
                    System.out.println("----------------------------");
                    break;
                }
            }
//        }


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
