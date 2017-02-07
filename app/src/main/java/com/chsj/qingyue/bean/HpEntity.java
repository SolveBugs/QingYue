package com.chsj.qingyue.bean;

import java.io.Serializable;


/**
 * Created by zhenqiang on 2017/1/22.
 */

public class HpEntity implements Serializable {


    /**
     * hpEntity : {"strLastUpdateDate":"2017-02-06 01:30:55","strDayDiffer":"","strHpId":"1612","strHpTitle":"VOL.1585","strThumbnailUrl":"http://image.wufazhuce.com/FpOpF7CrpuALN4eBk5ATtZSkgUPu","strOriginalImgUrl":"http://image.wufazhuce.com/FpOpF7CrpuALN4eBk5ATtZSkgUPu","strAuthor":"剧照","strContent":"有些人选择看到这个世界上的丑恶，\r\n那些无秩序的混乱。\r\n我选择看到美好。","strMarketTime":"2017-02-08","sWebLk":"http://m.wufazhuce.com/one/1612","strPn":"4234","wImgUrl":""}
     * result : SUCCESS
     */

    private HpEntityBean hpEntity;
    private String result;

    public HpEntityBean getHpEntity() {
        return hpEntity;
    }

    public void setHpEntity(HpEntityBean hpEntity) {
        this.hpEntity = hpEntity;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class HpEntityBean {
        /**
         * strLastUpdateDate : 2017-02-06 01:30:55
         * strDayDiffer :
         * strHpId : 1612
         * strHpTitle : VOL.1585
         * strThumbnailUrl : http://image.wufazhuce.com/FpOpF7CrpuALN4eBk5ATtZSkgUPu
         * strOriginalImgUrl : http://image.wufazhuce.com/FpOpF7CrpuALN4eBk5ATtZSkgUPu
         * strAuthor : 剧照
         * strContent : 有些人选择看到这个世界上的丑恶，
         * 那些无秩序的混乱。
         * 我选择看到美好。
         * strMarketTime : 2017-02-08
         * sWebLk : http://m.wufazhuce.com/one/1612
         * strPn : 4234
         * wImgUrl :
         */

        private String strLastUpdateDate;
        private String strDayDiffer;
        private String strHpId;
        private String strHpTitle;
        private String strThumbnailUrl;
        private String strOriginalImgUrl;
        private String strAuthor;
        private String strContent;
        private String strMarketTime;
        private String sWebLk;
        private String strPn;
        private String wImgUrl;

        public String getStrLastUpdateDate() {
            return strLastUpdateDate;
        }

        public void setStrLastUpdateDate(String strLastUpdateDate) {
            this.strLastUpdateDate = strLastUpdateDate;
        }

        public String getStrDayDiffer() {
            return strDayDiffer;
        }

        public void setStrDayDiffer(String strDayDiffer) {
            this.strDayDiffer = strDayDiffer;
        }

        public String getStrHpId() {
            return strHpId;
        }

        public void setStrHpId(String strHpId) {
            this.strHpId = strHpId;
        }

        public String getStrHpTitle() {
            return strHpTitle;
        }

        public void setStrHpTitle(String strHpTitle) {
            this.strHpTitle = strHpTitle;
        }

        public String getStrThumbnailUrl() {
            return strThumbnailUrl;
        }

        public void setStrThumbnailUrl(String strThumbnailUrl) {
            this.strThumbnailUrl = strThumbnailUrl;
        }

        public String getStrOriginalImgUrl() {
            return strOriginalImgUrl;
        }

        public void setStrOriginalImgUrl(String strOriginalImgUrl) {
            this.strOriginalImgUrl = strOriginalImgUrl;
        }

        public String getStrAuthor() {
            return strAuthor;
        }

        public void setStrAuthor(String strAuthor) {
            this.strAuthor = strAuthor;
        }

        public String getStrContent() {
            return strContent;
        }

        public void setStrContent(String strContent) {
            this.strContent = strContent;
        }

        public String getStrMarketTime() {
            return strMarketTime;
        }

        public void setStrMarketTime(String strMarketTime) {
            this.strMarketTime = strMarketTime;
        }

        public String getSWebLk() {
            return sWebLk;
        }

        public void setSWebLk(String sWebLk) {
            this.sWebLk = sWebLk;
        }

        public String getStrPn() {
            return strPn;
        }

        public void setStrPn(String strPn) {
            this.strPn = strPn;
        }

        public String getWImgUrl() {
            return wImgUrl;
        }

        public void setWImgUrl(String wImgUrl) {
            this.wImgUrl = wImgUrl;
        }

        @Override
        public String toString() {
            return "HpEntityBean{" +
                    "strLastUpdateDate='" + strLastUpdateDate + '\'' +
                    ", strDayDiffer='" + strDayDiffer + '\'' +
                    ", strHpId='" + strHpId + '\'' +
                    ", strHpTitle='" + strHpTitle + '\'' +
                    ", strThumbnailUrl='" + strThumbnailUrl + '\'' +
                    ", strOriginalImgUrl='" + strOriginalImgUrl + '\'' +
                    ", strAuthor='" + strAuthor + '\'' +
                    ", strContent='" + strContent + '\'' +
                    ", strMarketTime='" + strMarketTime + '\'' +
                    ", sWebLk='" + sWebLk + '\'' +
                    ", strPn='" + strPn + '\'' +
                    ", wImgUrl='" + wImgUrl + '\'' +
                    '}';
        }
    }
}
