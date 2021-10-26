package org.openwatchproject.openwatchfaceview;

public interface DataRepository {
    int getWeatherIcon();
    int getSteps();
    int getTargetSteps();
    int getDistance();
    int getTargetDistance();
    int getKCal();
    int getTargetKCal();
    int getWeatherTemp();
    int getHeartRate();
    boolean isBatteryCharging();
    int getBatteryPercentage();
}
