package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeartherModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataDTO data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        @SerializedName("today")
        private TodayDTO today;
        @SerializedName("weatherList")
        private List<WeatherListDTO> weatherList;

        public TodayDTO getToday() {
            return today;
        }

        public void setToday(TodayDTO today) {
            this.today = today;
        }

        public List<WeatherListDTO> getWeatherList() {
            return weatherList;
        }

        public void setWeatherList(List<WeatherListDTO> weatherList) {
            this.weatherList = weatherList;
        }

        public static class TodayDTO {
            @SerializedName("hours")
            private List<HoursDTO> hours;
            @SerializedName("airQuantity")
            private AirQuantityDTO airQuantity;
            @SerializedName("comfortLevel")
            private ComfortLevelDTO comfortLevel;
            @SerializedName("tempInfo")
            private TempInfoDTO tempInfo;
            @SerializedName("updateTime")
            private String updateTime;
            @SerializedName("wind")
            private WindDTO wind;

            public List<HoursDTO> getHours() {
                return hours;
            }

            public void setHours(List<HoursDTO> hours) {
                this.hours = hours;
            }

            public AirQuantityDTO getAirQuantity() {
                return airQuantity;
            }

            public void setAirQuantity(AirQuantityDTO airQuantity) {
                this.airQuantity = airQuantity;
            }

            public ComfortLevelDTO getComfortLevel() {
                return comfortLevel;
            }

            public void setComfortLevel(ComfortLevelDTO comfortLevel) {
                this.comfortLevel = comfortLevel;
            }

            public TempInfoDTO getTempInfo() {
                return tempInfo;
            }

            public void setTempInfo(TempInfoDTO tempInfo) {
                this.tempInfo = tempInfo;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public WindDTO getWind() {
                return wind;
            }

            public void setWind(WindDTO wind) {
                this.wind = wind;
            }

            public static class AirQuantityDTO {
                @SerializedName("no2")
                private int no2;
                @SerializedName("pm25")
                private int pm25;
                @SerializedName("o3")
                private int o3;
                @SerializedName("so2")
                private int so2;
                @SerializedName("pm10")
                private int pm10;
                @SerializedName("co")
                private double co;

                public int getNo2() {
                    return no2;
                }

                public void setNo2(int no2) {
                    this.no2 = no2;
                }

                public int getPm25() {
                    return pm25;
                }

                public void setPm25(int pm25) {
                    this.pm25 = pm25;
                }

                public int getO3() {
                    return o3;
                }

                public void setO3(int o3) {
                    this.o3 = o3;
                }

                public int getSo2() {
                    return so2;
                }

                public void setSo2(int so2) {
                    this.so2 = so2;
                }

                public int getPm10() {
                    return pm10;
                }

                public void setPm10(int pm10) {
                    this.pm10 = pm10;
                }

                public double getCo() {
                    return co;
                }

                public void setCo(double co) {
                    this.co = co;
                }
            }

            public static class ComfortLevelDTO {
                @SerializedName("uv")
                private int uv;
                @SerializedName("dressingIndex")
                private String dressingIndex;
                @SerializedName("humidity")
                private int humidity;
                @SerializedName("coldIndex")
                private String coldIndex;
                @SerializedName("apparentTemperature")
                private int apparentTemperature;
                @SerializedName("uvIndex")
                private String uvIndex;
                @SerializedName("washIndex")
                private String washIndex;
                @SerializedName("sportIndex")
                private String sportIndex;

                public int getUv() {
                    return uv;
                }

                public void setUv(int uv) {
                    this.uv = uv;
                }

                public String getDressingIndex() {
                    return dressingIndex;
                }

                public void setDressingIndex(String dressingIndex) {
                    this.dressingIndex = dressingIndex;
                }

                public int getHumidity() {
                    return humidity;
                }

                public void setHumidity(int humidity) {
                    this.humidity = humidity;
                }

                public String getColdIndex() {
                    return coldIndex;
                }

                public void setColdIndex(String coldIndex) {
                    this.coldIndex = coldIndex;
                }

                public int getApparentTemperature() {
                    return apparentTemperature;
                }

                public void setApparentTemperature(int apparentTemperature) {
                    this.apparentTemperature = apparentTemperature;
                }

                public String getUvIndex() {
                    return uvIndex;
                }

                public void setUvIndex(String uvIndex) {
                    this.uvIndex = uvIndex;
                }

                public String getWashIndex() {
                    return washIndex;
                }

                public void setWashIndex(String washIndex) {
                    this.washIndex = washIndex;
                }

                public String getSportIndex() {
                    return sportIndex;
                }

                public void setSportIndex(String sportIndex) {
                    this.sportIndex = sportIndex;
                }
            }

            public static class TempInfoDTO {
                @SerializedName("maxTemperature")
                private String maxTemperature;
                @SerializedName("uv")
                private String uv;
                @SerializedName("minTemperature")
                private String minTemperature;
                @SerializedName("temperature")
                private String temperature;
                @SerializedName("weather")
                private String weather;
                @SerializedName("humidity")
                private String humidity;
                @SerializedName("air")
                private String air;
                @SerializedName("apparentTemperature")
                private String apparentTemperature;
                @SerializedName("label")
                private String label;
                @SerializedName("day")
                private String day;

                public String getMaxTemperature() {
                    return maxTemperature;
                }

                public void setMaxTemperature(String maxTemperature) {
                    this.maxTemperature = maxTemperature;
                }

                public String getUv() {
                    return uv;
                }

                public void setUv(String uv) {
                    this.uv = uv;
                }

                public String getMinTemperature() {
                    return minTemperature;
                }

                public void setMinTemperature(String minTemperature) {
                    this.minTemperature = minTemperature;
                }

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getHumidity() {
                    return humidity;
                }

                public void setHumidity(String humidity) {
                    this.humidity = humidity;
                }

                public String getAir() {
                    return air;
                }

                public void setAir(String air) {
                    this.air = air;
                }

                public String getApparentTemperature() {
                    return apparentTemperature;
                }

                public void setApparentTemperature(String apparentTemperature) {
                    this.apparentTemperature = apparentTemperature;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }
            }

            public static class WindDTO {
                @SerializedName("windStrength")
                private String windStrength;
                @SerializedName("windDirection")
                private String windDirection;

                public String getWindStrength() {
                    return windStrength;
                }

                public void setWindStrength(String windStrength) {
                    this.windStrength = windStrength;
                }

                public String getWindDirection() {
                    return windDirection;
                }

                public void setWindDirection(String windDirection) {
                    this.windDirection = windDirection;
                }
            }

            public static class HoursDTO {
                @SerializedName("hour")
                private String hour;
                @SerializedName("weather")
                private String weather;
                @SerializedName("temperature")
                private int temperature;

                public String getHour() {
                    return hour;
                }

                public void setHour(String hour) {
                    this.hour = hour;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public int getTemperature() {
                    return temperature;
                }

                public void setTemperature(int temperature) {
                    this.temperature = temperature;
                }
            }
        }

        public static class WeatherListDTO {
            @SerializedName("maxTemperature")
            private String maxTemperature;
            @SerializedName("uv")
            private String uv;
            @SerializedName("minTemperature")
            private String minTemperature;
            @SerializedName("temperature")
            private String temperature;
            @SerializedName("weather")
            private String weather;
            @SerializedName("humidity")
            private int humidity;
            @SerializedName("air")
            private String air;
            @SerializedName("apparentTemperature")
            private String apparentTemperature;
            @SerializedName("label")
            private String label;
            @SerializedName("day")
            private String day;

            public String getMaxTemperature() {
                return maxTemperature;
            }

            public void setMaxTemperature(String maxTemperature) {
                this.maxTemperature = maxTemperature;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getMinTemperature() {
                return minTemperature;
            }

            public void setMinTemperature(String minTemperature) {
                this.minTemperature = minTemperature;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public String getAir() {
                return air;
            }

            public void setAir(String air) {
                this.air = air;
            }

            public String getApparentTemperature() {
                return apparentTemperature;
            }

            public void setApparentTemperature(String apparentTemperature) {
                this.apparentTemperature = apparentTemperature;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }
}
