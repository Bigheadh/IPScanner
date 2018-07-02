package com.zhjs.saas.job.test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class QueueTest implements Runnable{

    private DelayQueue<Customer> delayQueue = new DelayQueue();

    public void onCumputer(String name, Integer money) {
        Customer customer = new Customer(name, money);
        delayQueue.put(customer);
        System.out.println("姓名：" + name + "上机时间" + money);
    }

    public void outCumputer(Customer customer) {
        System.out.println("姓名：" + customer.getName() + "下机");
    }

    public static void main(String[] args) {
        QueueTest queueTest = new QueueTest();
        System.out.println("开始营业...");
        new Thread(queueTest).start();
        queueTest.onCumputer("张三", 3);
        queueTest.onCumputer("张五", 5);
        queueTest.onCumputer("张十一", 11);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Customer take = delayQueue.take();
                outCumputer(take);
            } catch (Exception e) {

            }
        }
    }

    class Customer implements Delayed {
        private String name;

        private long endTime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public Customer(String name, int money) {
            this.name = name;
            this.endTime = money * 1000 + System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return this.endTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Customer customer = (Customer)o;
            return this.endTime - customer.endTime > 0 ? 1 : (this.endTime - customer.endTime < 0 ? -1 : 0);
        }
    }
}
