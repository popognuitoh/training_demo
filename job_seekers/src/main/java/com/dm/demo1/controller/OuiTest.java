package com.dm.demo1.controller;


import java.net.Socket;

class OuiTest{

    private boolean requestSent = false;

    public static void main (String[] arg){

    }

    synchronized void clientConnexion() throws Exception {
        while (requestSent){
            wait();
        }
        Socket socket = new Socket("localhost",8800);
        requestSent=true;
        notify();
    }

    synchronized void serverConnexion() throws Exception {
        while (!requestSent){
            wait();
        }
        Socket socket = new Socket("localhost",8800);
        requestSent = false;
        notify();
    }
}

class ServerConnect implements Runnable{

    private OuiTest ouiTest;

    public ServerConnect(OuiTest ouiTest) {
        this.ouiTest = ouiTest;
    }

    @Override
    public void run() {

    }
}

class ClientConnect implements Runnable{

    @Override
    public void run() {

    }
}
