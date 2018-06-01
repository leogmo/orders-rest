package com.cjl;

import java.util.UUID;

import com.cjl.intf.UuidGenerator;

public class RandomUuidGenerator implements UuidGenerator {
    
    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}