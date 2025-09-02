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

package com.nhnacademy.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CounterHandler implements Runnable  {
    private final long countMaxSize;

    private long count;
    private Thread mainThread;

    public CounterHandler(long countMaxSize, Thread mainThread) {
        if(countMaxSize<=0){
            throw new IllegalArgumentException();
        }

        this.countMaxSize = countMaxSize;
        this.count=0L;

        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            log.debug("thread:{},state:{},count:{}",Thread.currentThread().getName(),Thread.currentThread().getState(),count);

            //TODO#2 Thread.yield()를 사용해서 수행되고 있는 작업을 다른 Thread에게 양보 하세요.
            Thread.yield(); // threadA, B가 번갈아가며 실행될 가능성이 올라감
            // 1. threadA or B(주체, 현재 실행되고 있는 스레드)가 ->
            // 2. 양보(yield, 동사)함 ->
            // 3. 다른 스레드(객체, 대상)에게..
            //      ㄴ 현재 실행중인 스레드가 A일 때 -> B, main 스레드한테 양보
            //      ㄴ 현재 실행중인 스레드가 B일 때 -> A, main 스레드한테 양보

        }while (count<countMaxSize);
    }
}
