/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

/**
 *
 * @author Cedric Ohle
 */
class LatchEngine extends Engine{
    private float leftMax;
    private float rightMax;
    
    public void setLeftMax(float leftMax){
        this.leftMax = leftMax;
    }
    
    public void setRightMax(float rightMax){
        this.rightMax = rightMax;
    }
    
    public float getLeftMax(){
        return this.leftMax;
    }
    
    public float getRightMax(){
        return this.rightMax;
    }
}
