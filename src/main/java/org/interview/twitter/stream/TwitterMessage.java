/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interview.twitter.stream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that describes a particular tweet message on Twitter
 * @author brandonpickup
 */
public class TwitterMessage implements Comparable<TwitterMessage>
{
    //instance varibale for a tweet message
    private String id_str;
    private String created_at;
    private String text;
    private TwitterAuthor user;
        
    /**
     * No argument constructor
     */
    public TwitterMessage()
    {
        // no default values assigned to instance variables
    }
    
    /**
     * Constructor
     * @param messageID - String
     * @param messageCreationDate - String
     * @param messageText - String
     * @param messageAuthor - TwitterAuthor
     */
    public TwitterMessage(String messageID, String messageCreationDate, String messageText, TwitterAuthor messageAuthor)
    {
        this.id_str = messageID;
        this.text = messageText;
        this.user = messageAuthor;
        this.created_at = messageCreationDate;
    }

    /**
     * Returns the message ID of a tweet
     * @return id_str - String
     */
    public String getId_str()
    {
        return this.id_str;
    }

    /**
     * Sets the message ID of a tweet
     * @param id_str - String
     */
    public void setId_str(String id_str)
    {
        this.id_str = id_str;
    }

    /**
     * Returns the message creation date of a tweet
     * @return created_at - String
     */
    public String getCreated_at()
    {
        return this.created_at;
    }

    /**
     * Sets the message creation date of a tweet
     * @param created_at  - String
     */
    public void setCreated_at(String created_at)
    {
        this.created_at = created_at;
    }

    /**
     * Returns the message text of a tweet
     * @return messageText - String
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * Sets the messageText of a tweet object
     * @param text - String
     */
    public void setText(String text)
    {
        this.text = text;
    }

    /**
     * Returns the messageAuthor of a tweet
     * @return user - TwitterAuthor
     */
    public TwitterAuthor getUser()
    {
        return this.user;
    }

    /**
     * Sets the messageAuthor of a tweet
     * @param user - TwitterAuthor
     */
    public void setUser(TwitterAuthor user)
    {
        this.user = user;
    }

    /**
     * Override of the equals method for comparing equality of 2 TwitterMessage objects
     * @param obj - Object that should be a TwitterMessage
     * @return boolean
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof TwitterMessage))
        {
            return false;
        }
        TwitterMessage message = (TwitterMessage) obj;
        return ( this.getId_str().equals(message.getId_str()) &&
                 this.getCreated_at().equals(message.getCreated_at()) &&
                 this.getText().equals(message.getText()) &&
                 this.getUser().equals(message.getUser()) );
    }
    
    /**
     * Override of the toString() method to return a description of the author in a readble format
     * @return String
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getUser().toString());
        builder.append("\nMessage creation date:\t");  builder.append(this.getCreated_at());
        builder.append("\nMessage identity value:\t"); builder.append(this.getId_str());
        builder.append("\nTwitter message text :\t");  builder.append(this.getText());
        
        return builder.toString();
    }

    /**
     * Functionality to sort messages by author and then by message creation date if the author is the same
     * @param message - TwitterMessage
     * @return - int
     */
    @Override
    public int compareTo(TwitterMessage message)
    {
        LocalDateTime thisDateTime = parseStringTime(this.getCreated_at());
        LocalDateTime messageDateTime = parseStringTime(message.getCreated_at());
        
        if (message.getUser().equals(this.getUser()))
        {
            return thisDateTime.compareTo(messageDateTime);
        }
        
        return (message.getUser()).compareTo(this.getUser());
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
