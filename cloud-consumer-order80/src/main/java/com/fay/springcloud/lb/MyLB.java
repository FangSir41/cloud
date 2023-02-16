package com.fay.springcloud.lb;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger=new AtomicInteger(0);
    public int getAtomicInteger(){
        for (;;) {
            int current = atomicInteger.get();
            int next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if(atomicInteger.compareAndSet(current,next)){
                return current;
            }
        }
    }
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {
        int index=getAtomicInteger()%serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
