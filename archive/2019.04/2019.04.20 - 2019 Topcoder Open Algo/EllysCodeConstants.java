package task;

public class EllysCodeConstants {
    public String getLiteral(String candidate) {
        String[] suffixes = new String[]{"LLU", "ULL", "LL", "UL", "LU", "L", "U"};

        StringBuilder sb = new StringBuilder(candidate);
        String suff = "";
        for (String suffix : suffixes) {
            if (candidate.endsWith(suffix)) {
                for (int i = 0; i < suffix.length(); i++) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                suff = suffix;
                break;
            }
        }
        if (sb.length() == 0) {
            return "";
        }

        for (int i = 0; i < sb.length(); i++) {
            switch (sb.charAt(i)) {
                case 'O':
                    sb.setCharAt(i, '0');
                    break;
                case 'I':
                    sb.setCharAt(i, '1');
                    break;
                case 'Z':
                    sb.setCharAt(i, '2');
                    break;
                case 'S':
                    sb.setCharAt(i, '5');
                    break;
                case 'T':
                    sb.setCharAt(i, '7');
                    break;
                default:
                    if (sb.charAt(i) > 'F') {
                        return "";
                    }
            }
        }
        StringBuilder sb1 = new StringBuilder("0x");
        sb1.append(sb);
        sb1.append(suff);

        return sb1.toString();
    }
}
