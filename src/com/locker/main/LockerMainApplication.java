package com.locker.main;

import com.locker.model.LockerType;
import com.locker.service.LockerServiceImpl;

import java.util.Scanner;

/**
 * @author kavya k on 10-Dec-2020
 */
public class LockerMainApplication {
    private static LockerServiceImpl lockerService = new LockerServiceImpl();

    public static void main(String[] args){
        System.out.println("Press");
        System.out.println("1 for requesting locker.");
        System.out.println("2 for collecting from locker.");
        System.out.println("3 Display your locker status");
        System.out.println("4 for displaying current status of locker.");
        System.out.println("any other key for exit");
        Scanner scanner = new Scanner(System.in);
        Integer req = scanner.nextInt();
        while(req == 1 || req == 2 || req == 3 || req == 4){
            if(req == 1){
                System.out.println("Enter user id and locker size in the format userId:: locker size(small , medium or large)");
                String details = scanner.next();
                System.out.println(details);
                String[] userDetails = details.split("::");
                Integer userId = Integer.parseInt(userDetails[0]);
                String lockerSize = userDetails[1];
                System.out.println(lockerSize);
                lockerService.allocateLocker(userId, LockerType.valueOf(lockerSize.toUpperCase()));
            }else if(req == 2){
                System.out.println("Enter user id");
                Integer userId = scanner.nextInt();
                lockerService.getLockerDetailsByUserId(userId);
                System.out.println("select a locker to release");
                int lockerId = scanner.nextInt();
                lockerService.releaseLocker(userId,lockerId);
            }else if(req == 3){
                System.out.println("Enter user id");
                Integer userId = scanner.nextInt();
                lockerService.getLockerDetailsByUserId(userId);
            }else if(req == 4){
                lockerService.printLockerStatus();
            }
            System.out.println("1 for requesting locker.");
            System.out.println("2 for collecting from locker.");
            System.out.println("3 for displaying current status of locker.");
            System.out.println("any other key for exit");
            req = scanner.nextInt();
        }
        scanner.close();
    }
}
