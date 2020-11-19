package com.dm.demo1.controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

class TreadTest {
    private int number ;
    private boolean isSetup = false;

    synchronized void setNumber(int number) throws InterruptedException {
        while (isSetup){
            wait();
        }
        this.number = number;
        System.out.println("setNumber:"+ number);
        isSetup=true;
        notify();
    }

    synchronized void getNumber() throws InterruptedException {
        while (!isSetup){
            wait();
        }
        System.out.println("getNumber:" + number);
        isSetup = false;
        notify();
    }
}

class PutInThread extends Thread {
    TreadTest treadTest;
    int i;
    public PutInThread(TreadTest treadTest){
        this.treadTest=treadTest;
        Thread t2 = new Thread(this,"setThread");
        t2.start();
    }
    public void run(){
        while (true){
            try {
                treadTest.setNumber(i++);
                Thread.sleep(3000);
            } catch (Exception e) { }
        }
    }
}

class ReadThread extends Thread {
    TreadTest treadTest;
    public ReadThread(TreadTest treadTest){
        this.treadTest=treadTest;
        Thread t1 = new Thread(this,"readThread");
        t1.start();
    }
    public void run(){
        while (true){
            try { treadTest.getNumber();
                Thread.sleep(3000);
            } catch (Exception e) { }
        }
    }
}


class Thread1 extends Thread{
    int i =0;
    boolean check=false;
    int[] nums = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};

    synchronized void action1() throws InterruptedException {
        while ( i<nums.length/2){
            while (check){
                wait();
            }
            int finalI = i;
            Thread t1 = new Thread(()->{
                System.out.println("getFirstMultiply: "+  nums[finalI]*2 );
            },"monThread1");
            t1.start();
            check = true;
            i++;
            notify();
        }
    }

    synchronized void action2() throws InterruptedException {
//        while (!check){
//            wait();
//        }
//        Thread t2 = new Thread(()->{
//            for (int i =nums.length/2; i<nums.length ; i++){
//                System.out.println("getSecondMultiply: "+  nums[i]*2 );
//            }
//        },"monThread2");
//        t2.start();
//        check=false;
//        Thread.sleep(2000);
        while (i>=nums.length/2){
            while (!check){
                wait();
            }
            int finalI = i;
            Thread t2 = new Thread(()->{
                System.out.println("getSecondMultiply: "+  nums[finalI]*2 );
            },"monThread2");
            t2.start();
            check = false;
            i++;
            notify();
        }
    }
}


class MonWork1 implements Callable<String>{

    @Override
    public String call(){

        return "MonWork 1: je suis la";
    }
}

class MonWork2 implements Callable<String>{

    @Override
    public String call(){

        return "MonWork 2: je suis la";
    }
}

class PoolTest implements Runnable{

    @Override
    public void run() {
        System.out.println("first Task completed : " + Thread.currentThread().getName());
    }
}


class PoolTest2 implements Runnable{

    @Override
    public void run() {
        System.out.println("second Task completed : " + Thread.currentThread().getName());
    }
}

class InterThread {
    public static void main(String[] args) throws InterruptedException {
//        TreadTest treadTest = new TreadTest();
//        new PutInThread(treadTest);
//        new ReadThread(treadTest);
//        MultiplyTest multiplyTest = new MultiplyTest();
//        Thread1 thread1 = new Thread1();
//        thread1.action1();
//        thread1.action2();



//        Callable<String> monWork1= new MonWork1();
//        Callable<String> monWork2= new MonWork2();
//        FutureTask<String> oneTask1 = new FutureTask<>(monWork1);
//        FutureTask<String> oneTask2 = new FutureTask<>(monWork2);
//        Thread thread1 = new Thread(oneTask1);
//        Thread thread2 = new Thread(oneTask2);
//        thread1.start();
//        thread2.start();



//        ExecutorService executorService1 = Executors.newFixedThreadPool(5);
//        for (int i =0 ; i< 10 ; i++){
//            PoolTest thread = new PoolTest();
//            executorService1.execute(thread);
//            Thread.sleep(1000);
//        }
//        executorService1.shutdown();

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        for (int i =0 ; i< 1000 ; i++){
            PoolTest thread = new PoolTest();
            PoolTest2 thread2 = new PoolTest2();
            executorService2.execute(thread);
            executorService2.execute(thread2);
////            Thread.sleep(1000);
        }
        executorService2.shutdown();
        Thread.sleep(5000);
        System.out.println("actives thread after shutdown=========================: " + Thread.activeCount());




//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("==========");
//            }
//        },500,1000);


//        int num = Runtime.getRuntime().availableProcessors();
//        System.out.println("==========" +num);
    }


}