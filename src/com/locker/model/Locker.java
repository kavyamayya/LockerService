package com.locker.model;

/**
 * @author kavya k on 10-Dec-2020
 */
public class Locker {
    private int lockerId;
    private LockerType lockerType;
    private int userId;
    private String status;

    public int getLockerId() {
        return lockerId;
    }

    public void setLockerId(int lockerId) {
        this.lockerId = lockerId;
    }

    public LockerType getLockerType() {
        return lockerType;
    }

    public void setLockerType(LockerType lockerType) {
        this.lockerType = lockerType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return " | "+ lockerId + " | "+ lockerType +" | "+ status +" | "+ userId+" | ";
    }
}
