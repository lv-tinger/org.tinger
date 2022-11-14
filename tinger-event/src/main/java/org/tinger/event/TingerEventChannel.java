package org.tinger.event;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.InsufficientCapacityException;
import com.lmax.disruptor.TimeoutBlockingWaitStrategy;
import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.tinger.common.thread.NamedThreadFactory;
import org.tinger.core.event.Consumer;
import org.tinger.core.event.Event;
import org.tinger.core.event.EventChannel;
import org.tinger.core.event.Producer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tinger on 2022-10-04
 */
public class TingerEventChannel implements EventChannel {

    private Disruptor<EventWrapper> disruptor;

    private final int size;

    private final String name;

    private final AtomicInteger used = new AtomicInteger(0);

    private final List<Consumer> consumers = new ArrayList<>();

    private Producer producer;

    public TingerEventChannel(String name, int size) {
        this.name = name;
        this.size = size == 0 ? 1024 : size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getBusy() {
        return used.get();
    }

    @Override
    public int getIdle() {
        return this.size - this.used.get();
    }

    @Override
    public int percent() {
        return getIdle() * 100 / getSize();
    }

    @Override
    public void listen(Consumer consumer) {
        this.consumers.add(consumer);
    }

    @Override
    public void cancel(Consumer consumer) {
        this.consumers.remove(consumer);
    }

    @Override
    public void notice(Event event) {
        this.producer.produce(event);
    }


    public TingerEventChannel init() {
        EventFactory<EventWrapper> eventFactory = EventWrapper::new;
        ThreadFactory threadFactory = NamedThreadFactory.getInstance(this.getName());
        this.disruptor = new Disruptor<>(eventFactory, this.size, threadFactory, ProducerType.SINGLE, new TimeoutBlockingWaitStrategy(5, TimeUnit.SECONDS));

        this.producer = event -> {
            try {
                long next = disruptor.getRingBuffer().tryNext();
                used.incrementAndGet();
                EventWrapper wrapper = disruptor.getRingBuffer().get(next);
                wrapper.setEvent(event);
                disruptor.getRingBuffer().publish(next);
            } catch (InsufficientCapacityException e) {
                throw new RuntimeException(e);
            }
        };
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
            for (Consumer consumer : consumers) {
                if (consumer.accept(event.getEvent())) {
                    consumer.process(event.getEvent());
                }
            }
            used.decrementAndGet();
        });
        return this;
    }

    public TingerEventChannel start() {
        disruptor.start();
        return this;
    }

    public TingerEventChannel close() {
        try {
            disruptor.shutdown(5, TimeUnit.MINUTES);
        } catch (TimeoutException ignore) {

        }

        return this;
    }
}