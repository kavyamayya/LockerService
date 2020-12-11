package com.locker.service;

import com.locker.model.Locker;
import com.locker.model.LockerType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kavya k on 10-Dec-2020
 */
public class LockerServiceImpl {
    public Map<String, List<Locker>> availableLockers = new HashMap<>();
    public Map<Integer, List<Locker>> userLocker = new HashMap<>();

    public LockerServiceImpl(){
        int id = 1;
        for(int i=0;i<3;i++){
            Locker locker = new Locker();
            locker.setLockerId(id);
            locker.setLockerType(LockerType.SMALL);
            locker.setStatus("FREE");
            id++;
            availableLockers.computeIfAbsent("small" ,s -> new ArrayList<>()).add(locker);
        }
        for(int i=0;i<3;i++){
            Locker locker = new Locker();
            locker.setLockerId(id);
            locker.setLockerType(LockerType.MEDIUM);
            locker.setStatus("FREE");
            id++;
            availableLockers.computeIfAbsent("medium" ,s -> new ArrayList<>()).add(locker);
        }
        for(int i=0;i<3;i++){
            Locker locker = new Locker();
            locker.setLockerId(id);
            locker.setLockerType(LockerType.LARGE);
            locker.setStatus("FREE");
            id++;
            availableLockers.computeIfAbsent("large" ,s -> new ArrayList<>()).add(locker);
        }
        System.out.println(availableLockers);
    }
    public void allocateLocker(Integer userId, LockerType lockerSize) {
        int size = availableLockers.get(lockerSize.toString().toLowerCase()).size();
        if (size == 0) {
            System.out.println("sorry, " + lockerSize.toString() + " sized locker is not available.");
        } else {
            Locker locker = availableLockers.get(lockerSize.toString().toLowerCase()).get(size - 1);
            availableLockers.get(lockerSize.toString().toLowerCase()).remove(locker);
            locker.setUserId(userId);
            locker.setStatus("OCCUPIED");
            userLocker.computeIfAbsent(userId, s -> new ArrayList<>()).add(locker);
        }
    }

    public void releaseLocker(Integer userId, Integer lockerId) {
        Locker locker = userLocker.get(userId).stream().filter(s -> s.getLockerId() == lockerId).findFirst().orElse(null);
        locker.setStatus("FREE");
        locker.setUserId(0);
        availableLockers.get(locker.getLockerType().toString().toLowerCase()).add(locker);
        userLocker.get(userId).remove(locker);
        if(userLocker.get(userId).size() == 0){
            userLocker.remove(userId);
        }
    }

    public void getLockerDetailsByUserId(Integer userId){
        if(!userLocker.containsKey(userId)){
            System.out.println("Sorry. You don't have lockers assigned");
            return;
        }
        System.out.println("your available lockers are ");
        displayLockerHeader();
        userLocker.get(userId).stream().forEach(s -> System.out.println(s));
    }
     private void displayLockerHeader(){
         System.out.println(" | locker id | locker type | status | user id |");
     }
    public void printLockerStatus() {
        System.out.println("Available lockers");
        displayLockerHeader();
        for(Map.Entry<String,List<Locker>> entry : availableLockers.entrySet()){
            int size = entry.getValue().size();
            for(int i=0;i<size;i++){
                System.out.println(entry.getValue().get(i));
            }
        }
    }
}
