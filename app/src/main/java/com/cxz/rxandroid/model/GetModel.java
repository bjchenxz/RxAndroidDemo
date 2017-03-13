package com.cxz.rxandroid.model;

import java.util.List;

/**
 * Get测试
 * Created by chenxz on 2017/3/13.
 */
public class GetModel {

    private String date;
    private List<Stories> stories;
    private List<TopStories> topStories;
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }
    public List<Stories> getStories() {
        return stories;
    }

    public void setTopStories(List<TopStories> topStories) {
        this.topStories = topStories;
    }
    public List<TopStories> getTopStories() {
        return topStories;
    }

    @Override
    public String toString() {
        return "GetModel{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", topStories=" + topStories +
                '}';
    }

    class TopStories {
        private String image;
        private int type;
        private int id;
        private String gaPrefix;
        private String title;
        public void setImage(String image) {
            this.image = image;
        }
        public String getImage() {
            return image;
        }

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }
        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "TopStories{" +
                    "image='" + image + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", gaPrefix='" + gaPrefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    class Stories {
        private List<String> images;
        private int type;
        private int id;
        private String gaPrefix;
        private String title;
        public void setImages(List<String> images) {
            this.images = images;
        }
        public List<String> getImages() {
            return images;
        }

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }
        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Stories{" +
                    "images=" + images +
                    ", type=" + type +
                    ", id=" + id +
                    ", gaPrefix='" + gaPrefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

}
