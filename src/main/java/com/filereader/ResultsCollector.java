package com.filereader;

public class ResultsCollector {

    int numberOfUser;
    int numberOfDeviceResolution;
    long totalSpend;
    String firstUserId;

    public int getNumberOfUser() {
        return numberOfUser;
    }

    public void setNumberOfUser(int numberOfUser) {
        this.numberOfUser = numberOfUser;
    }

    public int getNumberOfDeviceResolution() {
        return numberOfDeviceResolution;
    }

    public void setNumberOfDeviceResolution(int numberOfDeviceResolution) {
        this.numberOfDeviceResolution = numberOfDeviceResolution;
    }

    public long getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(long totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(String firstUserId) {
        this.firstUserId = firstUserId;
    }

    @Override
    public String toString() {
        return "ResultsCollector{" +
                "numberOfUser=" + numberOfUser +
                ", numberOfDeviceResolution=" + numberOfDeviceResolution +
                ", totalSpend=" + totalSpend +
                ", firstUserId='" + firstUserId + '\'' +
                '}';
    }
}
