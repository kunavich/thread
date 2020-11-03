package by.kunavich.task_tread.model;

import by.kunavich.task_tread.Main;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base {
    private List<Thread> terminals = new ArrayList<>();
    private Logger logger = LogManager.getLogger(Main.class);
    private Deque<Van> vanQueue = new LinkedList<>();
    private Lock queLock = new ReentrantLock();
    private static Lock terminalsLock = new ReentrantLock();
    private static Lock instanceLock = new ReentrantLock();
    private final int TERMINALS = 3;

    private Semaphore mutex = new Semaphore(TERMINALS);

    private Thread relise;
    private static Base instance;

    private Base() {
    }

    public void relise() {

        relise = new Thread(() -> {
            Base base = getInstance();
            while (!base.vanQueue.isEmpty()) {
                terminalsLock.lock();
                for (int i = 0; i < terminals.size(); i++) {
                    Thread thread = terminals.get(i);
                    if (!thread.isAlive()) {
                        terminals.remove(thread);
                        logger.info("    thread " + thread.getId() + " removed");
                        System.out.println("    thread " + thread.getId() + " removed");
                        mutex.release();
                    }
                }
                terminalsLock.unlock();
            }
        });
        relise.start();
    }

    public static Base getInstance() {

        if (instance == null) {
            instanceLock.lock();
            instance = new Base();
            instanceLock.unlock();
        }


        return instance;
    }


    public void addVan(Van van) {
        queLock.lock();
        if (van.isPerishableGoods()) {
            vanQueue.addFirst(van);
        } else {
            vanQueue.add(van);
        }
        queLock.unlock();
    }


    public void start() {
        relise();
        try {
            while (!vanQueue.isEmpty()) {
                Terminal terminal = new Terminal();
                queLock.lock();
                terminal.setCurrentVan(vanQueue.poll());
                queLock.unlock();
                Thread thread = new Thread(terminal);

                mutex.acquire();
                thread.start();
                logger.info("thread " + thread.getId() + " started");
                System.out.println("thread " + thread.getId() + " started");
                terminalsLock.lock();
                terminals.add(thread);
                terminalsLock.unlock();
            }

        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        } finally {
            mutex.release();
        }
        try {
            relise.join();
            for (Thread thread : terminals) {
                //thread.

                thread.join();

            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        }
    }

}
