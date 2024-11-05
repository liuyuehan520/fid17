package com.fdi17.common.datasource.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomIdGenerator implements IdentifierGenerator {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final SecureRandom sr = new SecureRandom();

    @Override
    public Number nextId(Object entity) {
        return getTwelveId();
    }

    @Override
    public String nextUUID(Object entity) {
        return String.valueOf(getTwelveId());
    }

    public long getTwelveId() {
        String randomPart = String.format("%04d", 1000 + sr.nextInt(9000));
        String timePart = String.format("%04d", System.currentTimeMillis() % 10000);
        String counterPart =  String.format("%04d", counter.getAndIncrement());
        if (counter.get() >= 10000) {
            counter.set(0);
        }
        return Long.parseLong(randomPart + timePart + counterPart);
    }

    private enum Singleton {
        INSTANCE;

        final CustomIdGenerator generator;

        Singleton() {
            generator = new CustomIdGenerator();
        }
    }

    public static CustomIdGenerator getInstance() {
        return Singleton.INSTANCE.generator;
    }

}
