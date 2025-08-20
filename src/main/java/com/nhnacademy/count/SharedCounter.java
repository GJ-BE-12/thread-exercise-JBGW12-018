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

package com.nhnacademy.count;

import java.util.concurrent.Semaphore;

public class SharedCounter {
    private long count;
    private Semaphore semaphore;

    public SharedCounter(){
        count =0L;
    }

    public SharedCounter(long count) {
        if(count <0){
            throw new IllegalArgumentException("count > 0 ");
        }
        this.count = count;
        //TODO#1-1 semaphore를 생성 합니다.( 동시에 하나의 Thread만 접근할 수 있습니다. ), permits parameter를 확인하세요.
        semaphore = new Semaphore(2); // permits = 동시에 접근할 수 있는 thread 개수 = 1개
    }

    public long getCount(){
        /*TODO#1-2 count 를 반환 합니다.
            semaphore.acquire()를 호출하여 허가를 획득 합니다.
            쓰레드가 작업이 완료되면
            semaphore.release()를 호출하여
            허가를 반환 합니다.
         */
        try {
            semaphore.acquire(); // 허가 획득
            return count;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release(); // 허가 반납 (반드시 실행해야함, try에서 return해도 finally에서 release 실행시키고 종료)
        }
    }

    public long increaseAndGet(){
        /* TODO#1-3 count = count + 1 증가시키고 count를 반환 합니다.
           1-2 처럼 semaphore를 이용해서 동기화할 수 있도록 구현 합니다.
        */
        try {
            semaphore.acquire(); // 허가 획득
            count = count + 1;
            return count;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release(); // 허가 반납 (반드시 실행해야함)
        }
    }

    public long decreaseAndGet(){
        /*TODO#1-4 count = count-1 감소시키고 count를 반환 합니다.
          1-2 처럼 semaphore를 이용해서 동기화할 수 있도록 구현 합니다.
        */
        try {
            semaphore.acquire(); // 허가 획득
            count = count - 1;
            return count;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release(); // 허가 반납 (반드시 실행해야함)
        }
    }
}
