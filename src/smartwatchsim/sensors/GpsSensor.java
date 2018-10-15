/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartwatchsim.sensors;

public class GpsSensor extends Thread {

    private static final double MIN_FACTOR = 0.000001;
    private static final double MAX_FACTOR = 0.00003;
    private double currentLat;
    private double currentLong;
    private double currentEle;
    private double latFactor;
    private double longFactor;
    private double eleFactor;
    private boolean isMovingNorth;
    private boolean isMovingEast;

    public GpsSensor(double initialLat, double initialLong, double initialEle) {
        this.currentLat = initialLat;
        this.currentLong = initialLong;
        this.currentEle = initialEle;
        this.latFactor = 0.000001;
        this.longFactor = 0.000001;
        this.eleFactor = 0.1;
        this.isMovingNorth = true;
        this.isMovingEast = true;
        new Thread(() -> {
            try {
                while (true) {
                    long minTime = 10000;
                    long maxTime = 25000;
                    long randomSleepTime = minTime + (long) (Math.random() * (maxTime - minTime));
                    sleep(randomSleepTime);
                    this.latFactor = genRandomFactor();
                    this.longFactor = genRandomFactor();
                    this.isMovingNorth = genRandomBoolean();
                    this.isMovingEast = genRandomBoolean();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private boolean genRandomBoolean() {
        return Math.random() < 0.5;
    }

    private double genRandomFactor() {
        return MIN_FACTOR + (Math.random() * (MAX_FACTOR - MIN_FACTOR));
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public double getCurrentLong() {
        return currentLong;
    }

    public double getCurrentEle() {
        return currentEle;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
                if (this.isMovingNorth) {
                    this.currentLat += this.latFactor;
                } else {
                    this.currentLat -= this.latFactor;
                }
                if (isMovingEast) {
                    this.currentLong += this.longFactor;
                } else {
                    this.currentLong -= this.longFactor;
                }
                if (genRandomBoolean()) {
                    this.currentEle += this.eleFactor;
                } else {
                    this.currentEle -= this.eleFactor;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
