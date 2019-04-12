package ro.mirodone;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONN_STRING = "jdbc:sqlite:C:\\Users\\mirodone\\Desktop\\java2018\\TestDB\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";


    public static void main(String[] args) {

        //try with resources for closing the connection
/*        try( Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mirodone\\Desktop\\java2018\\xDatabases\\testjava.db");
             Statement statement = conn.createStatement())
        { statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");*/

        try {
            Connection conn = DriverManager.getConnection(CONN_STRING);
            //conn.setAutoCommit(false); // all changes must be committed manually
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text," +
                    COLUMN_PHONE + " integer, " +
                    COLUMN_EMAIL + " text" + ")");

            insertContact(statement,"John", 34743244, "john@mail.com" );
            insertContact(statement,"Mary", 6577312, "mmm@email.com" );
            insertContact(statement,"Lisa", 89898989, "lisalisa@mail.com" );

//            statement.execute("INSERT INTO " + TABLE_CONTACTS +
//                    "(" + COLUMN_NAME + ", " +
//                    COLUMN_PHONE + ", " +
//                    COLUMN_EMAIL + ")" +
//                    "VALUES ('John', 34743244, 'john@mail.com')");



//           statement.execute("INSERT INTO contacts (name, phone, email)" +
//                           "VALUES ('John', '74564356', 'john@mail.com')");

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " + COLUMN_PHONE + "=222222" + " WHERE " + COLUMN_NAME + "='Lisa'");
//         statement.execute("UPDATE contacts SET phone=8787878 WHERE name='Mary'");


//         statement.execute("DELETE FROM contacts WHERE name='john'");

//replaced with executeQuery()
//        statement.execute("SELECT * FROM contacts");
//        ResultSet results = statement.getResultSet();

            ResultSet results = statement.executeQuery("SELECT * FROM contacts");

            while (results.next()) {
                System.out.println(results.getString("name") + " " +
                        results.getString("phone") + " " +
                        results.getString("email"));
            }
            results.close();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

    private static void insertContact (Statement st, String name, int phone, String email) throws SQLException{
    st.execute("INSERT INTO " + TABLE_CONTACTS +
            "(" + COLUMN_NAME + ", " +
            COLUMN_PHONE + ", " +
            COLUMN_EMAIL + ")" +
            "VALUES ('" + name +"', " + phone +", '" + email +"')");
}
}
