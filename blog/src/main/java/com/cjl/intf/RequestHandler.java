package com.cjl.intf;

import java.util.Map;

import com.cjl.Answer;

public interface RequestHandler<V extends Validable> {

    Answer process(V value, Map<String, String> urlParams, boolean shouldReturnHtml);

}
