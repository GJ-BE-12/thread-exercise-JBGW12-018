/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy;

import com.nhnacademy.thread.CounterHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args ) {
        log.debug("Main thread:{} - state:{}", Thread.currentThread().getName(), Thread.currentThread().getState()); // Main thread -> RUNNABLE

        CounterHandler counterHandler = new CounterHandler(10L, Thread.currentThread()); // main thread도 넘겨줌(대기 상태 출력하려고...)
        Thread thread = new Thread(counterHandler); // new Thread(..)
        log.debug("thread-state:{}",thread.getState()); // -> NEW 상태 (my-counter thread)
        thread.setName("my-counter");
        thread.start(); // start() -> RUNNABLE 상태 (my-counter thread)

        //TODO#1 thread가 실행 후 (1-10 count 증가 후  아래 로그가 출력 됩니다.)
        //thread.join()을 호출 하면 thread가 종료될 때 까지 main thread가 대기하게 됩니다.
        try {
            thread.join(); // main 스레드 대기 -> WAITING (thread 종료할 때까지)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } // thread 종료 시,

        log.debug("Application exit!");
        log.debug("Main thread:{} - state:{}", Thread.currentThread().getName(), Thread.currentThread().getState()); // Main thread -> RUNNABLE
        log.debug("thread-state:{}",thread.getState()); // -> TERMINATED 상태 (my-counter thread)
    } // Main thread -> TERMINATED
}