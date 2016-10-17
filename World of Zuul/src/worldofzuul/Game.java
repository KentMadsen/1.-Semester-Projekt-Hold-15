package worldofzuul;

// TODO: Write Documentation
public class Game {

    private Parser parser;
    private Room currentRoom;
    private Player player;

    public Game() {
        createRooms();
        player = new Player();
        parser = new Parser();
    }

    private void createRooms() {
        Room outside1, outside2, helipad, hospital, policestation, grocerystore, firestation, house1, house2, drugstore, pub, gasstation;

        outside1 = new Room("on westside of the mainstreet");
        outside2 = new Room("on the eastside of the mainstreet");
        helipad = new Room("on a helipad");
        hospital = new Room("in a hospital");
        policestation = new Room("in the policestation");
        grocerystore = new Room("in the grocerystore");
        firestation = new Room("in the firestation");
        house1 = new Room("in the red house");
        house2 = new Room("in the blue house");
        drugstore = new Room("in the drugstore");
        pub = new Room("in the pub");
        gasstation = new Room("in the gasstation");

        hospital.setExit("east", outside1);

        outside1.setExit("northwest", drugstore);
        outside1.setExit("northeast", house1);
        outside1.setExit("southwest", firestation);
        outside1.setExit("southeast", pub);
        outside1.setExit("west", hospital);

        drugstore.setExit("south", outside1);

        house1.setExit("south", outside1);
        house1.setExit("east", house2);

        firestation.setExit("north", outside1);

        pub.setExit("north", outside1);

        house2.setExit("west", house1);
        house2.setExit("south", outside2);

        grocerystore.setExit("south", outside2);

        outside2.setExit("northwest", house2);
        outside2.setExit("northeast", grocerystore);
        outside2.setExit("southwest", policestation);
        outside2.setExit("southeast", gasstation);
        outside2.setExit("east", helipad);

        policestation.setExit("north", outside2);

        gasstation.setExit("north", outside2);

        helipad.setExit("west", outside2);

        currentRoom = hospital;
    }

    public void play() {
        printWelcome();

        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.schrodinger()) {
                System.out.println("You are dead.");
                finished = true;
            }
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (null != commandWord) {
            switch (commandWord) {
                case HELP:
                    printHelp();
                    break;
                case GO:
                    goRoom(command);
                    break;
                case STATUS:
                    player.getStatus();
                    break;
                case GRAB://TODO laves når vi har implementeret items i rummene.
                   
                case DROP:
                    
                case QUIT:
                    wantToQuit = quit(command);
                    break;
                default:
                    break;
            }
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        if (null != command.getCommandWord());
        switch (command.getSecondWord()) { //Allows abbreviations for directions.
            case "ne":
                direction = "northeast";
                break;
            case "nw":
                direction = "northwest";
                break;
            case "se":
                direction = "southeast";
                break;
            case "sw":
                direction = "southwest";
                break;
            case "n":
                direction = "north";
                break;
            case "s":
                direction = "south";
                break;
            case "e":
                direction = "east";
                break;
            case "w":
                direction = "west";
                break;

            default:
                break;
        }

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            player.degenHungerAndThirst(); //update hunger and thirst gauges on roomchange.
            //player.updateHealth(-50); //testing of dying player.
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

} // Class Game
