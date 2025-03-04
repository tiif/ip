package duke;

/**
 * Ui class for duke
 */
public class Ui {
    /**
     * Show welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm ChatBot" + "\n" + "What can I do for you?" + "\n");
    }

    /**
     * Show goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print the list of task.
     * @param tasks the TaskList
     */
    public void printTaskList(TaskList tasks) {
        int size = tasks.getSize();
        for (int i = 0; i < size; i++) {
            Task curr = tasks.getTask(i);
            System.out.println((i + 1) + ". " + curr);
        }
        return;
    }

    /**
     * Printed when task is added.
     * @param curr current task being added
     * @param taskSize the getSize of TaskList
     */
    public void printAddTask(Task curr, int taskSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(curr);
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    /**
     * Printed when task is deleted.
     * @param curr current task being deleted
     * @param taskSize the getSize of TaskList
     */
    public void printDelete(Task curr, int taskSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(curr);
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    /**
     * Printed when task is marked as done.
     * @param curr current task
     * @param index index of the task in TaskList
     */
    public void printMark(Task curr, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((index + 1) + ". " + curr);
    }
    /**
     * Printed when task is marked as not done.
     * @param curr current task
     * @param index index of the task in TaskList
     */
    public void printUnmark(Task curr, int index) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println((index + 1) + ". " + curr);
    }

    /**
     * Show loading error.
     * @param message error message
     */
    public void showLoadingError(String message) {
        System.out.println(message);
    }

    /**
     * Print search query result.
     * @param results a TaskList of search result
     */
    public void printQueryResult(TaskList results) {
        System.out.println("Here are the matching tasks in your list:");
        printTaskList(results);
    }
}
