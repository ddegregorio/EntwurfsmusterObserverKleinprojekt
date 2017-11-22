package DemoApp;

import java.util.Scanner;

/**
 * Class that is used to parse the input, which
 * the user has typed on the command line.
 *
 * The class will try to recognize commands
 * that the user has typed on the command line and
 * will return the corresponding command-objects.
 */
public class Parser {
    private Scanner reader;
    private ValidCommands validCommands;

    public Parser() {
        reader = new Scanner(System.in);
        validCommands = new ValidCommands();
    }

    /**
     * parses the command line and returns a Command-object
     * that has been read from the command line
     *
     * @return Command-object
     */
    public Command getCommand(){
        String line = reader.nextLine();

        Scanner tokenizer = new Scanner(line);

        if(tokenizer.hasNext())
        {
            return validCommands.getCommand(tokenizer.next());
        }

        return Command.UNKNOWN;
    }
}

