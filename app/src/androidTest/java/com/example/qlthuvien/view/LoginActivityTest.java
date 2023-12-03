package com.example.qlthuvien.view;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
public class LoginActivityTest {
    @Rule
    @JvmField
    public ActivityTestRule<com.example.qlthuvien.view.activities.LoginActivity> mainActivityRule = new ActivityTestRule(com.example.qlthuvien.view.activities.LoginActivity.class);
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1_DangNhapThanhCong()
    {
        onView(withId(R.id.txt_username)).perform(typeText("hphthao1705@gmail.com"));
        onView(withId(R.id.txt_password)).perform(typeText("123"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        onView(withText("Đăng nhập thành công!")).check(matches(isDisplayed()));
    }
    @Test
    public void test2_DangNhapThatBai()
    {
        //dung username, sai password
        onView(withId(R.id.txt_username)).perform(typeText("hphthao1705@gmail.com"));
        onView(withId(R.id.txt_password)).perform(typeText("1705"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        onView(withText("Mật khẩu trống hoặc sai định dạng")).check(matches(isDisplayed()));
        //sai username, dung password
        onView(withId(R.id.txt_username)).perform(clearText(),typeText("hphthao@gmail.com"));
        onView(withId(R.id.txt_password)).perform(clearText(),typeText("123"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        onView(withText("Tài khoản hoặc sai tài khoản")).check(matches(isDisplayed()));
    }
    @Test
    public void test3_BoTrongVaNhanDangNhap()
    {
        //khong nhap gi
        onView(withId(R.id.btn_dangnhap)).perform(click());
        onView(withText("Tài khoản hoặc sai tài khoản")).check(matches(isDisplayed()));
        //nhap username
        onView(withId(R.id.txt_username)).perform(typeText("aaa"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        onView(withText("Tài khoản hoặc sai tài khoản")).check(matches(isDisplayed()));
        //nhap password
        onView(withId(R.id.txt_password)).perform(typeText("aaa"));
        onView(withId(R.id.btn_dangnhap)).perform(click());
        onView(withText("Tài khoản hoặc sai tài khoản")).check(matches(isDisplayed()));
    }
}
