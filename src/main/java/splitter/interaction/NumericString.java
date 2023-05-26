package splitter.interaction;

class NumericString {
    static boolean isNumeric(String string) {
        String[] parts = string.split("\\.", 2);
        for (String part: parts) {
            if (part.equals("")) {
                return false;
            }

            boolean isNotNumeric = part.chars().anyMatch(c -> !Character.isDigit(c));
            if (isNotNumeric) {
                return false;
            }
        }

        return true;
    }
}