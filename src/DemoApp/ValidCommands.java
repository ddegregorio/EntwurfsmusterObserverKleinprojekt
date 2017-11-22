package DemoApp;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Class that holds a mapping between Strings
 * and Command-objects
 */
public class ValidCommands {

    // mapping between strings and commands
    private HashMap<String, Command> validCommands;

    public ValidCommands()
    {
        validCommands = new HashMap<>();
        for(Command command : Command.values()) {

            // insert all the commands in the mapping
            // leave UNKNOWN out of the mapping
            if(command != Command.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Checks if the mapping contains a string that
     * is mapped to a command
     *
     * @param userinput string that should be found in the mapping
     * @return returns the a command object if it has found a corresponding
     * mapping
     * returns UNKNOWN if there is no mapping for the provided string.
     */
    public Command getCommand(String userinput)
    {
        Command command = validCommands.get(userinput);
        if(command != null) {
            return command;
        }
        else {
            return Command.UNKNOWN;
        }
    }




}
