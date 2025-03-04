package duke;

/**
 * Parser class for the command.
 */
public class Parser {
    private String command;
    private String[] parsedStr;

    /**
     * Constructor for parser.
     */

    public Parser() {
        this.command = "";
    }

    /**
     * Parse the fullCommand and get the type of the command.
     * @param fullCommmand everything the user entered in a line
     * @return type of the command
     */
    public String parseCommand(String fullCommmand) {
        parsedStr = fullCommmand.split("\\s+");
        this.parsedStr = parsedStr;
        if (parsedStr[0].equals("f")) {
            return processFind();
        } else if (parsedStr[0].equals("ls")) {
            return processList();
        } else if (parsedStr[0].equals("mark")) {
            return processMark();
        } else if (parsedStr[0].equals("unmark")) {
            return processUnmark();
        } else if (fullCommmand.equals("bye")) {
            return processBye();
        } else if (parsedStr[0].equals("td")) {
            return processTodo();
        } else if (parsedStr[0].equals("ddl")) {
            return processDeadline();
        } else if (parsedStr[0].equals("ev")) {
            return processEvent();
        } else if (parsedStr[0].equals("dd")) {
            return processDelete();
        }
        return processInvalid();
    }

    private String processInvalid() {
        this.command = "";
        return "";
    }

    String processFind() {
        this.command = "find";
        return "find";
    }

    String processList() {
        if (parsedStr.length > 1) {
            //error handling
            System.out.println("Do not input another argument beside list");
            return "";
        }
        this.command = "list";
        return "list";
    }

    String processMark() {
        if (parsedStr.length < 2) {
            //error handling
            System.out.println("Please specify the index of the task");
            return "";
        } else if (parsedStr.length > 2) {
            System.out.println("Extra argument detected!");
            return "";
        }
        this.command = "mark";
        return "mark";
    }

    String processUnmark() {
        if (parsedStr.length < 2) {
            System.out.println("Please specify the index of the task");
            return "";
        } else if (parsedStr.length > 2) {
            System.out.println("Extra argument detected!");
            return "";
        }
        this.command = "unmark";
        return "unmark";
    }

    String processBye() {
        this.command = "bye";
        return "bye";
    }

    String processTodo() {
        int size = parsedStr.length;
        if (size < 2) {
            System.out.println("You do not specify the todo name");
            return "";
        }
        this.command = "todo";
        return "todo";
    }

    String processDeadline() {
        this.command = "deadline";
        return "deadline";
    }

    String processEvent() {
        this.command = "event";
        return "event";
    }

    String processDelete() {
        this.command = "delete";
        return "delete";
    }
    /**
     * Parse query for the find command
     * @return the search query
     */
    public String parseQuery() {
        if (command.equals("find")) {
            //assume only one word query, no error handling yet
            return parsedStr[1];
        }
        return "";
    }

    /**
     * Parse the index that used dateString "mark", "unmark" or delete.
     * run after parseCommand
     * @return the index, in 0-indexed form
     */
    public int parseToIndex() {
        if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
            int index = Integer.parseInt(parsedStr[1]) - 1;
            return index;
        }
        System.out.println("invalid operation");
        return -1;
    }

    /**
     * Parse the command into task for todo, deadline and event.
     * MUST run after parseCommand
     * @return a Task
     */
    public Task parseToTask() {
        assert (this.command.length() > 0);
        if (this.command.equals("todo")) {
            return getTodo();
        } else if (command.equals("deadline")) {
            return getDeadline();
        } else if (command.equals("event")) {
            return getEvent();
        }
        return getNoTask();
    }

    private static Task getNoTask() {
        return null;
    }

    private Event getEvent() {
        boolean isFromDetected = false;
        boolean isToDetected = false;
        String beginString = "";
        String endString = "";
        String description = "";

        for (int i = 1; i < parsedStr.length; i++) {
            if (parsedStr[i].equals("/from")) {
                isFromDetected = true;
                continue;
            } else if (parsedStr[i].equals("/to")) {
                isToDetected = true;
                continue;
            }
            if (isFromDetected == true && isToDetected == false) {
                beginString += parsedStr[i] + " ";
            } else if (isFromDetected == false && isToDetected == false) {
                description += parsedStr[i] + " ";
            } else {
                endString += parsedStr[i] + " ";
            }
        }

        //error handling
        if (description.equals("")) {
            System.out.println("Please input the name of the event");
            return null;
        } else if (beginString.equals("")) {
            System.out.println("Please specify start time");
            return null;
        } else if (endString.equals("")) {
            System.out.println("Please specify the end time");
            return null;
        }

        //remove the last space of beginString, endString and description
        description = description.substring(0, description.length() - 1);
        beginString = beginString.substring(0, beginString.length() - 1);
        endString = endString.substring(0, endString.length() - 1);

        return new Event(description, beginString, endString);
    }

    private Deadline getDeadline() {
        boolean isDescriptionRead = false;
        String byDate = "";
        String description = "";
        int length = parsedStr.length;
        for (int i = 1; i < length; i++) {
            if (parsedStr[i].equals("/by")) {
                isDescriptionRead = true;
                continue;
            }
            if (isDescriptionRead == true) {
                byDate += parsedStr[i] + " ";
            } else {
                description += parsedStr[i] + " ";
            }
        }
        //error handling
        if (description.equals("")) {
            System.out.println("Please input the name of the deadline task");
            return null;
        } else if (byDate.equals("")) {
            System.out.println("Please specify when is the deadline");
            return null;
        }
        //remove the last space added in byDate and description
        description = description.substring(0, description.length() - 1);
        byDate = byDate.substring(0, byDate.length() - 1);
        return new Deadline(description, byDate);
    }

    private Todo getTodo() {
        int size = this.parsedStr.length;
        String description = "";
        for (int i = 1; i < size; i++) {
            description += this.parsedStr[i] + " ";
        }
        if (description.equals("")) {
            System.out.println("Please input the name of the todo task");
            return null;
        }
        return new Todo(description);
    }
}
