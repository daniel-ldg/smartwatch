package smartwatchsim.gui;

import javax.swing.JLabel;

public class StopWatch extends Thread {

    private static final int MSECS_PER_SEC = 1000;
    private static final int SECS_PER_MIN = 60;
    private static final int MIN_PER_HR = 60;
    private static final String TIME_FORMAT = "%02d:%02d:%02d:%03d";
    private JLabel jLabel;
    private boolean isRunning;
    private long startTime;
    private long finalTime;

    public StopWatch(JLabel jLabel) {
        this.jLabel = jLabel;
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }
    
    public void stopStopWatch() {
        this.isRunning = false;
    }
    
    public boolean isRunning() {
        return this.isRunning;
    }
    
    public long getFinalTimeMillis() {
        return this.finalTime;
    }

    @Override
    public void run() {
        while (isRunning) {
            long currentTime = System.currentTimeMillis();
            int diffTime = (int) (currentTime - this.startTime);
            this.finalTime = diffTime;

            int mSecs = diffTime % MSECS_PER_SEC;
            diffTime /= MSECS_PER_SEC;

            int sec = diffTime % SECS_PER_MIN;
            diffTime /= SECS_PER_MIN;

            int min = diffTime % MIN_PER_HR;
            diffTime /= MIN_PER_HR;

            int hours = diffTime;
            
            this.jLabel.setText(String.format(TIME_FORMAT, hours, min, sec, mSecs));
            
            try {
                sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
