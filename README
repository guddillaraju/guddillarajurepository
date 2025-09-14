
---

## 🧾 Reasonable Assumptions

To fulfill the requirements and ensure consistent behavior, the following assumptions were made:

1. **System Compatibility**
   - The utility is designed to run on Unix-like systems (Linux/macOS) with support for `bash`, `date`, and `echo`.
   - Windows users may need to adjust shell execution logic or use WSL.

2. **Time Zone Handling**
   - All scheduling is based on the system’s local time zone (e.g., IST for India).
   - No conversion is done for UTC or other zones.

3. **Command File Format**
   - The `/tmp/commands.txt` file is assumed to be well-formed.
   - Malformed lines are ignored with a warning (not fatal).

4. **Past One-Time Commands**
   - Commands scheduled for times earlier than the current system time are skipped.

5. **Recurring Command Execution**
   - Recurring commands begin immediately and repeat every `n` minutes.
   - There is no upper limit on how long the utility runs.

6. **Concurrency**
   - Multiple commands can be scheduled and executed concurrently using a thread pool.

7. **Output Logging**
   - All command outputs (stdout and stderr) are appended to `sample-output.txt`.
   - Each entry includes a timestamp and the original command.

8. **Security**
   - The utility executes commands as-is using `bash -c`. It assumes trusted input.
   - No sandboxing or validation is performed on command content.

9. **Java Version**
   - The utility is compatible with Java 8 and above.

---

## 🧪 Sample Output

**sample-output.txt**
