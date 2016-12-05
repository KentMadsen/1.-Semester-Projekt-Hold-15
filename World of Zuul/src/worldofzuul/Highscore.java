/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.*;
import java.io.*;

/**
 *
 * @author Fract
 */
public class Highscore extends HighscoreSystem 
{
    private final static String dir_Highscore = Content.Directory_Config + "\\highscore";
    private final static String dir_Players = dir_Highscore + "\\players";
    
    /**
     * 
     * @param inputValue
     * @return 
     */
    private boolean AllowedCharacter( char inputValue )
    {
     if ( inputValue <= 'A' || 
          inputValue >= 'z' )
         return true;
     
     if( inputValue <= '0' || 
         inputValue >= '9' )
         return true;
     
     if( inputValue == '-' || 
         inputValue == '_')
         return true;
     
        return false;
    }
    
    /**
     * 
     * @param InputName
     * @return 
     */
    private boolean ParseNameCharacters( String InputName )
    {
        // 
        for( char c : InputName.toCharArray() )
        {
            boolean Continue = false;
            
            if( AllowedCharacter( c ) == true )
                Continue = true;
            
            if( Continue == false )
                return false;
        }
        
        return true;
    }
    
    /**
     * 
     * @param InputText
     * @return 
     */
    private String[] Tokenize( String InputText )
    {
        ArrayList<String> retValues = new ArrayList();
        
        StringBuilder builder = new StringBuilder();
        
        for( int x = 0; 
                 x <= InputText.length() - 1; 
                 x ++ )
        {
            char current = InputText.charAt( x );
            
            // If space, newline or end of line, append to list
            if( current == ' '   ||
                current == '\n'  ||
                x == InputText.length() -1 )
                    retValues.add( builder.toString() );
            
            // Only append allowed characters
            if( AllowedCharacter( current ) )
                builder.append( current );       
        }
        
        if( retValues.size() == 0 )
            return null;
        
        return ( String[] )retValues.toArray();
    }
    
    /**
     * 
     */
    public Highscore()
    {
        SetCurrentPlayerName( "player" );
        
        if( hDirectories.Exist( dir_Players ) )
        {
            hDirectories.Create( dir_Players, 
                                 true );
        }
        
        
    }
    
    /**
     * 
     * @param name 
     */
    public Highscore( String name )
    {
        this();
        
        SetCurrentPlayerName( name );
        
    }
    
    /**
     * Loads a character's, current score
     * @param CharacterName
     * @return True: Loaded, False: Error occured
     */
    public boolean LoadCurrentCharacter( String CharacterName )
    {
        if( ParseNameCharacters( CharacterName ) )
        {
            return false;
        }
        
        
        return true;
    }
    
    /**
     * Saves a character's, current score
     * @param CharacterName
     * @return True: Saved, False: Error occured
     */
    public boolean SaveCurrentCharacter( String CharacterName )
    {
        if( ParseNameCharacters( CharacterName ) )
        {
            return false;
        }
        
        
        
        return true;
    }
    
        /**
     * Loads other Character's that are saved
     */
    public void LoadPlayers()
    {
        
        
    }
    
    /**
     * 
     */
    public static final class hFiles
    {
        // Wrapper function
        public static boolean Create( String Path )
        {
            File f = new File( Path );
            
            return Create( f );
        }
                
        /**
         * 
         * @return 
         */
        public static boolean Create( File filePath )
        {
            
            if( Exist( filePath ) == false )
            {
                try
                {
                    return filePath.createNewFile(); 
                }
                catch( Exception ex )
                {
                    
                }
                
            }
            else
            {
                
            }
            
            return false; 
        }
        
        // Wrapper Function
        public static boolean Remove( String path )
        {
            File f = new File( path );
            
            return Remove( f );
        }
        
        /**
         * 
         * @return 
         */
        public static boolean Remove( File filePath )
        {
            try
            {
                if( Exist( filePath ) )
                    filePath.delete();
            }
            catch( Exception Ex )
            {
                
            }
            
           return false; 
        }
        
        // Wrapper Function
        public static boolean Exist( String path )
        {
            File f = new File( path );
            
            return Exist( f );
        }
        
        /**
         * 
         * @return 
         */
        public static boolean Exist( File filePath )
        {
            try
            {
                if( filePath.isFile() )
                {
                    return filePath.exists();
                }
            }
            catch( Exception ex )
            {
                
            }
            
            return false;
        }
        
        public static final class List
        {
            
            public static File[] FilesInDirectory( File filePath )
            {
                ArrayList<File> ListOfFoundFiles = new ArrayList();
                
                File[] ListOfStuffFound = filePath.listFiles();
                
                if( ListOfStuffFound.length == 0 )
                    return null;
                
                for( File f : ListOfStuffFound )
                {
                    
                    if( f.isFile() )
                    {
                        ListOfFoundFiles.add( f );
                    }
                    
                }
                
                File[] Files = ( File[] )ListOfFoundFiles.toArray();
                
                return Files;
            }
        }
        
        private static final class Content
        {
            public static String GetText( File filePath )
            {
            
                return null;
            }
        
            } // End Class hContent
        
    } // End hFiles
    
    /**
     * 
     */
    public static final class hDirectories
    {
        // Wrapper Function
        public static boolean Create( String path, boolean createParents )
        {
            File f = new File( path );
            
            return Create( f, createParents );
        }
        
        public static boolean Create( File Path, boolean createParents )
        {
            try
            {
                if( createParents == true )
                {
                    Path.mkdirs();
                }
                else
                {
                    Path.mkdir();
                }
            }
            catch( Exception Ex )
            {
                
            }
            
            return false;
        }
        
        // Wrapper function
        public static boolean Remove( String path )
        {
            File f = new File( path );
            
            return Remove( f );
        }
        
        public static boolean Remove( File Path )
        {
            try
            {
                if( Exist( Path ) == true )
                {
                    Path.delete();
                        
                    return true;
                }   
                else
                {
                    return false;
                }
                
            }
            catch( Exception ex )
            {
                
            }
            
            return false;
        }
        
        // Wrapper function
        public static boolean Exist( String path )
        {
            File f = new File( path );
            
            return Exist( f );
        }
        
        public static boolean Exist( File Path )
        {
            try
            {
                if( Path.isDirectory() )
                {
                    return Path.exists();
                }  
            }
            catch( Exception Ex )
            {
                
            }
                    
            return false;   
        }
    
    } // End Class hDirectories
    
}  // End Class Main