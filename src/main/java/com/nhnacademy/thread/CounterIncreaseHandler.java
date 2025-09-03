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

import java.util.Objects;

@Slf4j
public class CounterIncreaseHandler implements Runnable {
    private final SharedCounter sharedCounter;

    public CounterIncreaseHandler(SharedCounter sharedCounter) {
        //sharedCounter를 초기화 합니다.  sharedCounter가 null 이면 IllegalArgumentException이 발생 합니다.
        if(Objects.isNull(sharedCounter)){
            throw new IllegalArgumentException(String.format("SharedCount is null"));
        }
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        //현제 Thread의 interrupted이 ture <--  while의 종료조건 : interrupt가 발생 했다면 종료 합니다.
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                //sharedCounter의 count를 1증가 시키고 count값을 반환 합니다.
                long count = sharedCounter.increaseAndGet();
                log.debug("thread:{}, count:{}", Thread.currentThread().getName(), count);
            } catch (Exception e) {
                log.debug("{} - interrupt!",Thread.currentThread().getName());
                //현제 Thread에 interrupt()를 호출하여 interrput()를 발생 시킵 니다. 즉 현제 Thread의 interrupted 값이 -> true로 변경 됩니다. -> 즉 while 문을 종료하게 됩니다.
                Thread.currentThread().interrupt();
            }
        }
    }

}
