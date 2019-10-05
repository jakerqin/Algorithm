package test;

public class Student {

    public void testBlockedState() throws Exception{
        class Toilet{
            public void pee() {
                try {
                    Thread.sleep(21000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        Toilet t1 = new Toilet();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (t1){
                    t1.pee();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (t1){
                    t1.pee();
                }
            }
        });

        thread1.start();

        Thread.sleep(200);
        thread2.start();
        Thread.sleep(200);
        System.out.println(thread2.getState());



    }


    public static void main(String[] args) throws Exception {
        Student s = new Student();
        s.testBlockedState();

    }


}