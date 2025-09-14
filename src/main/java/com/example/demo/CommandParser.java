package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private static final Pattern oneTimePattern = Pattern.compile("^(\\d+) (\\d+) (\\d+) (\\d+) (\\d+) (.+)$");
    private static final Pattern recurringPattern = Pattern.compile("^\\*/(\\d+) (.+)$");

    public static List<ScheduledCommand> parseCommands(String filePath) throws IOException {
        List<ScheduledCommand> commands = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            Matcher oneTime = oneTimePattern.matcher(line);
            Matcher recurring = recurringPattern.matcher(line);

            if (oneTime.matches()) {
                int minute = Integer.parseInt(oneTime.group(1));
                int hour = Integer.parseInt(oneTime.group(2));
                int day = Integer.parseInt(oneTime.group(3));
                int month = Integer.parseInt(oneTime.group(4));
                int year = Integer.parseInt(oneTime.group(5));
                String command = oneTime.group(6);

                LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute);
                commands.add(new ScheduledCommand(ScheduledCommand.Type.ONE_TIME, time, 0, command));
            } else if (recurring.matches()) {
                int interval = Integer.parseInt(recurring.group(1));
                String command = recurring.group(2);
                commands.add(new ScheduledCommand(ScheduledCommand.Type.RECURRING, null, interval, command));
            }
        }

        reader.close();
        return commands;
    }
}
