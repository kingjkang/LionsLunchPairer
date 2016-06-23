package sample;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by justinkang on 6/22/16.
 */
public class LLDB {

    static final String WRITE_OBJECT_SQL = "INSERT INTO java_objects(name, object_value) VALUES (?, ?)";

    static final String READ_OBJECT_SQL = "SELECT object_value FROM java_objects WHERE id = ?";

    public static Connection getConnection() throws Exception {
        String driver = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost:3306/lionsLunch";
        String user = "root";
        String password = "sunflower";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void addMember(String initEID, String initName, String initPhoneNumber, String initEmailAddress, String initYearClassification,
                                 Boolean initPairMajor, String initMajor, String initPermissions, Boolean initWeeklyPair, String initPersonality, String initAdditionalInfo){

        try {
            //creating a sql database connection
            Connection conn = getConnection();

            //the sql insert statement
            String query =  " insert into members(member_eid, member_name, member_phone, member_email, member_year, member_pair_major, member_major, member_permission, member_weekly_pair" +
                    ", member_personality, member_additional_info)" + " values(?,?,?,?,?,?,?,?,?,?,?)";

            //create the sql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, initEID);
            preparedStmt.setString(2, initName);
            preparedStmt.setString(3, initPhoneNumber);
            preparedStmt.setString(4, initEmailAddress);
            preparedStmt.setString(5, initYearClassification);
            preparedStmt.setBoolean(6, initPairMajor);
            preparedStmt.setString(7, initMajor);
            preparedStmt.setString(8, initPermissions);
            preparedStmt.setBoolean(9, initWeeklyPair);
            preparedStmt.setString(10, initPersonality);
            preparedStmt.setString(11, initAdditionalInfo);
            //maybe add past pairs to add him or herself to the list idk

            //execute the preparedstatement
            preparedStmt.execute();

            //close the connection
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteMember(String memberEID){

        try {
            //creating a sql database connection
            Connection conn = getConnection();

            //creating the sql delete element statement
            String query = "delete from members where member_eid = ?";

            //creating the preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, memberEID);

            //execute the prepared statememnt
            preparedStmt.execute();

            //close the connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePastPairs(LionsLunchMember updatingMember){

        try {
            //creating a sql database connection
            Connection conn = getConnection();

            //creating the sql delete element statement
            String query = "update members set member_past_pairs = ? where member_eid = ?";

            //creating the preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, updatingMember.getPastPairs());
            preparedStmt.setString(2, updatingMember.getEID());

            //execute the prepared statememnt
            preparedStmt.execute();

            //close the connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<LionsLunchMember> getMemberInfo(){
        ArrayList<LionsLunchMember> memz = new ArrayList<LionsLunchMember>();
        try {
            //getting our sql database connectoin
            Connection conn = getConnection();

            //oru SQL Select Query
            String query = "select * from members";

            //create the java statment
            Statement st = conn.createStatement();

            //execute the query and get a java result set
            ResultSet rs = st.executeQuery(query);

            //iterate throuhg the java result set and create the internal javaDB
            LionsLunchMember addMem;
            while (rs.next()){
                String eid = rs.getString("member_eid");
                String name = rs.getString("member_name");
                String phone = rs.getString("member_phone");
                String email = rs.getString("member_email");
                String year = rs.getString("member_year");
                Boolean pairMajor = rs.getBoolean("member_pair_major");
                String major = rs.getString("member_major");
                String permission = rs.getString("member_permission");
                Boolean weeklyPair = rs.getBoolean("member_weekly_pair");
                String personality = rs.getString("member_personality");
                String addInfo = rs.getString("member_additional_info");
                String pastPairs = rs.getString("member_past_pairs");
                addMem = new LionsLunchMember(eid, name, phone, email, year, pairMajor, major, permission, weeklyPair, personality, addInfo, pastPairs);
                memz.add(addMem);
            }

            //close the statement
            st.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return memz;
    }


    public static void getFromDB(){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/learning";
        String user = "root";
        String password = "sunflower";

        try {

            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement("SELECT * FROM Authors");
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(LLDB.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(LLDB.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }


}
