package free.zereb.utils;

import free.zereb.Main;
import free.zereb.data.Record;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArgumentHandler {

    private HashMap<String, Argument> arguments = new HashMap<>();

    public ArgumentHandler(){
        arguments.put("-add", args -> {
            String username = args[1];
            String password = args[2];
            String url = args[3];
            Record record = new Record(0, password, username, url, "");
            Main.records.add(Record.insertInDB(record));
            System.exit(0);
        });

        arguments.put("-h", args -> {
            System.out.println("-add <username> <password> <url>");
            System.out.println("-rm <id>");
            System.out.println("<url> - gets passwords for url");
            System.out.println("-r print all records");
            System.exit(0);
        });

        arguments.put("-rm", args -> {
            String username = args[1];
            String url = args[2];
            System.out.println("Found these records, remove? [y/n]");
            List<Record> toRemove = Main.records.stream()
                    .filter(record -> record.url.contains(url))
                    .filter(record -> record.username.equals(username))
                    .collect(Collectors.toList());
            toRemove.forEach(System.out::println);
            if (toRemove.size() < 1)
                System.exit(0);
            Scanner scanner = new Scanner(System.in);
            if (scanner.next().equals("y")){
                Main.records.removeAll(toRemove);
                toRemove.forEach(Record::removeFromDB);
            }
            System.exit(0);
        });

        arguments.put(".*", args -> {
            if (args.length < 1)
                return;
            String url = args[0];
            Main.records.stream()
                    .filter(record -> record.url.contains(url))
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
            System.exit(0);
        });

        arguments.put("-r", args -> {
            Main.records.forEach(System.out::println);
            System.exit(0);
        });

    }

    public void runArgs(String[] args){
        for (String arg: args){
            arguments.forEach((cmd, interf) ->{
                if (arg.matches(cmd))
                    interf.argRun(args);
            });
        }
    }
}

