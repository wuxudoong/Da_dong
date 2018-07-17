package com.example.vedio.activity;

/**
 * Created by dadong on 2018/6/25.
 * Describe:
 */
public class houseActivity {

    public houseActivity() {
    }

    /**
     * 18届程序员小弟回嘉发展
     * 求合租室友
     * 可以一起去找房子
     * 黑不了qq，微信
     * 修不了电脑，手机
     * 性格佳
     * 爱干净
     * 会做中餐
     *
     * @param rent      个人租金
     * @param character 室友性格
     * @return true: 我非常期待和您合租, false: sorry，我不希望和您合租
     */
    private boolean findRoomMate(int rent, String character) {
        if (rent > 800) {
            return false;
        }
        if (!"爱干净".equals(character)) {
            return false;
        }
        if (!"愿意为他人考虑".equals(character)) {
            return false;
        }
        return true;
    }
}
