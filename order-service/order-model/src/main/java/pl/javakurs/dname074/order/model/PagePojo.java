package pl.javakurs.dname074.order.model;

import java.util.List;

public class PagePojo<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;
}
