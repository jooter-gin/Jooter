package sample;

import java.sql.*;

public final class DataSource {

   private static final String DB_NAME = "zchmtson";
//
   private static final String CONNECTION_STRING = "jdbc:postgresql://hattie.db.elephantsql.com:5432/" + DB_NAME;

//    private static final String DB_NAME = "Jooter2";

//   private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/" + DB_NAME;

    private Connection c;

    private static final DataSource instance = new DataSource();

    private PreparedStatement insertIntoUsers;

    private PreparedStatement insertIntoScooters;

    private PreparedStatement deleteFromScooters;

    private PreparedStatement queryUsers;

    private PreparedStatement queryAdmins;

    private PreparedStatement queryScooters;

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

    public static String getColumnUserID() {
        return COLUMN_USER_ID;
    }

    public static String getColumnUserPassword() {
        return COLUMN_USER_PASSWORD;
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

    public static String getColumnAdminID() {
        return COLUMN_ADMIN_ID;
    }

    public static String getColumnAdminLogin() {
        return COLUMN_ADMIN_LOGIN;
    }

    public static String getColumnAdminPassword() {
        return COLUMN_ADMIN_PASSWORD;
    }

    public static String getColumnAdminEmail() {
        return COLUMN_ADMIN_EMAIL;
    }

    private static final String TABLE_SCOOTERS = "Scooters";
    private static final String COLUMN_SCOOTER_ID = "Id";
    private static final String COLUMN_SCOOTER_MODEL = "Model";
    private static final String COLUMN_SCOOTER_MAX_VELOCITY = "MaxVelocity";
    private static final String COLUMN_SCOOTER_COLOR = "ScooterColor";
    private static final String COLUMN_SCOOTER_AVAILABILITY = "ScooterAvailability";
    private static final String COLUMN_SCOOTER_BASKET = "ScooterBasket";
    private static final String COLUMN_SCOOTER_RANGE = "ScooterRange";
    private static final String COLUMN_SCOOTER_PRICE = "ScooterPrice";
    private static final String COLUMN_SCOOTER_BATTERY = "ScooterBattery";

    public static String getColumnScooterID() {return COLUMN_SCOOTER_ID;}

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

    private final String CREATE_SCOOTERS_TABLE = "CREATE TABLE IF NOT EXISTS "+" " + TABLE_SCOOTERS+
            "(" +
            COLUMN_SCOOTER_ID + " SERIAL PRIMARY KEY, " +
            COLUMN_SCOOTER_MODEL + " varchar(20) NOT NULL, " +
            COLUMN_SCOOTER_MAX_VELOCITY + " INT NOT NULL, "  +
            COLUMN_SCOOTER_COLOR  + " varchar(20) NOT NULL, "  +
            COLUMN_SCOOTER_AVAILABILITY + " INT NOT NULL, " +
            COLUMN_SCOOTER_BASKET + " INT NOT NULL, " +
            COLUMN_SCOOTER_RANGE + " INT NOT NULL, " +
            COLUMN_SCOOTER_PRICE + " INT NOT NULL, " +
            COLUMN_SCOOTER_BATTERY + " INT NOT NULL )";


    public static String getColumnScooterId() {
        return COLUMN_SCOOTER_ID;
    }

    public static String getColumnScooterModel() {
        return COLUMN_SCOOTER_MODEL;
    }

    public static String getColumnScooterMaxVelocity() {
        return COLUMN_SCOOTER_MAX_VELOCITY;
    }

    public static String getColumnScooterColor() {
        return COLUMN_SCOOTER_COLOR;
    }

    public static String getColumnScooterAvailability() {
        return COLUMN_SCOOTER_AVAILABILITY;
    }

    public static String getColumnScooterBasket() {
        return COLUMN_SCOOTER_BASKET;
    }

    public static String getColumnScooterRange() {
        return COLUMN_SCOOTER_RANGE;
    }

    public static String getColumnScooterPrice() {
        return COLUMN_SCOOTER_PRICE;
    }

    public static String getColumnScooterBattery() {
        return COLUMN_SCOOTER_BATTERY;
    }

    private static final String QUERY_USERS = "SELECT * FROM " + TABLE_USERS;

    public ResultSet queryUsers() throws SQLException{

        return queryUsers.executeQuery();
    }

    private static final String QUERY_ADMINS = " SELECT * FROM " + TABLE_ADMINS;

    public ResultSet queryAdmins() throws SQLException{

        return queryAdmins.executeQuery();
    }

    private static final String QUERY_SCOOTERS = " SELECT * FROM " + TABLE_SCOOTERS;

    public ResultSet queryScooters() throws SQLException{
        return queryScooters.executeQuery();
    }

    private final String INSERT_INTO_USERS = " INSERT INTO " + TABLE_USERS + " ( "  + COLUMN_USER_NAME + ", " + COLUMN_USER_SURNAME + ", " + COLUMN_USER_LOGIN + ", " + COLUMN_USER_PASSWORD + ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_CARD_NO + ", " + COLUMN_USER_ACC_BALANCE + " ) " +
            "VALUES ( ? , ? , ? , ?, ?, ?, ?)";

    private final String INSERT_INTO_SCOOTERS =" INSERT INTO " + TABLE_SCOOTERS + " ( " + COLUMN_SCOOTER_MODEL +", " + COLUMN_SCOOTER_MAX_VELOCITY + ", " + COLUMN_SCOOTER_COLOR + ", " + COLUMN_SCOOTER_AVAILABILITY + ", " + COLUMN_SCOOTER_BASKET + ", " + COLUMN_SCOOTER_RANGE +", "+ COLUMN_SCOOTER_PRICE + ", " + COLUMN_SCOOTER_BATTERY + " ) " +
            "VALUES ( ? , ? , ? , ?, ?, ?, ?, ? )";

    private final String DELETE_SCOOTER = " DELETE FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_ID + " = " + " ? ";



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

    public boolean insertScooters(Scooter scooter){


        try {
            c.setAutoCommit(false);
            insertIntoScooters.setString(1, scooter.getScooterModel());
            insertIntoScooters.setInt(2, scooter.getScooterMaxVelocity());
            insertIntoScooters.setString(3, scooter.getScooterColor());
            insertIntoScooters.setInt(4, scooter.getScooterAvailability());
            insertIntoScooters.setInt(5, scooter.getScooterBasket());
            insertIntoScooters.setInt(6, scooter.getScooterRange());
            insertIntoScooters.setInt(7, scooter.getScooterPrice());
            insertIntoScooters.setInt(8,scooter.getScooterBattery());
            int affectedRows =  insertIntoScooters.executeUpdate();
            if(affectedRows == 1){

                c.commit();
                return true;

            }else{

                throw new SQLException("The scooter insertion failed");

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
        return false;
    }

    public void deleteScooter (int scooterId) {



        try {
            c.setAutoCommit(false);
            deleteFromScooters.setInt(1,scooterId);
            deleteFromScooters.executeUpdate();
            c.commit();

        }catch(SQLException e){

            e.printStackTrace();
        }
    }

    public void open () {

        try{
            c = DriverManager.getConnection(CONNECTION_STRING, "zchmtson", "eidFBsA6ftUlntzXqXBjWrnBwEuXra3h");
            Statement stm = c.createStatement();
            c.setAutoCommit(false);
            stm.executeUpdate(CREATE_USERS_TABLE);
            stm.executeUpdate(CREATE_ADMINS_TABLE);
            stm.executeUpdate(CREATE_SCOOTERS_TABLE);
            c.commit();

            insertIntoUsers = c.prepareStatement(INSERT_INTO_USERS);
            insertIntoScooters = c.prepareStatement(INSERT_INTO_SCOOTERS);
            deleteFromScooters = c.prepareStatement(DELETE_SCOOTER);
            queryUsers = c.prepareStatement(QUERY_USERS);
            queryAdmins = c.prepareStatement(QUERY_ADMINS);
            queryScooters = c.prepareStatement(QUERY_SCOOTERS);

            stm.close();


        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    public void close(){

        try{
            insertIntoUsers.close();
            insertIntoScooters.close();
            deleteFromScooters.close();
            queryUsers.close();
            queryAdmins.close();
            queryScooters.close();
            c.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

