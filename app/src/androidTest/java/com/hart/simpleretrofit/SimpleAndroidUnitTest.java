package com.hart.simpleretrofit;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.hart.simpleretrofit.dto.Result;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

/**
 * Created by Esteban on 4/15/16.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleAndroidUnitTest extends ActivityInstrumentationTestCase2<MainActivity>{

    Activity mActivity;

    public SimpleAndroidUnitTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception{
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void testCategorizeResults(){
        if (mActivity instanceof TestInterface){
            Map<String, List<Result>> resultMap = ((TestInterface)mActivity).categorizeResults();
            int timeout = 1000;
            int tempTime = 0;

            while (resultMap == null && tempTime <= timeout){
                try {
                    Thread.sleep(100);
                    tempTime += 100;
                    resultMap = ((TestInterface)mActivity).categorizeResults();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (resultMap != null) {
                for (String s : resultMap.keySet()) {
                    for (Result r : resultMap.get(s)) {
                        Log.i("Test", "Title: " + r.name.title);
                        Assert.assertTrue(r.name.title.equals(s));
                    }
                }
            }
        }
    }

    @Test
    public void testSortByLastName(){
        List<Result> results = ((TestInterface)mActivity).sortByLastName();
        int timeout = 1000;
        int tempTime = 0;

        while (results == null && tempTime <= timeout){
            try {
                Thread.sleep(100);
                tempTime += 100;
                results = ((TestInterface)mActivity).sortByLastName();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.assertThat(results, isLastNameAscendingOrdered());
    }

    private Matcher<? super List<Result>> isLastNameAscendingOrdered()
    {
        return new TypeSafeMatcher<List<Result>>()
        {
            @Override
            public void describeTo (Description description)
            {
                description.appendText("Type safety didn't match");
            }

            @Override
            protected boolean matchesSafely (List<Result> item)
            {
                for(int i = 0 ; i < item.size() -1; i++) {
                    if (item.get(i).name.last.compareTo(item.get(i+1).name.last) <= 0){
                        return true;
                    }
                }
                return false;
            }
        };
    }

    @After
    public void clearActivity(){

    }
}
