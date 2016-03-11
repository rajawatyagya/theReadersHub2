package com.ideas.business;

public enum Membership {
    IVORY(3,7,20,1000),
    SILVER(4,10,30,2000),
    GOLD(5,15,40,3000),
    PLATINUM(6,20,50,5000);
    private int booksAllowedAtATime;
    private int periodAllowed;
    private int chargesForExtraDay;
    private int packageCost;
    Membership(int booksAllowedAtATime,int periodAllowed,int chargesForExtraDay,int packageCost){
        this.setBooksAllowedAtATime(booksAllowedAtATime);
        this.setChargesForExtraDay(chargesForExtraDay);
        this.setPackageCost(packageCost);
        this.setPeriodAllowed(periodAllowed);
    }
    public int getBooksAllowedAtATime() {
        return booksAllowedAtATime;
    }
    public void setBooksAllowedAtATime(int booksAllowedAtATime) {
        this.booksAllowedAtATime = booksAllowedAtATime;
    }
    public int getPeriodAllowed() {
        return periodAllowed;
    }
    public void setPeriodAllowed(int periodAllowed) {
        this.periodAllowed = periodAllowed;
    }
    public int getChargesForExtraDay() {
        return chargesForExtraDay;
    }
    public void setChargesForExtraDay(int chargesForExtraDay) {
        this.chargesForExtraDay = chargesForExtraDay;
    }
    public int getPackageCost() {
        return packageCost;
    }
    public void setPackageCost(int packageCost) {
        this.packageCost = packageCost;
    }
}
