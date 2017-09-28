/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

/**
 *
 * @author sabar
 */
public class HNode {
    public int value = -1;
    public int douplicate;
    public HNode parent = null;
    public HNode leftChild;
    public HNode rightChild;
    
    
   HNode(HNode left, HNode right, int inval){
        this.value = inval;
        this.douplicate = 1;
        this.leftChild = left;
        this.rightChild = right;
    
        
    }
   
   public HNode getlChild(){
       return leftChild;
   }
   public HNode getrChild(){
       return rightChild;
   }
    
   public int getVal(){
       return value;
   }
   
   public void setVal(int input){
       this.value = input;
   }
   
   public void setDoup(){
       this.douplicate++;
   }
   
   public void setLeft(HNode lin){
       this.leftChild = lin;
   }
   
   public void setRight(HNode rin){
       this.rightChild = rin;
   }
   
   public void setP(HNode pin){
       this.parent = pin;
   }
   
   //possible removal of children
   public void deleteVal(int val){
       
   }
   
   public boolean isLeaf(){
       return (leftChild == null && rightChild == null);
   }
   
   
}
