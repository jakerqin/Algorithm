package test;

public class Main {
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        int[] hash = new int[256];
        StringBuffer buffer = new StringBuffer("");

        for (int i = 0; i < s.length(); ++i) {
            hash[t.charAt(i)]++;
        }

        int l = 0, count = t.length(), max = s.length() + 1;
        String result = "";
        for (int r = 0; r < s.length(); r++) {
            hash[s.charAt(r)]--;

            if (hash[s.charAt(r)] >=0) {
                count--;
            }

            while (l < r && hash[s.charAt(l)]<0){
                hash[s.charAt(l)] ++;
                l++;
            }

            if(count ==0 && max > r-l+1){
                max = r-l +1;
                result = s.substring(l,r+1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String str = "ADOBECODEBANC";
        char[] chars = str.toCharArray();
        System.out.println(chars[3]);
        System.out.println(str.charAt(3));
        //System.out.println(minWindow("ADOBECODEBANC","ABC"));

    }
}





/*
for (int r = 0; r < sArr.length; ++r) {
        hash[sArr[r]]--;

        if (hash[sArr[r]] >= 0) {
        count--;
        }

        while (l < r && hash[sArr[l]] < 0) {
        hash[sArr[l]]++;
        l++;
        }

        if (count == 0 && max > r - l + 1) {
        max = r - l + 1;
        result = s.substring(l, r + 1);
        }
 }*/
