package free.zereb;

import free.zereb.commands.*;
import free.zereb.data.Record;
import free.zereb.utils.ArgumentHandler;
import free.zereb.commands.Command;
import free.zereb.utils.H2Connect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

public static ArrayList<Record> records;
public static HashMap<String, Command> commands = new HashMap<>();
public static H2Connect h2;
public static final String XDG_CONFIG_HOME = System.getenv().get("XDG_CONFIG_HOME");
public static String PASS;


    public Main(String[] args) {


        try {
            PASS = new String(Files.readAllBytes(Paths.get(XDG_CONFIG_HOME+"/pswd/.pass")));
        } catch (IOException e) {
            System.out.println(".pass does not exist");
            new CreatePass().run();
        }
        h2 = new H2Connect();
        records = Record.getRecordsFromDB();
        new ArgumentHandler().runArgs(args);

        commands.put("q", new Exit());
        commands.put("help", new Help());
        commands.put("add", new AddRecord());
        commands.put("rm", new RemoveRecords());
        commands.put("records", new getRecords());

        commands.get("help").run();

        while (true) {
            Scanner in = new Scanner(System.in);
            String command = in.next();
            commands.forEach((cmd, run) -> {
                if (command.equals(cmd))
                    run.run();
            });
        }
    }

    public static void main(String[] args) {
        new Main(args);
    }

}
