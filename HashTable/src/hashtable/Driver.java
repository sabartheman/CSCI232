/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package hashtable;

import java.util.Scanner;
import java.util.Random;

public class Driver {
    
    
    
    
    public static void main(String[] args) {
    
        //scanning in values to initialize the tree.
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        
        System.out.println("Hash Table program initialized");
        System.out.print("Enter size of hash table: ");
        int inputSize = scan.nextInt();
        System.out.print("\nEnter initial number of items: ");
        //number of randomly inserted values before table is started
        int inputItems = scan.nextInt();
        //initializes the hashtable and this puts user into infinite while loop
        //until "x" is entered, this is not told to user
        
        HashTable hash = new HashTable(inputSize,inputItems);
        
        
    }
    
}
