package com.example.scotl.SQLite;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class DeleteDatabase
{
    @Test
    public void Main()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        appContext.deleteDatabase("SCoTLLocalDb");
    }
}
