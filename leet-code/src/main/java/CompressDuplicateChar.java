/**
 * 给定一组字符，使用原地算法将其压缩。
 * <p>
 * 压缩后的长度必须始终小于或等于原数组长度。
 * <p>
 * 数组的每个元素应该是长度为1的字符（不是 int 整数类型）。
 * <p>
 * 在完成原地修改输入数组后，返回数组的新长度。
 * <p>
 * 进阶：
 * 你能否仅使用O(1) 空间解决问题？
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["a","a","b","b","c","c","c"]
 * <p>
 * 输出：
 * 返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * <p>
 * 说明：
 * "aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-compression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dkd
 */
public class CompressDuplicateChar {

    public static void main(String[] args) {
        String v = "abbbbbbbbccsbzxcc";
        String compress = compress(v);
        System.out.println(compress);
    }

    public static int compress(char[] chars) {
        int cnt = 1;
        int pre = 0;
        int skip = 0;
        for (int current = 1; current < chars.length; pre++, current++) {
            while (current < chars.length && chars[skip] == chars[current]) {
                current++;
                cnt++;
            }

            if (cnt > 1) {
                skip += cnt;
                for (char c : (cnt + "").toCharArray()) {
                    chars[++pre] = c;
                }
                cnt = 1;
            } else {
                chars[++pre] = chars[skip];
            }

        }
        return pre;
    }

    public static String compress(String v) {
        String pre = null;
        String current = null;
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < v.length(); i++) {
            current = v.substring(i, i + 1);
            if (current.equals(pre)) {
                cnt++;
                continue;
            }
            if (pre != null) {
                sb.append(pre);
            }
            if (cnt > 1) {
                sb.append(cnt);
                cnt = 1;
            }
            pre = current;
        }
        if (current != null) {
            sb.append(current);
        }
        if (cnt > 1) {
            sb.append(cnt);
        }
        return sb.toString();
    }
}
