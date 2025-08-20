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

public class SharedCounter {
    private long count;

    public SharedCounter(){
        this(0l);
    }

    public SharedCounter(long count) {
        //TODO#1-1 생성자를 초기화 합니다. count < 0 IllegalArgumentException아 발생 합니다.
        if (count < 0) {
            throw new IllegalArgumentException("count는 양의 정수여야 합니다.");
        }

        this.count = count;
    }

    // synchronized : multi thread 환경 -> 동기화 구현에 사용
    //      - 동기화 : 하나의 공유 자원에 동시에 접근하지 못하도록 막아주는 개념

    // semaphore : 허용된 개수만큼 (소유권 X, 다른 스레드도 release 가능)
    // mutex : 한번에 하나만 (소유권 O, lock설정한 스레드만 unlock 가능)
    // synchronized : 한번에 하나만(특정 코드나, 블럭) (소유권 O)

    //TODO#1-2 mehtod 단위 lock을 걸고, count 를 반환 합니다.
    public synchronized/*synchronized 메서드 : 메서드 전체를 동기화*/ long getCount(){
        return count;
    }

    public long increaseAndGet(){
        //TODO#1-3 block 단위로 lock을 걸고 count = count + 1 증가시키고 count를 반환 합니다.
        /*synchronized 블럭 : 필요한 부분만 보호*/
        synchronized (this) {
            count = count + 1;
            return count;
        }
    }

    public long decreaseAndGet(){
        //TODO#1-4 count = count -1  부분 lock을 걸고, count를 반환 합니다.
        /*synchronized 블럭*/
        synchronized (this) {
            count = count - 1;
        }

        return count;
    }
}
