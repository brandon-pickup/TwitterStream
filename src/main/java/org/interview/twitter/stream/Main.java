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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.interview.oauth.twitter.TwitterAuthenticationException;
import org.interview.oauth.twitter.TwitterAuthenticator;

/**
 *
 * @author brandonpickup
 */
public class Main
{    
    private static final int MAX_NUMBER_OF_MESSAGES = 100;
    private static final int MAX_SECONDS_ALLOWED_TO_STREAM = 30;
    private static final String CONSUMER_KEY = "vp8qXAMoZzy6jowJdtouPLUUb";
    private static final String CONSUMER_SECRET = "IMx3eIRfXXbRimoIz7cNpZCl0dr9dYEdRuDVTr2C4LdResXjN7";
    private static final ArrayList<TwitterMessage> messages = new ArrayList<>();
    
    public static void main(String[] args) throws ParseException
    {   
 
        //Connect to the API and save output to messages ArrayList
        streamTweetsAndSave();
        
        //sort by user creation date, and for users that have posted more than once, sort by message creation date
        System.out.println("\nSorting messages");
        Collections.sort(messages);
        
        //print output to console
        System.out.println("\nMessages sorted and available:");
        printMessagesToConsole(messages);
    }
    
    /**
     * Method to connect to the Stream API of twitter and stream on a specific track for 30s or 100 records
     */
    private static void streamTweetsAndSave()
    {
        
        TwitterAuthenticator authenticator = new TwitterAuthenticator(System.out, CONSUMER_KEY, CONSUMER_SECRET);
        
        //required types that need to be closed
        HttpResponse httpResponse = null;
        HttpRequest httpRequest = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try
        {
            HttpRequestFactory httpRequestFactory = authenticator.getAuthorizedHttpRequestFactory();
            
            String trackValue = "chelsea";
            GenericUrl url = new GenericUrl("https://stream.twitter.com/1.1/statuses/filter.json?track="+trackValue);

            httpRequest = httpRequestFactory.buildGetRequest(url);
            
            System.out.println("\nStarting to stream");
            httpResponse = httpRequest.execute();
            
            inputStream = httpResponse.getContent();
            //start the clock
            long startTime = System.currentTimeMillis();
            
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // Take in the input
            String input;
            while((input = bufferedReader.readLine()) != null){
                //Observe the time upon reading the next line
                long currentTime = System.currentTimeMillis();
                
                //httpRequest.setReadTimeout(30 * 1000) did not behave as I expected - i.e. only read for 30s. Hence my own implementation
                if (messages.size() >= MAX_NUMBER_OF_MESSAGES || (currentTime-startTime)/1000 > MAX_SECONDS_ALLOWED_TO_STREAM)
                {
                    //Logic to determine which message to output
                    String outputMessage = messages.size() >= MAX_NUMBER_OF_MESSAGES ? ("Message limit of "+MAX_NUMBER_OF_MESSAGES+" messages reached"):("Time limit of "+MAX_SECONDS_ALLOWED_TO_STREAM+"s reached");
                    System.out.println("\n"+outputMessage);
                    break;
                }
                
                // Parse the line received from the Twitter API
                TwitterMessage message = parseInputFromTwitter(input);
                
                //do not save the message if the parser returns a null value
                if (message != null)
                    messages.add(message);
            }
        }
        catch (TwitterAuthenticationException ex)
        {
            System.out.println(ex.toString());
        }
        catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            try
            {
                bufferedReader.close();
                inputStream.close();
                httpResponse.disconnect();
                System.out.println("\nDisconnected succesfully after timeout");
            } catch (IOException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    /**
     * Helper method to parse a JSON string from the TwitterAPI to create a TwitterMessage 
     * @param line - JSON String from the Twitter Stream API
     * @return TwitterMessage if successfully parsed
     */
    private static TwitterMessage parseInputFromTwitter(String line)
    {
        Gson g = new Gson();
        
        //only return a TwitterMessage if the String starts with the correct character
        //this resolves an issue faced when parsing empty lines
        return line.startsWith("{") ? g.fromJson(line.trim(), TwitterMessage.class) : null;
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
