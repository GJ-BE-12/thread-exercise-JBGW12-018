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

import com.nhnacademy.count.SharedCounter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Slf4j
public class CounterIncreaseHandler implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(CounterIncreaseHandler.class);
    private final SharedCounter sharedCounter;

    public CounterIncreaseHandler(SharedCounter sharedCounter) {
        //TODO#2-1 sharedCounter를 초기화 합니다.  sharedCounter가 null 이면 IllegalArgumentException이 발생 합니다.
        if (sharedCounter == null) {
            throw new IllegalArgumentException("sharedCounter는 필수입니다.");
        }

        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        //TODO#2-2 현재 Thread의 interrupted이 ture <--  while의 종료조건 : interrupt가 발생 했다면 종료 합니다.
        //현재 thread에 인터럽트가 발생하지 않은 동안 반복
        while(!Thread.currentThread().isInterrupted()/* whlie 조건을 수정 하세요!*/) {
            try {
                Thread.sleep(1000);
                //TODO 2-3 sharedCounter의 count를 1증가 시키고 count값을 반환 합니다.
                long count = sharedCounter.increaseAndGet(); // threadA, B가 동시에 접근함 (같은 count값을 가질 수 있음 = race condition)

                log.debug("thread:{}, count:{}", Thread.currentThread().getName(), count); // 1초마다 증가시킨 count값 출력
            } catch (Exception e) {
                // main에서 인터럽트를 발생시키면 실행
                log.debug("{} - interrupt!", Thread.currentThread().getName());

                //TODO#2-4 현제 Thread에 interrupt()를 호출하여 interrput()를 발생 시킵 니다. 즉 현제 Thread의 interrupted 값이 -> true로 변경 됩니다. -> 즉 while 문을 종료하게 됩니다.
                Thread.currentThread().interrupt(); // 인터럽트 발생시킴 -> while문 종료
            }
        }
    }

}
