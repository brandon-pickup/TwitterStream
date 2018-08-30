/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interview.twitter.stream;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author brandonpickup
 */
public class TwitterAuthorTest
{
    @Test
    public void testDefaultConstructor()
    {
        TwitterAuthor author = null;
        Assert.assertNull(author);
        author = new TwitterAuthor();
        Assert.assertNotNull(author);   
    }
    
    @Test
    public void testParamsConstructor()
    {
        TwitterAuthor author = null;
        Assert.assertNull(author);
        
        String userID = "12345";
        String creationDate = "2018-08-28";
        String screenName = "testUser";
        
        author = new TwitterAuthor(userID, creationDate, screenName);
        Assert.assertNotNull(author);
        
        Assert.assertEquals(userID, author.getId_str());
        Assert.assertEquals(creationDate, author.getCreated_at());
        Assert.assertEquals(screenName, author.getScreen_name());
    }
    
    @Test
    public void testGettersAndSetters()
    {
        TwitterAuthor author = null;
        Assert.assertNull(author);
        
        String userID = "12345";
        String creationDate = "2018-08-28";
        String screenName = "testUser";
        
        author = new TwitterAuthor();
        
        Assert.assertNull(author.getId_str());
        Assert.assertNull(author.getCreated_at());
        Assert.assertNull(author.getScreen_name());
        
        author.setId_str(userID);
        Assert.assertEquals(userID, author.getId_str());
        
        author.setCreated_at(creationDate);
        Assert.assertEquals(creationDate, author.getCreated_at());
        
        author.setScreen_name(screenName);
        Assert.assertEquals(screenName, author.getScreen_name());
    }
    
    @Test
    public void testEquals()
    {
        String userID_author1 = "12345";
        String creationDate_author1 = "2018-08-28";
        String screenName_author1 = "testUser1";
        
        TwitterAuthor author1 = new TwitterAuthor(userID_author1, creationDate_author1, screenName_author1);
        TwitterAuthor author2 = new TwitterAuthor(userID_author1, creationDate_author1, screenName_author1);
        
        String userID_author2 = "54321";
        String creationDate_author2 = "2018-08-27";
        String screenName_author2 = "testUser2";
        
        TwitterAuthor author3 = new TwitterAuthor(userID_author2, creationDate_author2, screenName_author2);
        
        TwitterMessage message = new TwitterMessage();
        
        Assert.assertEquals(author1, author2);
        Assert.assertTrue(author1.equals(author2));
        Assert.assertNotEquals(author1, author3);
        Assert.assertFalse(author1.equals(author3));
        Assert.assertNotEquals(null, author1);
        Assert.assertNotEquals(message, author3);
        
    }
}
