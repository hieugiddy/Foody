package com.app.foody.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.app.foody.Adapters.OnboardingAdapter;
import com.app.foody.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class Slide extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private Button buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();
        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setLayoutOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() +1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                } else {
                    startActivity(new Intent(getApplicationContext(), DangNhap.class));
                    finish();
                }
            }
        });
    }
    private  void setupOnboardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemPayOnline = new OnboardingItem();
        itemPayOnline.setTitle("Pay Your Bill Online");
        itemPayOnline.setDescription("Electric bill payments is a feature of online, mobile and telephone banking");
        itemPayOnline.setImage(R.drawable.pay_online);

        OnboardingItem itemOnTheWay = new OnboardingItem();
        itemOnTheWay.setTitle("Your Food Is On The Way");
        itemOnTheWay.setDescription("Our delivery rider is on the way to delivery your order");
        itemOnTheWay.setImage(R.drawable.on_the_way);

        OnboardingItem itemEatTogether = new OnboardingItem();
        itemEatTogether.setTitle("Eat Together");
        itemEatTogether.setDescription("Enjoy your meal and have a great day. Don't forget to rate us");
        itemEatTogether.setImage(R.drawable.eat_together);

        onboardingItems.add(itemPayOnline);
        onboardingItems.add(itemOnTheWay);
        onboardingItems.add(itemEatTogether);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);

    }

    private void setLayoutOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
         layoutParams.setMargins(8,0,8,0);
         for(int i=0; i<indicators.length; i++){
             indicators[i] = new ImageView(getApplicationContext());
             indicators[i].setImageDrawable(ContextCompat.getDrawable(
                     getApplicationContext(),
                     R.drawable.onboarding_indicator_inactive
             ));
             indicators[i].setLayoutParams(layoutParams);
             layoutOnboardingIndicators.addView(indicators[i]);
         }
    }

    private  void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i=0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if(index == onboardingAdapter.getItemCount() - 1){
            buttonOnboardingAction.setText("Start");
        }else{
            buttonOnboardingAction.setText("Next");
        }
    }
}





























