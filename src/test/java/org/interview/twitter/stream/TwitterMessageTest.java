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
public class TwitterMessageTest
{
    @Test
    public void testDefaultConstructor()
    {
        TwitterMessage message = null;
        Assert.assertNull(message);
        message = new TwitterMessage();
        Assert.assertNotNull(message);   
    }
    
    @Test
    public void testParamsConstructor()
    {
        TwitterMessage message = null;
        Assert.assertNull(message);
        
        String userID = "12345";
        String creationDate = "Mon Oct 30 13:25:07 +0000 2017";
        String screenName = "testUser";
        TwitterAuthor author = new TwitterAuthor(userID, creationDate, screenName);
        
        String messageID = "123";
        String messageCreationDate = "Mon Oct 30 13:25:07 +0000 2017";
        String messageText = "Test message tweet";
        
        message = new TwitterMessage(messageID, messageCreationDate, messageText, author);
        Assert.assertNotNull(message);
        
        Assert.assertEquals(messageID, message.getId_str());
        Assert.assertEquals("2017-10-30T13:25:07", message.getCreated_at().toString());
        Assert.assertEquals(screenName, author.getScreen_name());
    }
    
    @Test
    public void testGettersAndSetters()
    {
        TwitterMessage message = null;
        Assert.assertNull(message);
        
        message = new TwitterMessage();
        Assert.assertNull(message.getId_str());
        Assert.assertNull(message.getCreated_at());
        Assert.assertNull(message.getText());
        Assert.assertNull(message.getUser());
        
        String userID = "12345";
        String creationDate = "Mon Oct 30 13:25:07 +0000 2017";
        String screenName = "testUser";
        TwitterAuthor author = new TwitterAuthor(userID, creationDate, screenName);
        
        String messageID = "123";
        String messageCreationDate = "Mon Oct 30 13:25:07 +0000 2017";
        String messageText = "Test message tweet";
        
        message.setId_str(messageID);
        Assert.assertEquals(messageID, message.getId_str());
        
        message.setCreated_at(messageCreationDate);
        Assert.assertEquals("2017-10-30T13:25:07", message.getCreated_at().toString());
        
        message.setText(messageText);
        Assert.assertEquals(messageText, message.getText());
        
        message.setUser(author);
        Assert.assertEquals(author, message.getUser());
    }
    
    @Test
    public void testEquals()
    {
        String userID_message1 = "12345";
        String userID_message2 = "98765";
        
        String creationDate_message1 = "Mon Oct 30 13:25:07 +0000 2017";
        String creationDate_message2 = "Sat Oct 28 13:25:07 +0000 2017";
        
        String screenName_message1 = "testUser1";
        String screenName_message2 = "testUser2";
        
        TwitterAuthor author_message1 = new TwitterAuthor(userID_message1, creationDate_message1, screenName_message1);
        TwitterAuthor author_message2 = new TwitterAuthor(userID_message2, creationDate_message2, screenName_message2);
        
        String messageID_message1 = "123";
        String messageID_message2 = "987";
        
        String messageCreationDate_message1 = "Mon Oct 30 13:25:07 +0000 2017";
        String messageCreationDate_message2 = "Sat Oct 28 13:25:07 +0000 2017";
        
        String messageText_message1 = "Test message tweet number 1";
        String messageText_message2 = "Test message tweet number 2";
        
        TwitterMessage message1 = new TwitterMessage(messageID_message1, messageCreationDate_message1, messageText_message1, author_message1);
        TwitterMessage message2 = new TwitterMessage(messageID_message1, messageCreationDate_message1, messageText_message1, author_message1);
        TwitterMessage message3 = new TwitterMessage(messageID_message2, messageCreationDate_message2, messageText_message2, author_message2);
        
        Assert.assertEquals(message1, message2);
        Assert.assertTrue(message1.equals(message2));
        Assert.assertFalse(message1.equals(message3));
        Assert.assertNotEquals(message1, message3);
        Assert.assertNotEquals(null, message1);
        Assert.assertNotEquals(null, message3);
        Assert.assertNotEquals(message1, author_message1);
        
    }
    
    @Test
    public void testCompareTo()
    {
        String userID_message1 = "12345";
        String userID_message2 = "98765";
        
        String creationDate_message1 = "Mon Oct 30 13:25:07 +0000 2017";
        String creationDate_message2 = "Sat Oct 28 13:25:07 +0000 2017";
        
        String screenName_message1 = "testUser1";
        String screenName_message2 = "testUser2";
        
        TwitterAuthor author_message1 = new TwitterAuthor(userID_message1, creationDate_message1, screenName_message1);
        TwitterAuthor author_message2 = new TwitterAuthor(userID_message2, creationDate_message2, screenName_message2);
        
        String messageID_message1 = "123";
        String messageID_message2 = "987";
        
        String messageCreationDate_message1 = "Mon Oct 30 13:25:07 +0000 2017";
        String messageCreationDate_message2 = "Sat Oct 28 13:25:07 +0000 2017";
        
        String messageText_message1 = "Test message tweet number 1";
        String messageText_message2 = "Test message tweet number 2";
        
        TwitterMessage message1 = new TwitterMessage(messageID_message1, messageCreationDate_message1, messageText_message1, author_message1);
        TwitterMessage message2 = new TwitterMessage(messageID_message1, messageCreationDate_message1, messageText_message1, author_message1);
        TwitterMessage message3 = new TwitterMessage(messageID_message2, messageCreationDate_message2, messageText_message2, author_message2);
        
        Assert.assertTrue(message1.compareTo(message2) == 0);
        Assert.assertTrue(message1.compareTo(message3) > 0);
        Assert.assertTrue(message3.compareTo(message1) < 0);
    }
}
