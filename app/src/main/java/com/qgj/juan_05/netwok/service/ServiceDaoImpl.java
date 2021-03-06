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
    static {//???????????????????????????Dao
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.serverURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServiceDao = retrofit.create(ServiceDao.class);
    }


    //???????????????
    public static LoginModel login( AccountModel accountModel) throws IOException {
        return Call(mServiceDao.login(accountModel));
    }
    //???????????????????????????
    public static UserInfoModel getUserInfo(String authorization) throws IOException {
        return Call(mServiceDao.getUserInfo(authorization));
    }
    //  ???????????????????????????
    public static DataModel upUserinfo(String authorization, UpUserinfoModel model) throws IOException {
        return Call(mServiceDao.upUserinfo(authorization,model));
    }
    //  ?????????????????????
    public static DataModel upDatePassWold(String authorization, UpPassWordModel model) throws IOException {
        return Call(mServiceDao.upDatePassWold(authorization,model));
    }
    // //?????????????????????
    public static DataModel sendFeedBack(String authorization, FeedBackModel model) throws IOException {
        return Call(mServiceDao.sendFeedBack(authorization,model));
    }
    //?????????????????????
    public static AbnnerModel getBannerType(String type) throws IOException {
        return Call(mServiceDao.getBannerType(type));
    }
    //// ???????????????

    public static TackOutModel gethotel() throws IOException {
        return Call(mServiceDao.gethotel());
    }

    //???????????????
    public static AbnnerModel getBannerImg() throws IOException {
        return Call(mServiceDao.getBannerImg());
    }
    public static AbnnerModel getTackOut() throws IOException {
        return Call(mServiceDao.getTackOut());
    }
    //???????????????
    public static HomeServiceModel getAllService() throws IOException {
        return Call(mServiceDao.getAllService());
    }

    //?????????????????????
    public static HomeNewsModel getAllHomeNewsType() throws IOException {
        return Call(mServiceDao.getAllHomeNewsType());
    }
    //??????id?????????????????????
    public static HomeNewAllModel getHomeNewsById(int id) throws IOException {
        return Call(mServiceDao.getHomeNewsById(id));
    }
    //??????id?????????????????????
    public static NewsInfoModel getNewsInfoById(int id) throws IOException {
        return Call(mServiceDao.getNewsInfoById(id));
    }
    ////??????id????????????????????????
    public static NewsInfoplModel getNewsPingLunById(int id) throws IOException {
        return Call(mServiceDao.getNewsPingLunById(id));
    }
    //?????????????????????
    public static WearthInfoModel getWeatherInfoToday() throws IOException {
        return Call(mServiceDao.getWeatherInfoToday());
    }
    //????????????????????????
    public static WeartherModel getWeatherInfo() throws IOException {
        return Call(mServiceDao.getWeatherInfo());
    }
    //???????????????banner
    public static OutAbnnerModel getHospitalBannerById(int id) throws IOException {
        return Call(mServiceDao.getHospitalBannerById(id));
    }
    //?????????????????????
    public static OutPationModel getOutpatientAll( ) throws IOException {
        return Call(mServiceDao.getOutpatientAll());
    }
    //???????????????????????????
    public static BusLienModel getBusLienAll( ) throws IOException {
        return Call(mServiceDao.getBusLienAll());
    }
    //??????bus???id?????????????????????
    public static BusLineInfoModel getBusLienInfoById(int id) throws IOException {
        return Call(mServiceDao.getBusLienInfoById(id));
    }

    //??????????????????????????????
    public static ActivityBannerModel getActivityBanner() throws IOException {
        return Call(mServiceDao.getActivityBanner());
    }

    //???????????????????????????
    public static ActivityTypeModel getActivityType() throws IOException {
        return Call(mServiceDao.getActivityType());
    }

    //????????????????????????id ????????????????????????
    public static ActivyInfoModel getActivityTypeInfoById(int id) throws IOException {
        return Call(mServiceDao.getActivityTypeInfoById(id));
    }
    ////????????????????????????????????????id ??????????????????
    public static ActivityInfoModel getActivityInfoById(int id) throws IOException {
        return Call(mServiceDao.getActivityInfoById(id));
    }
    ////????????????????????????
    public static BaoMingModel getActivityStatus(String token, int id) throws IOException {
        return Call(mServiceDao.getActivityStatus(token,id));
    }
    ////??????????????????
    public static DataModel baomingActivity(String token, BaoMing bm) throws IOException {
        return Call(mServiceDao.baomingActivity(token,bm));
    }
    ////?????????????????????
    public static CardInfoModel getCardInfo(String token) throws IOException {
        return Call(mServiceDao.getCardInfo(token));
    }
    ////????????????????????????
    public static CardInfoModel getDoctorType(int token) throws IOException {
        return Call(mServiceDao.getDoctorType(token));
    }
    ////??????????????????
    public static DataModel addCard(String token, AddCardModel bm) throws IOException {
        return Call(mServiceDao.addCard(token,bm));
    }
    ////??????????????????
    public static CardDepartmentModel getDepartmentAll() throws IOException {
        return Call(mServiceDao.getDepartmentAll());
    }
    ////??????????????????
    public static CardDepartmentModel getDepartmentAll(int type) throws IOException {
        return Call(mServiceDao.getDepartmentAll(type));
    }
    //?????????????????????
    public static MovieModel getMovieAll() throws IOException {
        return Call(mServiceDao.getMovieAll());
    }
    //???????????????????????????
    public static HotMovieModel getMovieHotAll() throws IOException {
        return Call(mServiceDao.getMovieHotAll());
    }
    //   ????????????i???
    //?????????????????????
    public static MoviesInfoModel getMovieInfoById(int id) throws IOException {
        return Call(mServiceDao.getMovieInfoById(id));
    }
    //??????????????????pl
    public static MoviePlModel getMoviePlById(int id) throws IOException {
        return Call(mServiceDao.getMoviePlById(id));
    }
    //??????????????????
    public static LifeModel getLifeAll() throws IOException {
        return Call(mServiceDao.getLifeAll());
    }
    //??????????????????
    public static AbnnerModel getLifeBanner() throws IOException {
        return Call(mServiceDao.getLifeBanner());
    }
    //??????
    public static HouseModel getHouseAll() throws IOException {
        return Call(mServiceDao.getHouseAll());
    }
    //????????????
    public static WaiMaiModel getWaiMaiFenLei() throws IOException {
        return Call(mServiceDao.getWaiMaiFenLei());
    }
    //????????????
    //????????????Id??????????????????
    public static WaiMai2Model getWaiMaiInfoAll(int id) throws IOException {
        return Call(mServiceDao.getWaiMaiInfoAll(id));
    }
    //????????????
    public static AbnnerModel getMovieBanner() throws IOException {
        return Call(mServiceDao.getMovieBanner());
    }

    //????????????
    public static AbnnerModel getOpenBanner(int pager,int size) throws IOException {
        return Call(mServiceDao.getOpenBanner(pager,size));
    }
    ////??????
    public static CinameModel getCinema() throws IOException {
        return Call(mServiceDao.getCinema(5,20));
    }
    //fa?????????
    public static DataModel sendMoviePl(SendMoviePlModel sendMoviePlModel,String token) throws IOException {
        return Call(mServiceDao.sendMoviePl(sendMoviePlModel,token));
    }
    //??????  ???????????????
    public static <T> T Call(Call<T> call) throws IOException {
        return call.execute().body();
    }
}
