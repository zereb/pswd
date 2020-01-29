package free.zereb.utils;

import free.zereb.Main;
import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connect {

    private final String JDBC_DRIVER = "org.h2.Driver";
//    private final String DB_URL = "jdbc:h2:~/.config/pswd/pswd";
    private final String DB_URL = "jdbc:h2:" + Main.XDG_CONFIG_HOME + "/pswd/pswd";
    private final String USER = "test";

    public H2Connect(){
        try {
            Server server = Server.createTcpServer("-tcpAllowOthers").start();
            Class.forName(JDBC_DRIVER);
            init();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, Main.PASS);
    }

    private void init(){
        InputStream in = Main.class.getResourceAsStream("/init.sql");
        try(Connection connection = getConnection()
        ){
            RunScript.execute(connection, new InputStreamReader(in));
        }catch (Exception e){
            e.printStackTrace();
        }
    }





}
