package free.zereb.utils;

import free.zereb.Main;
import free.zereb.data.Record;

import java.util.HashMap;
import java.util.stream.Collectors;

public class ArgumentHandler {

    private HashMap<String, Argument> arguments = new HashMap<>();

    public ArgumentHandler(){
        arguments.put("-add", args -> {
            String username = args[1];
            String password = args[2];
            String url = args[3];
            Record record = new Record(password, username, url);
            Main.records.add(Record.insertInDB(record));
        });

        arguments.put("-h", args -> {
           System.out.println("-add <username> <password> <url>");
           System.out.println("<url> - gets passwords for url");
        });

        arguments.put("", args -> {
            if (args.length < 1)
                return;
            String url = args[0];
            Main.records.stream().filter(record -> record.url.contains(url))
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
            System.exit(0);
        });
    }

    public void runArgs(String[] args){
        for (String arg: args){
            arguments.forEach((cmd, interf) ->{
                if (arg.contains(cmd))
                    interf.argRun(args);
            });
        }
    }
}

