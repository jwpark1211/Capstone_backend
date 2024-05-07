package capstone.bookitty.global.api.dto;

import java.util.List;

public class AladinBookListResponseDTO {

    public List<DetailBook> item;

    public static class DetailBook {
        public String title;
        public String link;
        public String author;
        public String pubDate;
        public String description;
        public String isbn13;
        public int priceStandard;
        public String cover;
        public String publisher;
        public String categoryName;
        public String mallType;

        public Info subInfo;
        public static class Info {
            public String subTitle;
            public String originalTitle;
            public int itemPage;

        }
    }
}
