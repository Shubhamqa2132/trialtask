/**
 * 
 */
package testproject.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

import org.testng.Reporter;

/**
 * @author shubham
 *
 */

public class MailAPIUtils {

	final String userName = "testopenaxes@gmail.com";
	final String password = "openaxes";

	//for debugging purpose
	public static void main(String[] arg){
		//MailAPIUtils aa = new MailAPIUtils();
		//aa.inboxCleanUp();
		//aa.getClickHereLinkFromEmail("cpNamethlgagmc");
		
	}
	
/*

public class MailAPIUtils {

	private final String userName;
	private final String password;

	public MailAPIUtils(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	*/
    
    public String getClickHereLinkFromEmail(final String fName){
    	String clickHere = null;
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File(this.getClass().getResource("/smtp.properties").toURI())));
            Session session = Session.getDefaultInstance(props, null);
            
            //make gmail app less secure to connect to api
            //https://www.google.com/settings/security/lesssecureapps
            
            Reporter.log("<br>Connecting to Gmail...", true);
            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", userName, password);
            
            
            Reporter.log("<br>Loading inbox...", true);
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
 
            Reporter.log("<br>search criteria::if mail content contains "+fName, true);
            
            Message[] messages = null;
            
            for(int i=0; i<WaitTime.TOO_LONG_TO; i++){
            	SearchTerm term = new SearchTerm() {
                	@Override
                    public boolean match(Message message) {
                        try {
                        	//for debugging purpose
                        	System.out.println("getSubject::"+message.getSubject());
                        	//System.out.println("getContent::"+message.getContent().toString());
                        	System.out.println("");
                            if (message.getContent().toString().toLowerCase().contains(fName.toLowerCase())) {
                                return true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                };
                Reporter.log("<br>Searching for mail...", true);
    			messages = inbox.search(term);
    			if(messages.length<1){
    				Reporter.log("<br>Wating for Mail to arrive::"+(i+1)+" sec", true);
    				Thread.sleep(1000);
    			}else{
    				Reporter.log("<br>Mail received::"+ messages.length, true);
    				Reporter.log("<br>Subject::"+ messages[0].getSubject(), true);
    				break;
    			}
    				
            }
            
            System.out.println("Mail Subject:- " + messages[0].getSubject());
            System.out.println("Mail Content:- " + messages[0].getContent());
            clickHere = messages[0].getContent().toString().split("a href='")[1].split("'>click here")[0];//.replace("%2Fapply%2F", "%2Fatest%2Fapply%2F");
            Reporter.log("<br>Mail link - clickHere::"+clickHere, true);
            
            //closing connection
            Reporter.log("<br>Closing connection...", true);
            inbox.close(true);
            store.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clickHere;
    }
    
    public String getClickHereToAcknowledeLinkFromEmail(final String fName){
    	String clickHere = null;
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File(this.getClass().getResource("/smtp.properties").toURI())));
            Session session = Session.getDefaultInstance(props, null);
            
            //make gmail app less secure to connect to api
            //https://www.google.com/settings/security/lesssecureapps
            
            Reporter.log("<br>Connecting to Gmail...", true);
            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", userName, password);
            
            
            Reporter.log("<br>Loading inbox...", true);
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
 
            Reporter.log("<br>search criteria::if mail content contains "+fName, true);
            
            Message[] messages = null;
            
            for(int i=0; i<WaitTime.TOO_LONG_TO; i++){
            	SearchTerm term = new SearchTerm() {
                	@Override
                    public boolean match(Message message) {
                        try {
                        	//for debugging purpose
                        	System.out.println("getSubject::"+message.getSubject());
                        	//System.out.println("getContent::"+message.getContent().toString());
                        	System.out.println("");
                            if (message.getContent().toString().toLowerCase().contains(fName.toLowerCase())) {
                                return true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                };
                Reporter.log("<br>Searching for mail...", true);
    			messages = inbox.search(term);
    			if(messages.length<1){
    				Reporter.log("<br>Wating for Mail to arrive::"+(i+1)+" sec", true);
    				Thread.sleep(1000);
    			}else{
    				Reporter.log("<br>Mail received::"+ messages.length, true);
    				Reporter.log("<br>Subject::"+ messages[0].getSubject(), true);
    				break;
    			}
    				
            }
            
            System.out.println("Mail Subject:- " + messages[0].getSubject());
            //System.out.println("Mail Content:- " + messages[0].getContent());
            clickHere = messages[0].getContent().toString().split("href=")[1].split(" target=")[0].replace("\"", "");//.replace("%2Fapply%2F", "%2Fatest%2Fapply%2F");
            Reporter.log("<br>Mail link - clickHere::"+clickHere, true);
            
            //closing connection
            Reporter.log("<br>Closing connection...", true);
            inbox.close(true);
            store.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clickHere;
    }
    public String inboxCleanUp(){
    	Reporter.log("<br>Cleaning inbox for account::"+userName, true);
    	String clickHere = null;
        Properties props = new Properties();
        try {
        	String pathTosmtp = new File(this.getClass().getResource("/smtp.properties").toURI()).toString();
            props.load(new FileInputStream(new File(pathTosmtp)));
            Session session = Session.getDefaultInstance(props, null);
            
            //make gmail app less secure to connect to api
            //https://www.google.com/settings/security/lesssecureapps
            
            Reporter.log("<br>Connecting to Gmail...", true);
            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", userName, password);
            
            
            Reporter.log("<br>Loading inbox...", true);
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_WRITE);
                       
            Message[] messages = inbox.getMessages();
            
            for(int i=0; i<messages.length; i++){            	
            	//setting delete flag for mail.
                Reporter.log("<br>Setting delete flag for::"+messages[i].getSubject(), true);
                messages[i].setFlag(Flags.Flag.DELETED, true);
            }
            
            //closing connection
            Reporter.log("<br>Closing connection...", true);
            inbox.close(true);
            store.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clickHere;
    }
}
