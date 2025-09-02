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
public class CounterHandler implements Runnable  {
    private final long countMaxSize;

    private long count;
    private Thread mainTread;

    public CounterHandler(long countMaxSize, Thread mainTread) {
        if(countMaxSize<=0){
            throw new IllegalArgumentException();
        }

        this.countMaxSize = countMaxSize;
        this.count=0L;

        this.mainTread = mainTread;
    }

    @Override
    public void run() {

        do {
            try {
                log.debug("Main thread:{} - state:{}", Thread.currentThread().getName(), mainTread.getState()); // Main thread -> WAITING
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            log.debug("thread:{},state:{},count:{}",Thread.currentThread().getName(), Thread.currentThread().getState(), count);
        }while (count<countMaxSize);
    }
}
