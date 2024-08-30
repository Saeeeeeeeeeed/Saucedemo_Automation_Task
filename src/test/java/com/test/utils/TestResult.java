package com.test.utils;

import java.time.Duration;

public class TestResult {


    private String name;
    private String environment;
    private String page;
    private String url;
    private String status;
    private String duration;


    public TestResult(String name, String status, String duration) {
        this.name = name;
        this.status = status;
        this.duration = duration;
    }

    public TestResult(String name, String status, String duration, String description) {
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.duration = description;
    }

    public TestResult() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return convertToReadableFormat(name);
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        String system = "";
        String url = getURL();
        if (url.contains("delta")) {
            system = "Delta";
        } else if (url.contains("omega")) {
            system = "Omega";
        }
        return system;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        String page = "";
        String url = getURL();
        if (url.contains("SSSSSS")) {
            page = "All";
        }
        return page;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }



    //convert @Test method to readable format
    public static String convertToReadableFormat(String name) {
        String[] words = name.split("(?=\\p{Upper})");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word.toLowerCase()).append(" ");
        }
        return sb.toString().trim().replaceAll("\\s+", " ");
    }

    public String summaryFormatDuration(Duration duration) {
        long totalSeconds = duration.getSeconds();
        StringBuilder sb = new StringBuilder();
        if (totalSeconds < 60) {
            sb.append(totalSeconds).append("sec");
        } else if (totalSeconds < 3600) {
            long totalMinutes = totalSeconds / 60;
            long seconds = totalSeconds % 60;
            sb.append(totalMinutes).append("min ");
            if (seconds != 0) {
                sb.append(seconds).append("sec");
            }
        } else {
            long totalMinutes = duration.toMinutes();
            long hours = totalMinutes / 60;
            long minutes = totalMinutes % 60;
            sb.append(hours).append("hr ");
            if (minutes != 0) {
                sb.append(minutes).append("min");
            }
        }
        return sb.toString();
    }

    public String detailsFormatDuration(Duration duration) {
        long totalSeconds = duration.getSeconds();
        long absSeconds = Math.abs(totalSeconds);
        StringBuilder sb = new StringBuilder();
        if (totalSeconds < 0) {
            sb.append("-");
        }
        if (absSeconds < 60) {
            sb.append(absSeconds).append(" sec");
        } else {
            long minutes = absSeconds / 60;
            long seconds = absSeconds % 60;
            sb.append(minutes).append(" min ");
            if (seconds != 0) {
                sb.append(seconds).append(" sec");
            }
        }
        return sb.toString();
    }






}
