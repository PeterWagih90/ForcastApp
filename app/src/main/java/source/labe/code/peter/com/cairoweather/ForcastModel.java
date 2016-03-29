package source.labe.code.peter.com.cairoweather;

/**
 * Created by Peter on 3/29/2016.
 */
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForcastModel {

    public class ForecastDay {
        String period, icon, icon_url, nameOfTheDay, fcttext, fcttext_metric, pop;

        public ForecastDay(JSONObject rawData) {
            try {

                this.period = rawData.getString("period");
                this.icon = rawData.getString("icon");
                this.icon_url = rawData.getString("icon_url");
                this.nameOfTheDay = rawData.getString("title");
                this.fcttext = rawData.getString("fcttext");
                this.fcttext_metric = rawData.getString("fcttext_metric");
                this.pop = rawData.getString("pop");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public String getNameOfTheDay() {
            return nameOfTheDay;
        }

        public void setNameOfTheDay(String nameOfTheDay) {
            this.nameOfTheDay = nameOfTheDay;
        }

        public String getFcttext() {
            return fcttext;
        }

        public void setFcttext(String fcttext) {
            this.fcttext = fcttext;
        }

        public String getFcttext_metric() {
            return fcttext_metric;
        }

        public void setFcttext_metric(String fcttext_metric) {
            this.fcttext_metric = fcttext_metric;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

    }

    public class PeriodSimpleForcast {

        public String getFullDate() {
            return fullDate;
        }

        public void setFullDate(String fullDate) {
            this.fullDate = fullDate;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonthname_short() {
            return monthname_short;
        }

        public void setMonthname_short(String monthname_short) {
            this.monthname_short = monthname_short;
        }

        public String getWeekday_short() {
            return weekday_short;
        }

        public void setWeekday_short(String weekday_short) {
            this.weekday_short = weekday_short;
        }

        public String getTz_long() {
            return tz_long;
        }

        public void setTz_long(String tz_long) {
            this.tz_long = tz_long;
        }

        public String getPeriodNum() {
            return periodNum;
        }

        public void setPeriodNum(String periodNum) {
            this.periodNum = periodNum;
        }

        public String getFahrenheit_high() {
            return fahrenheit_high;
        }

        public void setFahrenheit_high(String fahrenheit_high) {
            this.fahrenheit_high = fahrenheit_high;
        }

        public String getCelsius_high() {
            return celsius_high;
        }

        public void setCelsius_high(String celsius_high) {
            this.celsius_high = celsius_high;
        }

        public String getFahrenheit_low() {
            return fahrenheit_low;
        }

        public void setFahrenheit_low(String fahrenheit_low) {
            this.fahrenheit_low = fahrenheit_low;
        }

        public String getCelsius_low() {
            return celsius_low;
        }

        public void setCelsius_low(String celsius_low) {
            this.celsius_low = celsius_low;
        }

        public String getConditions() {
            return conditions;
        }

        public void setConditions(String conditions) {
            this.conditions = conditions;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getIn_qpf_allday() {
            return in_qpf_allday;
        }

        public void setIn_qpf_allday(String in_qpf_allday) {
            this.in_qpf_allday = in_qpf_allday;
        }

        public String getMm_qpf_allday() {
            return mm_qpf_allday;
        }

        public void setMm_qpf_allday(String mm_qpf_allday) {
            this.mm_qpf_allday = mm_qpf_allday;
        }

        public String getIn_qpf_night() {
            return in_qpf_night;
        }

        public void setIn_qpf_night(String in_qpf_night) {
            this.in_qpf_night = in_qpf_night;
        }

        public String getMm_qpf_night() {
            return mm_qpf_night;
        }

        public void setMm_qpf_night(String mm_qpf_night) {
            this.mm_qpf_night = mm_qpf_night;
        }

        public String getIn_snow_allday() {
            return in_snow_allday;
        }

        public void setIn_snow_allday(String in_snow_allday) {
            this.in_snow_allday = in_snow_allday;
        }

        public String getMm_snow_allday() {
            return mm_snow_allday;
        }

        public void setMm_snow_allday(String mm_snow_allday) {
            this.mm_snow_allday = mm_snow_allday;
        }

        public String getIn_snow_day() {
            return in_snow_day;
        }

        public void setIn_snow_day(String in_snow_day) {
            this.in_snow_day = in_snow_day;
        }

        public String getMm_snow_day() {
            return mm_snow_day;
        }

        public void setMm_snow_day(String mm_snow_day) {
            this.mm_snow_day = mm_snow_day;
        }

        public String getIn_snow_night() {
            return in_snow_night;
        }

        public void setIn_snow_night(String in_snow_night) {
            this.in_snow_night = in_snow_night;
        }

        public String getMm_snow_night() {
            return mm_snow_night;
        }

        public void setMm_snow_night(String mm_snow_night) {
            this.mm_snow_night = mm_snow_night;
        }

        public String getMaxwind_mph() {
            return maxwind_mph;
        }

        public void setMaxwind_mph(String maxwind_mph) {
            this.maxwind_mph = maxwind_mph;
        }

        public String getMaxwind_kph() {
            return maxwind_kph;
        }

        public void setMaxwind_kph(String maxwind_kph) {
            this.maxwind_kph = maxwind_kph;
        }

        public String getMaxwind_dir() {
            return maxwind_dir;
        }

        public void setMaxwind_dir(String maxwind_dir) {
            this.maxwind_dir = maxwind_dir;
        }

        public String getMaxwind_degree() {
            return maxwind_degree;
        }

        public void setMaxwind_degree(String maxwind_degree) {
            this.maxwind_degree = maxwind_degree;
        }

        public String getAvewind_mph() {
            return avewind_mph;
        }

        public void setAvewind_mph(String avewind_mph) {
            this.avewind_mph = avewind_mph;
        }

        public String getAvewind_kph() {
            return avewind_kph;
        }

        public void setAvewind_kph(String avewind_kph) {
            this.avewind_kph = avewind_kph;
        }

        public String getAvewind_dir() {
            return avewind_dir;
        }

        public void setAvewind_dir(String avewind_dir) {
            this.avewind_dir = avewind_dir;
        }

        public String getAvewind_degree() {
            return avewind_degree;
        }

        public void setAvewind_degree(String avewind_degree) {
            this.avewind_degree = avewind_degree;
        }

        public String getAvehumidity() {
            return avehumidity;
        }

        public void setAvehumidity(String avehumidity) {
            this.avehumidity = avehumidity;
        }

        public String getMaxhumidity() {
            return maxhumidity;
        }

        public void setMaxhumidity(String maxhumidity) {
            this.maxhumidity = maxhumidity;
        }

        public String getMinhumidity() {
            return minhumidity;
        }

        public void setMinhumidity(String minhumidity) {
            this.minhumidity = minhumidity;
        }

        public String fullDate;
        public String day;
        public String month;
        public String year;
        public String monthname_short;
        public String weekday_short;
        public String tz_long;
        public String periodNum;
        public String fahrenheit_high;
        public String celsius_high;
        public String fahrenheit_low;
        public String celsius_low;
        public String conditions;
        public String icon;
        public String icon_url;
        public String pop;
        public String in_qpf_allday;
        public String mm_qpf_allday;
        public String in_qpf_night;
        public String mm_qpf_night;
        public String in_snow_allday;
        public String mm_snow_allday;
        public String in_snow_day;
        public String mm_snow_day;
        public String in_snow_night;
        public String mm_snow_night;
        public String maxwind_mph;
        public String maxwind_kph;
        public String maxwind_dir;
        public String maxwind_degree;
        public String avewind_mph;
        public String avewind_kph;
        public String avewind_dir;
        public String avewind_degree;
        public String avehumidity;
        public String maxhumidity;
        public String minhumidity;
        public String in_qpf_day;
        public String mm_qpf_day;

    }

    public class SimpleForCastModel {

        ArrayList<PeriodSimpleForcast> allPeriods;

        public ArrayList<PeriodSimpleForcast> getAllPeriods() {
            return allPeriods;
        }

        public void setAllPeriods(ArrayList<PeriodSimpleForcast> allPeriods) {
            this.allPeriods = allPeriods;
        }

        public SimpleForCastModel() {
            this.allPeriods = new ArrayList<ForcastModel.PeriodSimpleForcast>();
        }

        // "date":{
        // "epoch":"1459098000",
        // "pretty":"7:00 PM EET on March 27, 2016",
        // "day":27,
        // "month":3,
        // "year":2016,
        // "yday":86,
        // "hour":19,
        // "min":"00",
        // "sec":0,
        // "isdst":"0",
        // "monthname":"March",
        // "monthname_short":"Mar",
        // "weekday_short":"Sun",
        // "weekday":"Sunday",
        // "ampm":"PM",
        // "tz_short":"EET",
        // "tz_long":"Africa/Cairo"
        // },
        // "period":1,
        // "high": {
        // "fahrenheit":"71",
        // "celsius":"21"
        // },
        // "low": {
        // "fahrenheit":"57",
        // "celsius":"14"
        // },
        // "conditions":"Clear",
        // "icon":"clear",
        // "icon_url":"http://icons.wxug.com/i/c/k/clear.gif",
        // "skyicon":"",
        // "pop":0,
        // "qpf_allday": {
        // "in": 0.00,
        // "mm": 0
        // },
        // "qpf_day": {
        // "in": null,
        // "mm": null
        // },
        // "qpf_night": {
        // "in": 0.00,
        // "mm": 0
        // },
        // "snow_allday": {
        // "in": 0.0,
        // "cm": 0.0
        // },
        // "snow_day": {
        // "in": null,
        // "cm": null
        // },
        // "snow_night": {
        // "in": 0.0,
        // "cm": 0.0
        // },
        // "maxwind": {
        // "mph": 16,
        // "kph": 26,
        // "dir": "West",
        // "degrees": 0
        // },
        // "avewind": {
        // "mph": 8,
        // "kph": 14,
        // "dir": "NNW",
        // "degrees": 0
        // },
        // "avehumidity": 65,
        // "maxhumidity": 0,
        // "minhumidity": 0
        // }

        public SimpleForCastModel(JSONObject rawData) {
            try {
                this.allPeriods = new ArrayList<ForcastModel.PeriodSimpleForcast>();
                JSONArray allPeriods = rawData.getJSONArray("forecastday");
                for (int i = 0; i < allPeriods.length(); i++) {
                    JSONObject period = allPeriods.getJSONObject(i);
                    JSONObject date = period.getJSONObject("date");
                    PeriodSimpleForcast p = new PeriodSimpleForcast();
                    p.fullDate = date.getString("pretty");
                    p.day = date.getString("day");
                    p.month = date.getString("month");
                    p.year = date.getString("year");
                    p.monthname_short = date.getString("monthname_short");
                    p.weekday_short = date.getString("weekday_short");
                    p.tz_long = date.getString("tz_long");
                    p.periodNum = period.getString("period");
                    JSONObject high = period.getJSONObject("high");
                    p.fahrenheit_high = high.getString("fahrenheit");
                    p.celsius_high = high.getString("celsius");

                    JSONObject low = period.getJSONObject("low");

                    p.fahrenheit_low = low.getString("fahrenheit");
                    p.celsius_low = low.getString("celsius");

                    p.conditions = period.getString("conditions");
                    p.icon = period.getString("icon");
                    p.icon_url = period.getString("icon_url");
                    p.pop = period.getString("pop");

                    JSONObject qpf_allday = period.getJSONObject("qpf_allday");

                    p.in_qpf_allday = qpf_allday.getString("in");
                    p.mm_qpf_allday = qpf_allday.getString("mm");

                    JSONObject qpf_day = period.getJSONObject("qpf_day");
                    p.in_qpf_day = qpf_day.getString("in");
                    p.mm_qpf_day = qpf_day.getString("mm");

                    JSONObject qpf_night = period.getJSONObject("qpf_night");
                    p.in_qpf_night = qpf_night.getString("in");
                    p.mm_qpf_night = qpf_night.getString("mm");

                    JSONObject snow_allday = period.getJSONObject("snow_allday");

                    p.in_snow_allday = snow_allday.getString("in");
                    p.mm_snow_allday = snow_allday.getString("cm");

                    JSONObject snow_day = period.getJSONObject("snow_day");

                    p.in_snow_day = snow_day.getString("in");
                    p.mm_snow_day = snow_day.getString("cm");

                    JSONObject snow_night = period.getJSONObject("snow_night");

                    p.in_snow_night = snow_night.getString("in");
                    p.mm_snow_night = snow_night.getString("cm");

                    JSONObject maxwind = period.getJSONObject("maxwind");

                    p.maxwind_mph = maxwind.getString("mph");
                    p.maxwind_kph = maxwind.getString("kph");
                    p.maxwind_dir = maxwind.getString("dir");
                    p.maxwind_degree = maxwind.getString("degrees");

                    JSONObject avewind = period.getJSONObject("avewind");

                    p.avewind_mph = avewind.getString("mph");
                    p.avewind_kph = avewind.getString("kph");
                    p.avewind_dir = avewind.getString("dir");
                    p.avewind_degree = avewind.getString("degrees");

                    p.avehumidity = period.getString("avehumidity");
                    p.maxhumidity = period.getString("maxhumidity");
                    p.minhumidity = period.getString("minhumidity");
                    this.allPeriods.add(p);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<ForecastDay> getAllForcastDay() {
        return allForcastDay;
    }

    public void setAllForcastDay(ArrayList<ForecastDay> allForcastDay) {
        this.allForcastDay = allForcastDay;
    }

    private String time;
    private ArrayList<ForecastDay> allForcastDay;
    private SimpleForCastModel simpleForCastList;

    public SimpleForCastModel getSimpleForCastList() {
        return simpleForCastList;
    }

    public void setSimpleForCastList(SimpleForCastModel simpleForCastList) {
        this.simpleForCastList = simpleForCastList;
    }

    public void prepareData(String jsonResponse) {

        try {
            JSONObject Alldata = new JSONObject(jsonResponse);
            if (Alldata.has("response")) {
                JSONObject forcastObject = Alldata.getJSONObject("forecast");
                JSONObject txt_forecast = forcastObject.getJSONObject("txt_forecast");
                JSONObject simpleforecast = forcastObject.getJSONObject("simpleforecast");

                // txt_forecast

                time = txt_forecast.getString("date");
                JSONArray forecastday = txt_forecast.getJSONArray("forecastday");
                allForcastDay = new ArrayList<ForecastDay>();
                for (int i = 0; i < forecastday.length(); i++) {
                    JSONObject tempObject = forecastday.getJSONObject(i);
                    ForecastDay f = new ForecastDay(tempObject);
                    allForcastDay.add(f);
                }

                // simpleforecast

                this.simpleForCastList = new SimpleForCastModel(simpleforecast);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
