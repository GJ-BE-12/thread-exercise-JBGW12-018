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
public class Counter {
    private final long countMaxSize;
    private long count;

    public Counter(long countMaxSize) {
        //TODO#1 countMaxSize < 0 작다면 IllegalArgumentException 예외가 발생 합니다.
        if (countMaxSize < 0) {
            throw new IllegalArgumentException("countMaxSize는 0 이상이어야 합니다.");
        }
        //TODO#2 this.countMaxSize 초기화 합니다.
        this.countMaxSize = countMaxSize;
        //TODO#3 this.count 값을 0으로 초기화 합니다.
        this.count = 0;
    }

    public void run() throws InterruptedException {

        do {

            /*TODO#4 1초 간격 으로 count++ 됩니다.
              Thread.sleep method를 사용하세요.
              https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Thread.html#sleep(java.time.Duration)
            */
            Thread.sleep(1000);

            count++;

            /*TODO#5 count 출력
                name:{Thread name}, count:{count 변수}
                Thread name : Thread.currentThread().getName();
                ex) name:my-thread, count:1
             */
            System.out.println("name: " + Thread.currentThread().getName() +
                    ", count: " + count);

        }while (count<countMaxSize);
    }
}
