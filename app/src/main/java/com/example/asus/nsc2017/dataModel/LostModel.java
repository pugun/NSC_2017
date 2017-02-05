package com.example.asus.nsc2017.dataModel;

/**
 * Created by Dell on 5/2/2560.
 */

public class LostModel {

    /**
     * detail : ครั้งหนึ่งยังมีจอมยุทธ์ ออกเดินทางไปสุดฟ้า หวังเพื่อที่จะตามหา ยอดวิชาที่หายไป  จะเอาไปแก้แค้น ให้กับอาจารย์เขา จะต้องเป็นจ้าวยุทธ์ แล้วเขาต้องยิ่งใหญ่ บังเอิญเกิดตกเขา บังเอิญมีกิ่งไม้ บังเอิญจึงรอดตาย บังเอิญคัมภีร์อยู่ที่นั่น
     * lostDate : 2016-11-12
     * lostTime : 11:12
     */

    private String detail;
    private String lostDate;
    private String lostTime;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLostDate() {
        return lostDate;
    }

    public void setLostDate(String lostDate) {
        this.lostDate = lostDate;
    }

    public String getLostTime() {
        return lostTime;
    }

    public void setLostTime(String lostTime) {
        this.lostTime = lostTime;
    }
}
