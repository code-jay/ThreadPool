package com.jay;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
    private int id;
    public Processor(int id){
        this.id=id;
    }

    public void run(){
        System.out.println("Starting: "+id);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        System.out.println("Completing: "+id);
    }
}

public class Main {

    public static void main(String[] args) {
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(coreCount);

        for (int i=0;i<5;i++){
            executor.submit(new Processor(i));
        }

        executor.shutdown();

        System.out.println("All tasks submitted");
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        }catch (InterruptedException e){}
        System.out.println("All tasks completed");
    }
}
