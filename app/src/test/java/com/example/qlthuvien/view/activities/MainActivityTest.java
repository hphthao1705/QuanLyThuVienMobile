package com.example.qlthuvien.view.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.qlthuvien.R;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import kotlin.jvm.JvmField;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    @JvmField
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule(MainActivity.class);
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void test1_TimKiemThanhCong()
    {
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        onView(withId(R.id.searchView)).perform(click());
        //onView(withId(R.id.searchView)).perform(clearText(), typeText("pari"));
        onView(withId(R.id.searchView)).perform(typeText("pari"));
    }
    @Test
    public void test2_TimKiemThatBai()
    {

    }
    @Test
    public void test3_KhongTimKiemNua()
    {

    }
}