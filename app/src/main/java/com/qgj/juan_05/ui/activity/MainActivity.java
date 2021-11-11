package com.qgj.juan_05.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ActivityMainBinding;
import com.qgj.juan_05.ui.fragment.guilde.GuildFragment;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    public static String token="";
    public static String serverURL = "http://124.93.196.45:10001";


    NavHostFragment navHostFragment;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.a_main_nav_host);
        navController = navHostFragment.getNavController();
        binding.aMainBottomNav.setVisibility(View.GONE);

        //判断是不是第一次登录
        isfrist();

        //初始化导航兰
        initNav();

    }

    private void isfrist() {
        new Thread(()->{
                SharedPreferences sharedPreferences = getSharedPreferences("pz",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("first",true)) {//默认是第一次进入
            startActivity(new Intent(MainActivity.this,GuildFragment.class));
        }else {
        }
        }).start();
    }

    /**初始化导航
     * bar绑定
     * 将底部导航绑定 Navigate
     *   onDestinationChanged  控制bar的显示和不显示
     */
    private void initNav() {    // 初始化导航
        // 通过 getSupportFragmentManager 获取 NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.a_main_nav_host);
        // 获取 NavController
        NavController navController = navHostFragment.getNavController();
        // 获取 BottomNavigationView
        BottomNavigationView bottomNavigationView = binding.aMainBottomNav;
        //bar绑定
        NavigationUI.setupActionBarWithNavController(this,navHostFragment.getNavController());
        // 将底部导航绑定 Navigate
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        getSupportActionBar().hide();
        getSupportActionBar().hide();
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {

//                 //页面底部是否显示导航监听
                if(bundle !=null &&bundle.getBoolean("login")){
                    getSupportActionBar().hide();
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if(bundle !=null ){
                    getSupportActionBar().show();
                    bottomNavigationView.setVisibility(View.GONE);
                }else {
                    getSupportActionBar().hide();
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@Nullable @org.jetbrains.annotations.Nullable View parent, @NonNull @NotNull String name, @NonNull @NotNull Context context, @NonNull @NotNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    /**
     *
     */
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
//        return super.onSupportNavigateUp();
    }
}