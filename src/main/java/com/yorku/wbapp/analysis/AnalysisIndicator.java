package com.yorku.wbapp.analysis;

public class AnalysisIndicator {
    String indicatorId;
    String indicatorValue;
    String indicatorType;

    public AnalysisIndicator(String indicatorId, String indicatorValue, String indicatorType) {
        this.indicatorId = indicatorId;
        this.indicatorValue = indicatorValue;
        this.indicatorType = indicatorType;
    }

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorValue() {
        return indicatorValue;
    }

    public void setIndicatorValue(String indicatorValue) {
        this.indicatorValue = indicatorValue;
    }

    public String getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
    }

    @Override
    public String toString() {
        return "AnalysisIndicator{" +
                "indicatorId='" + indicatorId + '\'' +
                ", indicatorValue='" + indicatorValue + '\'' +
                ", indicatorType='" + indicatorType + '\'' +
                '}';
    }
}
