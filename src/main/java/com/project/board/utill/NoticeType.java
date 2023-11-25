package com.project.board.utill;

public enum NoticeType {
    NOTICE(1,"notice"),
    UPDATE(2, "update"),
    REPORT(3, "report result"),
    NEWS(4,"other broadcast");

    private NoticeType(final int type, final String name){
        this.type = type;
        this.name = name;
    }
    private final int type;
    private final String name;

    public int getType(){
        return type;
    }
    public String getName(){
        return name;
    }
}
