package com.qgj.juan_05.netwok.service;

import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.AccountModel;
import com.qgj.juan_05.netwok.model.ActivityBannerModel;
import com.qgj.juan_05.netwok.model.ActivityInfoModel;
import com.qgj.juan_05.netwok.model.ActivityTypeModel;
import com.qgj.juan_05.netwok.model.ActivyInfoModel;
import com.qgj.juan_05.netwok.model.AddCardModel;
import com.qgj.juan_05.netwok.model.BaoMing;
import com.qgj.juan_05.netwok.model.BaoMingModel;
import com.qgj.juan_05.netwok.model.BusLienModel;
import com.qgj.juan_05.netwok.model.BusLineInfoModel;
import com.qgj.juan_05.netwok.model.CardDepartmentModel;
import com.qgj.juan_05.netwok.model.CardInfoModel;
import com.qgj.juan_05.netwok.model.CinameModel;
import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.model.FeedBackModel;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.netwok.model.HomeNewsModel;
import com.qgj.juan_05.netwok.model.HomeServiceModel;
import com.qgj.juan_05.netwok.model.HotMovieModel;
import com.qgj.juan_05.netwok.model.HouseModel;
import com.qgj.juan_05.netwok.model.LifeModel;
import com.qgj.juan_05.netwok.model.LoginModel;
import com.qgj.juan_05.netwok.model.MovieModel;
import com.qgj.juan_05.netwok.model.MoviePlModel;
import com.qgj.juan_05.netwok.model.MoviesInfoModel;
import com.qgj.juan_05.netwok.model.NewsInfoModel;
import com.qgj.juan_05.netwok.model.NewsInfoplModel;
import com.qgj.juan_05.netwok.model.OutAbnnerModel;
import com.qgj.juan_05.netwok.model.OutPationModel;
import com.qgj.juan_05.netwok.model.SendMoviePlModel;
import com.qgj.juan_05.netwok.model.TackOutModel;
import com.qgj.juan_05.netwok.model.UpPassWordModel;
import com.qgj.juan_05.netwok.model.UpUserinfoModel;
import com.qgj.juan_05.netwok.model.UserInfoModel;
import com.qgj.juan_05.netwok.model.WaiMai2Model;
import com.qgj.juan_05.netwok.model.WaiMaiModel;
import com.qgj.juan_05.netwok.model.WearthInfoModel;
import com.qgj.juan_05.netwok.model.WeartherModel;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class ServiceDaoImpl {

    private static   ServiceDao mServiceDao;
    static {//静态代码块加载数据Dao
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.serverURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServiceDao = retrofit.create(ServiceDao.class);
    }


    //登录的接口
    public static LoginModel login( AccountModel accountModel) throws IOException {
        return Call(mServiceDao.login(accountModel));
    }
    //获取用户信息的接口
    public static UserInfoModel getUserInfo(String authorization) throws IOException {
        return Call(mServiceDao.getUserInfo(authorization));
    }
    //  修改用户信息的接口
    public static DataModel upUserinfo(String authorization, UpUserinfoModel model) throws IOException {
        return Call(mServiceDao.upUserinfo(authorization,model));
    }
    //  修改密码的接口
    public static DataModel upDatePassWold(String authorization, UpPassWordModel model) throws IOException {
        return Call(mServiceDao.upDatePassWold(authorization,model));
    }
    // //意见反馈的信息
    public static DataModel sendFeedBack(String authorization, FeedBackModel model) throws IOException {
        return Call(mServiceDao.sendFeedBack(authorization,model));
    }
    //获取首页轮播图
    public static AbnnerModel getBannerType(String type) throws IOException {
        return Call(mServiceDao.getBannerType(type));
    }
    //// 店家相关接

    public static TackOutModel gethotel() throws IOException {
        return Call(mServiceDao.gethotel());
    }

    //新闻轮播图
    public static AbnnerModel getBannerImg() throws IOException {
        return Call(mServiceDao.getBannerImg());
    }
    public static AbnnerModel getTackOut() throws IOException {
        return Call(mServiceDao.getTackOut());
    }
    //所有的服务
    public static HomeServiceModel getAllService() throws IOException {
        return Call(mServiceDao.getAllService());
    }

    //所有的新闻列表
    public static HomeNewsModel getAllHomeNewsType() throws IOException {
        return Call(mServiceDao.getAllHomeNewsType());
    }
    //根据id获取所有的新闻
    public static HomeNewAllModel getHomeNewsById(int id) throws IOException {
        return Call(mServiceDao.getHomeNewsById(id));
    }
    //根据id获取所有的新闻
    public static NewsInfoModel getNewsInfoById(int id) throws IOException {
        return Call(mServiceDao.getNewsInfoById(id));
    }
    ////根据id获取新闻详情信息
    public static NewsInfoplModel getNewsPingLunById(int id) throws IOException {
        return Call(mServiceDao.getNewsPingLunById(id));
    }
    //获取今天的天气
    public static WearthInfoModel getWeatherInfoToday() throws IOException {
        return Call(mServiceDao.getWeatherInfoToday());
    }
    //获取近七天的天气
    public static WeartherModel getWeatherInfo() throws IOException {
        return Call(mServiceDao.getWeatherInfo());
    }
    //获取医院的banner
    public static OutAbnnerModel getHospitalBannerById(int id) throws IOException {
        return Call(mServiceDao.getHospitalBannerById(id));
    }
    //获取所有的医院
    public static OutPationModel getOutpatientAll( ) throws IOException {
        return Call(mServiceDao.getOutpatientAll());
    }
    //获取所有的公交路线
    public static BusLienModel getBusLienAll( ) throws IOException {
        return Call(mServiceDao.getBusLienAll());
    }
    //根据bus的id获取所有的路线
    public static BusLineInfoModel getBusLienInfoById(int id) throws IOException {
        return Call(mServiceDao.getBusLienInfoById(id));
    }

    //获取活动管理的轮播图
    public static ActivityBannerModel getActivityBanner() throws IOException {
        return Call(mServiceDao.getActivityBanner());
    }

    //获取活动管理的列表
    public static ActivityTypeModel getActivityType() throws IOException {
        return Call(mServiceDao.getActivityType());
    }

    //据活动管理分类的id 查询所有列表信息
    public static ActivyInfoModel getActivityTypeInfoById(int id) throws IOException {
        return Call(mServiceDao.getActivityTypeInfoById(id));
    }
    ////根据类查询所有列表信息的id 获取详细信息
    public static ActivityInfoModel getActivityInfoById(int id) throws IOException {
        return Call(mServiceDao.getActivityInfoById(id));
    }
    ////查看用户是否报名
    public static BaoMingModel getActivityStatus(String token, int id) throws IOException {
        return Call(mServiceDao.getActivityStatus(token,id));
    }
    ////报名这个活动
    public static DataModel baomingActivity(String token, BaoMing bm) throws IOException {
        return Call(mServiceDao.baomingActivity(token,bm));
    }
    ////查询用户就诊卡
    public static CardInfoModel getCardInfo(String token) throws IOException {
        return Call(mServiceDao.getCardInfo(token));
    }
    ////根据类型查询医生
    public static CardInfoModel getDoctorType(int token) throws IOException {
        return Call(mServiceDao.getDoctorType(token));
    }
    ////报名这个活动
    public static DataModel addCard(String token, AddCardModel bm) throws IOException {
        return Call(mServiceDao.addCard(token,bm));
    }
    ////查询所有科室
    public static CardDepartmentModel getDepartmentAll() throws IOException {
        return Call(mServiceDao.getDepartmentAll());
    }
    ////查询类型科室
    public static CardDepartmentModel getDepartmentAll(int type) throws IOException {
        return Call(mServiceDao.getDepartmentAll(type));
    }
    //查询影片信息列
    public static MovieModel getMovieAll() throws IOException {
        return Call(mServiceDao.getMovieAll());
    }
    //查询预告影片信息列
    public static HotMovieModel getMovieHotAll() throws IOException {
        return Call(mServiceDao.getMovieHotAll());
    }
    //   查询电影i新
    //查询影片信息列
    public static MoviesInfoModel getMovieInfoById(int id) throws IOException {
        return Call(mServiceDao.getMovieInfoById(id));
    }
    //查询影片信息pl
    public static MoviePlModel getMoviePlById(int id) throws IOException {
        return Call(mServiceDao.getMoviePlById(id));
    }
    //生活服务的图
    public static LifeModel getLifeAll() throws IOException {
        return Call(mServiceDao.getLifeAll());
    }
    //生活服务的图
    public static AbnnerModel getLifeBanner() throws IOException {
        return Call(mServiceDao.getLifeBanner());
    }
    //房源
    public static HouseModel getHouseAll() throws IOException {
        return Call(mServiceDao.getHouseAll());
    }
    //外卖分类
    public static WaiMaiModel getWaiMaiFenLei() throws IOException {
        return Call(mServiceDao.getWaiMaiFenLei());
    }
    //外卖列表
    //根据分类Id查询外卖列表
    public static WaiMai2Model getWaiMaiInfoAll(int id) throws IOException {
        return Call(mServiceDao.getWaiMaiInfoAll(id));
    }
    //电影广告
    public static AbnnerModel getMovieBanner() throws IOException {
        return Call(mServiceDao.getMovieBanner());
    }
    ////影院
    public static CinameModel getCinema() throws IOException {
        return Call(mServiceDao.getCinema(5,20));
    }
    //fa表评论
    public static DataModel sendMoviePl(SendMoviePlModel sendMoviePlModel,String token) throws IOException {
        return Call(mServiceDao.sendMoviePl(sendMoviePlModel,token));
    }
    //泛型  减少代码量
    public static <T> T Call(Call<T> call) throws IOException {
        return call.execute().body();
    }
}
