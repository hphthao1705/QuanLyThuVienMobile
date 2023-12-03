package com.example.qlthuvien.view;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.util.regex.Pattern.matches;

import android.view.KeyEvent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.qlthuvien.R;
import com.example.qlthuvien.RecyclerViewItemCountAssertion;
import com.example.qlthuvien.SwipeLeftAction;
import com.example.qlthuvien.view.activities.MainActivity;

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
    public void test1_TimKiemThanhCong() throws InterruptedException {
        onView(withId(R.id.searchView)).perform(click());
        //onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_T), closeSoftKeyboard()).perform(pressImeActionButton());

        Espresso.onView(withId(R.id.searchView))
                .perform(click(), pressKey(KeyEvent.KEYCODE_T));
        Thread.sleep(500);  // Add a small delay
        Espresso.onView(withId(R.id.searchView)).perform(ViewActions.pressImeActionButton());

        //onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_T), ViewActions.pressImeActionButton());;
        onView(withId(R.id.recyclerview_search)).check(new RecyclerViewItemCountAssertion(4));
    }
    @Test
    public void test2_TimKiemThatBai()
    {

    }
    @Test
    public void test3_KhongTimKiemNua()
    {

    }

    @Test
    public void test4_ThemSachVaoGioSach() throws InterruptedException {
        onView(withId(R.id.page_information)).perform(click());
        onView(withId(R.id.view_thoat)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("hphthao1705@gmail.com"));
        onView(withId(R.id.txt_password)).perform(typeText("123"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        Thread.sleep(5000);
        Espresso.onView(ViewMatchers.withId(R.id.recycler_bookinTop)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        Thread.sleep(2000);
        onView(withId(R.id.btn_addcart)).perform(click());
        onView(withId(R.id.btn_details_back)).perform(click());
        onView(withId(R.id.page_cart_book)).perform(click());
        onView(withId(R.id.rcvCartBook)).check(new RecyclerViewItemCountAssertion(1));
    }
    @Test
    public void test5_ThemTrungSachVaoGioSach() throws InterruptedException {
        onView(withId(R.id.page_information)).perform(click());
        onView(withId(R.id.view_thoat)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("hphthao1705@gmail.com"));
        onView(withId(R.id.txt_password)).perform(typeText("123"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        Thread.sleep(5000);
        Espresso.onView(ViewMatchers.withId(R.id.recycler_bookinTop)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        Thread.sleep(2000);
        onView(withId(R.id.btn_addcart)).perform(click());
        onView(withId(R.id.btn_details_back)).perform(click());
        onView(withId(R.id.page_cart_book)).perform(click());
        onView(withId(R.id.rcvCartBook)).check(new RecyclerViewItemCountAssertion(1));

        //add them 1 lan nua
        onView(withId(R.id.page_home)).perform(click());
        Thread.sleep(5000);
        Espresso.onView(ViewMatchers.withId(R.id.recycler_bookinTop)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        Thread.sleep(2000);
        onView(withId(R.id.btn_addcart)).perform(click());
        onView(withId(R.id.btn_details_back)).perform(click());
        onView(withId(R.id.page_cart_book)).perform(click());
        onView(withId(R.id.rcvCartBook)).check(new RecyclerViewItemCountAssertion(1));
    }
    @Test
    public void test6_XoaSachKhoiGioSach() throws InterruptedException {
        onView(withId(R.id.page_information)).perform(click());
        onView(withId(R.id.view_thoat)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("hphthao1705@gmail.com"));
        onView(withId(R.id.txt_password)).perform(typeText("123"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        Thread.sleep(5000);
        Espresso.onView(ViewMatchers.withId(R.id.recycler_bookinTop)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        Thread.sleep(2000);
        onView(withId(R.id.btn_addcart)).perform(click());
        onView(withId(R.id.btn_details_back)).perform(click());
        onView(withId(R.id.page_cart_book)).perform(click());
        onView(withId(R.id.rcvCartBook)).check(new RecyclerViewItemCountAssertion(1));

        //xoa
        onView(withId(R.id.page_cart_book)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.rcvCartBook))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeLeft()));
        Espresso.onView(withText("Đồng ý")).perform(click());
        onView(withId(R.id.rcvCartBook)).check(new RecyclerViewItemCountAssertion(0));
        onView(withText("Giỏ sách trống")).check(ViewAssertions.matches(isDisplayed()));
    }
    @Test
    public void test7_TuChoiXoaSachKhoiGioSach() throws InterruptedException {
        onView(withId(R.id.page_information)).perform(click());
        onView(withId(R.id.view_thoat)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("hphthao1705@gmail.com"));
        onView(withId(R.id.txt_password)).perform(typeText("123"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        Thread.sleep(5000);
        Espresso.onView(ViewMatchers.withId(R.id.recycler_bookinTop)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        Thread.sleep(2000);
        onView(withId(R.id.btn_addcart)).perform(click());
        onView(withId(R.id.btn_details_back)).perform(click());
        onView(withId(R.id.page_cart_book)).perform(click());
        onView(withId(R.id.rcvCartBook)).check(new RecyclerViewItemCountAssertion(1));

        //xoa
        onView(withId(R.id.page_cart_book)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.rcvCartBook))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeLeft()));
        Espresso.onView(withText("Từ chối")).perform(click());
        onView(withId(R.id.rcvCartBook)).check(new RecyclerViewItemCountAssertion(1));
    }

    private ViewAction swipeLeft() {
        return actionWithAssertions(new GeneralSwipeAction(
                Swipe.SLOW, // Swipe speed
                GeneralLocation.CENTER_RIGHT, // Starting point
                GeneralLocation.CENTER_LEFT, // Ending point
                Press.FINGER));
    }
}
