package com.dm.demo1.controller;

public class CompanyMeetingThread {

    private volatile int totalEmployers;

    private volatile int employersNumber;

    private CompanyMeetingThread(int totalEmployers) throws InterruptedException {
        this.totalEmployers = totalEmployers;
        Thread thread1 = new Thread(() -> {
            while (getTotalEmployers() > 0) {
                getTicket2();
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println("le ticket is Out!!!!!");
        }, "BackDoorThread");

        Thread thread2 = new Thread(() -> {
            while (getTotalEmployers() > 0) {
                getTicket1();
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println("le ticket is Out!!!!!");
        }, "FrontDoorThread");

        Thread thread3 = new Thread(() -> {
            while (getTotalEmployers() > 0) {
                getTicket3();
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println("le ticket is Out!!!!!");
        }, "MiddleThread");

        long startTime=System.currentTimeMillis();

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("程序运行时间： "+(System.currentTimeMillis()-startTime)+"ms");
    }

    private int getTotalEmployers() {
        return totalEmployers;
    }

    private synchronized void getTicket1(){
//        if (totalEmployers<=0){
//            System.out.println("le ticket is Out!!!!! from me");
//            return;
//        }
        totalEmployers --;
        employersNumber ++;
        System.out.println("Employee Number" + employersNumber +": admission from the " +Thread.currentThread().getName() + " il en reste" + totalEmployers + "Employee");
    }

    private synchronized void getTicket2(){
//        if (totalEmployers<=0){
//            System.out.println("le ticket is Out!!!!! from me");
//            return;
//        }
        totalEmployers --;
        employersNumber ++;
        System.out.println("Employee Number" + employersNumber +": admission from the " +Thread.currentThread().getName() + " il en reste" + totalEmployers + "Employee");
    }

    private synchronized void getTicket3(){
//        if (totalEmployers<=0){
//            System.out.println("le ticket is Out!!!!! from me");
//            return;
//        }
        totalEmployers --;
        employersNumber ++;
        System.out.println("Employee Number" + employersNumber +": admission from the " +Thread.currentThread().getName() + " il en reste" + totalEmployers + "Employee");
    }

    public static void main(String[] arg) throws InterruptedException {
        new CompanyMeetingThread(1000000);
//        new BackDoorThread(companyMeetingThread).start();
//        new FrontDoorThread(companyMeetingThread).start();
    }

}

//class BackDoorThread extends Thread {
//
//    private CompanyMeetingThread companyMeetingThread;
//
//    public BackDoorThread(CompanyMeetingThread companyMeetingThread) {
//        this.companyMeetingThread = companyMeetingThread;
//        this.setName("Back Door");
//    }
//
//    @Override
//    public void run() {
//        while (companyMeetingThread.getTotalEmployers()>0){
//            companyMeetingThread.getTicket();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("le ticket is Out!!!!!");
//    }
//}
//
//class FrontDoorThread extends Thread{
//
//    private CompanyMeetingThread companyMeetingThread;
//
//    public FrontDoorThread(CompanyMeetingThread companyMeetingThread) {
//        this.companyMeetingThread = companyMeetingThread;
//        this.setName("Front Door");
//    }
//
//    @Override
//    public void run() {
//        while (companyMeetingThread.getTotalEmployers()>0){
//            companyMeetingThread.getTicket();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("le ticket is Out!!!!!");
//    }
//}
