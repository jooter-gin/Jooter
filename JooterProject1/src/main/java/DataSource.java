import java.sql.*;

public final class DataSource {

    private static final String DB_NAME = "zchmtson";

    private static final String CONNECTION_STRING = "jdbc:postgresql://hattie.db.elephantsql.com:5432/" + DB_NAME;

    private Connection c;

    private static final DataSource instance = new DataSource();

    private PreparedStatement insertIntoUsers;

    private PreparedStatement queryUsers;

    public static final int REGULAR_ORDER = 1;

    public static final int ASC_ORDER = 2;

    public static final int DESC_ORDER = 3;

    private DataSource() {
    }

    public static DataSource getInstance() {

        return instance;
    }

    private static final String TABLE_USERS = "Users";
    private static final String COLUMN_USER_ID = "Id";
    private static final String COLUMN_USER_NAME = "Name";
    private static final String COLUMN_USER_SURNAME = "Surname";
    private static final String COLUMN_USER_LOGIN = "Login";
    private static final String COLUMN_USER_PASSWORD = "Password";
    private static final String COLUMN_USER_EMAIL = "Email";
    private static final String COLUMN_USER_CARD_NO = "CardNo";
    private static final String COLUMN_USER_ACC_BALANCE = "AccountBalance";

    public static String getColumnUserLogin() {
        return COLUMN_USER_LOGIN;
    }

    public static String getColumnUserEmail() {
        return COLUMN_USER_EMAIL;
    }

    private static final String TABLE_ADMINS = "Admins";
    private static final String COLUMN_ADMIN_ID = "Id";
    private static final String COLUMN_ADMIN_NAME = "Name";
    private static final String COLUMN_ADMIN_SURNAME = "Surname";
    private static final String COLUMN_ADMIN_LOGIN = "Login";
    private static final String COLUMN_ADMIN_PASSWORD = "Password";
    private static final String COLUMN_ADMIN_EMAIL = "Email";

    private final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS "+" " + TABLE_USERS +
            "(" +
            COLUMN_USER_ID + " SERIAL PRIMARY KEY,  " +
            COLUMN_USER_NAME + " varchar(20) NOT NULL, " +
            COLUMN_USER_SURNAME + " varchar(20) NOT NULL, " +
            COLUMN_USER_LOGIN + " varchar(20) NOT NULL, "  +
            COLUMN_USER_PASSWORD  + " varchar(20) NOT NULL, "  +
            COLUMN_USER_EMAIL + " varchar(20) NOT NULL, " +
            COLUMN_USER_CARD_NO + " varchar(20) NOT NULL, " +
            COLUMN_USER_ACC_BALANCE + " INT NOT NULL )";

    private final String CREATE_ADMINS_TABLE = "CREATE TABLE IF NOT EXISTS " + " " + TABLE_ADMINS +
            "(" +
            COLUMN_ADMIN_ID+ " SERIAL PRIMARY KEY, " +
            COLUMN_ADMIN_NAME + " varchar(20) NOT NULL, " +
            COLUMN_ADMIN_SURNAME +" varchar(20) NOT NULL, "+
            COLUMN_ADMIN_LOGIN + " varchar(20) NOT NULL,"  +
            COLUMN_ADMIN_PASSWORD  + " varchar(20) NOT NULL, "  +
            COLUMN_ADMIN_EMAIL + " varchar(20) NOT NULL) ";


    private static final String QUERY_USERS = "SELECT * FROM " + TABLE_USERS;

    public ResultSet queryUsers() throws SQLException{

        return queryUsers.executeQuery();
    }

    private final String INSERT_INTO_USERS = "INSERT INTO " + TABLE_USERS + " ( "  + COLUMN_USER_NAME + ", " + COLUMN_USER_SURNAME + ", " + COLUMN_USER_LOGIN + ", " + COLUMN_USER_PASSWORD + ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_CARD_NO + ", " + COLUMN_USER_ACC_BALANCE + " ) " +
            "VALUES ( ? , ? , ? , ?, ?, ?, ?)";

    public void insertUsers(User user){

        try {
            c.setAutoCommit(false);
            insertIntoUsers.setString(1, user.getUserName());
            insertIntoUsers.setString(2,user.getUserSurname());
            insertIntoUsers.setString(3, user.getUserLogin());
            insertIntoUsers.setString(4, user.getUserPassword());
            insertIntoUsers.setString(5, user.getUserEmail());
            insertIntoUsers.setString(6, user.getUserCardNo());
            insertIntoUsers.setInt(7, user.getUserAccountBalance());
            int affectedRows =  insertIntoUsers.executeUpdate();
            if(affectedRows==1) {
                c.commit();
            }else{
                throw new SQLException("The user insertion failed");
            }
        }catch(SQLException e1){
            try{
                System.out.println("Rollback");
                c.rollback();
            }catch(SQLException e2){
                System.out.println("Rollback failed");
                e2.printStackTrace();
            }
        }
    }

    public boolean open () {

        try{
            c = DriverManager.getConnection(CONNECTION_STRING, "zchmtson", "eidFBsA6ftUlntzXqXBjWrnBwEuXra3h");
            Statement stm = c.createStatement();
            c.setAutoCommit(false);
            stm.executeUpdate(CREATE_USERS_TABLE);
            stm.executeUpdate(CREATE_ADMINS_TABLE);
            c.commit();

            insertIntoUsers = c.prepareStatement(INSERT_INTO_USERS);
            queryUsers = c.prepareStatement(QUERY_USERS);

            stm.close();
            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public void close(){

        try{
            insertIntoUsers.close();
            queryUsers.close();
            c.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

