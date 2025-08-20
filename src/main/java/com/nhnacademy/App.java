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

import javax.management.monitor.CounterMonitor;
import javax.management.monitor.Monitor;

@Slf4j
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    //TODO#1 monitor로 사용한 객체를 생성 합니다.
    public static Object monitor = new Object(); // 단순 Object 객체가 모니터 역할을 함

    public static void main( String[] args ) throws InterruptedException {

        //TODO#2 counterHandlerA 객체를 생성 합니다. countMaxSize : 10, monitor
        CounterHandler counterHandlerA = new CounterHandler(10, monitor); // 모니터 객체를 넘김

        //threadA 생성시 counterHandlerA 객체를 paramter로 전달 합니다.
        Thread threadA = new Thread(counterHandlerA);

        //threadA의 name을 'my-counter-A' 로 설정 합니다.
        threadA.setName("my-counter-A");
        log.debug("threadA-state:{}",threadA.getState()); // 상태 : New

        //threadA를 시작 합니다.
        threadA.start();
        log.debug("threadA-state:{}",threadA.getState()); // 상태 : RUNNABLE


        //TODO#3 - Main Thread에서 2초 후 monitor를 이용하여 대기하고 있는 threadA를 깨움 니다.
        Thread.sleep(2000); // 2초 후
        synchronized (monitor) { // 동기화 블럭 (monitor는 synchronized 블럭안에서 사용해야함)
            monitor.notify(); // monitor로 treadA를 깨움(notify) : WAIT -> RUNNABLE
        }


        //Main Thread가 threadA  종료될 때 까지 대기 합니다. Thread.yield를 사용 합니다.
        do {
            Thread.yield();
        }while (threadA.isAlive());

        //'Application exit!' message를 출력 합니다.
        log.debug("Application exit!");

    }

}