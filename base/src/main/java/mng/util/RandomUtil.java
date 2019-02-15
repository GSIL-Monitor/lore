package mng.util;

import java.util.Random;

/**
 * @author oac
 */
public class RandomUtil {
    /**
     * 随机数（0-某数或某数-某数之间，不包括后面的某数）不传值为0-9
     * @param nums 数字范围
     * @return 随机数
     */
    public static int getRandom(int ...nums){
        Random r = new Random();
        int random = 0;
        switch (nums.length){
            case 0:
                random = r.nextInt(10);
                break;
            case 1:
                random = r.nextInt(nums[0]);
                break;
            case 2:
                random = r.nextInt(nums[1] - nums[0]) + nums[0];
            default:
                break;
        }
        return random;
    }
    public static void main(String[] args) {
        for(int i=0; i<20; i++){
            System.out.println(getRandom(1));
        }
    }
}
