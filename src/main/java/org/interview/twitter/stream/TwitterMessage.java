/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interview.twitter.stream;

/**
 * Class that describes a particular tweet message on Twitter
 * @author brandonpickup
 */
public class TwitterMessage
{
    //instance varibale for a tweet message
    private String messageID;
    private String messageCreationDate;
    private String messageText;
    private TwitterAuthor messageAuthor;
    
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
        this.messageID = messageID;
        this.messageCreationDate = messageCreationDate;
        this.messageText = messageText;
        this.messageAuthor = messageAuthor;
    }

    /**
     * Returns the messageId of a tweet
     * @return messageID
     */
    public String getMessageID()
    {
        return messageID;
    }

    /**
     * Sets the messageID of a tweet
     * @param messageID - String
     */
    public void setMessageID(String messageID)
    {
        this.messageID = messageID;
    }

    /**
     * Returns the messageCreationDate of a tweet
     * @return messageCreationDate
     */
    public String getMessageCreationDate()
    {
        return messageCreationDate;
    }

    /**
     * Sets the messageCreationDate of a tweet
     * @param messageCreationDate  - String
     */
    public void setMessageCreationDate(String messageCreationDate)
    {
        this.messageCreationDate = messageCreationDate;
    }

    /**
     * Returns the messageText of a tweet
     * @return messageText
     */
    public String getMessageText()
    {
        return messageText;
    }

    /**
     * Sets the messageText of a tweet object
     * @param messageText - String
     */
    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
    }

    /**
     * Returns the messageAuthor of a tweet
     * @return messageAuthor
     */
    public TwitterAuthor getMessageAuthor()
    {
        return messageAuthor;
    }

    /**
     * Sets the messageAuthor of a tweet
     * @param messageAuthor - TwitterAuthor
     */
    public void setMessageAuthor(TwitterAuthor messageAuthor)
    {
        this.messageAuthor = messageAuthor;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof TwitterMessage))
        {
            return false;
        }
        TwitterMessage message = (TwitterMessage) obj;
        return ( this.getMessageID().equals(message.getMessageID()) &&
                 this.getMessageCreationDate().equals(message.getMessageCreationDate()) &&
                 this.getMessageText().equals(message.getMessageText()) &&
                 this.getMessageAuthor().equals(message.getMessageAuthor()) );
    }
    
    
}
