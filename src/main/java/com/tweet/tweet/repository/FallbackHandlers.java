package com.tweet.tweet.repository;

import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FallbackHandlers {

    public static class FindFollowedByFallbackHandler implements FallbackHandler<List<String>> {

        @Override
        public Observable<List<String>> getFallback(HystrixInvokableInfo<?> hystrixInfo, Map<String, Object> requestProperties) {
            return Observable.from(new ArrayList<>());
        }
    }

}
