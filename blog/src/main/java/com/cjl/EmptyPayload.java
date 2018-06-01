package com.cjl;

import com.cjl.intf.Validable;

public class EmptyPayload implements Validable {
    @Override
    public boolean isValid() {
        return true;
    }
}
