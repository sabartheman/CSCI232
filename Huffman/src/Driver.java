import java.util.Scanner;
import java.util.*;

public class Driver {
    //used to take in a string for 
    public static String inString;
    public static int countChar =0;
    public static int[] alphabet = new int[26];
    public static int[] freq = new int[29];
    public static char[] alphaCompare = new char[29];
    public static char[] stringReplace;
    
    
    public static PriorityQ quen = new PriorityQ(100);
    public static Tree tree = new Tree();
            
    //create a object for the hashmap to store the frequency with value
    public static HashMap<Character,Integer> map = new HashMap<Character,Integer>();          
        //
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String option;
        boolean loop = true;
        char c;
        
        while(loop){
            //will ask for a option to pursue
            System.out.print("Enter first letter of enter, show, code, or decode: ");
            option = scan.nextLine();
            //converting the String to a char for if statements
            c= option.charAt(0);

            if(c == 'e'){
                System.out.println("\nEnter text lines, hit enter to terminate line.");
                inString = scan.nextLine();
                enter();
                System.out.println("\n Please Code the string before trying to Decode.");
            }else if(c == 's'){
                showTree();
            }else if(c == 'c'){
                code();
            }else if(c == 'd'){
                decode();
            }else if(c == 'x'){
                System.out.println("\nyou have chosed to exit");
                System.exit(0);
            }else{
                System.out.println("invalid entry, please only one letter");
            }

        }
    }
    
    public static void enter(){
        int[] keyTree;
        //a string for formats sake
        String headLine = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z [ / ]";

        //creating a uppercase only version of hte string, simplifying 
        //the process of count the number of characters
        String s = inString.toUpperCase();
        inString = inString.toUpperCase();
        stringReplace = inString.toCharArray();
                
        //looks at the String and sees how many times each letter appears.
        for(int i = 0; i < s.length(); i++){
           char c = s.charAt(i);
           Integer val = map.get(new Character(c));
           if(val != null){
             map.put(c, new Integer(val + 1));
           }else{
             map.put(c,1);
           }
        }
        String alphaS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        alphaCompare = alphaS.toCharArray();
        
        for(int i = 0; i<27; i++){
            if(map.get(alphaCompare[i]) != null){
                freq[i] = map.get(alphaCompare[i]);
                
            }else{
                freq[i] =0;
            }
        }
        System.out.println("\n"+headLine);
        for(int i =0; i<freq.length;i++){
            System.out.print(freq[i] + " ");
        }System.out.println();
        
        for(int i = 0; i<freq.length; i++){
            if(freq[i] > 0){
                countChar++;
            }
        }
        //debug statement to check to see how many characters are in entered string
        System.out.println("There are "+countChar+ " of different characters in this string");
        PriorityQ que = new PriorityQ(countChar);
        //created a priority Q so that creating the tree will organisze it automatically
        
        for(int i = 0; i<27; i++){
            if(map.get(alphaCompare[i]) != null){
                //tree.insert(freq[i], alphaCompare[i]);
                Tree ntree = new Tree();
                ntree.insert(freq[i], alphaCompare[i]);
                que.insert(ntree);
                
                
            }
        }
        quen = que;
        //will build a tree for the all the characters in the String.
        //uses a frew custom methods in tree.java
        while(que.isFinished()){
            //creating trees for each 
            Tree ltree = new Tree();
            Tree rtree = new Tree();
            Tree ptree = new Tree();
            ltree = quen.remove();
            rtree = quen.remove();
            System.out.print("left tree key: " + ltree.root.iData + " \nright tree key: " + rtree.root.iData + "\n");

            
            //ptree.insert((ltree.root.iData + rtree.root.iData),'?');
            ptree.insert(0,'?');
            System.out.println("parent tree key: " + ptree.root.iData + "\n \n");
            
            ptree.setLeft(ptree,ltree.root);
            ptree.setRight(ptree,rtree.root);
            //ptree.insertRight(rtree.root.iData, rtree.root.dData);
            //ptree.insertLeft(ltree.root.iData, rtree.root.dData);
            //ptree.displayTree();
            que.insert(ptree);
            System.out.println("\n\n");
        
        }
        tree = que.remove();
        //tree.displayTree();
        System.out.println("the tree should be built at this point");
    }
    
    
    
    
    
    //uses the diplay tree that came in the tree.java originally
    
    public static void showTree(){
        System.out.println("Now showing tree");
        tree.displayTree();
    }
    
    //takes the String and turns it into binary 
    public static void code(){
        System.out.println();
        Map<Character,String> pmap = tree.genMap(tree.root);
        
        for(int i = 0; i < freq.length; i++){
            if(freq[i] > 0){
                System.out.println("the letter " + alphaCompare[i] + " can be found at: " + pmap.get(alphaCompare[i]) );
            }
        }
        String tmp = "";
        for(int i=0; i< inString.length(); i++){
            tmp += pmap.get(inString.charAt(i));
            }
        System.out.println("The String is now encoded to:\n" + tmp);
        inString = tmp;
        System.out.println();
    }
    
    public static void decode(){
        System.out.println("this is a work in progress");
        Map<Character,String> pmap = tree.genMap(tree.root);
        String[] tmpString = new String[freq.length];
        String[] tmpChar = new String[freq.length];
        inString = "0000010010101011111";
        for(int i=0;i<countChar;i++){
            if(freq[i] >0){
                tmpString[i] = pmap.get(alphaCompare[i]);
                tmpChar[i] = Character.toString(alphaCompare[i]);
            }
            
        }
        for(int i=0;i<countChar;i++){
            System.out.println(tmpString[i]);
            
        }
        
        
        
        int k = 0;
        String chunk = "";
        String newString ="";
        for(int i=0; i<inString.length();i++){
            System.out.println("the value of i is: " + i);
            chunk += inString.charAt(i+k);
            System.out.println("chunk is now: "+chunk);
            System.out.println("the input string is: "+inString.substring(0,i+k));
            //System.out.println((chunk.equals(inString.substring(k, i+1))));
            
            for(int j=0;j<countChar;j++){    
                if(chunk.equals(inString.substring(k, i+1)) && pmap.containsValue(chunk)){
                    System.out.println("true");
                    for(int l =0; l< countChar;l++){
                        if(pmap.get(tmpChar[l]).equals(chunk))    
                           newString = inString.replace(chunk, tmpChar[l]);
                           System.out.println(newString);
                    }
                    chunk = "";
                    k++;    
                    }
                }
            }
                        
            //System.out.println(inString);
                
        System.out.println(inString);
        
        
        
        
        
    }
}
