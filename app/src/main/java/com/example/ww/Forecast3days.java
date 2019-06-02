package com.example.ww;

import com.google.gson.annotations.SerializedName;

public class Forecast3days {

    @SerializedName("fcst3hour")
    Fcst3hour fcst3hour;
    @SerializedName("timeRelease")
    String timeRelease;
    @SerializedName("fcstdaily")
    Fcstdaily fcstdaily;

    public String getTimeRelease() {
        return timeRelease;
    }

    public class Fcst3hour {

        @SerializedName("precipitation")
        Precipitation precipitation;
        @SerializedName("sky")
        Sky sky;
        @SerializedName("temperature")
        Temperature temperature;

        public class Precipitation {

            @SerializedName("type4hour")
            String t4;
            @SerializedName("type7hour")
            String t7;
            @SerializedName("type10hour")
            String t10;
            @SerializedName("type13hour")
            String t13;
            @SerializedName("type16hour")
            String t16;
            @SerializedName("type19hour")
            String t19;
            @SerializedName("type22hour")
            String t22;
            @SerializedName("type25hour")
            String t25;
            @SerializedName("type28hour")
            String t28;
            @SerializedName("type31hour")
            String t31;
            @SerializedName("type34hour")
            String t34;
            @SerializedName("type37hour")
            String t37;
            @SerializedName("type40hour")
            String t40;
            @SerializedName("type43hour")
            String t43;
            @SerializedName("type46hour")
            String t46;
            @SerializedName("type49hour")
            String t49;
            @SerializedName("type52hour")
            String t52;
            @SerializedName("type55hour")
            String t55;
            @SerializedName("type58hour")
            String t58;
            @SerializedName("type61hour")
            String t61;
            @SerializedName("type64hour")
            String t64;
            @SerializedName("type67hour")
            String t67;

            @SerializedName("prob4hour")
            String p4;
            @SerializedName("prob7hour")
            String p7;
            @SerializedName("prob10hour")
            String p10;
            @SerializedName("prob13hour")
            String p13;
            @SerializedName("prob16hour")
            String p16;
            @SerializedName("prob19hour")
            String p19;
            @SerializedName("prob22hour")
            String p22;
            @SerializedName("prob25hour")
            String p25;
            @SerializedName("prob28hour")
            String p28;
            @SerializedName("prob31hour")
            String p31;
            @SerializedName("prob34hour")
            String p34;
            @SerializedName("prob37hour")
            String p37;
            @SerializedName("prob40hour")
            String p40;
            @SerializedName("prob43hour")
            String p43;
            @SerializedName("prob46hour")
            String p46;
            @SerializedName("prob49hour")
            String p49;
            @SerializedName("prob52hour")
            String p52;
            @SerializedName("prob55hour")
            String p55;
            @SerializedName("prob58hour")
            String p58;
            @SerializedName("prob61hour")
            String p61;
            @SerializedName("prob64hour")
            String p64;
            @SerializedName("prob67hour")
            String p67;

        }

        public class Sky {

            @SerializedName("name4hour")
            String n4;
            @SerializedName("name7hour")
            String n7;
            @SerializedName("name10hour")
            String n10;
            @SerializedName("name13hour")
            String n13;
            @SerializedName("name16hour")
            String n16;
            @SerializedName("name19hour")
            String n19;
            @SerializedName("name22hour")
            String n22;
            @SerializedName("name25hour")
            String n25;
            @SerializedName("name28hour")
            String n28;
            @SerializedName("name31hour")
            String n31;
            @SerializedName("name34hour")
            String n34;
            @SerializedName("name37hour")
            String n37;
            @SerializedName("name40hour")
            String n40;
            @SerializedName("name43hour")
            String n43;
            @SerializedName("name46hour")
            String n46;
            @SerializedName("name49hour")
            String n49;
            @SerializedName("name52hour")
            String n52;
            @SerializedName("name55our")
            String n55;
            @SerializedName("name58hour")
            String n58;
            @SerializedName("name61hour")
            String n61;
            @SerializedName("name64hour")
            String n64;
            @SerializedName("name67hour")
            String n67;

            public String getN4() {
                return n4;
            }

            public String getN7() {
                return n7;
            }

            public String getN10() {
                return n10;
            }

            public String getN13() {
                return n13;
            }

            public String getN16() {
                return n16;
            }

            public String getN19() {
                return n19;
            }

            public String getN22() {
                return n22;
            }

            public String getN25() {
                return n25;
            }

            public String getN28() {
                return n28;
            }

            public String getN31() {
                return n31;
            }

            public String getN34() {
                return n34;
            }

            public String getN37() {
                return n37;
            }

            public String getN40() {
                return n40;
            }

            public String getN43() {
                return n43;
            }

            public String getN46() {
                return n46;
            }

            public String getN49() {
                return n49;
            }

            public String getN52() {
                return n52;
            }

            public String getN55() {
                return n55;
            }

            public String getN58() {
                return n58;
            }

            public String getN61() {
                return n61;
            }

            public String getN64() {
                return n64;
            }

            public String getN67() {
                return n67;
            }

        }

        public class Temperature {

            @SerializedName("temp4hour")
            String t4;
            @SerializedName("temp7hour")
            String t7;
            @SerializedName("temp10hour")
            String t10;
            @SerializedName("temp13hour")
            String t13;
            @SerializedName("temp16hour")
            String t16;
            @SerializedName("temp19hour")
            String t19;
            @SerializedName("temp22hour")
            String t22;
            @SerializedName("temp25hour")
            String t25;
            @SerializedName("temp28hour")
            String t28;
            @SerializedName("temp31hour")
            String t31;
            @SerializedName("temp34hour")
            String t34;
            @SerializedName("temp37hour")
            String t37;
            @SerializedName("temp40hour")
            String t40;
            @SerializedName("temp43hour")
            String t43;
            @SerializedName("temp46hour")
            String t46;
            @SerializedName("temp49hour")
            String t49;
            @SerializedName("temp52hour")
            String t52;
            @SerializedName("temp55hour")
            String t55;
            @SerializedName("temp58hour")
            String t58;
            @SerializedName("temp61hour")
            String t61;
            @SerializedName("temp64hour")
            String t64;
            @SerializedName("temp67hour")
            String t67;

            public String getT4() {
                return t4;
            }

            public void setT4(String t4) {
                this.t4 = t4;
            }

            public String getT7() {
                return t7;
            }

            public void setT7(String t7) {
                this.t7 = t7;
            }

            public String getT10() {
                return t10;
            }

            public void setT10(String t10) {
                this.t10 = t10;
            }

            public String getT13() {
                return t13;
            }

            public void setT13(String t13) {
                this.t13 = t13;
            }

            public String getT16() {
                return t16;
            }

            public void setT16(String t16) {
                this.t16 = t16;
            }

            public String getT19() {
                return t19;
            }

            public void setT19(String t19) {
                this.t19 = t19;
            }

            public String getT22() {
                return t22;
            }

            public void setT22(String t22) {
                this.t22 = t22;
            }

            public String getT25() {
                return t25;
            }

            public void setT25(String t25) {
                this.t25 = t25;
            }

            public String getT28() {
                return t28;
            }

            public void setT28(String t28) {
                this.t28 = t28;
            }

            public String getT31() {
                return t31;
            }

            public void setT31(String t31) {
                this.t31 = t31;
            }

            public String getT34() {
                return t34;
            }

            public void setT34(String t34) {
                this.t34 = t34;
            }

            public String getT37() {
                return t37;
            }

            public void setT37(String t37) {
                this.t37 = t37;
            }

            public String getT40() {
                return t40;
            }

            public void setT40(String t40) {
                this.t40 = t40;
            }

            public String getT43() {
                return t43;
            }

            public void setT43(String t43) {
                this.t43 = t43;
            }

            public String getT46() {
                return t46;
            }

            public void setT46(String t46) {
                this.t46 = t46;
            }

            public String getT49() {
                return t49;
            }

            public void setT49(String t49) {
                this.t49 = t49;
            }

            public String getT52() {
                return t52;
            }

            public void setT52(String t52) {
                this.t52 = t52;
            }

            public String getT55() {
                return t55;
            }

            public void setT55(String t55) {
                this.t55 = t55;
            }

            public String getT58() {
                return t58;
            }

            public void setT58(String t58) {
                this.t58 = t58;
            }

            public String getT61() {
                return t61;
            }

            public void setT61(String t61) {
                this.t61 = t61;
            }

            public String getT64() {
                return t64;
            }

            public void setT64(String t64) {
                this.t64 = t64;
            }

            public String getT67() {
                return t67;
            }

            public void setT67(String t67) {
                this.t67 = t67;
            }

        }



        public Sky getSky() {
            return sky;
        }
        public Temperature getTemperature() {
            return temperature;
        }
        public Precipitation getPrecipitation() {
            return precipitation;
        }

    }

    public class  Fcstdaily{

        @SerializedName("temperature")
        TempMinMax temp;

        public class TempMinMax{
            @SerializedName("tmax2day")
            String tmax2day;
            @SerializedName("tmin2day")
            String tmin2day;

            public String getTmax2day() {
                return tmax2day;
            }
            public String getTmin2day() {
                return tmin2day;
            }
        }
        public TempMinMax getTempMinMax() {
            return temp;
        }
    }
    public Fcstdaily getFcstdaily() {
        return fcstdaily;
    }

    public Fcst3hour getFcst3hour() {
        return fcst3hour;
    }

}
