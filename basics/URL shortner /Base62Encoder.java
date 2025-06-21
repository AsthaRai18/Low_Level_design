public class Base62Encoder {
    // Character set for Base62 (0-9, a-z, A-Z)
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int remainder = (int) (num % 62);
            sb.append(BASE62.charAt(remainder));
            num /= 62;
        }
        // Reverse the string because the least significant digit comes first
        return sb.reverse().toString();
    }
}
