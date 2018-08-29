/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interview.twitter.stream;

/**
 * Class that describes the author of a tweet on Twitter
 * @author brandonpickup
 */
public class TwitterAuthor
{

    
    //instance variable for the author of a tweet
    private String userID;
    private String userCreationDate;
    private String userScreenName;
    
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
        this.userID = userID;
        this.userCreationDate = userCreationDate;
        this.userScreenName = userScreenName;
    }
    
    /**
     * Sets the userID field for an Author
     * @param userID - String
     */
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    
    /**
     * Sets the userCreationDate for an Author
     * @param userCreationDate - String
     */
    public void setUserCreationDate(String userCreationDate)
    {
        this.userCreationDate = userCreationDate;
    }
    
    /**
     * Sets the userScreenName for an Author
     * @param userScreenName - String
     */
    public void setUserScreenName(String userScreenName)
    {
        this.userScreenName = userScreenName;
    }
    
    /**
     * Returns the userID for an Author
     * @return userID 
     */
    public String getUserID()
    {
        return this.userID;
    }
    
    /**
     * Returns the userCReationDate for an Author
     * @return userCReationDate
     */
    public String getUserCreationDate()
    {
        return this.userCreationDate;
    }
    
    /**
     * Returns the userScreenName for an Author
     * @return userScreenName
     */
    public String getUserScreenName()
    {
        return this.userScreenName;
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
        return (this.getUserID().equals(author.getUserID()) && 
                this.getUserCreationDate().equals(author.getUserCreationDate()) &&
                this.getUserScreenName().equals(author.getUserScreenName()) );
    }
}
