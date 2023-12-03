package com.example.qlthuvien;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

public class SwipeLeftAction implements ViewAction {
    @Override
    public String getDescription() {
        return "Swipe left on RecyclerView item.";
    }

    @Override
    public Matcher<View> getConstraints() {
        return ViewMatchers.isAssignableFrom(RecyclerView.class);
    }

    @Override
    public void perform(UiController uiController, View view) {
        RecyclerView recyclerView = (RecyclerView) view;
        // Assuming you have a LayoutManager with horizontal orientation
        recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, 1);
        uiController.loopMainThreadUntilIdle();
    }
}
