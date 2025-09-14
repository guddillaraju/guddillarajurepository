package com.example.demo;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommandScheduler {
    private final List<ScheduledCommand> commands;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
    private final File outputFile = new File("sample-output.txt");

    public CommandScheduler(List<ScheduledCommand> commands) {
        this.commands = commands;
    }

    public void start() {
        for (ScheduledCommand cmd : commands) {
            if (cmd.type == ScheduledCommand.Type.ONE_TIME) {
                scheduleOneTime(cmd);
            } else {
                scheduleRecurring(cmd);
            }
        }
    }

    private void scheduleOneTime(ScheduledCommand cmd) {
        long delay = Duration.between(LocalDateTime.now(), cmd.scheduledTime).toMillis();
        if (delay < 0) return; // Skip past commands

        executor.schedule(() -> executeCommand(cmd.command), delay, TimeUnit.MILLISECONDS);
    }

    private void scheduleRecurring(ScheduledCommand cmd) {
        executor.scheduleAtFixedRate(() -> executeCommand(cmd.command),
                0, cmd.intervalMinutes, TimeUnit.MINUTES);
    }

    private void executeCommand(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();
            logOutput(command, output.toString());
        } catch (Exception e) {
            logOutput(command, "Error: " + e.getMessage());
        }
    }

    private synchronized void logOutput(String command, String output) {
        try (FileWriter fw = new FileWriter(outputFile, true)) {
            fw.write("[" + LocalDateTime.now() + "] Command: " + command + "\n");
            fw.write("Output:\n" + output + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
