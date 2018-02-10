package com.example.isthisangela.miniproj1;

/**
 * Created by isthisangela on 2/9/18.
 */

public class Answer {
    static final Integer[] pics = Members.pics;
    static final String[] names = Members.names;
    static final int total = 54;
    int n, pic;
    String name, wrong1, wrong2, wrong3;
    public Answer() {
        n = (int) (Math.random() * total);
        pic = pics[n];
        name = names[n];
        wrong1 = names[this.fixIndex(n + 20)];
        wrong2 = names[this.fixIndex(n - 15)];
        wrong3 = names[this.fixIndex(n + 4)];
    }
    private int fixIndex(int ind) {
        int i = ind;
        while (i <= -1) {
            i += total;
        }
        while (i >= total) {
            i -= total;
        }
        return i;
    }
    public int getPic() {
        return pic;
    }
    public String getName() {
        return name;
    }
    public String getWrong1() { return wrong1; }
    public String getWrong2() { return wrong2; }
    public String getWrong3() { return wrong3; }
}
