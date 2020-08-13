/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metin_oktay_boz_hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MONSTER
 */
public class HashTable {
    private Person[] HashTablePerson = new Person[997];
    private Song[] HashTableSong = new Song[997];
    private int TotalSong=0;
    
    public void Run(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your commands:");
        while(1==1){    // menu loop
        String txt ="";
        txt = scan.nextLine();
        char[] ch = new char[txt.length()];
                for (int i = 0; i < txt.length(); i++) { 
                   ch[i] = txt.charAt(i);
                }
                    String str="";
                    String song="";
                    String name="";
                
                if(!txt.equals("")){    
                if(ch[0]=='I'){    //Insert person
                    str="";
                    for(int j=2; j<ch.length;j++){
                        if(ch[j]!=' ')                        
                        str += ch[j];
                    }
                    hashPerson(str);
                }
                
                else if(ch[0]=='L'){   //Like songs
                    str="";
                   for(int j=2; j<ch.length;j++){                                               
                        if(ch[j]==' '){
                            name= str;
                            str="";
                            break;
                        }
                        str += ch[j];
                    }
                   for(int j=(txt.indexOf(name) + name.length() +1); j<ch.length; j++){
                       str+= ch[j];
                   }
                   song = str;
                   hashSong(name,song);
                }
                
                else if(ch[0]=='E'){  //Erease song
                    str="";
                   for(int j=2; j<ch.length;j++){                       
                        if(ch[j]==' '){
                            name= str;
                            str="";
                            break;
                        }
                        str += ch[j];
                    }
                   for(int j=(txt.indexOf(name) + name.length() + 1); j<ch.length; j++){
                       str+= ch[j];
                   }
                   song = str;
                   deleteSong(name,song);
                }
                
                else if(ch[0]=='D'){   //Delete person
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    Delete(str);
                }
                
                else if(ch[0]=='M'){    //Match
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    MatchSongs(str);
                }
                
                else if(ch[0]=='P'){   //List the songs
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    PrintName(str);
                }
                
                else if(ch[0]=='R'){   //Recommend song
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    RecommendSong(str);
                }
                
                else if(ch[0]=='O'){   //Get txt file
                    System.out.println("");
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    Inputfile(str);
                }
        
        else if(txt.equals("X") || txt.equals("x")){   //Exit menu
            break;
        }
        else 
                System.out.println("Invalid command. Try again");
        }
        }
    }
    
    private void Inputfile(String FileName){   //Read txt file
        File f = new File(FileName);
        String txt ="";
        int count=0;        
        try {
            Scanner dosya = new Scanner(f);
            while(dosya.hasNextLine()){
                txt = dosya.nextLine();
                char[] ch = new char[txt.length()];
                for (int i = 0; i < txt.length(); i++) { 
                   ch[i] = txt.charAt(i);
                }
                    String str="";
                    String song;
                    String name="";
                    
                if(ch[0]=='I'){
                    str="";
                    for(int j=2; j<ch.length;j++){
                        if(ch[j]!=' ')                        
                        str += ch[j];
                    }
                    hashPerson(str);
                }
                
                else if(ch[0]=='L'){
                    str="";
                   for(int j=2; j<ch.length;j++){                                               
                        if(ch[j]==' '){
                            name= str;
                            str="";
                            break;
                        }
                        str += ch[j];
                    }
                   for(int j=(txt.indexOf(name) + name.length() +1); j<ch.length; j++){
                       str+= ch[j];
                   }
                   song = str;
                   hashSong(name,song);
                }
                
                else if(ch[0]=='E'){
                    str="";
                   for(int j=2; j<ch.length;j++){                       
                        if(ch[j]==' '){
                            name= str;
                            str="";
                            break;
                        }
                        str += ch[j];
                    }
                   for(int j=(txt.indexOf(name) + name.length() + 1); j<ch.length; j++){
                       str+= ch[j];
                   }
                   song = str;
                   deleteSong(name,song);
                }
                
                else if(ch[0]=='D'){
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    Delete(str);
                }
                
                else if(ch[0]=='M'){
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    MatchSongs(str);
                }
                
                else if(ch[0]=='P'){
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    PrintName(str);
                }
                
                else if(ch[0]=='R'){
                    str="";
                    for(int j=2; j<ch.length;j++){                        
                        str += ch[j];
                    }
                    RecommendSong(str);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File can not be found!");
        }
        //System.out.println(txt);
    }
    
    private void hashPerson(String newName){
        Person newperson = new Person();
        newperson.PersonName = newName;
       
        int KEY = getPersonKey(newName);  //Create person key
        //newperson.personkey = KEY;
        if(HashTablePerson[KEY]==null){  //Add new person
        HashTablePerson[KEY] = newperson;
        HashTablePerson[KEY].key = KEY;
        }
        else if(HashTablePerson[KEY]!=null && HashTablePerson[KEY].PersonName.equals(newName)){  //Control that created before or not 
            System.out.println(newName + " can not be created because already created.");
            System.out.println("");
        }
        else if(HashTablePerson[KEY]!=null){  // linear probing for collusion
            for(int i=KEY; i<HashTablePerson.length;i++){
                if(HashTablePerson[i]==null){
                    HashTablePerson[i]=newperson;
                    HashTablePerson[i].key = i;
                    break;
                }
            }
        }
   }
    
    private void hashSong(String personName, String newSongName){  //Add song
        Song newsong = new Song();
        newsong.SongName = newSongName;
        int KEY = getSongKey(newSongName);  //create song key
        
        int personkey = getPersonKey(personName);  //create person key
        
        if(HashTablePerson[personkey]==null){ //control the name created before or not
            System.out.println(personName + " is not created so Song cannot be liked.");
            System.out.println("");
        }
        else{
        if(HashTableSong[KEY]==null){   //add song
        personkey = controlPersonKey(personkey, personName);  //control the key and name are matched
        HashTableSong[KEY] = newsong;
        TotalSong++;
        HashTablePerson[personkey].songkey[HashTablePerson[personkey].songNumber++]=KEY;  //add the song key in person's node
        HashTableSong[KEY].PersonKeys[HashTableSong[KEY].PersonNumber++]=personkey;       //add the person key in song's node
        }
        else if(HashTableSong[KEY]!=null && HashTableSong[KEY].SongName.equals(newSongName)){    //if song created before, just add keys in person's and song's node
            HashTablePerson[personkey].songkey[HashTablePerson[personkey].songNumber++]=KEY;
            HashTableSong[KEY].PersonKeys[HashTableSong[KEY].PersonNumber++]=personkey;
        }
        else if(HashTableSong[KEY]!=null){   //linear probing for collusion
            for(int i=KEY; i<HashTableSong.length;i++){
                if(HashTableSong[i]==null){
                    HashTableSong[i]= newsong;
                    break;
                }
            }
        }
        }
    }
    
    private void deleteSong(String personName, String DeletedSongName){   //erease song
        boolean count = false;  //control the song is exist or not
        int personkey = getPersonKey(personName);        
        Integer songkey = getSongKey(DeletedSongName);
        
        if(HashTablePerson[personkey]!=null && HashTableSong[songkey]!=null){  //control the nodes are null or not
            personkey = controlPersonKey(personkey, personName);     //control the person name and key is matched
            songkey = controlSongKey(songkey, DeletedSongName);      // control the song name and key is matched
        for(int i=0; i<HashTablePerson[personkey].songkey.length; i++){
            if(HashTablePerson[personkey].songkey[i]!=null && HashTablePerson[personkey].songkey[i].equals(songkey)){
               System.out.println(personName + " doesn't like " + DeletedSongName + " anymore.");
                System.out.println("");
               HashTablePerson[personkey].songkey[i]=null;
               HashTablePerson[personkey].songNumber--;
               count = true;
               break;
            }            
        }
            if(count==true){   //erease the person key in the deleted song's node
            for(int i=0; i<HashTableSong[songkey].PersonKeys.length; i++){
                if(HashTableSong[songkey].PersonKeys[i]!=null)
                if(HashTableSong[songkey].PersonKeys[i]==personkey){
                   HashTableSong[songkey].PersonKeys[i]=null; 
                }
            }
            }
            if(count == false){    //control the name liked the song before or not
                System.out.println(personName + " did not like " + DeletedSongName + " before So the song can't be erased.");
                System.out.println("");
            }
        }
        else   //if person or song is not exist
            System.out.println("The song is not in the list, So can not be eraesed.");
    }
    
    private void Delete(String Name){  //Delete person
        int personKEY = getPersonKey(Name);       
                
        if(HashTablePerson[personKEY]==null){   //control the name is exist or not
            System.out.println(Name + " can't be deleted because he/she is not in the list.");
            System.out.println("");
        }        
        else{
            personKEY = controlPersonKey(personKEY, Name);
            HashTablePerson[personKEY]=null;
        }
    }
    
    
    private void PrintName(String name){   //List the songs that name likes
        int KEY = getPersonKey(name);
        
        boolean count =false;
        if(HashTablePerson[KEY]!=null){
            KEY = controlPersonKey(KEY, name);
          for(int i=0; i<HashTablePerson[KEY].songkey.length; i++){  //control the name liked any song before
            if(HashTablePerson[KEY].songkey[i]!=null){
                count = true;
            break;
            }
          }
        }
        if(HashTablePerson[KEY]==null)  //control name is exist
        {
            System.out.println(name + " is not in the list.");
            System.out.println("");
        }       
        else if(HashTablePerson[KEY].songkey!=null && count==true){
            System.out.println(name + " likes:");
           for(int i=0; i<HashTablePerson[KEY].songkey.length; i++){
               if(HashTablePerson[KEY].songkey[i]!=null){
                  int songkey = HashTablePerson[KEY].songkey[i];
                  System.out.println(HashTableSong[songkey].SongName);
                } 
            }
            System.out.println("");
        } 
        else
        {
            System.out.println(name + " has no song.");
            System.out.println("");
        }
    }
    
    private void MatchSongs(String name){
        int personkey = getPersonKey(name);
        boolean control = false;
        if(HashTablePerson[personkey] == null){   //control the name is exist
            System.out.println("There is no " + name + " in the list.");
        }
        else{
            personkey = controlPersonKey(personkey, name);
            for(int i=0; i<HashTablePerson[personkey].songkey.length; i++){   //get the song keys in person node
                if(HashTablePerson[personkey].songkey[i]!=null){
                    int songkey = HashTablePerson[personkey].songkey[i];
                    for(int j=0; j<HashTableSong[songkey].PersonKeys.length; j++){   //check the other keys in song keys
                        if(HashTableSong[songkey].PersonKeys[j]!=null){
                        if(HashTableSong[songkey].PersonKeys[j]!=personkey){
                            int otherkey = HashTableSong[songkey].PersonKeys[j];
                            if(HashTablePerson[otherkey]!=null){        //There is a match, increase count
                            HashTablePerson[otherkey].count++;
                            control=true;
                            }
                        }
                        }
                    }
                }
            }
            if(control==true){   //control there is match
            System.out.println("Possible friends of " + name);
            for(int i=0; i<HashTablePerson.length; i++){
                if(HashTablePerson[i]!=null && i!=personkey && HashTablePerson[i].count!=0){
                    System.out.println(HashTablePerson[i].PersonName + " " + (HashTablePerson[i].count*100/HashTablePerson[personkey].songNumber) + "%");
                    HashTablePerson[i].count =0;
                }
            }
            }
            else
                System.out.println("There is no possible friend.");
        }
        System.out.println("");       
    }
    
    
    private void RecommendSong(String name){
        int personkey = getPersonKey(name);
        String[] recsongs = new String[5];
        int recCount=0;
        if(HashTablePerson[personkey]!= null){  //control name is exist
          personkey = controlPersonKey(personkey, name);
          
          for(int i=0; i<HashTableSong.length; i++){
              if(HashTableSong[i]!=null){
                  for(int j=0; j<HashTableSong[i].PersonKeys.length; j++){  //search person key in song nodes
                      if(HashTableSong[i].PersonKeys[j]!=null){
                      if(HashTableSong[i].PersonKeys[j]==personkey){   //there is personkey so person already liked this song
                          break;
                      }
                      }
                      if(recCount<5)   //there is no person key, so person did not liked it before
                   recsongs[recCount++] = HashTableSong[i].SongName;
                      break;
                  }
              }  
            }
            
            if(recCount==0)  //there is no song that person didn't like before in the list
                    System.out.println("There is no song to recommend.");
            else{
            System.out.println("Recommended Songs for " + name);
            for(int i=0; i<recsongs.length; i++){                
                if(recsongs[i]!=null)
                System.out.println(recsongs[i]);
            }
            }
        }
        else{
            System.out.println("There is no " + name + " in the list.");
        }
    }
    
     private int getPersonKey(String name){  //create person key
        char[] ch = new char[name.length()];
        long ascii=1;
        int KEY=0;
        
        for (int i = 0; i < name.length(); i++) { 
            ch[i] = name.charAt(i);
            ascii *= ch[i];            
        }        
            
        ascii = ascii%997;
        ascii=Math.abs(ascii);  //if ascii is negative
        KEY = (int) ascii;
        return KEY;
     }
     
     private int getSongKey(String name){   //create song key
        char[] ch = new char[name.length()];
        long ascii=1;
        int KEY=0;
        
        for (int i = 0; i < name.length(); i++) { 
            ch[i] = name.charAt(i);
            ascii *= ch[i];            
        }        
            
        ascii = ascii%997;
        ascii=Math.abs(ascii);  //if ascii is negative
        KEY = (int) ascii;
        return KEY;
     }
     
    private int controlPersonKey(int personkey, String personName){  //control the name and key matching
        if(!HashTablePerson[personkey].PersonName.equals(personName)){  //There is collusion, search probing
           for(int i = personkey; i<HashTablePerson.length; i++){
               if(HashTablePerson[i].PersonName == personName){
                  personkey = i;
                  break;
               }
           } 
        }
        return personkey;
    }
    
    private int controlSongKey(int songkey, String songName){  //control the name and key matching
        if(!HashTableSong[songkey].SongName.equals(songName)){  //There is collusion, search probing
           for(int i = songkey; i<HashTableSong.length; i++){
               if(HashTableSong[i].SongName.equals(songName)){
                  songkey = i;
                  break;
               }
           } 
        }
        return songkey;
    }
}
