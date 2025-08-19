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
        //TODO#1 counterHandlerA 객체를 생성 합니다. countMaxSize : 10
        CounterHandler counterHandlerA = new CounterHandler(10);
        //TODO#2 threadA 생성시 counterHandlerA 객체를 paramter로 전달 합니다.
        Thread threadA = new Thread(counterHandlerA);
        //TODO#3 threadA의 name을 'my-counter-A' 로 설정 합니다.
        threadA.setName("my-counter-A");
        log.debug("threadA-state:{}",threadA.getState());


        //TODO#4 counterHandlerB 객체를 생성 합니다. countMaxSize : 10
        CounterHandler counterHandlerB = new CounterHandler(10);
        //TODO#5 threadB 생성시 counterHandlerB 객체를 paramter로 전달 합니다.
        Thread threadB = new Thread(counterHandlerB);
        //TODO#6 threadB의 name을 'my-counter-B' 로 설정 합니다.
        threadB.setName("my-counter-B");
        log.debug("threadB-state:{}",threadB.getState());

        //TODO#7 threadA를 시작 합니다.
        threadA.start(); // A 시작

        //TODO#8 threadA 작업이 완료될 때까지 main Thread는 대기 합니다.
        threadA.join(); // main thread 대기 (A 종료때까지)
        log.debug("threadA-state:{}",threadA.getState()); // A 상태 출력

        //TODO#9 threadB를 시작 합니다.
        threadB.start(); // B 시작
        //TODO#10 threadB 작업이 완료될 때까지 main Thread는 대기 합니다.
        threadB.join(); // main thread 대기 (B 종료때까지)
        log.debug("threadB-state:{}",threadB.getState()); // B 상태 출력

        //TODO#11 'Application exit!' message를 출력 합니다.
        log.debug("Application exit!");
    }
}