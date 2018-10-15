/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartwatchsim.sensors;

public class HeartRateSensor extends Thread {
    
    private int currentHeartRate;
    private int maxHeartRate;
    private int minHeartRate;

    public HeartRateSensor(int age, int initialValue) {
        this.currentHeartRate = initialValue;
        this.maxHeartRate = 220 - age;
        this.minHeartRate = 80;
    }

    public int getCurrentHeartRate() {
        return currentHeartRate;
    }

    @Override
    public void run() {
        while (true) {            
            int newHeartRate;
            if (Math.random() < 0.5) {
                newHeartRate = this.currentHeartRate + 1;
            } else {
                newHeartRate = this.currentHeartRate - 1;
            }
            if (newHeartRate > this.minHeartRate && newHeartRate < this.maxHeartRate) {
                this.currentHeartRate = newHeartRate;
            }
            
            try {
                long minTime = 1000;
                long maxTime = 3000;
                long randomSleepTime = minTime + (long) (Math.random() * (maxTime - minTime));
                
                Thread.sleep(randomSleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    

}
