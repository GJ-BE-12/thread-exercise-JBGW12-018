package com.nhnacademy.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertDaemon extends Thread {

    private static final Logger log = LoggerFactory.getLogger(AlertDaemon.class);

    public AlertDaemon() {
        //TODO#1 - setDaemon() 메서드를 이용해서 daemon thread로 설정
        setDaemon(true);
        //TODO#2 - Thread 이름을 alert-daemon으로 설정
        setName("alert-daemon");

        // ShutdownHook: JVM이 종료되기 직전에 실행되는 Thread
        // 프로그램의 정상/비정상 종료 시 필요한 리소스 정리나 로그 기록 등을 처리하며,
        // 예기치 않은 종료 상황에서도 안전한 종료를 보장합니다.
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    //TODO#3 AlertDaemon Thread가 종료 시점에 적절한 메시지를 출력합니다.
                    log.debug("name:{} 안전한 종료", currentThread().getName()); // 데몬 스레드 -> 일반 스레드가 모두 종료되면 종료
                })
        );
    }

    @Override
    public void run() {
        //TODO#4 1초에 한 번씩 Alert Daemon message를 출력 합니다.
        for (int i=0; ; i++) { // 1초에 한번씩 (횟수 제한 x)
            try {
                Thread.sleep(990); // AlertDaemon -> Counter 순서로 출력하기위해..
                log.debug("name:{}", currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
