package Submission;

import java.sql.SQLException;
import java.util.List;

public interface DBHelperIF {
    void createSchemaA() throws SQLException, ClassNotFoundException;
    void createTableA();

    void insertData(String name, String korean, String english, String math) throws ClassNotFoundException, SQLException;

    void readData() throws ClassNotFoundException, SQLException;
}
