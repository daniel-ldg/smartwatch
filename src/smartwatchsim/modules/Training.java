package smartwatchsim.modules;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Training {

    private List<Trackpoint> trackpoints;

    public Training() {
        this.trackpoints = new ArrayList<>();
    }

    private Trackpoint getLastTrackpoint() {
        return this.trackpoints.get(this.trackpoints.size() - 1);
    }

    public Integer getAverageHeartRate() {
        double sum = 0.0;
        for (Trackpoint tp : this.trackpoints) {
            sum += tp.getHeartRate();
        }
        return Double.valueOf(sum / this.trackpoints.size()).intValue();
    }

    public void addTrackpoint(Trackpoint trackpoint) {
        if (this.trackpoints.isEmpty()) {
            trackpoint.setAbsoluteDistance(0);
            trackpoint.setRelativeDistance(0);
            trackpoint.setSpeed(0);
        } else {
            double relativeDistance = calcDistance(getLastTrackpoint(), trackpoint);
            double absoluteDistance = getLastTrackpoint().getAbsoluteDistance() + relativeDistance;

            trackpoint.setRelativeDistance(relativeDistance);
            trackpoint.setAbsoluteDistance(absoluteDistance);
            
            Duration timeBetween = Duration.between(getLastTrackpoint().getTimestamp(), Instant.now());
            double metersPerSecond = relativeDistance / timeBetween.getSeconds();
            double kmPerHour = metersPerSecond * 3.6;
            
            trackpoint.setSpeed(kmPerHour);
        }
        this.trackpoints.add(trackpoint);
    }

    public double getCurrentSpeed() {
        if (this.trackpoints.isEmpty()) {
            return 0;
        }
        ArrayList<Double> upToLastThreeRecords = new ArrayList<>();

        int size = this.trackpoints.size();
        for (int i = (size - 3); i < size; i++) {
            try {
                upToLastThreeRecords.add(this.trackpoints.get(i).getSpeed());
            } catch (IndexOutOfBoundsException e) {
                // expected
            }
        }
        
        double sum = 0.0;
        for (double value : upToLastThreeRecords) {
            sum += value;
        }
        
        return sum / upToLastThreeRecords.size();
    }

    public double getAverageSpeed() {
        double sum = 0.0;
        for (Trackpoint tp : this.trackpoints) {
            sum += tp.getSpeed();
        }
        return sum / this.trackpoints.size();
    }

    public double getTotalDistance() {
        return getLastTrackpoint().getAbsoluteDistance();
    }

    // taken from https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
    private double calcDistance(Trackpoint fromTrackpoint, Trackpoint toTrackpoint) {

        double latDistance = Math.toRadians(toTrackpoint.getLatitude() - fromTrackpoint.getLatitude());
        double lonDistance = Math.toRadians(toTrackpoint.getLongitude() - fromTrackpoint.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(fromTrackpoint.getLatitude()))
                * Math.cos(Math.toRadians(toTrackpoint.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        int r = 6371; // Radius of the earth
        double dist = r * c * 1000; // convert to meters
        double height = fromTrackpoint.getElevation() - toTrackpoint.getElevation();
        dist = Math.pow(dist, 2) + Math.pow(height, 2);

        return Math.sqrt(dist);
    }

}
