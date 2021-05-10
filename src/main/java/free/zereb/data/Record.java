package free.zereb.data;

import free.zereb.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Record {

    public int id;
    private final String password;
    public final String username;
    public final String url;
    public final String notes;

    public Record(int id, String password, String username, String url, String notes) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.url = url;
        this.notes = notes;
    }


    public String toString(){
        if (notes.isBlank())
            return id + " Username: " + username + " Password: " + password + " " + url;
        else
            return id + " Username: " + username + " Password: " + password + " " + url + " notes: " + notes;

    }

    public static Record insertInDB(Record record){
        int generatedId = generateId();
        record.id = generatedId;
        try (Connection connection = Main.h2.getConnection();
             PreparedStatement prep = connection.prepareStatement("insert into records (id, password, username, url, notes) values (?,?,?,?,?)")
        ){
            prep.setInt(1, generatedId);
            prep.setString(2, record.password);
            prep.setString(3, record.username);
            prep.setString(4, record.url);
            prep.setString(5, record.notes);
            prep.execute();
            System.out.println("Added record: " + record.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public static void removeFromDB(Record record){
        try (Connection connection = Main.h2.getConnection();
             PreparedStatement prep = connection.prepareStatement("delete from records where id = ?")
        ){
            prep.setInt(1, record.id);
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
                       resultSet.getInt(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getString(4),
                       resultSet.getString(5))
               );
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }


    public static  int generateId(){
        try (Connection connection = Main.h2.getConnection();
             PreparedStatement statement = connection.prepareStatement("select id from records order by id desc limit 1");
             ResultSet resultSet = statement.executeQuery();
        ){
            while (resultSet.next()){
                return resultSet.getInt(1) + 1;}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}


