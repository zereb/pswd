package free.zereb.commands;

import free.zereb.Main;
import free.zereb.data.Record;
import free.zereb.utils.Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CreatePass implements Command {


    @Override
    public String getDescription() {
        return "Removes records from db";
    }

    @Override
    public void run() {
        Scanner recSc = new Scanner(System.in);
        System.out.println("Create new .pass file? [y/n]");
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equals("y")){
            System.out.println("input password:");
            String password = scanner.next();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(Main.XDG_CONFIG_HOME+"pswd/.pass"))){
                writer.write(password);
            }catch (IOException e){
                e.printStackTrace();
                System.exit(0);
            }
        }else
            System.exit(0);
    }
}
