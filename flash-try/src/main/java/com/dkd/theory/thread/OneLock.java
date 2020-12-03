package com.dkd.theory.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用分段锁
 * @author dkd
 */
@Slf4j
public class OneLock {
    private final byte[] lock = new byte[0];

    public void test() {
        log.info("will enter synchronized");
        synchronized (lock) {
            log.info("entered synchronized and sleep 2000ms");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.error("thread sleep exception.", e);
            }
        }
        log.info("exit synchronized");
    }

    public static void main(String[] args) {
        final OneLock oneLock = new OneLock();
        new Thread(() -> {
            oneLock.test();
        }).start();

        new Thread(() -> oneLock.test()).start();
    }
}
