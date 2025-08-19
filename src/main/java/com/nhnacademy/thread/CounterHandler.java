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
//TODO#1 Runnable interface을 implements(구현) 합니다.
public class CounterHandler implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(CounterHandler.class);
    private final long countMaxSize;

    private long count;

    public CounterHandler(long countMaxSize) {
        //TODO#2 countMaxSize <=0 이면 IllegalArgumentException()이 발생 합니다.
        if (countMaxSize <= 0) {
            throw new IllegalArgumentException("countMaxSize는 양의 정수여야 합니다.");
        }

        this.countMaxSize = countMaxSize;
        this.count=0L;
    }

    @Override
    public void run() {
        /* TODO#3 run method를 구현 합니다.
             - 1초에 한 번식 다음과 같이 출력 됩니다.
             - count 1 ~ 10 까지 출력 됩니다.
            ex) thread:my-thread,count:1 ....
         */

        do {
            count++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }

            log.debug("thread:{}, count:{}", Thread.currentThread().getName(), count);
        }while (count<countMaxSize);
    }
}
