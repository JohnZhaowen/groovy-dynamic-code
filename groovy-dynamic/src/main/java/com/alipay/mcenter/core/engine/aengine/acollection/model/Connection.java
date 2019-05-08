/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.acollection.model;

/**
 * 链接
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public class Connection {

    private String begin;

    private String end;

    private int    length = 0;

    public Connection(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    public Connection() {

    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
