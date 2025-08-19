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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
//TODO#1 CounterThread는 Thread를 상속 합니다.
public class CounterThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(CounterThread.class);
    private final long countMaxSize;

    private long count;

    public CounterThread(String name, long countMaxSize) {
        //TODO#2 name <-- null 이거나 공백 문자열이면 IllegalArgumentException이 발생 합니다.
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("name은 필수입니다.");
        }
        //TODO#3 countMaxSize <=0 이면 IllegalArgumentException이 발생 합니다.
        if (countMaxSize <= 0) {
            throw new IllegalArgumentException("countMaxSize는 양의 정수여야 합니다.");
        }

        this.setName(name);
        this.countMaxSize = countMaxSize;
        this.count = 0;
    }

    @Override
    public void run() {

        /*TODO#4 run method를 구현 합니다.
            1초에 한 번식 다음과 같이 출력 됩니다.
            - Thread.sleep(1000)을 사용하세요.
            ex) thread:my-thread, count:1
            - count : 1~10 까지 출력 됩니다.
            - thread name : this.getName()<- 호출, CounterThread 가 Thread를 extends 하고 있음.
         */

        do {
            count++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            log.debug("thread:{}, count:{}", this.getName(), count);
        }while (count<countMaxSize);
    }
}
