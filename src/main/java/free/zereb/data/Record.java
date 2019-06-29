package free.zereb.data;

import free.zereb.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Record {

    private final String password;
    public final String username;
    public final String url;

    public Record(String password, String username, String url) {
        this.password = password;
        this.username = username;
        this.url = url;
    }


    public String toString(){
        return "Username:" + username + " Password: " + password + " " + url;
    }

    public static Record insertInDB(Record record){
        try (Connection connection = Main.h2.getConnection();
             PreparedStatement prep = connection.prepareStatement("insert into records (password, username, url) values (?,?,?)")
        ){
            prep.setString(1, record.password);
            prep.setString(2, record.username);
            prep.setString(3, record.url);
            prep.execute();
            System.out.println("Added record: " + record.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public static void removeFromDB(Record record){
        try (Connection connection = Main.h2.getConnection();
             PreparedStatement prep = connection.prepareStatement("delete from records where username = ? and url = ?")
        ){
            prep.setString(1, record.username);
            prep.setString(2, record.url);
            prep.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static ArrayList<Record> getRecordsFromDB(){
        ArrayList<Record> records = new ArrayList<>();
        try (Connection connection = Main.h2.getConnection();
             PreparedStatement prep = connection.prepareStatement("select * from records");
             ResultSet resultSet = prep.executeQuery()
        ){
           while (resultSet.next()){
               records.add(new Record(
                       resultSet.getString(1),
                       resultSet.getString(2),
                       resultSet.getString(3)
               ));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

}
