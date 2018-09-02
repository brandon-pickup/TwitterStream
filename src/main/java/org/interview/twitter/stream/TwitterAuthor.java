package org.interview.twitter.stream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that describes the author of a tweet on Twitter
 * @author brandonpickup
 */
public class TwitterAuthor implements Comparable<TwitterAuthor>
{
    /**
     * instance variable for the author of a tweet
     * Variable names are of the identical to the fields returned by the Twitter stream API
     */
    private String id_str;
    private String screen_name;
    private String created_at;
   
    /**
     * No argument constructor
     */
    public TwitterAuthor()
    {
        // no default values assigned to instance variables
    }
    
    /**
     * Constructor
     * @param userID - String
     * @param userCreationDate - String
     * @param userScreenName - String
     */
    public TwitterAuthor(String userID, String userCreationDate, String userScreenName)
    {
        this.id_str = userID;
        this.screen_name = userScreenName;
        this.created_at = userCreationDate;
    }
    
    /**
     * Sets the user ID field of an Author
     * @param id_str - String
     */
    public void setId_str(String id_str)
    {
        this.id_str = id_str;
    }
    
    /**
     * Sets the user creation date of an Author
     * @param created_at - String
     */
    public void setCreated_at(String created_at)
    {
        this.created_at = created_at;
    }
    
    /**
     * Sets the user screen name of an Author
     * @param screen_name - String
     */
    public void setScreen_name(String screen_name)
    {
        this.screen_name = screen_name;
    }
    
    /**
     * Returns the user ID of an Author
     * @return id_str - String 
     */
    public String getId_str()
    {
        return this.id_str;
    }
    
    /**
     * Returns the user creation date of an Author
     * @return created_at - String
     */
    public String getCreated_at()
    {
        return this.created_at;
    }
    
    /**
     * Returns the user screen name for an Author
     * @return screen_name- String
     */
    public String getScreen_name()
    {
        return this.screen_name;
    }
    
    /**
     * Override of the equals method 
     * @param obj - TwitterAuthor
     * @return boolean
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof TwitterAuthor))
        {
            return false;
        }
        TwitterAuthor author = (TwitterAuthor) obj;
        return (this.getId_str().equals(author.getId_str()) && 
                this.getCreated_at().equals(author.getCreated_at()) &&
                this.getScreen_name().equals(author.getScreen_name()) );
    }
    
    /**
     * Override of the toString() method to return a description of the author in a readable format
     * @return String
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Author details:");
        builder.append("\n\tUser creation date:\t");  builder.append(this.getCreated_at());
        builder.append("\n\tUser identity value:\t"); builder.append(this.getId_str());
        builder.append("\n\tUser screen name:\t");    builder.append(this.getScreen_name());
        
        return builder.toString();
    }

    /**
     * Functionality to sort on creation date when compare to is used
     * @param author - TwitterAuthor
     * @return int
     */
    @Override
    public int compareTo(TwitterAuthor author)
    {
        LocalDateTime thisDateTime = parseStringTime(this.getCreated_at());
        LocalDateTime authorDateTime = parseStringTime(author.getCreated_at());
        
        return authorDateTime.compareTo(thisDateTime);
    }
    
    /**
     * Helper to parse a Twitter timestamp to LocalDateTime format
     * @param time - String
     * @return LocalDateTime
     */
    private LocalDateTime parseStringTime(String time)
    {
        String TWITTER="EEE MMM dd HH:mm:ss Z yyyy";
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(TWITTER));
    }
}
