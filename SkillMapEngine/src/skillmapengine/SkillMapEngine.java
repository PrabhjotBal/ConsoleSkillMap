/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skillmapengine;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author pers
 */
public class SkillMapEngine {

    
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    
    /**
     * @param args the command line arguments
     */
     Scanner in=new Scanner(System.in);
     void employeeProfile(String user)
     {
         System.out.println("Welcome "+user);
     }
      void employerProfile(String user)
     {
         System.out.println("Welcome "+user);
     }
     void Signin(byte typeOfUser)
        {
            System.out.println("Enter Username");
            String username=in.next();
            System.out.println("Enter Password");
            String password=in.next();
            if(typeOfUser==1)
            {
                    try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/SkillMapEngineDB", "root", "");
                stmt = con.prepareStatement("select email,password from employeetbl where email=? and password=?");
                stmt.setString(1, username);
                stmt.setString(2, password);
                rs=stmt.executeQuery();
                if(rs.next())
                {
                 String user=rs.getString(1);   
                
                  employeeProfile(user);
                }
                else
                {
                    System.out.println("Invalid username or password");
                    Signin((byte)1);
                }
                
                        } catch (Exception e) {
            System.out.println("Error " + e);
            
                
            }
                    
            }
            else if(typeOfUser==2)
            {
                      try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/SkillMapEngineDB", "root", "");
                stmt = con.prepareStatement("select email,password from employertbl where email=? and password=?");
                stmt.setString(1, username);
                stmt.setString(2, password);
                rs=stmt.executeQuery();
                if(rs.next())
                {
                 String user=rs.getString(1);   
                
                  employerProfile(user);
                }
                else
                {
                    System.out.println("Invalid username or password");
                    Signin((byte)2);
                }
                
                        } catch (Exception e) {
            System.out.println("Error " + e);
            
                
            }
                
            }
            else
            {
                System.out.println("Invalid Input");
            }
        }
      
       String emailV,phNoV,companyName="";
       void employer()
       {
        System.out.println("Enter your email?");
        String emailcheck=in.next();
        if(isValidEmailAddress(emailcheck))
        {
            emailV=emailcheck;
        }
        else
        {
            System.out.println("Incorrect Email..");
            employer();
        }
           do
           {
               
        System.out.println("Enter Ph. No.");
          phNoV=in.next();
               isValidMobile(phNoV);
           }
           while(isValidMobile(phNoV)==false);
           
        
           System.out.println("Enter Password");
           String password=in.next();
            System.out.println("Enter Company Name");
            companyName=in.next();
            System.out.println("Do you want to save the Registration Details?(y/n)");
            char answer=in.next().charAt(0);
            if(answer=='y')
                {
                    try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/SkillMapEngineDB", "root", "");
                stmt = con.prepareStatement("insert into Employertbl values(?,?,?,?)");
                stmt.setString(1, emailV);
                stmt.setString(2, phNoV);
                stmt.setString(3, companyName);
                stmt.setString(4, password);
                stmt.execute();
                System.out.println("Registration Successful");
                        } catch (Exception e) {
            System.out.println("Error " + e);
            
                        }          
                }
            else if(answer=='n')
            {
                employer();
            }   
            
            else
            {
                System.out.println("Invalid input");
            }
       
       }
       
       void employee()
       {
           //Validation left for employee
           String email,ename,dob,phno,qualification,trade,skills,password;
           byte experience=0;
           System.out.println("Enter Email");
           email=in.next();
           System.out.println("Enter Name");
           ename=in.next();
           
           System.out.println("Enter Password");
           password=in.next();
               System.out.println("Enter DOB(dd-mm-yy)");
           dob=in.next();
               System.out.println("Enter Ph No.");
           phno=in.next();
               System.out.println("Enter Qualification");
           qualification=in.next();
           
           System.out.println("Trade");
           trade=in.next();
           System.out.println("Are you a fresher?(y/n)");
           char foption=in.next().charAt(0);
           if(foption=='y')
           {
               experience=0;
           }
           else if(foption=='n')
           {
               System.out.println("Experience in months:");
               experience=in.nextByte();
           }
           else
           {
               System.out.println("Invalid option");
           }
           System.out.println("Skills ");
           skills=in.next();
            System.out.println("Do you want to save the Registration Details?(y/n)");
            Scanner in=new Scanner(System.in);
            char answerE=in.next().charAt(0);
            if(answerE=='y')
                {
                    try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/SkillMapEngineDB", "root", "");
                stmt = con.prepareStatement("insert into Employeetbl values(?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, email);
                stmt.setString(2, ename);
                stmt.setString(3, dob);
                stmt.setString(4, phno);
                stmt.setString(5, qualification);
                stmt.setString(6, trade);
                stmt.setByte(7, experience);
                stmt.setString(8, skills);
                stmt.setString(9, password);
                stmt.execute();
                System.out.println("Registration Successful");
                
                        } catch (Exception e) {
            System.out.println("Error " + e);
            
                        }          
                }
            else if(answerE=='n')
            {
                employee();
            }
            else
            {
                System.out.println("Invalid Input");
                employee();
            }
       }
     void CreateUser(byte typeOfUser)
    {
        switch(typeOfUser)
        {
            case 1:employee();
                break;
            case 2:employer();
                break;
                        default:
                            System.out.println("Invalid Input");
        }
    }
    public boolean isValidMobile(String phNo)
        {
            
        String regexStr = "^[0-9]{10}$";
        if(phNo.matches(regexStr))
        {
            return true;
            
        }
        else
        {
            return false;
            
        }
        }
        
    public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
     void checkUser(byte typeOfUser)
    {
        
        System.out.println("Already a member?(y/n)");
        char ans=in.next().charAt(0);
        if(ans=='y')
        {
            Signin(typeOfUser);
        }
        else if(ans=='n')
        {
            CreateUser(typeOfUser);
        }
        else
        {
            System.out.println("Invalid Option");
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in=new Scanner(System.in);
        SkillMapEngine sme=new SkillMapEngine();
        System.out.println("Welcome to Skill Map Engine.");
        System.out.println("Choose an option:");
        System.out.println("1.Employee");
        System.out.println("2.Employer");
       
        byte option=in.nextByte();
        switch(option)
        {
            case 1:
                    sme.checkUser((byte)1);
                break;
            case 2:
                sme.checkUser((byte)2);
                break;
            
            default:
                System.out.println("Invalid Option");
                main(args);
                break;
                        
        }
        }
    }

