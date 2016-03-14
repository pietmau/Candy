package com.candyspace.androidtest;

import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.candyspace.androidtest.api.dagger.ApiModule;
import com.candyspace.androidtest.api.dagger.DaggerApiComponent;
import com.candyspace.androidtest.classes.MockApiModule;
import com.candyspace.androidtest.classes.MockApiModuleNumbers;
import com.robotium.solo.Solo;

public class TestMockApi extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public TestMockApi() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        App app = (App) getInstrumentation().getTargetContext().getApplicationContext();

        // We inject the Mock api
        DaggerApiComponent.builder().apiModule(new MockApiModule(app)).build().inject(app);
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void test() throws InterruptedException {
        solo.waitForView(R.id.recycler_view);
        final RecyclerView recyclerView = (RecyclerView) solo.getView(R.id.recycler_view);
        View v = recyclerView.findViewHolderForAdapterPosition(0).itemView;
        // 0 should be a hero view
        TextView textView = (TextView) v.findViewById(R.id.hero_item_title);
        String title = textView.getText().toString();
        assertTrue(title.contains("Hillary Clinton Lauds Reagans on AIDS. A Backlash Erupts."));
        // scrollRecyclerViewToBottom does not actually scroll to bottom https://github.com/RobotiumTech/robotium/issues/804
        // so, scrolling a bit to reach the bottom
        // this might fail if device is very small
        for (int i = 0; i < 20; i++) {
            solo.scrollRecyclerViewToBottom(0);
        }
        View child = recyclerView.findViewHolderForAdapterPosition(19).itemView;
        // 19 should be a normal view
        textView = (TextView) child.findViewById(R.id.grid_item_title);
        title = textView.getText().toString();
        assertTrue(title.contains("Security Forms Ring Around Donald Trump After Disruption at Ohio Rally"));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        getInstrumentation().getTargetContext().getApplicationContext();
        App app = (App) getInstrumentation().getTargetContext().getApplicationContext();
        DaggerApiComponent.builder().apiModule(new ApiModule()).build().inject(app);
    }
}