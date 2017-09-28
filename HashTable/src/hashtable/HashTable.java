/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;
import java.util.Random;
import java.util.Scanner;


public class HashTable {

    
    
    private HNode[] hashArray;
    private int arraySize;
    //book uses for deleting items from list.
    private HNode nonItem = new HNode(null,null,-1);
 
    public HashTable(int size, int numberofrandos){
        
        //this value will determine the hash function 
        //H(x) = x % size //which 
        arraySize = size;
        hashArray = new HNode[size];
        System.out.println("the size of the array is " + hashArray.length);
        
        
        
        
        
        Random ran = new Random();
        for(int i =0;i<numberofrandos;i++){
            int test = ran.nextInt(99);
            int testindex = (test % arraySize);
            boolean scaninput = false;
            boolean scanloop = true;
                while(scanloop){
                    scaninput = scan(test);
                    if(scaninput){
                        System.out.println("There is a duplicate, re-rolling random number");
                        test = ran.nextInt(99);
                    }
                    else{
                        scanloop = false;
                    }
                }
            hashArray[testindex] = insert(test,hashArray[testindex]);
            System.out.println("inserted value "+ test+" into the array at index" + testindex);
        }
        
        
        boolean scanvalue = false;

        //deleted item will show -1 to show that something was there before.
        nonItem = new HNode(null,null,-1);
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Enter in option \n's' = search \n'i' = insert \n'd' = display \n'r' = remove");
            String option = scan.next();
            switch(option){
                case "s":
                    System.out.println("\nYou have picked search");
                    System.out.println("Enter in the value you want to find(ints only please)");
                    int sinput = scan.nextInt();
                    scanvalue = scan(sinput);
                    if(scanvalue){
                        System.out.println("You value of "+sinput+" exists in the tree");
                        scanvalue = false;
                    }else{
                        System.out.println("there is no detected value of "+sinput+" here");
                    }
                    break;
                    
                case "i":
                    System.out.println("\nYou have picked insert");
                    System.out.println("Enter a value to insert into the table: ");
                    boolean scaninput = false;
                    
                    boolean scanloop = true;
                    int input = scan.nextInt();
                    while(scanloop){
                        scaninput = scan(input);
                        if(scaninput){
                            System.out.println("This value is already in here, please enter different integer");
                            input = scan.nextInt();
                        }
                        else{
                            scanloop = false;
                        }
                    }
                    int arrayindex = (input % arraySize);
                    
                    hashArray[arrayindex] = insert(input,hashArray[arrayindex]);
                    break;
                    
                    
                case "d":
                    System.out.println("\nYou have picked display");
                    for(int i =0;i<arraySize;i++){
                        System.out.print(i+". ");
                        display( hashArray[i]);
                        System.out.println();

                    }
                    break;
                    
                    
                case "r":
                    System.out.println("\nYou have picked remove");
                    System.out.println("Enter in the value that you want to delete");
                    int rinput = scan.nextInt();
                    ///scan here to make sure that this value is not in the table first
                    //if it isn't then have if statement that will avoid trying to remove this value.
                    
                    scanvalue = scan(rinput);
                    //initially scanvalue is false so that if there is nothing in the array that matches the looked for remove value then
                    //it wont waste time trying to remove, which could be painful
                    if(scanvalue){
                        int index = rinput % arraySize;
                        hashArray[index] = remove(rinput,hashArray[index]);
                        scanvalue = false;
                    }else{
                        System.out.println("\nThe value you want to remove is not in the hash table");
                        
                    }
                    break;
                
                case "x":
                    System.out.println("\nYou have opted to leave");
                    System.exit(0);
                default:
                    System.out.println("invalid entry please input only listed.");
                    break;
                
                
                
                
            }
            
            
            
            
        }
    }
    //----------------------------------------------------------------------------
    //scans the whole hash array everytime called, can be inefficient
    public boolean scan(int scaninput){
        //this method returns true if there is a tree with the falue looked for somewhere in it.
        int searchFlag = 0;
        //iterative scan function here
        for(int i=0;i<arraySize;i++){
            if(hashArray[i] != null){
                HNode iter = hashArray[i];
                while(iter != null){
                    //a fairly complex syntax loop that iteratively searchs the binary tree
                    //for the scaninput value
                    for (HNode c=hashArray[i]; c!=null; c = scaninput<c.getVal() ? c.getlChild() : c.getrChild())
                      if (scaninput == c.getVal())
                            searchFlag =1;
                            iter = null;
    
                }
            }
        }
        
        
        
        
        
        
        if(searchFlag ==1){
            return true;
        }else{
            return false;
        }
    }
//    ---------------------------------------------------------------------------------------------------
  
    
    public HNode insert(int input,HNode tmp){
        //inserts according to the hash function which is defined by the array size
        //also recusive
        HNode parent  = tmp ;
        
        if(tmp == null){
            tmp = new HNode(null,null,input);
            return tmp;
            //System.out.println("Sucessfully inserted "+input+" in the tree");
        }
        else{
            if      (input < tmp.getVal()){
                if(tmp.getlChild() == null){    
                    HNode lc = new HNode(null, null,input);
                    tmp.setLeft(lc);
                    return tmp;
                }
                else{
                    tmp = tmp.leftChild;
                    while(tmp != null){

                        if(      tmp.getVal() < input && tmp.leftChild != null){
                            tmp = tmp.leftChild;
                        }else if(tmp.getVal() < input && tmp.leftChild == null){
                            tmp.setLeft(new HNode(null,null,input));
                            return parent;
                        } 
                        else if(tmp.getVal() > input && tmp.rightChild != null){
                            tmp = tmp.rightChild;
                        }
                        else if(tmp.getVal() > input && tmp.rightChild == null){
                            tmp.setRight(new HNode(null,null,input));
                            return parent;
                        }
                        else if(tmp.getVal() == input){
                            tmp.setDoup();
                        }   
                        
                    }
                    
                }
            }else if(input > tmp.getVal()){
                if(tmp.getrChild() == null){    
                    HNode rc = new HNode(null, null,input);
                    tmp.setRight(rc);
                    return tmp;
                }
                else{
                    tmp = tmp.rightChild;
                    while(tmp != null){

                        if(      tmp.getVal() < input && tmp.leftChild != null){
                            tmp = tmp.leftChild;
                        }else if(tmp.getVal() < input && tmp.leftChild == null){
                            tmp.setLeft(new HNode(null,null,input));
                            return parent;
                        }
                        else if(tmp.getVal() > input && tmp.rightChild != null){
                            tmp = tmp.rightChild;
                        }
                        else if(tmp.getVal() > input && tmp.rightChild == null){
                            tmp.setRight(new HNode(null,null,input));
                            return parent;
                        }
                        else if(tmp.getVal() == input){
                            tmp.setDoup();
                        }   
                        
                    }
                }    
            }else if(input == tmp.getVal()){
                System.out.println("you have a duplicate number: " +input + " in this binary tree");
                tmp.setDoup();
        
            }else{
                System.out.println("you shouldn't be here");
            }
            
    }       
        System.out.println("end of insert, shouldn't be here");
        return null;
        
    }  
    
    
    public void display(HNode tmp){
        
        //look through the whole hasharray to see whats in it
        if(tmp == null){
            System.out.print("nothing here");
            return;
        }
        //for deleted values print off this
        else{
            
        if(tmp.getlChild() != null ){
            display(tmp.getlChild());   
        }
            System.out.print(" " + tmp.getVal() + " ");
        
        if(tmp.getrChild() != null ){
            display(tmp.getrChild());
        }
        }
    }
    //---------------------------------------------------------------------------------------
    //can we pretend that this part works
    public HNode remove(int input, HNode tmp){
        //initially setting parent node to return at end of function
        HNode parent = tmp;
              /* Base Case: If the tree is empty */
        if (tmp == null){
            System.out.println("The value " + input + " does not exist in this hash table");
            return tmp;
        }
 
        /* Otherwise, iterate down the tree */
        while(tmp != null){
            if(      tmp.getVal() > input && tmp.leftChild != null){
                tmp = tmp.leftChild;
            }
            else if (tmp.getVal()> input && tmp.leftChild == null){
                System.out.println("The value " + input + " does not exist in this hash table");
                return parent;
            }else if(tmp.getVal() < input && tmp.rightChild != null ){
                tmp = tmp.rightChild;
            }else if(tmp.getVal() < input && tmp.rightChild == null){
                System.out.println("The value " + input + " does not exist in this hash table");
            }else if(tmp.getVal() == input){
                if(tmp.rightChild == null && tmp.leftChild == null){
                    tmp = null;
                    return parent;
                }else if(tmp.rightChild == null && tmp.leftChild != null){
                    tmp = tmp.leftChild;
                    return parent;
                }else if(tmp.rightChild != null && tmp.leftChild == null){
                    tmp = tmp.rightChild;
                    return parent;
                }else if(tmp.rightChild != null && tmp.leftChild != null){
                    HNode testparent = null;
                    HNode testchild = tmp.leftChild;
                    while(tmp.rightChild != null){
                        testparent = testchild;
                        testchild = testparent.rightChild;
                    }
                    if( testparent == null){
                        HNode swaptmp = tmp;
                        tmp = testchild;
                        testchild = swaptmp;
                        tmp.leftChild = testchild.leftChild;
                    }else{ 
                        HNode swaptmp = tmp;
                        tmp = testchild;
                        testchild = swaptmp;
                        tmp.rightChild = testchild.leftChild;

                    }
                }
            }
        
        }
   
        return parent;
    }
    
}

