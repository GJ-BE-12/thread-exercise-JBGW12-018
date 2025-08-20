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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args ) throws InterruptedException {
        //counterHandlerA 객체를 생성 합니다. countMaxSize : 10
        CounterHandler counterHandlerA = new CounterHandler(10L);
        //threadA 생성시 counterHandlerA 객체를 paramter로 전달 합니다.
        Thread threadA = new Thread(counterHandlerA);
        //threadA의 name을 'my-counter-A' 로 설정 합니다.
        threadA.setName("my-counter-A");
        log.debug("threadA-state:{}",threadA.getState()); // A 상태: New

        //counterHandlerB 객체를 생성 합니다. countMaxSize : 10
        CounterHandler counterHandlerB = new CounterHandler(10L);
        //threadB 생성시 counterHandlerB 객체를 paramter로 전달 합니다.
        Thread threadB = new Thread(counterHandlerB);
        //threadB의 name을 'my-counter-B' 로 설정 합니다.
        threadB.setName("my-counter-B");
        log.debug("threadB-state:{}",threadB.getState()); // B 상태: New

        //threadA를 시작 합니다.
        threadA.start();
        log.debug("threadA-state:{}",threadA.getState()); // A 상태: RUNNABLE

        //threadB를 시작 합니다.
        threadB.start();
        log.debug("threadB-state:{}",threadB.getState()); // B 상태: RUNNABLE

        //TODO#1 - main Thread 에서 3초 후  threadA에 interrupt 예외를 발생 시킴 니다.
        Thread.sleep(3000);
        threadA.interrupt(); // threadA 인터럽트 발생! -> threadA는 카운트(반복문) 종료
        //TODO#3 Main Thread가 threadA, ThreadB가 종료될 때 까지 대기 합니다. Thread.yield를 사용 합니다.
        while (threadA.isAlive() || threadB.isAlive()) {
            Thread.yield(); // threadA, B가 살아있는 동안 Main Thread 대기
        }

        //threadA, threadB 상태를 출력 합니다.
        log.debug("threadA-status:{}",threadA.getState()); // A 상태: TERMINATED
        log.debug("threadB-status:{}",threadB.getState()); // B 상태: TERMINATED

        //main thread 종료, 'Application exit!' message를 출력 합니다.
        log.debug("Application exit!");
    }
}