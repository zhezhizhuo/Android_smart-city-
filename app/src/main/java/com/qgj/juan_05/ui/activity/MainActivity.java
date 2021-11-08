package com.qgj.juan_05.ui.activity;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    public static String token;
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
        //初始化导航兰
        initNav();
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

    /**
     *
     */
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
//        return super.onSupportNavigateUp();
    }
}