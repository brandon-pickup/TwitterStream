/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interview.twitter.stream;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.interview.oauth.twitter.TwitterAuthenticationException;
import org.interview.oauth.twitter.TwitterAuthenticator;

/**
 *
 * @author brandonpickup
 */
public class Main
{    
    private static final int MAX_NUMBER_OF_MESSAGES =100;
    private static final String CONSUMER_KEY = "vp8qXAMoZzy6jowJdtouPLUUb";
    private static final String CONSUMER_SECRET = "IMx3eIRfXXbRimoIz7cNpZCl0dr9dYEdRuDVTr2C4LdResXjN7";
        
    public static void main(String[] args)
    {
        
        File file = new File("output.txt");
        
        //Connect to the API and save output to file
        //streamTweetsAndSave(file);
        
        //Obtain a list of TwitterMessages by reading from saved file
        List<TwitterMessage> messages = processFileContentsForTwitterMessages(file);
        
        //sort by user creation date, and for users that have posted more than once, sort by message creation date
        Collections.sort(messages);
        
        //print output to console
        printMessagesToConsole(messages);
    }
    
    /**
     * Method to connect to the Stream API of twitter, stream bieber track tweets for 30s and save output to a file
     * The implementation will always stream for 30s before timing out.
     * Less than 100 entries is handled upon processing the saved file
     * @param saveFile 
     */
    private static void streamTweetsAndSave(File saveFile)
    {
        TwitterAuthenticator authenticator = new TwitterAuthenticator(System.out, CONSUMER_KEY, CONSUMER_SECRET);
        
        //required types that need to be closed
        OutputStream output = null;
        HttpResponse response = null;
        
        try
        {
            HttpRequestFactory httpRequestFactory = authenticator.getAuthorizedHttpRequestFactory();
            
            String trackValue = "bieber";
            GenericUrl url = new GenericUrl("https://stream.twitter.com/1.1/statuses/filter.json?track="+trackValue);

            HttpRequest httpRequest = httpRequestFactory.buildGetRequest(url);
            httpRequest.setReadTimeout(3 * 1000);
            
            output = new FileOutputStream(saveFile);
            
            response = httpRequest.execute();
            response.download(output);
        }
        catch (TwitterAuthenticationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (java.net.SocketTimeoutException ex)
        {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("30s timeout reached");
        }
        catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            try
            {
                output.close();
                response.disconnect();
            } catch (IOException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    /**
     * Helper method to print the messages to the console
     * @param messages 
     */
    private static void printMessagesToConsole(List<TwitterMessage> messages)
    {
        int messageCounter = 0;
        for (TwitterMessage message: messages)
        {
            //print the message in a readable format to the console
            printStartMessage(++messageCounter);
            System.out.println(message.toString());
            printEndOfMessage();
        }
    }
    
    /**
     * Processes the contents of a given file to obtain a list of Twitter messages
     * @param file - file that contains JSON strings obtained from the HttpResponse upon querying Twitter stream API
     * @return Collection of TwitterMessages
     */
    private static List<TwitterMessage> processFileContentsForTwitterMessages (File file)
    {
        ArrayList<TwitterMessage> messages = new ArrayList<>();
        Gson g = new Gson();
        
        try
        {
            Scanner sc = new Scanner (new FileReader(file));
            int counter = 0;
            while (sc.hasNext())
            {
                //do not process more than 100 messages as this is the specification
                if (++counter > MAX_NUMBER_OF_MESSAGES)
                {
                    break;
                }
                String line = sc.nextLine();
                TwitterMessage message = g.fromJson(line, TwitterMessage.class);
                messages.add(message);
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("Could not process file contents as the file could not be found");
        }
        
        return messages;
    }
   
    /**
     * Private helper to print the header for each message to the console
     * @param counter - number of this message in the list
     */
    private static void printStartMessage(int counter)
    {
        System.out.println("==========================================================");
        System.out.println("Message no: "+counter);
        System.out.println("==========================================================");
    }
    
    /**
     * Private helper to print the footer for each message to the console
     */
    private static void printEndOfMessage()
    {
        System.out.println("==========================================================\n\n");
       
    }
}
