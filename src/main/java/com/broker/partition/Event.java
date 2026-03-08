package com.broker.partition;

public class Event {

    private final String key;
    private final String value;
    private final long timestamp;
    private final int partition;
    private long offset; // set by PartitionManager later when we add offset tracking

    public Event(String key, String value, long timestamp, int partition) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.partition = partition;
    }

    public String getKey() { return key; }
    public String getValue() { return value; }
    public long getTimestamp() { return timestamp; }
    public int getPartition() { return partition; }
    public long getOffset() { return offset; }
    public void setOffset(long offset) { this.offset = offset; }
}