package com.scienjus.smartqq.kancolle;

import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.client.SmartQQClient;
import org.junit.Assert;

import static org.junit.Assert.*;

public class RereaderTest {

    @org.junit.Test
    public void reread() {
        Rereader re = new Rereader();
        Assert.assertFalse(re.reread("复读"));
        Assert.assertFalse(re.reread("复读"));
        Assert.assertTrue(re.reread("复读"));
        Assert.assertFalse(re.reread("复读"));
        Assert.assertFalse(re.reread("复读"));
        Assert.assertFalse(re.reread("复读2"));
        Assert.assertFalse(re.reread("复读2"));
        Assert.assertFalse(re.reread("复读3"));
        Assert.assertTrue(re.reread("复读2"));
        Assert.assertFalse(re.reread("复读2"));
        Assert.assertFalse(re.reread("复读3"));
        Assert.assertFalse(re.reread("复读"));

    }
}