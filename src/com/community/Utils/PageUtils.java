package com.community.Utils;

/**
 * @Author: jack
 * @Create: 2018-09-04-9:41
 * @Desc: 实现分页的工具类
 **/
public class PageUtils {
    /**当前页*/
    private Integer CurPage;
    /**每个页面显示多少条记录*/
    private Integer count;
    /**从第几条记录开始查询*/
    private Integer start;
    /**一共有多少条记录*/
    private Integer totalCounts;
    /**一共有多少页*/
    private Integer totalNum;
    /**每次显示的按钮个数*/
    private Integer size = 5;
    /**第一个按钮显示的数字*/
    private Integer begin = 1;
    /**第五个按钮显示的数字*/
    private Integer end;
    /**上一页*/
    private Integer previews;
    /**下一页*/
    private Integer next;

    /**构造函数*/
    public PageUtils(){

    }

    /**
     * count:每页显示的记录数
     * size:显示的按钮个数
     * */
    public PageUtils(int count,int size){
        this.count = count;
        this.size = size;
    }


    public Integer getCurPage() {
        return CurPage;
    }

    /**
     * 设置鼠标点击的当前页面为第几页*/
    public void setCurPage(String curPage) {
        /**如果鼠标点击的不为空并且点击的是数字*/
        if(curPage != null && curPage.trim().matches("\\d")){
            Integer currentPage = Integer.parseInt(curPage);
            this.CurPage = currentPage;
//            if(currentPage>0 && currentPage<=totalNum){
//
//            }
        }


    }

    /**传入每个页面显示的记录数和显示的按钮个数*/
    public PageUtils(Integer count,Integer size){
        this.count = count;
        if(size%2 == 0){
            size++;
        }
        this.size = size;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**从第几条记录开始查询
     * start = (CurPage)*count
     * start=鼠标点击的页数*每页显示的记录数*/
    public Integer getStart() {
        return (CurPage-1)*count;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer allCounts) {
        totalCounts = allCounts;
    }

    /**获取总页数*/
    public Integer getTotalNum() {

        return totalCounts%count == 0?totalCounts/count : totalCounts/count+1;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**获取第一个按钮显示的数字*/
    public Integer getBegin() {
        /**获取总页数*/
        totalNum = getTotalNum();
        /**总页数小于显示的按钮个数*/
        if(totalNum<size){
            begin = 1;
        /**鼠标点击的页数在左侧，begin为1；
         * 鼠标点击的页数在右侧，begin改变
         * 例如:假设size为5，总页数为10
         * 当鼠标点击1、2、3时，页面显示的是1,2,3,4,5
         * 当鼠标点击4时，页面显示的是2,3,4,5,6
         * 依次类推*/

        }else if(CurPage <= size/2+1){
            begin = 1;
        }else{
            /**假设size为5，总页数为10
             * 当鼠标点击9、10时，页面显示的是总是6,7,8,9,10
             * 也就是说begin的值一直不变
             * 同理end也是不变的
             */
            if(CurPage>=totalNum-size/2+1){
                begin = totalNum - size+1;
                return begin;
            }
            begin = CurPage - size/2;
        }
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    /**获取第五个按钮显示的数字*/
    public Integer getEnd() {
        /**获取总页数*/
        totalNum = getTotalNum();
        /***
         * 总页数小于显示的按钮个数
         */
        if(totalNum<size){
            end = getTotalNum();
        }else if(CurPage <= size/2 + 1){
            end = size;
        }else{
            if(CurPage>=totalNum-size/2+1){
                end = totalNum;
                return end;
            }
            end = CurPage + size/2;
        }
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getPreviews() {
        if(CurPage<=1){
            CurPage = 1;
            return 1;
        }
        return CurPage-1;
    }

    public void setPreviews(Integer previews) {
        this.previews = previews;
    }

    public Integer getNext() {
        if(CurPage>=totalNum){
            CurPage = totalNum;
            return totalNum;
        }
        return CurPage+1;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "CurPage=" + CurPage +
                ", count=" + count +
                ", start=" + start +
                ", totalCounts=" + totalCounts +
                ", totalNum=" + totalNum +
                ", size=" + size +
                ", begin=" + begin +
                ", end=" + end +
                ", previews=" + previews +
                ", next=" + next +
                '}';
    }
}
