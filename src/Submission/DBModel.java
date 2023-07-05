package Submission;

public class DBModel {
    String columnName;
    int columnKorean;
    int columnEnglish;
    int columnMath;
    Double columnAverage;
    public DBModel(String columnName, int columnKorean, int columnEnglish, int columnMath, Double columnAverage) {
        this.columnName = columnName;
        this.columnKorean = columnKorean;
        this.columnEnglish = columnEnglish;
        this.columnMath = columnMath;
        this.columnAverage = columnAverage;
    }
}
