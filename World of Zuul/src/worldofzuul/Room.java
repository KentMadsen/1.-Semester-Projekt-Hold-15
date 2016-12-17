package worldofzuul;

import worldofzuul.utilities.Dice;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class contains information about the rooms.
 * A room consists of a description string and three seperate hashmaps containng information about what exits a given room has,
 * what items are placed in it and whether there are zombies in the room and if yes, which zombies there are.
 
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Room {
    private boolean lock = false;
    private String name;
    private boolean spawnable = true;
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Items> placements;
    private HashMap<String, Zombie> zombies;
    
    private Dice spawnChance = new Dice( 0,     // Min
                                         100 ); // Max

    /*public int zombieAmount()
    {
        return zombies.size();
    }*/
    
    /**
     * 
     * @param name
     * @param description 
     */
    public Room(String name, String description) {
        this.description = description;
        exits = new HashMap<>();
        placements = new HashMap<>();
        zombies = new HashMap<>();
        this.name = name;
    }
    
    /**
     * 
     * @param direction
     * @param neighbor 
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Places an item in the room
     */
    public void placeItem(Items item) {
        placements.put(item.getName(), item);
    }

    /**
     * Removes an item from the room
     * @param key 
     */
    public void removeItem(String key) {
        placements.remove(key);
    }

    /**
     * Places a zombie in a room
     * @param zombie 
     */
    public void placeZombie(Zombie zombie) {
        zombies.put(zombie.getName(), zombie);
    }

    /**
     * Removes the zombie from the room. Should be used when it's killed
     * @param key 
     */
    public void removeZombie(String key) {
        zombies.remove(key);
    }

    /**
     * Retrieves a short description of the room
     * @return 
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Retrieves a long description of the room
     * @return 
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getZombieString() + getExitString();
    }

    /**
     * Get Exit
     * @return 
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * 
     * @return 
     */
    public String getZombieString() {
        if (!zombies.isEmpty()) {
            String zombieString = "A zombified version of ";
            Set<String> keys = zombies.keySet();
            for (String zombie : keys) {
                zombieString += zombie;
            }
            return zombieString + " is in the room!" + ".\n";
        } else {
            return "";
        }

    }
    /**
     * 
     * @param direction
     * @return 
     */
    public Room getExit(String direction) { 
        Room room = exits.get(direction);

        return room;
    }

    /**
     * 
     * @param index
     * @return 
     */
    public Room getExit(int index) {
        Set<String> keys = exits.keySet();
        int i = 0;
        for (String exit : keys) {
            if (i == index) {
                return exits.get(exit);
            }
            i++;
        }
        return null;
    }

    /**
     * Returns the item (an object) with the key "key" in the placements HashMap
     * @param key
     * @return 
     */
    public Items getItem(String key) {
        return placements.get(key);
    }

    /**
     * A method that returns all the items in a room.
     *
     * @return
     */
    public HashMap<String, Items> getAllItems() {
        return this.placements;
    }
     /** 
     * @param key
     * @return 
     */
    public Zombie getZombie(String key) {
        return zombies.get(key);
    }
    
    /**
     * Spawns zombies
     */
    private void spawnZombie() {
        int HP, DMG;
        HP = 5;
        DMG = 2;
        
        Zombie monster = new Zombie(HP, DMG );
        
        //String monsterName = monster.getRandomName();
        
        zombies.put( monster.getName(), 
                     monster );
    }
    
    /**
     * 
     * @param value 
     */
    public void setSpawnable(boolean value) {
        spawnable = value;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getSpawnable() {
        return spawnable;
    }
    
    /**
     * 
     */
    public void spawnRandomZombie() {
        if( spawnable == true ) {
        
            int rValue = spawnChance.calculate();
        
            if(rValue >= 20 && rValue <= 80) {
                spawnZombie();
            }

        }
    }

    /**
     * Prints a list of items in the current room
     */
    public void searchRoom() {
        if (placements.isEmpty()) {
            System.out.println("The room is empty.");
        } else {
            String itemString = "Items in room:";
            Set<String> keys = placements.keySet();
            for (String item : keys) {
                itemString += " " + item;
            }
            System.out.println(itemString);
        }
    }
    
    /**
     * 
     * @return 
     */
    public boolean isLocked() {
        return lock;
    }
    
    /**
     * 
     * @param status 
     */
    public void setLock(boolean status) {
        lock = status;
    }

    /**
     * 
     * @return 
     */
    public int getSize() {
        return exits.size();
    }
    
    /**
     * 
     * @return 
     */
    public HashMap<String, Room> getNeighbours() {
        return exits;
    }
    
    /**
     * 
     * @return 
     */
    public HashMap<String, Items> getPlacements() {
        return placements;
    }
    
    /**
     * 
     * @return 
     */
    public HashMap<String, Zombie> getZombies() {
        return zombies;
    }
    
    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    
}
