package com.broker.partition;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class PartitionManager {

    // Each partition has its own BlockingQueue
    // Key: partitionId (0, 1, 2...), Value: the queue for that partition
    private final ConcurrentHashMap<Integer, BlockingQueue<Event>> partitions;

    private final int partitionCount;

    public PartitionManager(int partitionCount) {
        this.partitionCount = partitionCount;
        this.partitions = new ConcurrentHashMap<>();

        // Initialize N partitions on startup
        for (int i = 0; i < partitionCount; i++) {
            partitions.put(i, new LinkedBlockingQueue<>());
        }

        System.out.println("PartitionManager initialized with " + partitionCount + " partitions");
    }

    // Called by ProducerServiceImpl when an event arrives
    // Assigns event to partition via hash(key) % partitionCount
    public void assign(String key, String value, long timestamp) {
        int partitionId = Math.abs(key.hashCode() % partitionCount);
        BlockingQueue<Event> queue = partitions.get(partitionId);
        Event event = new Event(key, value, timestamp, partitionId);
        queue.offer(event);
//        System.out.println("Event assigned to partition " + partitionId + " | key: " + key); // add this
    }

    // Called by ConsumerServiceImpl to pull next event for a given partition
    // Blocks until an event is available
    public Event poll(int partitionId) throws InterruptedException {
        return partitions.get(partitionId).take(); // blocking
    }

    public int getPartitionCount() {
        return partitionCount;
    }
}
