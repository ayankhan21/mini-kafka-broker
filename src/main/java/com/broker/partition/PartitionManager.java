package com.broker.partition;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class PartitionManager {

    private final ConcurrentHashMap<Integer, BlockingQueue<Event>> partitions;
    private final ConcurrentHashMap<Integer, AtomicLong> offsets;
    private final int partitionCount;

    public PartitionManager(int partitionCount) {
        this.partitionCount = partitionCount;
        this.partitions = new ConcurrentHashMap<>();
        this.offsets = new ConcurrentHashMap<>();

        for (int i = 0; i < partitionCount; i++) {
            partitions.put(i, new LinkedBlockingQueue<>());
            offsets.put(i, new AtomicLong(0));
        }

        System.out.println("PartitionManager initialized with " + partitionCount + " partitions");
    }

    public void assign(String key, String value, long timestamp) {
        int partitionId = Math.abs(key.hashCode() % partitionCount);
        BlockingQueue<Event> queue = partitions.get(partitionId);

        long offset = offsets.get(partitionId).getAndIncrement();

        Event event = new Event(key, value, timestamp, partitionId);
        event.setOffset(offset);

        queue.offer(event);
    }

    public Event poll(int partitionId) throws InterruptedException {
        return partitions.get(partitionId).take();
    }

    public int getPartitionCount() {
        return partitionCount;
    }
}