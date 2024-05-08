package capstone.bookitty.global.api.dto;

import java.util.List;

public class AladinBookSearchResponseDTO {

    public List<DetailBook> item;

    public static class DetailBook{
        public String title;
        public String link;
        public String author;
        public String pubDate;
        public String description;
        public String isbn13;
        public int priceStandard;
        public String cover;
        public String publisher;
        public String mallType;
        public String categoryName;
        public static class Info {
            public String subTitle;
            public String originalTitle;
            public int itemPage;

        }
    }
}
