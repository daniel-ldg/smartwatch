/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartwatchsim.modules;

import java.time.Instant;

public class Trackpoint {
    
    private double latitude;
    private double longitude;
    private double elevation;
    private double speed;
    private double absoluteDistance;
    private double relativeDistance;
    private int heartRate;
    private Instant timestamp;

    public Trackpoint(double latitude, double longitude, double elevation, int heartRate) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.heartRate = heartRate;
        this.timestamp = Instant.now();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAbsoluteDistance() {
        return absoluteDistance;
    }

    public double getRelativeDistance() {
        return relativeDistance;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAbsoluteDistance(double absoluteDistance) {
        this.absoluteDistance = absoluteDistance;
    }

    public void setRelativeDistance(double relativeDistance) {
        this.relativeDistance = relativeDistance;
    }

}
